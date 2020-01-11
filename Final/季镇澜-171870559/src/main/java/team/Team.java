package team;

import creature.Creature;
import javafx.scene.image.Image;

import java.util.ArrayList;

public interface Team {
    void arrange();
    boolean checkLost();
    void setTeamImage();
    ArrayList<Creature> getAllCreature();
    Image showImage();
}
