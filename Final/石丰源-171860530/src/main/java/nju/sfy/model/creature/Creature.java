package nju.sfy.model.creature;

import nju.sfy.model.Thing2D;
import nju.sfy.model.creature.monster.Monster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Creature extends Thing2D implements Runnable{

    private int health;
    private int attackValue;
    private int damageArea;
    private int moveSpeed;

    public Creature(){
        super();
    }
    public Creature(String name){
        super(name);
    }
    public Creature(String name, int health, int attackValue, int damageArea, int moveSpeed){
        this.name = name;
        this.health = health;
        this.attackValue = attackValue;
        this.damageArea = damageArea;
        this.moveSpeed = moveSpeed;
    }

    public int getHealth() {
        return health;
    }

    public boolean isDead(){
        return (health <= 0);
    }
    public void move(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            list.add(i);
        }
        Collections.shuffle(list);
        for(int i = 0; i < 4; i++){
            int t = list.get(i);
            boolean moveSuccess = false;
            if(t == 0){
                moveSuccess = doMove(1, 0);
            }
            else if(t == 1){
                moveSuccess = doMove(-1, 0);
            }
            else if(t == 2){
                moveSuccess = doMove(0, 1);
            }
            else{
                moveSuccess = doMove(0, -1);
            }
            if(moveSuccess)
                break;
        }
    }
    public boolean doMove(int offsetX, int offsetY){
        if(isDead())
            return false;
        //要检查field是否为空
        int x = tile.getX() + offsetX;
        int y = tile.getY() + offsetY;
        synchronized (field){
            if(field.isInField(x, y) && !field.getTiles()[x][y].hasHolder()){
                if(!field.getTiles()[x][y].hasHolder()){
                    field.getTiles()[x][y].setHolder(this);
                    tile.removeHolder();
                    tile = field.getTiles()[x][y];
                    return true;
                }
                else
                    return false;
            }
            else{
                return false;
            }
        }
    }
    public void attack(){
        for(int i = -damageArea; i <= damageArea; i++){
            for(int j = -damageArea; j <= damageArea; j++){
                if(i != 0 || j != 0){
                    synchronized (field){
                        if(tile == null)
                            return;
                        int x = tile.getX() + i;
                        int y = tile.getY() + j;
                        if(field.isInField(x, y)){
                            Thing2D t = field.getTiles()[x][y].getHolder();
                            if(t == null)
                                continue;
                            if(this instanceof Monster){
                                if(!(t instanceof Monster)){
                                    Creature c = (Creature) t;
                                    c.beAttacked(attackValue);
                                    return;
                                }
                            }
                            else{
                                if(t instanceof Monster){
                                    Creature c = (Creature) t;
                                    c.beAttacked(attackValue);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public synchronized void beAttacked(int damage){
        health -= damage;
        if(health <= 0){
            textArea.appendText(name + " is dead\n");
            tile.setImage2(image2);
            tile.removeHolder();
            tile.draw();
            tile = null;
        }
    }
    public synchronized void betAttacked(int damage){
        health -= damage;
        if(health <= 0){
            tile.removeHolder();
            tile = null;
        }
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            if(isDead()){
                return;
            }
            else{
                /*
                Random random = new Random();
                int x = random.nextInt(10);

                if(x %  2 == 0){
                    move();
                }
                else{
                    attack();
                }
                 */
                move();
                attack();
                try {
                    Thread.sleep(moveSpeed);        //相当于每moveSpeed秒移动一次
                    synchronized (field){
                        field.draw();
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }
}
