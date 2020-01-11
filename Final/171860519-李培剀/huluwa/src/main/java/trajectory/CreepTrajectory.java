package trajectory;

import creature.Creature;
import javafx.scene.control.TextArea;

public class CreepTrajectory extends Trajectory {

    CreepTrajectory(Creature attacker, Creature target, boolean isGrandpaDead, TextArea ta) {
        super(attacker, target, isGrandpaDead, ta);
        this.x = attacker.getPosition().getX() + attacker.getPosition().getImageSize() * 0.10;
        this.y = attacker.getPosition().getY() + attacker.getPosition().getImageSize() * 0.40;
    }

    @Override
    public String toString() { return getClass().getSimpleName() + "("  + x + "," + y + ")"; }

}
