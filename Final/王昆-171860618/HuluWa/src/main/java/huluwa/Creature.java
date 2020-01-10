package huluwa;

import javafx.application.Platform;
import javafx.scene.image.*;
import huluwa.position.Position;
import java.util.*;
import java.util.concurrent.TimeUnit;

public abstract class Creature implements Runnable{
    protected ImageView icon;
    protected Position position;
    protected int height;
    protected int width;
    protected int speed;
    protected int range;
    int attack;
    private boolean moving = false;
    protected boolean alive = true;
    protected String name;
    static BattleMap map;
    @Override
    public String toString(){
        return this.name;
    }
    public static Creature searchByName(String name){
        for(Creature c:GoodCreature.army){
            if(c.name.equals(name))
                return c;
        }
        for(Creature c:BadCreature.army){
            if(c.name.equals(name))
                return c;
        }
        return null;
    }
    public ImageView getIcon(){
        return this.icon;
    }
    public int getHeight(){return height;}
    public int getWidth(){return width;}
    public Position getPosition() {
        return position;
    }
    public void joinMap(Position p){
        if(map.isFree(p)){
            this.position = p;
            map.add(this,this.position);
        }else{
            System.out.println(this + "无法加入");
        }
    }
    /*
    运动相关：
        moving 代表生物是否移动的状态，
        当生物在地图上成功移动时，设置moving状态为true,同时该生物线程调用wait()等待
        当主线程的移动动画播放完成后会调用moveDone方法，设置moving为true，唤醒生物线程
     */
    public synchronized void moveDone(){
        moving = false;
        this.notify();
        //System.out.println(this + "结束运动");
    }
    public synchronized boolean move(Position p){
        if(!alive){
            return false;
        }
        //System.out.println(this + "准备移动");
        if(map.move(this,p)){
            moving =true;
            if(GameController.currentCondition()== Condition.RUNNING){
                try{
                    //System.out.println(this + "开始等待线程");
                    this.wait();
                }catch(InterruptedException e){
                    return false;
                }
            }
            //System.out.println(this + "线程唤醒");
            this.position = p;
            return true;
        }else {
            return false;
        }
     }
     /*
       攻击相关：
       在attack()函数中，当判断攻击生效时，在调用死亡对象的getAttacked()方法前获取该对象的锁
       确保同一个生物不会被攻击两次
      */
    public  boolean isAlive(){
         return alive;
    }
    public synchronized void getAttacked(){
        if(!alive){
            System.err.println("不安全线程问题：" + this +"受到了多于一次的攻击");
            return;
        }
        this.alive = false;
        map.remove(this);
        if(this instanceof GoodCreature){
            synchronized (GoodCreature.class){
                GoodCreature.aliveNumber--;
            }
        }else{
            synchronized (BadCreature.class){
                BadCreature.aliveNumber--;
            }
        }
        Platform.runLater(()->{
            GameController.getInstance().Check();});
    }
    private void attack(Creature c){
        if(!alive)
            return;
        double prob = 100*attack*1.0/((attack+c.attack)*1.0);
        Random r = new Random();
        int z = r.nextInt(100);
        if(prob >= z){
            synchronized (c){
                if(c.isAlive() && !c.moving)
                    c.getAttacked();
            }
        }else{
            synchronized (this){
                if(isAlive())
                    this.getAttacked();
            }
        }
     }
    public void run(){
        Class T = GoodCreature.class;
        if(this instanceof  GoodCreature)
            T = BadCreature.class;
        try{
            if(GameController.currentCondition() == Condition.RUNNING)
                GameController.getInstance().writeRecords(System.currentTimeMillis(),
                        "set " + this +" "+ this.position);
            while(alive){
                //寻找一个目标
                //System.out.println(this + "准备开始寻找");
                Creature enemy = map.getClosestEnemy(this.position,T);
                if(enemy == null){
                    //System.out.println("找不到目标");
                 return;
                }
               //System.out.println(this+" "+alive+"位于"+this.huluwa.position+"找到最近的敌人位于"+enemy.getPosition());
                int dx = enemy.getPosition().getX() - this.position.getX();
                int dy = enemy.getPosition().getY() - this.position.getY();
                int dis = Math.abs(dx) + Math.abs(dy);
                if(dis <= this.range){
                    //距离小于攻击范围，尝试攻击敌人
                    //System.out.println(this+"尝试攻击"+enemy);
                    attack(enemy);
                    TimeUnit.MILLISECONDS.sleep(1000);
                }else {
                    int xDir = dx > 0 ? 1 : -1;
                    int yDir = dy > 0 ? 1 : -1;
                    int mx = Math.abs(dx) >= Math.abs(dy) ? xDir : 0;
                    int my = Math.abs(dy) > Math.abs(dx) ? yDir : 0;
                    //向敌人移动
                    //System.out.println(this + "向敌人移动");
                    Position new_position = new Position(this.position.getX() + mx, this.position.getY() + my);
                    if (!this.move(new_position)) {
                        //向敌人移动失败，进行随机移动
                        int randomX = -1 + new Random().nextInt(2);
                        int randomY = -1 + new Random().nextInt(2);
                        Position random_position = new Position(this.position.getX() + randomX, this.position.getY() + randomY);
                        if(!this.move(random_position)){
                            //System.out.println(this.name + "随机移动失败");
                            //随机移动也失败，进行休息
                            TimeUnit.MILLISECONDS.sleep(1000);
                        }
                    }
                }
                //TimeUnit.MILLISECONDS.sleep(this.speed);
                //System.out.println(this +"存活" + " moving:"+moving + " alive:"+alive  );
            }
            //System.out.println(this +"结束" + " moving:"+moving + " alive:"+alive);
        }catch(Exception e){
                e.printStackTrace();
                GameController.crashed();
        }
    }
 }

class GoodCreature extends Creature{
    protected static List<GoodCreature> army = new ArrayList<>();
    protected static int aliveNumber = 0;
    public static void setFormation(List<Position> f) throws Exception{
        if(f==null || aliveNumber!= f.size())
            throw new Exception("错误的阵型信息");
        for(GoodCreature c:army){
            map.remove(c);
        }
        for(int i=0;i<aliveNumber;i++){
            Position p = f.get(i);
            army.get(i).joinMap(p);
        }
    }
    public static void addArmy(Creature c) throws Exception{
        if(c instanceof GoodCreature){
            army.add((GoodCreature) c);
            aliveNumber++;
        }
        else
            throw new Exception("添加了错误的生物种类");
    }
}
 class BadCreature extends Creature{
    static List<BadCreature> army= new ArrayList<>();
    static int aliveNumber = 0;
    public static void setFormation(List<Position> f) throws Exception{
         if(f==null || aliveNumber!= f.size())
             throw new Exception("错误的阵型信息");
        for(BadCreature c:army){
            map.remove(c);
        }
         for(int i=0;i<aliveNumber;i++){
             Position p = f.get(i);
             army.get(i).joinMap(p);
         }
     }
     public static void addArmy(Creature c) throws Exception{
         if(c instanceof BadCreature){
             army.add((BadCreature) c);
             aliveNumber++;
         }
         else
             throw new Exception("添加了错误的生物种类");
     }
}
 class HuluWa extends GoodCreature{
     HuluWa(String n, String url){
         this.name = n;
         this.width = 75;
         this.speed = 1200;
         this.height = 105;
         this.range = 1;
         this.attack = 7;
         this.icon = new ImageView(url);
         this.icon.setFitHeight(height);
         this.icon.setFitWidth(width);
     }
 }
 class Grandpa extends GoodCreature{
     Grandpa(){
         this.name = "GrandPa";
         this.width = 75;
         this.speed = 1500;
         this.range = 2;
         this.height = 100;
         this.attack = 4;
         this.icon = new ImageView("bin/laoyeye.png");
         this.icon.setFitHeight(height);
         this.icon.setFitWidth(width);
     }
 }
 class Scorpion extends BadCreature{
     Scorpion(){
         this.name = "Scorpion";
         this.width = 115;
         this.height = 120;
         this.speed = 1000;
         this.range = 1;
         this.attack = 10;
         this.icon = new ImageView("bin/xiezi.png");
         this.icon.setFitHeight(height);
         this.icon.setFitWidth(width);
     }
 }
 class Snake extends BadCreature{
     Snake(){
         this.name = "Snake";
         this.width = 100;
         this.height = 120;
         this.speed = 800;
         this.range = 2;
         this.attack = 5;
         this.icon = new ImageView("bin/shejing.png");
         this.icon.setFitHeight(height);
         this.icon.setFitWidth(width);
     }
 }
 class Follower extends BadCreature{
     Follower(int i){
         this.name = "Follower-"+i;
         this.width = 80;
         this.height = 105;
         this.speed = 1200;
         this.range = 1;
         this.attack = 3;
         this.icon = new ImageView("bin/wugong.png");
         this.icon.setFitHeight(height);
         this.icon.setFitWidth(width);
     }
 }
 