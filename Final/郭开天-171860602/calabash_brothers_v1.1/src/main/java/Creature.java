//import java.lang.annotation.Target;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Creature implements Runnable{
    int id;
    String name;
    boolean alive;
    boolean group;
    int locationx;
    int locationy;
    Image image;
    ImageView s;
    Buff buff;
    BattleField field;
    public int getNo(){
        return id;
    }
    public String getName(){
        return name;
    }
    public boolean getAlive(){
        return alive;
    }
    public boolean getGroup(){
        return group;
    }
    public int getX(){
        return locationx;
    }
    public int getY(){
        return locationy;
    }

    public Creature(){
    }
    public Creature(int id,String name){
        this.id=id;
        this.name=name;
        image=searchImage(name);
        s=new ImageView(image);
        alive=true;
    }
    public Creature(Creature target,boolean group,BattleField field,int x,int y){
        id=target.id;
        name=target.name;
        image=searchImage(name);
        s=new ImageView(image);
        this.field=field;
        this.group=group;
        alive=true;
        locationx=x;
        locationy=y;

        App.root.getChildren().add(s);
        //s.setX(x*100);
        //s.setY(y*100);
    }
    public Creature(int id,String name,boolean group,BattleField field,int x,int y){
        this.id=id;
        this.name=name;
        image=searchImage(name);
        s=new ImageView(image);
        this.group=group;
        this.field=field;
        alive=true;
        locationx=x;
        locationy=y;

        App.root.getChildren().add(s);
        //s.setX(x*100);
        //s.setY(y*100);
    }
    Image searchImage(String name){
        if(name.equals("dw"))
            return new Image("pic/1.png",100,100,true,true);
        else if(name.equals("ew"))
            return new Image("pic/2.png",100,100,true,true);
        else if(name.equals("sw"))
            return new Image("pic/3.png",100,100,true,true);
        else if(name.equals("si"))
            return new Image("pic/4.png",100,100,true,true);
        else if(name.equals("ww"))
            return new Image("pic/5.png",100,100,true,true);
        else if(name.equals("lw"))
            return new Image("pic/6.png",100,100,true,true);
        else if(name.equals("qw"))
            return new Image("pic/7.png",100,100,true,true);
        else
            return new Image("pic/Sidekicks.png",100,100,true,true);
    }
    public void run(){
        try{
            while(!field.isEnd()){
                synchronized(field){
                    if(alive==true){
                        move();
                    }
                    else{
                        dead();
                    }
                    buffEffect();
                    field.battleWait(this);
                }
            }
        }catch(InterruptedException e){
            System.out.println("Exiting via interrupr");
        }
    }

    public void replayRunAlive(boolean aom,Creature target,int tempx,int tempy){
        if(aom)
            replayAttack(target);
        else
            replayMove(tempx, tempy);
        buffEffect();
    }
    public void replayRunDeath(){
        buffEffect();
    }
    public void replayMove(int tempx,int tempy){
        int xorign=locationx;
        int yorign=locationy;
        locationx=tempx;
        locationy=tempy;
        field.creatureMove(this, xorign, yorign, locationx, locationy);
    }
    public void replayAttack(Creature target){
        attack(target);
    }
    
    void move(){
        int xorign=locationx;
        int yorign=locationy;

        int xmax=field.getXmax();
        int ymax=field.getYmax();

        int[]pair=trendCentre();
        int tempx=pair[0];
        int tempy=pair[1];
        
        if(tempx<0)tempx=0;
        else if(tempx>=xmax)tempx=xmax-1;
        if(tempy<0)tempy=0;
        else if(tempy>=ymax)tempy=ymax-1;
        synchronized(field){
            //System.out.println("lock:"+name);
            Creature targetcreature=field.targetAquire(tempx, tempy);
            if(targetcreature!=null){
                tempx=locationx;
                tempy=locationy;
                if(targetcreature.group!=group&&targetcreature.alive==true){
                    attack(targetcreature);
                }
            }
            else
                field.creatureMove(this, xorign, yorign,tempx,tempy);
            //System.out.println("unlock:"+name);

        }

        final int tx=tempx,
                ty=tempy,
                ox=locationx,
                oy=locationy;

        //App.runLater(s,ox,oy,tx,ty);

        /*Platform.runLater(new Runnable() {
            @Override
            public void run() {

                ImageView iv=s;
                Timeline t=new Timeline();
                t.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO,new KeyValue(iv.xProperty(),ox*100)),
                        new KeyFrame(new Duration(100),new KeyValue(iv.xProperty(),tx*100)),
                        new KeyFrame(Duration.ZERO,new KeyValue(iv.yProperty(),oy*100)),
                        new KeyFrame(new Duration(100),new KeyValue(iv.yProperty(),ty*100))
                );
                t.play();
            }
        });*/

        locationx=tempx;
        locationy=tempy;
    }
    void attack(Creature target){
        field.creatureKill(this,target);
    }
    //死亡后的行为
    void dead(){
        field.creatureDead(this);
    }
    void underAttack(){
        death();
    }
    public void death(){
        //System.out.println(name+" death");
        alive=false;
        buff=new RemoveBody(this);
        field.creatureDeath(this);
    }
    //直接从比赛中移除一个生物
    public void reMove(){
        field.creatureReM(this);
        locationx=locationy=0;
    }
    void buffEffect(){
        if(buff!=null){
            buff.remman--;
            if(buff.remman>0){
                buff.ongoEffect();
            }
            else{
                buff.finalEffect();
                buff=null;
            }
        }
    }
    int[] trendCentre(){
        int tempx=locationx;
        int tempy=locationy;
        int tx1=field.xmax-1-locationx;
        int tx2=locationx;
        int ty1=field.ymax-1-locationy;
        int ty2=locationy;
        double d=Math.random();
        int i=(int)(d*(tx1+tx2+ty1+ty2));
        if(i<tx1)tempx++;
        else if(i>=tx1&&i<tx1+tx2)tempx--;
        else if(i>=tx1+tx2&&i<tx1+tx2+ty1)tempy++;
        else if(i>=tx1+tx2+ty1)tempy--;
        int[] ret={tempx,tempy};
        return ret;
    }
}