package creature;

import battlefield.BattleField;
import javafx.scene.image.Image;

import java.util.concurrent.locks.Lock;

import static utils.Config.*;

public class Scorpion extends Louluo {
    public Scorpion(String n, BattleField bf, Lock lock) {
        super(n, bf, 0, lock);
        force = SCORPION_FORCE;
    }

    @Override
    void setImage() {
        image = new Image("image/" + name+ ".png");
    }
}
