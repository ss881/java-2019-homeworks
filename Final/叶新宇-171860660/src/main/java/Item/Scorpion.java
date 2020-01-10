package Item;

import javafx.scene.image.Image;
import java.util.concurrent.TimeUnit;
import Behavior.Shoot;
import Property.*;

public class Scorpion extends Lackey implements Shoot, Runnable{
    public Scorpion() {
        this.battlefield = battlefield;
        image = new Image("scorpion.png");
        attack = 70;
        denfense = 40;
        id = Identity.MONSTER;
        hp = maxHP = 700;
    }

    @Override
    public String toString() {
        return "蝎子精";
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public String getStatus() {
        StringBuilder info = new StringBuilder("");
        info.append("SC " + getPosition().toString());
        info.append(" " + getHPRatio());
        return info.toString();
    }

    @Override
    public Bullet shoot() {
        Position bulletPos = new Position(getPosition().getX() - 60, getPosition().getY());
        Bullet bullet = new Bullet(this.toString(), BulletType.STINGER, Direction.LEFT, bulletPos, battlefield);
        return bullet;
    }

    public void run() {
        boolean shootFlag = true;
        while(hp > 0) {
            Position next = nextMove();
            stepNum++;
            if(hp <= 0)
                break;
            fight(next);
            if(shootFlag) {
                Bullet bullet =shoot();
                bulletExecutor.execute(bullet);
                synchronized (bullets) {
                    bullets.add(bullet);
                }
            }
            shootFlag = !shootFlag;
            try{
                synchronized (this) {
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
