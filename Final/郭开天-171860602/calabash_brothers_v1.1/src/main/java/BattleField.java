import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.concurrent.Callable;

//import java.util.concurrent.Callable;

public class BattleField{
    Creature[]creaturesG0;
    Creature[]creaturesG1;
    int g0death;
    int g1death;
    Creature[][]space;
    FieldAwaker awaker;
    int xmax;
    int ymax;
    int numwait;
    boolean timeout;
    boolean end;
    int round;

    //log相关
    public LogWrite write;
    public LogRead read;
    //boolean writing;
    //boolean reading;

    public int getXmax(){
        return xmax;
    }
    public int getYmax(){
        return ymax;
    }

    //    **
    //   ***
    //    **
    //    **
    //   ****

    public BattleField(String str){
        awaker=new FieldAwaker(this);
        write=new LogWrite();
        read=new LogRead();
        read.readInit("test");
        playReady();
        while(!combatEnd()){
            playTurn();
        }
    }
    public BattleField(){
        xmax=7;
        ymax=7;
        space=new Creature[xmax][ymax];
        awaker=new FieldAwaker(this);
        write=new LogWrite();
        read=new LogRead();
        write.writeInit("test");
    }
    public BattleField(int x,int y){
        xmax=x;
        ymax=y;
        space=new Creature[xmax][ymax];
        awaker=new FieldAwaker(this);
        write=new LogWrite();
        read=new LogRead();
        write.writeInit("test");
    }

    public void fieldInit(Formation form0,Formation form1,Creature[]g0,Creature[]g1){
        try{
            if(xmax<form0.getMinx()||xmax<form1.getMinx()||ymax<form0.getMiny()||ymax<form1.getMiny())
                throw new Exception("BattleField error:Field is inconsistent with fomation");
            int n0=form0.getlenth();
            int n1=form1.getlenth();
            if(n0!=g0.length||n1!=g1.length)
                throw new Exception("BattleField error:creature group is inconsistent with fomaion");
            creaturesG0=new Creature[n0];
            creaturesG1=new Creature[n1];
            for(int i=0;i<n0;i++){
                creaturesG0[i]=new Creature(g0[i],false,this,form0.getX(i),form0.getY(i));
                space[form0.getX(i)][form0.getY(i)]=creaturesG0[i];
            }
            for(int i=0;i<n1;i++){
                creaturesG1[i]=new Creature(g1[i],true,this,xmax-1-form1.getX(i),form1.getY(i));
                space[xmax-1-form1.getX(i)][form1.getY(i)]=creaturesG1[i];
            }
        }catch(Exception e){
            System.err.println(e);
        }
        if(write.writing)logReady();
    }

    //     ****
    //    **  **
    //       **
    //      **
    //    ******

    public void logReady(){
        write.intWrite(xmax);
        write.intWrite(ymax);
        write.intWrite(creaturesG0.length);
        write.intWrite(creaturesG1.length);
        for(int i=0;i<creaturesG0.length;i++){
            //write.intWrite(i);
            write.stringWrite(creaturesG0[i].name);
            write.intWrite(creaturesG0[i].getX());
            write.intWrite(creaturesG0[i].getY());
        }
        for(int i=0;i<creaturesG1.length;i++){
            //write.intWrite(i);
            write.stringWrite(creaturesG1[i].name);
            write.intWrite(creaturesG1[i].getX());
            write.intWrite(creaturesG1[i].getY());
        }
    }
    public void logMove(Creature target,boolean aom,int targetid,int x,int y){
        synchronized(this){
            write.boolWrite(target.group);
            write.intWrite(target.id);
            //write.boolWrite(target.alive);
            if(target.alive){
                write.boolWrite(aom);
                if(aom){
                    write.intWrite(targetid);
                }
                else{
                    write.intWrite(x);
                    write.intWrite(y);
                }
            }
        }
    }
    public void logTurn(){
        synchronized(this){
            write.boolWrite(false);
            write.intWrite(-1);
        }
    }
    public void playReady(){
        xmax=read.intRead();
        ymax=read.intRead();
        space=new Creature[xmax][ymax];
        int g0length=read.intRead();
        int g1length=read.intRead();
        creaturesG0=new Creature[g0length];
        creaturesG1=new Creature[g1length];
        for(int i=0;i<g0length;i++){
            int id=i;
            String name=read.stringRead();
            int x=read.intRead();
            int y=read.intRead();
            creaturesG0[i]=new Creature(id, name, false, this, x, y);
            space[x][y]=creaturesG0[i];
        }
        for(int i=0;i<g1length;i++){
            int id=i;
            String name=read.stringRead();
            int x=read.intRead();
            int y=read.intRead();
            creaturesG1[i]=new Creature(id, name, true, this, x, y);
            space[x][y]=creaturesG1[i];
        }
        printField();
        try{
            for(int i=0;i<creaturesG0.length;i++){
                if(creaturesG0[i]!=space[creaturesG0[i].getX()][creaturesG0[i].getY()])
                    throw new Exception("reply error:group don't agree with space");
                if(creaturesG1[i]!=space[creaturesG1[i].getX()][creaturesG1[i].getY()])
                    throw new Exception("reply error:group don't agree with space");
            }
        }catch(Exception e){
                System.err.println(e);
        }
    }
    public void playTurn(){
        while(true){
            boolean group=read.boolRead();
            int id=read.intRead();
            if(id==255)
                break;
            Creature target;
            if(group==false)
                target=creaturesG0[id];
            else
                target=creaturesG1[id];
            if(target.getAlive()){
            boolean aom=read.boolRead();
                if(aom){
                    int targetid=read.intRead();
                    Creature atktarget;
                    if(group)
                        atktarget=creaturesG0[targetid];
                    else
                        atktarget=creaturesG1[targetid];
                    target.replayRunAlive(aom, atktarget, 0, 0);
                }
                else{
                    int x=read.intRead();
                    int y=read.intRead();
                    target.replayRunAlive(aom, null, x, y);
                }
            }else{
                target.replayRunDeath();
            }
        }
        round++;
        //read.intRead();

        System.out.println("\nround:"+round);
        printField();
    }

    //     ****
    //    **  **
    //       **
    //    **  **
    //     ****

    public Creature getG0(int n){
        try{
            if(n>=creaturesG0.length||n<0){
                throw new Exception("error:array transboundary");
            }
        }catch(Exception e){
            System.err.println(e);
            return null;
        }
        return creaturesG0[n];
    }
    public int getG0length(){
        return creaturesG0.length;
    }
    public Creature getG1(int n){
        try{
            if(n>=creaturesG1.length||n<0){
                throw new Exception("error:array transboundary");
            }
        }catch(Exception e){
            System.err.println(e);
            return null;
        }
        return creaturesG1[n];
    }
    public int getG1length(){
        return creaturesG1.length;
    }

    //    ** **
    //   **  **
    //  **   **
    //  *********
    //       **

    //战斗是否结束
    public boolean combatEnd(){
        if(creaturesG0.length==g0death||creaturesG1.length==g1death){
            end=true;
            return true;
        }
        else
            return false;
    }

    //
    public boolean isEnd(){
        return end;
    }

    public void creatureDead(Creature target){
        if(write.writing)logMove(target, false, 0, 0, 0);
    }
    //在地图上移动生物
    /*public void creatureMove(int id,boolean group,int x,int y){

    }*/
    public void creatureMove(Creature targetcreature,int xorign,int yorign,int x,int y){
        try{
            if(x<0||x>xmax-1||y<0||y>xmax-1){
                throw new Exception("error:Coordinate transboundary");
            }
            if(space[xorign][yorign]!=targetcreature){
                throw new Exception("error:can't find target creature " +targetcreature.getName()+targetcreature.getGroup()+targetcreature.getNo()+ " in "+xorign+" "+yorign);
            }
            space[xorign][yorign]=null;
            if(space[x][y]!=null){
                throw new Exception("error:"+targetcreature.getName()+" space "+ x+ " "+ y +" is occupied");
            }
            space[x][y]=targetcreature;
            System.out.print("|"+targetcreature.name+" move to "+x+y+"|");

            //更新ui
            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    ImageView iv=targetcreature.s;
                    Timeline t=new Timeline();
                    t.getKeyFrames().addAll(
                            new KeyFrame(Duration.ZERO,new KeyValue(iv.xProperty(),xorign*100)),
                            new KeyFrame(new Duration(100),new KeyValue(iv.xProperty(),x*100)),
                            new KeyFrame(Duration.ZERO,new KeyValue(iv.yProperty(),xorign*100)),
                            new KeyFrame(new Duration(100),new KeyValue(iv.yProperty(),y*100))
                    );
                    t.play();
                }
            });

        }
        catch(Exception e){
            System.err.println(e);
            return;
        }
        if(write.writing)logMove(targetcreature, false, 0,x,y);
    }
    //获取一个位置上的生物
    public Creature targetAquire(int x,int y){
        try{
            if(x<0||x>xmax-1||y<0||y>xmax-1){
                throw new Exception("error:Coordinate transboundary");
            }
        }
        catch(Exception e){
            System.err.print(e);
            return null;
        }
        return space[x][y];
    
    }
    //杀死一个生物
    /*public void creatureKill(int id,boolean group){

    }*/
    public void creatureKill(Creature killer,Creature targetcreature){
        targetcreature.death();
        System.out.print("|"+killer.name+" kill "+targetcreature.name+"|");
        if(write.writing)logMove(killer, true, targetcreature.id,0,0);

        //更新ui
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                ImageView iv=targetcreature.s;
                Timeline t=new Timeline();
                t.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO,new KeyValue(iv.rotateProperty(),0)),
                        new KeyFrame(new Duration(1000),new KeyValue(iv.rotateProperty(),180))
                        //new KeyFrame(Duration.ZERO,new KeyValue(iv.opacityProperty(),1)),
                        //new KeyFrame(new Duration(1000),new KeyValue(iv.opacityProperty(),0))

                );
                t.play();
            }
        });
    }
    //移除一个生物
    public void creatureReM(Creature target){
        int x=target.getX();
        int y=target.getY();
        try{
            if(x<0||x>xmax-1||y<0||y>xmax-1){
                throw new Exception("error:Coordinate transboundary");
            }
            if(space[x][y]!=target){
                throw new Exception("error:can't find target creature " +target.getName() + " in "+x+" "+y);
            }

        }
        catch(Exception e){
            System.err.print(e);
        }
        space[x][y]=null;

        //更新ui
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                ImageView iv=target.s;
                Timeline t=new Timeline();
                t.getKeyFrames().addAll(
                        //new KeyFrame(Duration.ZERO,new KeyValue(iv.rotateProperty(),0)),
                        //new KeyFrame(new Duration(1000),new KeyValue(iv.rotateProperty(),180))
                        new KeyFrame(Duration.ZERO,new KeyValue(iv.opacityProperty(),1)),
                        new KeyFrame(new Duration(1000),new KeyValue(iv.opacityProperty(),0))

                );
                t.play();
            }
        });
    }
    //宣布一个生物死亡
    /*public void creatureDeath(int id,boolean group){

    }*/
    public void creatureDeath(Creature targetcreature){
        if(targetcreature.getGroup()==false){
            g0death++;
        }
        else{
            g1death++;
        }
        //System.out.println(g0death+" "+g1death);
    }
    //打印地图情景
    public void printField(){
        System.out.print("x ");
        for(int i=0;i<ymax;i++)
            System.out.print("__");
        System.out.println();
        for(int i=0;i<xmax;i++){
            System.out.print(i+" ");
            for(int j=0;j<ymax;j++){
                if(space[i][j]==null){
                    System.out.print("  ");
                }
                else if(space[i][j].getAlive()==false){
                    System.out.print("XX");
                }
                else if(space[i][j].getGroup()==false)
                    System.out.print("A"+space[i][j].getNo());
                else if(space[i][j].getGroup()==true)
                    System.out.print("B"+space[i][j].getNo());
            }
            System.out.print("\n");
        }
        System.out.print("y ");
        for(int i=0;i<ymax;i++)
            System.out.print(i+" ");
        System.out.println();
    }
    //中断
    public synchronized void battleWait(Creature target)throws InterruptedException{
        synchronized(this){
            numwait++;
            System.out.print(target.name);
            if(target.group)
                System.out.print("1");
            else
                System.out.print("0");
            System.out.print(target.id+" ");
            if(jobDone()){
                if(timeout){
                    if(write.writing)logTurn();
                    printField();
                    notifyAll();
                    reSet();
                    combatEnd();
                }else{
                    wait();
                }
            }
            else
                wait();
        }
    }
    public synchronized void battleNotify()throws InterruptedException{
        synchronized(this){
            while(!end){
                timeout=false;
                Thread.sleep(10);
                System.out.print("|");
                timeout=true;
                if(jobDone()){
                    if(write.writing)logTurn();
                    printField();
                    notifyAll();
                    reSet();
                    combatEnd();
                }else{
                    wait();
                }
            }
        }
    }
    public void reSet(){
        synchronized(this){
        numwait=0;
        timeout=false;
        }
        //combatEnd();
        round++;
        System.out.println("\nround:"+round);
    }
    public boolean jobDone(){
        synchronized(this){
            if(numwait>=creaturesG0.length+creaturesG1.length){
                System.out.println("job done");
                return true;
            }
            else
                return false;
        }
    }
}

class FieldAwaker implements Callable<Boolean> {
    BattleField field;
    public FieldAwaker(BattleField field){
        this.field=field;
    }
    public synchronized Boolean call()throws Exception{
        field.battleNotify();
        //System.out.println("done");
        return true;
    }
}

