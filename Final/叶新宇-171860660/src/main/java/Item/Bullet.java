package Item;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import Property.BulletType;
import Property.Direction;
import Property.Position;
import Property.Identity;
import BattleField.Block;
import Behavior.Hit;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Bullet extends Item implements Runnable {
    private Image image;
    private int attack;
    private String shooter;
    private Identity target;
    private BulletType type;
    private Position pos;
    private int dirX;
    private int dirY;
    private boolean isOut = false;
    private final Block[][] battlefield;
    private static Queue<Hit> hit;

    public Bullet(String name, BulletType type, Direction dir, Position pos, Block[][] battlefield) {
        this.shooter = name;
        this.pos = pos;
        this.type = type;
        this.battlefield = battlefield;
        this.dirX = dir.getDeltaX();
        this.dirY = dir.getDeltaY();
        attack = 70;
        image = new Image(type.getImagePath());
        if(type == BulletType.MONSTER||type == BulletType.STINGER) {
            target = Identity.CALABASH;
            if(type == BulletType.STINGER)
                attack = 120;
        }
        else {
            target = Identity.MONSTER;
            if(type == BulletType.FLAME || type == BulletType.WATER)
                attack = 100;
        }
    }

    public int getAttack() {
        return attack;
    }

    public String getShooter() {
        return shooter;
    }

    public void setOut() {
        isOut = true;
    }

    public boolean isOut() {
        return isOut;
    }

    public static void setHit(Queue<Hit> queue) {
        hit = queue;
    }

    public Position getPos() {
        return pos;
    }

    public void setDir(Direction dir) {
        this.dirX = dir.getDeltaX();
        this.dirY = dir.getDeltaY();
    }

    public String getStatus() {
        String status = "B " + type.ordinal() + " " + pos.toString();
        return status;
    }

    public void display(GraphicsContext gc) {
        if(type == BulletType.FLAME || type == BulletType.WATER)
            gc.drawImage(image, pos.getX(),pos.getY() + 20,60,20);
        else if(type == BulletType.STINGER)
            gc.drawImage(image, pos.getX(),pos.getY() + 25,60,10);
        else
            gc.drawImage(image, pos.getX(),pos.getY() + 20,20,20);
    }

    public void run() {
        int i, j;
        while(!isOut && pos.getX() > 0 && pos.getX() < 960 && pos.getY() > 0 && pos.getY() < 540) {
            pos.setX(pos.getX() + dirX);
            pos.setY(pos.getY() + dirY);
            i = pos.getXH();
            j = pos.getYH();
            if(i == 16 || j == 9)   break;
            synchronized (battlefield) {
                if(!battlefield[i][j].isEmpty()) {
                    Creature creature = battlefield[i][j].getHolder();
                    if(target == creature.getId()) {
                        Hit newhit = new Hit(this,creature);
                        synchronized (hit) {
                            hit.offer(newhit);
                        }
                        if(creature.isDead()) {
                            battlefield[i][j].withdraw();
                            DeadCreature dead = new DeadCreature();
                            dead.setPosition(creature.getPosition().getX(),creature.getPosition().getY());
                            battlefield[i][j].join(dead);
                        }
                    }
                }
            }
            try{
                TimeUnit.MILLISECONDS.sleep(50);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isOut = true;
    }
}
