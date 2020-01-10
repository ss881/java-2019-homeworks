package trajectory;

import creature.*;
import ground.Position;
import javafx.geometry.Point2D;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;

public abstract class Trajectory {

    protected static final int IMAGE_SIZE = 32;
    protected TextArea ta;

    protected final Image image;
    protected final Creature attacker;
    protected final Creature target;
    protected final Position targetPosition;
    protected final boolean isGrandpaDead;
    protected final double attackerDamageCoefficient; // 攻击者伤害补正。每丢失3点血，damage减少1%
    protected final double targetDamageCoefficient; // 被攻击者伤害补正。
    protected final double convergingCoefficient; // 夹击加成。
    protected final double grandpaBuff;

    protected double attackerRealDamage;
    protected double targetRealDamage;
    protected double speed; // 每秒通过的格子数
    protected double x, y;
    protected int liveTime;

    protected double finalDamage;

    Trajectory(Creature attacker, Creature target, boolean isGrandpaDead, TextArea ta) {
        this.ta = ta;
        this.image = new Image(attacker.getClass().getSimpleName() + "trajectory.png");
        this.attacker = attacker;
        this.target = target;
        this.targetPosition = target.getPosition();
        this.isGrandpaDead = isGrandpaDead;
        this.attackerDamageCoefficient = (attacker.getCurHealth() / attacker.getMaxHealth() + 2) / 3;
        this.targetDamageCoefficient = (target.getCurHealth() / target.getMaxHealth() + 2) / 3;
        this.convergingCoefficient = target.getConvergingCoefficient(this.attacker);
        this.grandpaBuff = 0.1;
        this.speed = 10;
        this.liveTime = 0;
        if (attacker instanceof Good)
            this.attackerRealDamage = attacker.getDamage() * attackerDamageCoefficient * convergingCoefficient * (1 + (isGrandpaDead ? 0 : grandpaBuff));
        else
            this.attackerRealDamage = attacker.getDamage() * attackerDamageCoefficient;
        if (target instanceof Good)
            this.targetRealDamage = target.getDamage() * targetDamageCoefficient * (1 + (isGrandpaDead ? 0 : grandpaBuff));
        else
            this.targetRealDamage = target.getDamage() * targetDamageCoefficient;
        if (attackerRealDamage >= targetRealDamage)
            finalDamage = (24 + 12 * Math.random()) * (Math.pow(attackerRealDamage / targetRealDamage + 3, 4) / 512 + 0.5);
        else
            finalDamage = (24 + 12 * Math.random()) / (Math.pow(targetRealDamage / attackerRealDamage + 3, 4) / 512 + 0.5);
        if (target.getCurHealth() <= finalDamage)
            target.setCanAttack(false);
    }

    public void move(int millisecond) { // 刷新间隔
        Point2D vector = new Point2D(target.getPosition().getX() - attacker.getPosition().getX(), target.getPosition().getY() - attacker.getPosition().getY());
        vector = vector.normalize();
        Point2D increment = vector.multiply(IMAGE_SIZE * speed * (millisecond * 0.001));
        x += increment.getX();
        y += increment.getY();
        liveTime += millisecond;
    }

    public void doDamage() {
        System.out.println(attacker.toString() + " deals " + String.format("%.2f", finalDamage) + " damage to " + target.toString());
        ta.appendText(attacker.toString() + " deals " + String.format("%.2f", finalDamage) + " damage to " + target.toString() + "\n");
        target.getDamaged(finalDamage);
    }

    /* getter and setter */

    public Image getImage() { return image; }

    public Creature getAttacker() { return attacker; }

    public Creature getTarget() { return target; }

    public Position getTargetPosition() { return targetPosition; }

    public double getSpeed() { return speed; }

    public int getLiveTime() { return liveTime; }

    public double getX() { return x; }

    public double getY() { return y; }

}
