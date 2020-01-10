package Item;

import javafx.scene.image.Image;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import Behavior.*;
import Property.*;

public class Grandfather extends Creature implements Buff, Runnable {
    public Grandfather() {
        this.battlefield = battlefield;
        image = new Image("grandfather.png");
        attack = 20;
        denfense = 20;
        hp = maxHP = 500;
        id = Identity.CALABASH;
    }

    @Override
    public String toString() {
        return "爷爷";
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public String getStatus() {
        StringBuilder status = new StringBuilder("");
        status.append("G " + getPosition().toString());
        status.append(" " + getHPRatio());
        return status.toString();
    }

    public void buff(){
        int i = getPosition().getXH();
        int j = getPosition().getYH();
        synchronized (battlefield) {
            for(int m = i - 2; m <= i + 2; m++) {
                for(int n = j - 2; n <= j + 2; n++) {
                    if(m > 0 && m < 16 && n > 0 && n < 9) {
                        Creature creature = battlefield[m][n].getHolder();
                        if(creature instanceof CalabashBoy) {
                            synchronized (creature) {
                                ((CalabashBoy) creature).getBuff();
                            }
                        }
                    }
                }
            }
        }
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
        while(hp > 0) {
            buff();
            Position next = nextMove();
            stepNum++;
            if(hp <= 0)
                break;
            fight(next);
            try{
                TimeUnit.MILLISECONDS.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
