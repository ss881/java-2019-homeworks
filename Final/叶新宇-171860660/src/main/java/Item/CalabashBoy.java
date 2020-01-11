package Item;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import Behavior.Shoot;
import Property.*;

public class CalabashBoy extends Creature implements Shoot, Runnable {
    private CalabashProperty color;
    private int cbuff = 0; // cure buff
    private int adbuff = 0; //Attack & Defense buff
    private static ExecutorService bulletExecutor = null;
    private static ArrayList<Bullet> bullets = null;

    public CalabashBoy(CalabashProperty color) {
        this.battlefield = battlefield;
        this.color = color;
        int index = color.ordinal() + 1;
        image = new Image("" + index + ".png");
        attack = color.getAttack();
        denfense = color.getDefense();
        hp = maxHP = color.getMaxHP();
        id = Identity.CALABASH;
    }

    public void getBuff() {
        cbuff = 3;
        adbuff = 5;
    }

    public static void setBulletExecutor(ExecutorService executor) {
        bulletExecutor = executor;
    }

    public static void setBullets(ArrayList<Bullet> bulletArrayList) {
        bullets = bulletArrayList;
    }


    public CalabashProperty getColor() {
        return color;
    }

    @Override
    public String toString() {
        return color.getName();
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public String getStatus() {
        StringBuilder status = new StringBuilder("");
        status.append("C " + color.ordinal() + " " + getPosition().toString());
        status.append(" " + getHPRatio());
        return status.toString();
    }

    public Bullet shoot() {
        Position bulletPos = new Position(getPosition().getX() + 60, getPosition().getY());
        BulletType bulletCategory = BulletType.CALABASH;
        if(color == CalabashProperty.GREEN)
            bulletCategory = BulletType.FLAME;
        if(color == CalabashProperty.CYAN)
            bulletCategory = BulletType.WATER;
        Bullet bullet = new Bullet(this.toString(), bulletCategory, Direction.RIGHT, bulletPos, battlefield);
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
                if(battlefield[i - 1][j].getHolder().getId() == Identity.MONSTER) {
                    next.setXH(i - 1);
                    return next;
                }
            }
            else if(i < 15 && !battlefield[i + 1][j].isEmpty()) {
                if(battlefield[i + 1][j].getHolder().getId() == Identity.MONSTER) {
                    next.setXH(i + 1);
                    return next;
                }
            }
            else if(j > 0 && !battlefield[i][j-1].isEmpty()) {
                if(battlefield[i][j - 1].getHolder().getId() == Identity.MONSTER) {
                    next.setYH(j - 1);
                    return next;
                }
            }
            else if(j < 8 &&!battlefield[i][j+1].isEmpty()) {
                if(battlefield[i][j + 1].getHolder().getId() == Identity.MONSTER) {
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
        else if(choice == 2 && i > 0)
            next.setXH(i - 1);
        else if(choice == 3)
            ;//不动
        else if(choice > 3 && i < 15)
            next.setXH(i + 1);
        return next;
    }

    public void run() {
        boolean shootFlag = true;
        boolean buffed = false;
        while(hp > 0) {
            Position next = nextMove();
            stepNum++ ;
            if(hp <= 0)
                break;
            synchronized (this) {
                if(adbuff > 0 && hp > 0 && buffed == false) {
                    setAttack(attack + 10);
                    setDenfense(denfense + 10);
                    adbuff--;
                    buffed = true;
                }
            }
            fight(next);
            if(shootFlag) {
                if(color == CalabashProperty.GREEN) {
                    Bullet bullet = shoot();
                    bulletExecutor.execute(bullet);
                    synchronized (bullets) {
                        bullets.add(bullet);
                    }
                }
                else if(color == CalabashProperty.CYAN) {
                    Bullet[] bullets1 = {shoot(), shoot(), shoot()};
                    bullets1[0].setDir(Direction.RIGHT_UP);
                    bullets1[1].setDir(Direction.RIGHT_DOWN);
                    for(Bullet bullet : bullets1)
                        bulletExecutor.execute(bullet);
                    synchronized (bullets) {
                        for(Bullet bullet : bullets1)
                            bullets.add(bullet);
                    }
                    shootFlag = !shootFlag;
                    //五娃三个方向射击
                }
                else {
                    Bullet bullet = shoot();
                    bulletExecutor.execute(bullet);
                    synchronized (bullets) {
                        bullets.add(bullet);
                    }
                    shootFlag = !shootFlag;
                }
            }
            else if(color != CalabashProperty.GREEN)
                shootFlag = !shootFlag;
            try{
                synchronized (this) {
                    if(cbuff > 0 && hp > 0) {
                        hp += 20;
                        if(hp > maxHP)
                            hp = maxHP;
                    }
                    cbuff--;
                    if(buffed) {
                        setAttack(attack - 10);
                        setDenfense(denfense - 10);
                        buffed = false;
                    }
                }
                TimeUnit.MILLISECONDS.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
