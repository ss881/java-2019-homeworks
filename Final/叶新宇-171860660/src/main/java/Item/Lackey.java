package Item;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import Behavior.Shoot;
import Property.*;

public class Lackey extends Creature implements Shoot, Runnable {
    protected int cbuff = 0;
    protected static ExecutorService bulletExecutor = null;
    protected static ArrayList<Bullet> bullets = null;

    public Lackey() {
        this.battlefield = battlefield;
        image = new Image("lackey.png");
        attack = 30;
        denfense = 30;
        id = Identity.MONSTER;
        hp = 300;
        maxHP = 300;
    }

    public void getCure() {
        cbuff = 3;
    }

    public static void setBulletExecutor(ExecutorService executor) {
        bulletExecutor = executor;
    }

    public static void setBullets(ArrayList<Bullet> array) {
        bullets = array;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "杂兵";
    }

    @Override
    public String getStatus() {
        StringBuilder status = new StringBuilder("");
        status.append("L " + getPosition().toString());
        status.append(" " + getHPRatio());
        return status.toString();
    }

    public Bullet shoot() {
        Position bulletPos = new Position(getPosition().getX() - 60, getPosition().getY());
        BulletType bulletCategory = BulletType.MONSTER;
        Bullet bullet = new Bullet(this.toString(), bulletCategory, Direction.LEFT, bulletPos, battlefield);
        return bullet;
    }

    @Override
    Position nextMove() {
        Random random = new Random();
        int i = getPosition().getXH();
        int j = getPosition().getYH();
        Position next = new Position(getPosition().getX(), getPosition().getY());
        synchronized (battlefield) {
            if(i > 0 && !battlefield[i - 1][j].isEmpty()) {
                if(battlefield[i - 1][j].getHolder().getId() == Identity.CALABASH) {
                    next.setXH(i - 1);
                    return next;
                }
            }
            else if(i < 15 && !battlefield[i + 1][j].isEmpty()) {
                if(battlefield[i + 1][j].getHolder().getId() == Identity.CALABASH) {
                    next.setXH(i + 1);
                    return next;
                }
            }
            else if(j > 0 && !battlefield[i][j - 1].isEmpty()) {
                if(battlefield[i][j - 1].getHolder().getId() == Identity.CALABASH) {
                    next.setYH(j - 1);
                    return next;
                }
            }
            else if(j < 8 && !battlefield[i][j + 1].isEmpty()) {
                if(battlefield[i][j + 1].getHolder().getId() == Identity.CALABASH) {
                    next.setYH(j + 1);
                    return next;
                }
            }
        }
        if(stepNum > 10)
            return moveToCenter();
        int choice = random.nextInt() % 7;
        if(choice == 0 && j > 0)
            next.setYH(j - 1);
        else if(choice == 1 && j < 8)
            next.setYH(j + 1);
        else if(choice == 2 && i < 15)
            next.setXH(i + 1);
        else if(choice == 3)
            ;
        else if(choice > 3 && i > 0)
            next.setXH(i - 1);
        return next;
    }

    public void run() {
        boolean shootFlag = true;
        while(hp > 0) {
            Position next = nextMove();
            stepNum++ ;
            if(hp <= 0)
                break;
            fight(next);
            try{
                synchronized (this) {
                    if(shootFlag) {
                        Bullet bullet =shoot();
                        bulletExecutor.execute(bullet);
                        synchronized (bullets) {
                            bullets.add(bullet);
                        }
                    }
                    shootFlag = !shootFlag;
                    if(cbuff > 0 && hp > 0) {
                        hp += 20;
                        if(hp > maxHP)
                            hp = maxHP;
                    }
                    cbuff--;
                }
                TimeUnit.MILLISECONDS.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
