package Item;

import Behavior.Buff;
import javafx.scene.image.Image;
import java.util.concurrent.TimeUnit;
import Property.*;


public class Snake extends Lackey implements Buff, Runnable {
    public Snake() {
        this.battlefield = battlefield;
        image = new Image("snake.png");
        attack = 40;
        denfense = 40;
        id = Identity.MONSTER;
        hp = maxHP = 700;
    }

    @Override
    public String toString() {
        return "蛇精";
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public String getStatus() {
        StringBuilder info = new StringBuilder("");
        info.append("SN " + getPosition().toString());
        info.append(" " + getHPRatio());
        return info.toString();
    }

    public void buff(){
        int i = getPosition().getXH();
        int j = getPosition().getYH();
        synchronized (battlefield) {
            for(int m = i - 2; m <= i + 2; m++) {
                for(int n = j - 2; n <= j + 2; n++) {
                    if(m > 0 && m < 16 && n > 0 && n < 9) {
                        Creature creature = battlefield[m][n].getHolder();
                        if(creature instanceof Lackey) {
                            synchronized (creature) {
                                ((Lackey) creature).getCure();
                            }
                        }
                    }
                }
            }
        }
    }

    public void run() {
        while(hp > 0) {
            buff();
            Position next = nextMove();
            stepNum++ ;

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
