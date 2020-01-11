package trajectory;

import creature.Creature;
import javafx.scene.control.TextArea;

public class HuluwaTrajectory extends Trajectory {

    HuluwaTrajectory(Creature attacker, Creature target, boolean isGrandpaDead, TextArea ta) {
        super(attacker, target, isGrandpaDead, ta);
        this.x = attacker.getPosition().getX() + IMAGE_SIZE * 0.30;
        this.y = attacker.getPosition().getY() + IMAGE_SIZE * 0.10;
    }

    @Override
    public String toString() { return getClass().getSimpleName() + "("  + x + "," + y + ")"; }

}
