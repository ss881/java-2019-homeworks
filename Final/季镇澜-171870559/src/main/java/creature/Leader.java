package creature;

import battlefield.BattleField;
import battlefield.Position;
import javafx.scene.image.Image;

import java.util.Random;
import java.util.concurrent.locks.Lock;

import static utils.Config.GENERAL_SPEED;
import static utils.Config.LEADER_FORCE;

public class Leader extends Creature {
    public Leader(String n, BattleField bf, boolean isGood, Lock lock) {
        super(n, bf, lock);
        speed = GENERAL_SPEED;
        force = LEADER_FORCE;
        this.isGood = isGood;
    }

    public void loadImage()
    {
        String url = "image/" + name + ".png";
        image = new Image(url);
    }

    //   领袖只能左右横跳
    @Override
    public Position chooseDirection() {
        Random random = new Random();
        int choice = random.nextInt(2);
        Position ret = new Position();
        switch (choice) {
            case 0:
                ret = new Position(0, -1);
                break;
            case 1:
                ret = new Position(0, 1);
                break;
        }
        return ret;
    }
}
