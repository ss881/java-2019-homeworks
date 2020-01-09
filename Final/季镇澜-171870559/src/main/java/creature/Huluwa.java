package creature;

import battlefield.BattleField;
import javafx.scene.image.Image;

import java.util.concurrent.locks.Lock;

import static utils.Config.*;

public class Huluwa extends Creature {
    private int rank;

    Huluwa(String n, BattleField bf, int rank, Lock lock) {
        super(n, bf,lock);
        this.rank = rank;
        String url = "image/" + name + ".png";
        image = new Image(url);
        speed = HULU_SPEED;
        force = HULU_FORCE;
        isGood = true;
    }
}
