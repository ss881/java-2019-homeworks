package thread;

import controller.*;
import creature.Creature;
import history.Action;
import javafx.scene.canvas.Canvas;
import java.util.ArrayList;
import java.util.Random;


public class CreatureThread  extends Thread{

    private Creature creature;
    private MyMap map;
    private Canvas myCanvas;
    private boolean living;
    private ArrayList<Action> actions;
    public CreatureThread(Creature creature,MyMap map,Canvas canvas, ArrayList<Action> actions){
        this.map = map;
        creature.getInfo();
        System.out.println("创建!");
        this.creature = creature;
        this.myCanvas = canvas;
        this.actions = actions; //保存动作
        this.living = true;
    }

    @Override
    public  synchronized void run() {//每个生物再活着时始终进行移动，攻击，使用技能的循环直到死亡
        while(true){
            if(this.creature.isAlive()){
                try {
                    this.move();
                    Thread.sleep(500);
                    this.attack();
                    Thread.sleep(500);
                    this.skill();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
            else {
                if (this.living) { //结束延迟5s后死亡
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.living = false;
                    return; //结束线程
                }
            }
        }
    }

    private void skill() {
        if(!this.living) return;
        synchronized (this.map) {
            if (!this.creature.isAlive()) return;
            Random t = new Random();
            int k = t.nextInt(10);
            if (k >= 1) {  //0.1的概率使用技能
                return;
            }
            this.creature.skill(this.map,this.myCanvas);
            System.out.println(creature.getName()+"技能");
            int start[] = { this.creature.getI() , this.creature.getJ() };
            Action action = new Action(2,start,start);
            synchronized (this.actions) {
                this.actions.add(action);
            }
        }//保存动作
    }

    private void attack() {
        if(!this.living) return;
        synchronized (this.map) {
            if (!this.creature.isAlive()) return;
            Creature enemy = this.map.checkEnemy(this.creature.getI(),this.creature.getJ()); //根据坐标获取
            if (enemy == null) return;
            if (this.creature.isAlive() && enemy.isAlive()){
                int[] start = {this.creature.getI(),this.creature.getJ()};
                int []end = {enemy.getI(),enemy.getJ()};
                System.out.print("攻击");
                System.out.println(start[0]+" " + start[1] + " " +end[0] + " "+end[1]);
                Action action = new Action(1,start,end);
                synchronized (this.actions) {
                    this.actions.add(action);
                }
                this.creature.attack(enemy, this.myCanvas);
            }
        }//保存动作
    }

    public void move(){
        if(!this.living) return;
        try {
            //访问临界区方法，移动
            synchronized(this.map) {
                int pos[] = creature.getDestination(this.map); //检测
                int x= pos[0];
                int y =pos[1]; //移动的x，y
                int []start = {this.creature.getI(),this.creature.getJ()};
                Action action = new Action(0,start,pos);
                System.out.print("移动");
                System.out.println(start[0]+" " + start[1] + " " +pos[0] + " "+pos[1]);
                synchronized (this.actions) {
                    this.actions.add(action);
                }
                map.moveCreatureTo(this.creature, x, y);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("move error");
        }
    }

}