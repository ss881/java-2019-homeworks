package creature;

import battlefield.BattleField;
import javafx.scene.image.Image;

import java.util.concurrent.locks.Lock;

import static utils.Config.*;

public class Louluo extends Creature {
    int rank;

    public Louluo(String n, BattleField bf, int rank, Lock lock) {
        super(n, bf, lock);
        this.rank = rank;
        speed = GENERAL_SPEED;
        force = LOULUO_FORCE;
        isGood = false;
    }

    void setImage(){
        image = new Image("image/" + name.substring(1) + ".png");
    }
}
