package trajectory;

import creature.Creature;
import javafx.geometry.Point2D;
import javafx.scene.control.TextArea;

public class SnakeTrajectory extends Trajectory {

    SnakeTrajectory(Creature attacker, Creature target, boolean isGrandpaDead, TextArea ta) {
        super(attacker, target, isGrandpaDead, ta);
        this.attackerRealDamage = attacker.getDamage();
        this.x = attacker.getPosition().getX() + attacker.getPosition().getImageSize() * 0.10;
        this.y = attacker.getPosition().getY() + attacker.getPosition().getImageSize() * 0.30;
    }

    @Override
    public void move(int millisecond) {
        Point2D vector = new Point2D(target.getPosition().getX() - attacker.getPosition().getX(), 0);
        vector = vector.normalize();
        Point2D increment = vector.multiply(IMAGE_SIZE * speed * (millisecond * 0.001));
        x += increment.getX();
        y += increment.getY();
        liveTime += millisecond;
    }

    @Override
    public void doDamage() {
        finalDamage = attackerRealDamage;
        System.out.println(attacker.toString() + " deals " + String.format("%.2f", finalDamage) + " damage to " + target.toString());
        ta.appendText(attacker.toString() + " deals " + String.format("%.2f", finalDamage) + " damage to " + target.toString() + "\n");
        target.getDamaged(finalDamage);
    }

    @Override
    public String toString() { return getClass().getSimpleName() + "("  + x + "," + y + ")"; }

}
