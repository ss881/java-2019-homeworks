package Creature;

import Field.Field;
import javafx.scene.image.Image;

import java.util.Random;


public class Creature implements Runnable{
    protected int x,y;
    protected volatile Field field;
    private   volatile boolean alive;
    protected boolean principle;
    Image face;
    Image deadFace;
    String faceName;
    String deadFaceName;
    public Creature(){
        alive=true;
    }
    public void MoveTo(int x,int y){
        this.x=x;
        this.y=y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean isAlive(){
        return alive;
    }
    public void setFace(Image face,Image deadFace){
        this.face=face;
        this.deadFace=deadFace;
    }
    public void setFaceName(String faceName,String deadFaceName){
        this.faceName=faceName;
        this.deadFaceName=deadFaceName;
    }
    public Image getFace(){
        if(isAlive())
            return face;
        else
            return deadFace;
    }
    public String getFaceName(){
        if(isAlive())
            return faceName;
        else
            return deadFaceName;
    }
    private static final Object fightLock=new Object();
    private static final Object moveLock=new Object();


    private int getRandom(){
        Random r=new Random();
        return r.nextInt(100000);
    }
    private void fight(int i,int j){
        synchronized (fightLock){
            if(!alive)
                return;
            Creature creature=field.getCreature(i,j);
            if(creature!=null&&creature.alive&&creature.principle^principle){
                int me=getRandom();
                int you=getRandom();
                if(me>=you){
                    creature.alive=false;
                }
                else{
                    alive=false;
                }
            }
        }
    }

    private  void fightEnemy(){
            fight(x+1,y);
            fight(x-1,y);
            fight(x,y+1);
            fight(x,y-1);
    }
    private void testMove(int i,int j){
        synchronized (moveLock){
            if(i>=0&&i<=19&&j>=0&&j<=9){
                if(field.getCreature(i,j)==null){
                    field.moveCreature(this,i,j);
                    x=i;
                    y=j;
                }
            }
        }
    }
    private Creature getNearest(){
        int minSum=30;
        int sum=0;
        int minx=0,miny=0;
        for(int i=0;i<20;i++){
            for(int j=0;j<10;j++){
                //哈密顿距离
                Creature creature=field.getCreature(i,j);
                if(creature!=null){
                    if(creature.alive&&(creature.principle^principle)){
                        sum=Math.abs(creature.x-x)+Math.abs(creature.y-y);
                        if(sum<minSum){
                            minx=i;
                            miny=j;
                        }
                    }
                }
            }
        }
        return field.getCreature(minx,miny);
    }
    private  void upStep(){
       testMove(x,y+1);
    }
    private void downStep(){
       testMove(x,y-1);
    }
    private void leftStep(){
       testMove(x-1,y);
    }
    private void rightStep(){
       testMove(x+1,y);
    }
    private  void directionRun(){
        int tempX=x;
        int tempY=y;
        int xTarget=0;
        int yTarget=0;
        Creature creature=getNearest();
        if(creature!=null){
            xTarget=creature.x;
            yTarget=creature.y;
        }
        int xDirection=xTarget-x;
        int yDirection=yTarget-y;
        int xLen=Math.abs(xDirection);
        int yLen=Math.abs(yDirection);
        if(xDirection>=0&&yDirection>=0){
            if(yLen>=xLen){
                //Up
                upStep();
            }
            else{
                //Right
                rightStep();
            }
        }
        else if(xDirection<=0&&yDirection<=0){
            if(yLen>=xLen) {
                //Down
                downStep();
            }
            else{
                //Left
                leftStep();
            }
        }
        else if(xDirection>=0&&yDirection<=0){
            if(yLen>=xLen) {
                //Down
                downStep();
            }
            else{
                //Right
                rightStep();
            }
        }
        else{
            if(yLen>=xLen) {
                //Up
                upStep();
            }
            else{
                //Left
                leftStep();
            }
        }
        if(tempX==x&&tempY==y)
            randomRun();
    }
    private  void randomRun(){
        Random r=new Random();
        int dir=r.nextInt(4);
        if(dir==0){
            upStep();
        }
        else if(dir==1){
            downStep();
        }
        else if(dir==2){
            leftStep();
        }
        else if(dir==3){
            rightStep();
        }
    }
    private void move(long startTime){
            long endTime = System.currentTimeMillis();
            int time=0;
            for(;time<100;time++){
                if(endTime-startTime>=15000*time&&endTime-startTime<15000*time+10000){
                    directionRun();
                    break;
                }
            }
            if(time==100){
                randomRun();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (alive&&!Thread.currentThread().isInterrupted()) {
            fightEnemy();
            if (!alive) {
                Thread.currentThread().interrupt();
                break;
            }
           move(startTime);
        }
    }
}
