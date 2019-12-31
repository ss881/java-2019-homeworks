package Creature;

import Battle.*;
import Formation.Formation;
import Formation.Type;
import Record.Record;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.locks.Lock;

public abstract class Creature implements Runnable{
    protected int hp;

    protected int baseDamage;

    //protected Thread thread;

    private Image image;

    private ImageView imageView;

    protected boolean alive;

    protected int turn;

    protected final Object lock;

    public abstract boolean isEnemy(Creature creature);

    public abstract void print();

    public boolean isCreatureAlive(){
        return alive;
    }

    public Creature(Object lock, String imageFile) {
        //thread = new Thread();
        this.lock = lock;
        image = new Image(Paths.get(imageFile).toUri().toString());
        imageView = new ImageView(image);
        imageView.setFitHeight(Fight.BLOCKPIXELX);
        imageView.setFitWidth(Fight.BLOCKPIXELY);
        alive = true;
    }

    public void initTurn(){
        this.turn = Fight.getAllCreatureNum();
        System.out.println(this.toString() + " " + turn);
        Fight.addAllCreatureNum();
    }

    public void attack(int x, int y){
        Creature creature = Battle.getPosition(x, y).getHolder();
        //System.out.println(this.toString() + " attack " + creature.toString());
        creature.beAttacked(baseDamage);
    }

    protected void beAttacked(int atk){
        //System.out.println(this.toString() + "受到" + atk);
        hp-=atk;
        if (hp <= 0) {
            alive = false;
            image = new Image(Paths.get("src/main/resources/pic/dead.jpeg").toUri().toString());
            imageView.setImage(image);
            if (this instanceof Grandpa || this instanceof CalabashBro)
                Fight.minusJustice();
            else
                Fight.minusEvil();
            //System.out.println(this.toString() + " dead");
        }
    }

    protected Position<?> findNearestEnemy(){
        Position<?> position = Battle.findEnemy(this);
        assert position != null;
        return position;
    }

    public void move(int i, int j){  //图片移动
        /*synchronized (battle){
            Position<?> position = battle.getPositionByInstance(this);
            assert position != null;
            if (!battle.getNextPosition(position)){
                System.out.println("cannot move!");
            }
        }*/
        System.out.println("move " + i + " " + j);
        imageView.setTranslateY(imageView.getTranslateY() + i * Fight.BLOCKPIXELY);
        imageView.setTranslateX(imageView.getTranslateX() + j * Fight.BLOCKPIXELX);
    }

    public void action(Position<?> position){
        if (!alive)
            return;

        Position<?> p = findNearestEnemy();
        if (p == null){
            return;
        }
        int xEnemy = p.getX(), yEnemy = p.getY();
        int x = position.getX(), y = position.getY();
        /*
        若在攻击范围内，则攻击
         */
        if (Math.abs(x - xEnemy) + Math.abs(y - yEnemy) == 1){
            Record.writeMessage("Attack", x, y, xEnemy, yEnemy);
            attack(xEnemy, yEnemy);
            return;
        }

        /*
        否则则靠近目标
         */
        if (x - xEnemy > 0){
            if (Battle.moveTo(position, x - 1, y)){
                Record.writeMessage("Move", x, y, -1, 0);
                move( -1, 0);
                return;
            }
        }
        if (x - xEnemy < 0){
            if (Battle.moveTo(position, x + 1, y)){
                Record.writeMessage("Move", x, y, 1, 0);
                move( 1, 0);
                return;
            }
        }
        if (y - yEnemy > 0) {
            if (Battle.moveTo(position, x, y - 1)){
                Record.writeMessage("Move", x, y, 0, -1);
                move(0, -1);
                return;
            }
        }
        if (y - yEnemy < 0){
            if (Battle.moveTo(position, x, y + 1)){
                Record.writeMessage("Move", x, y, 0, 1);
                move(0, 1);
                return;
            }
        }
        System.out.println("cannot move");
        boolean success = false;
        int count = 0;
        do {
            count++;
            int d = (int) ((Math.random() * 10) % 4);
            switch (d){
                case 0:
                    if (x > 0 && Battle.moveTo(position, x - 1, y)){
                        Record.writeMessage("Move", x, y, -1, 0);
                        move(-1, 0);
                        success = true;
                    }
                    break;
                case 1:
                    if (x < Battle.getM() - 1 && Battle.moveTo(position, x + 1, y)){
                        Record.writeMessage("Move", x, y, 1, 0);
                        move(1, 0);
                        success = true;
                    }
                    break;
                case 2:
                    if (y > 0 && Battle.moveTo(position, x, y - 1)){
                        Record.writeMessage("Move", x, y, 0, -1);
                        move(0, -1);
                        success = true;
                    }
                    break;
                case 3:
                    if (y < Battle.getN() - 1 && Battle.moveTo(position, x , y + 1)){
                        Record.writeMessage("Move", x, y, 0, 1);
                        move(0, 1);
                        success = true;
                    }
                    break;
            }
            System.out.println(success);
        }while (!success && count < 10);



    }

    public void setHpAndBD(int hp, int bd){
        this.hp = hp;
        this.baseDamage = bd;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getTurn() {
        return turn;
    }

    public int getHp() {
        return hp;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
