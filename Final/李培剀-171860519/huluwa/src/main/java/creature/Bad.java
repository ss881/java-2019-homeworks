package creature;

import ground.*;
import javafx.scene.control.TextArea;

public abstract class Bad extends Creature {

    Bad(Ground ground, String imageName) { super(ground, imageName, "badtomb.png"); }

    @Override
    public String toString() { return this.getClass().getName(); }
}
