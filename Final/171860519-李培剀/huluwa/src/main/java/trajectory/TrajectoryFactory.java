package trajectory;

import creature.Creature;
import javafx.scene.control.TextArea;

public class TrajectoryFactory {

    private final Creature attacker;

    public TrajectoryFactory(Creature attacker) { this.attacker = attacker; }

    public Trajectory createTrajectory(Creature target, boolean isGrandpaDead, TextArea ta) {
        switch (attacker.getClass().getSimpleName()) {
            case "Huluwa": return new HuluwaTrajectory(attacker, target, isGrandpaDead, ta);
            case "Scorpion": return new ScorpionTrajectory(attacker, target, isGrandpaDead, ta);
            case "Creep": return new CreepTrajectory(attacker, target, isGrandpaDead, ta);
            case "Snake": return new SnakeTrajectory(attacker, target, isGrandpaDead, ta);
            default: return null;
        }
    }
}
