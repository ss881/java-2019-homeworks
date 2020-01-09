package creature;


import battlefield.BattleField;
import battlefield.Position;
import javafx.scene.image.Image;
import record.Record;

import java.util.Random;
import java.util.concurrent.locks.Lock;

import static utils.Config.*;

public abstract class Creature implements Runnable {
    protected String name;
    protected Image image;
    protected Position position;
    protected int life;
    protected int force;
    protected int speed;
    protected Lock lock;
    public BattleField battleField;
    public boolean isGood;
    public boolean isDead;
    public boolean isWait;
    public boolean isBattle;
    public int currentHealth;

    Creature(String n, BattleField bf, Lock lock) {
        name = n;
        isDead = false;
        isWait = false;
        isBattle = false;
        life = LIFE;
        battleField = bf;
        this.lock = lock;
        currentHealth = life;
    }

    public void setPosition(int x, int y) {
        position = new Position(x, y);
        position.setCreature(this);
        battleField.theField[x][y].setCreature(this);
    }

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public Position chooseDirection() {
        Random random = new Random();
        int choice = random.nextInt(5);
        Position ret = new Position();
        switch (choice) {
            case 0:
            case 1:
            case 2:
                if (isGood)
                    ret = new Position(1, 0);
                else
                    ret = new Position(-1, 0);
                break;
            case 3:
                ret = new Position(0, -1);
                break;
            case 4:
                ret = new Position(0, 1);
                break;
        }
        return ret;
    }

    //    进行一次随机判定，1为胜利，0为负
    public int battle(Creature enemy) {
        Random random = new Random();
        int isWin = 1;
        int res = random.nextInt(2);
        isBattle = true;
        enemy.isBattle = true;
        switch (res) {
            case 0:
                currentHealth -= enemy.force;
                break;
            case 1:
                enemy.currentHealth -= force;
                isWin = 0;
                break;
        }
        checkDead();
        enemy.checkDead();
        return isWin;
    }


    //        true为行动过，false为被堵住故放弃行动
    void move() {
        int i = 5;
        while (i > 0) {
            Position direction = chooseDirection();
            int preX = position.getX();
            int preY = position.getY();
            int objX = preX + direction.getX() * speed;
            int objY = preY + direction.getY() * speed;
            if (objX < ROW && objY < COL && objX >= 0 && objY >= 0) {
                Position objPosition = battleField.theField[objX][objY];
                if (objPosition.isEmpty()) {
                    battleField.theField[preX][preY].clear();
                    setPosition(objX, objY);
                    RECORDS.add(new Record("None", name, "Move", "", 1, 1, preX, preY, objX, objY));
                    break;
                } else if (!objPosition.getCreature().isDead && isGood != objPosition.getCreature().isGood) {
                    battle(objPosition.getCreature());
                    int ownLive = checkDead();
                    int enemyLive = objPosition.getCreature().checkDead();
                    RECORDS.add(new Record("None", getName(), "Battle", objPosition.getCreature().getName(), ownLive, enemyLive, preX, preY, objX, objY));
                    break;
                }
            }
            i -= 1;
        }
    }

    public void goDead(){
        isDead = true;
        image = new Image("image/tombstone.png");
    }

    public int checkDead() {
        if (currentHealth <= 0) {
            goDead();
            return 0;
        } else return 1;
    }

    public void run() {

        while (!isDead && !Thread.interrupted()) {
            try {
                synchronized (lock) {
                    if (!isWait) {
                        move();
                        isWait = true;
                        lock.wait();
                    } else
                        continue;
                    // lock.wait();
                }
            } catch (InterruptedException e) {
                System.out.println(name + "excep");
//                e.printStackTrace();
            }

        }
    }
}
