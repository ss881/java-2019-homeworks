package creature;

import ground.*;
import javafx.scene.control.TextArea;

public abstract class Good extends Creature {

    Good(Ground ground, String imageName) { super(ground, imageName, "goodtomb.png"); }

    @Override
    public String toString() { return this.getClass().getName(); }

}
