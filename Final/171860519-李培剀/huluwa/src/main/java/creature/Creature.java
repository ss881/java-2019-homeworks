package creature;

import ground.*;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import org.dom4j.Element;
import trajectory.Trajectory;
import trajectory.TrajectoryFactory;

import java.util.concurrent.TimeUnit;

public abstract class Creature implements Runnable {

    protected TextArea ta;

    protected final Image image;
    protected final Image tombImage;
    protected final Ground ground;
    protected final TrajectoryFactory trajectoryFactory;

    protected Position position;
    protected boolean isDead;
    protected boolean canAttack;

    protected double curHealth = 100;
    protected double maxHealth = 100;
    protected double damage = 10;
    protected double convergingBonus = 0.10;

    Creature(Ground ground, String imageName, String tombImageName) {
        this.image = new Image(imageName);
        this.tombImage = new Image(tombImageName);
        this.ground = ground;
        this.position = null;
        this.isDead = false;
        this.canAttack = true;
        this.trajectoryFactory = new TrajectoryFactory(this);
        this.ta = ground.getBattleLogArea();
    }

    /* inner methods */
    protected void setAttributes(Element ele_thisClass) {
        if (ele_thisClass != null) {
            this.damage = Double.parseDouble(ele_thisClass.elementText("damage"));
            this.convergingBonus = Double.parseDouble(ele_thisClass.elementText("convergingBonus"));
            this.maxHealth = Double.parseDouble(ele_thisClass.elementText("maxHealth"));
            this.curHealth = this.maxHealth;
        }
    }

    protected boolean isEnemyTo(Creature creature) {
        if (this instanceof Good)
            return creature instanceof Bad;
        else
            return creature instanceof Good;
    }

    protected void die() {
        isDead = true;
        position.addDeadCreature();
        System.out.println(this + " dead");
        ta.appendText(this + " dead\n");
    }

    protected void moveForward(Creature dst) {
        if (!ground.isMovable(this, dst))
            return;
        /* 寻路原则一：当同列有敌人时，向敌人的方向上移或下移。若未移动，进入原则二。 */
        if (this instanceof Good) {
            if (this.position.getRow() <= 7) {
                for (int i = 0; i < 16; i++) {
                    if (position.getRow() + i < 16 && ground.getPosition(position.getRow() + i, position.getCol()).getCreature() instanceof Bad) {
                        if (!ground.isOccupied(position.getRow() + 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                            return;
                        }
                    }
                    if (position.getRow() - i >= 0 && ground.getPosition(position.getRow() - i, position.getCol()).getCreature() instanceof Bad) {
                        if (!ground.isOccupied(position.getRow() - 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                            return;
                        }
                    }
                }
            }
            else { // this.position.getRow() > 7
                for (int i = 0; i < 16; i++) {
                    if (position.getRow() - i >= 0 && ground.getPosition(position.getRow() - i, position.getCol()).getCreature() instanceof Bad) {
                        if (!ground.isOccupied(position.getRow() - 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                            return;
                        }
                    }
                    if (position.getRow() + i < 16 && ground.getPosition(position.getRow() + i, position.getCol()).getCreature() instanceof Bad) {
                        if (!ground.isOccupied(position.getRow() + 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                            return;
                        }
                    }
                }
            }
        }
        else { // this instanceof Bad
            if (this.position.getRow() <= 7) {
                for (int i = 0; i < 16; i++) {
                    if (position.getRow() + i < 16 && ground.getPosition(position.getRow() + i, position.getCol()).getCreature() instanceof Good) {
                        if (!ground.isOccupied(position.getRow() + 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                            return;
                        }
                    }
                    else if (position.getRow() - i >= 0 && ground.getPosition(position.getRow() - i, position.getCol()).getCreature() instanceof Good) {
                        if (!ground.isOccupied(position.getRow() - 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                            return;
                        }
                    }
                }
            }
            else { // this.position.getRow() > 7
                for (int i = 0; i < 16; i++) {
                    if (position.getRow() - i >= 0 && ground.getPosition(position.getRow() - i, position.getCol()).getCreature() instanceof Good) {
                        if (!ground.isOccupied(position.getRow() - 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                            return;
                        }
                    }
                    else if (position.getRow() + i < 16 && ground.getPosition(position.getRow() + i, position.getCol()).getCreature() instanceof Good) {
                        if (!ground.isOccupied(position.getRow() + 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                            return;
                        }
                    }
                }
            }
        }
        /* 寻路原则二：若在敌阵前方，则优先向敌后移动；若已在敌后，则优先向最近的敌人上移或下移；若与最近的敌人同列，则向其上移或下移。若未移动，进入原则三。 */
        if (this instanceof Good) {
            if (position.getCol() < dst.position.getCol()) {
                if (ground.isOccupied(position.getRow(), position.getCol() + 1)) {
                    if (position.getRow() < dst.position.getRow()) {
                        if (!ground.isOccupied(position.getRow() + 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                            return;
                        }
                    } else if (position.getRow() > dst.position.getRow()) {
                        if (!ground.isOccupied(position.getRow() - 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                            return;
                        }
                    }
                } else {
                    ground.moveCreatureTo(this, position.getRow(), position.getCol() + 1);
                    return;
                }
            } else if (position.getCol() > dst.position.getCol()) {
                if (position.getRow() < dst.position.getRow()) {
                    if (!ground.isOccupied(position.getRow() + 1, position.getCol())) {
                        ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                        return;
                    }
                }
                else if (position.getRow() > position.getRow()) {
                    if (!ground.isOccupied(position.getRow() - 1, position.getCol())) {
                        ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                        return;
                    }
                }
                if (ground.isOccupied(position.getRow(), position.getCol() - 1)) {
                    if (position.getRow() < dst.position.getRow()) {
                        if (!ground.isOccupied(position.getRow() + 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                            return;
                        }
                    } else if (position.getRow() > position.getRow()) {
                        if (!ground.isOccupied(position.getRow() - 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                            return;
                        }
                    }
                } else {
                    ground.moveCreatureTo(this, position.getRow(), position.getCol() - 1);
                    return;
                }
            } else { // position.getCol() == dst.position.getCol()
                if (position.getRow() < dst.position.getRow()) {
                    if (!ground.isOccupied(position.getRow() + 1, position.getCol())) {
                        ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                        return;
                    }
                } else if (position.getRow() > position.getRow()) {
                    if (!ground.isOccupied(position.getRow() - 1, position.getCol())) {
                        ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                        return;
                    }
                }
            }
        }
        else { // this instanceof Bad
            if (position.getCol() < dst.position.getCol()) {
                if (position.getRow() < dst.position.getRow()) {
                    if (!ground.isOccupied(position.getRow() + 1, position.getCol())) {
                        ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                        return;
                    }
                }
                else if (position.getRow() > position.getRow()) {
                    if (!ground.isOccupied(position.getRow() - 1, position.getCol())) {
                        ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                        return;
                    }
                }
                if (ground.isOccupied(position.getRow(), position.getCol() + 1)) {
                    if (position.getRow() < dst.position.getRow()) {
                        if (!ground.isOccupied(position.getRow() + 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                            return;
                        }
                    } else if (position.getRow() > dst.position.getRow()) {
                        if (!ground.isOccupied(position.getRow() - 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                            return;
                        }
                    }
                } else {
                    ground.moveCreatureTo(this, position.getRow(), position.getCol() + 1);
                    return;
                }
            } else if (position.getCol() > dst.position.getCol()) {
                if (ground.isOccupied(position.getRow(), position.getCol() - 1)) {
                    if (position.getRow() < dst.position.getRow()) {
                        if (!ground.isOccupied(position.getRow() + 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                            return;
                        }
                    } else if (position.getRow() > position.getRow()) {
                        if (!ground.isOccupied(position.getRow() - 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                            return;
                        }
                    }
                } else {
                    ground.moveCreatureTo(this, position.getRow(), position.getCol() - 1);
                    return;
                }
            } else { // position.getCol() == dst.position.getCol()
                if (position.getRow() < dst.position.getRow()) {
                    if (!ground.isOccupied(position.getRow() + 1, position.getCol())) {
                        ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                        return;
                    }
                } else if (position.getRow() > position.getRow()) {
                    if (!ground.isOccupied(position.getRow() - 1, position.getCol())) {
                        ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                        return;
                    }
                }
            }
        }
        /* 寻路原则三：若与敌人同行，且前方是战友，则上下移动。若未移动，进入原则四。 */
        if (this instanceof Good) {
            if (dst.position.getRow() == position.getRow()) {
                if (ground.getPosition(position.getRow(), position.getCol() + 1).getCreature() instanceof Good) {
                    if (position.getRow() <= 7) {
                        if (!ground.isOccupied(position.getRow() - 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                            return;
                        }
                        else if (!ground.isOccupied(position.getRow() + 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                            return;
                        }
                    }
                    else { // position.getRow() > 7
                        if (!ground.isOccupied(position.getRow() + 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                            return;
                        }
                        else if (!ground.isOccupied(position.getRow() - 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                            return;
                        }
                    }
                }
            }
            else if (dst.position.getCol() == position.getCol()) {
                if (!ground.isOccupied(position.getRow(), position.getCol() + 1)) {
                    ground.moveCreatureTo(this, position.getRow(), position.getCol() + 1);
                    return;
                }
            }
        }
        else { // this instanceof Bad
            if (dst.position.getRow() == position.getRow()) {
                if (ground.getPosition(position.getRow(), position.getCol() - 1).getCreature() instanceof Bad) {
                    if (position.getRow() <= 7) {
                        if (!ground.isOccupied(position.getRow() - 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                            return;
                        }
                        else if (!ground.isOccupied(position.getRow() + 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                            return;
                        }
                    }
                    else { // position.getRow() > 7
                        if (!ground.isOccupied(position.getRow() + 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                            return;
                        }
                        else if (!ground.isOccupied(position.getRow() - 1, position.getCol())) {
                            ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                            return;
                        }
                    }
                }
            }
            else if (dst.position.getCol() == position.getCol()) {
                if (!ground.isOccupied(position.getRow(), position.getCol() - 1)) {
                    ground.moveCreatureTo(this, position.getRow(), position.getCol() - 1);
                    return;
                }
            }
        }
        /* 若与敌人同列，保持不动。 */
        if (this instanceof Good) {
            for (int i = 1; i < 16; i++) {
                if (position.getRow() - i >= 0) {
                    if (ground.getPosition(position.getRow() - i, position.getCol()).getCreature() instanceof Bad)
                        return;
                }
                if (position.getRow() + i < 16) {
                    if (ground.getPosition(position.getRow() + i, position.getCol()).getCreature() instanceof Bad)
                        return;
                }
            }
        }
        else { // this instanceof Bad
            for (int i = 1; i < 16; i++) {
                if (position.getRow() - i >= 0) {
                    if (ground.getPosition(position.getRow() - i, position.getCol()).getCreature() instanceof Good)
                        return;
                }
                if (position.getRow() + i < 16) {
                    if (ground.getPosition(position.getRow() + i, position.getCol()).getCreature() instanceof Good)
                        return;
                }
            }
        }
        /* 若与敌人距离小于等于2，保持不动。 */
        if (ground.getDistance(this, dst) <= 2)
            return;
        /* 寻路原则四：若尚未移动，且可以移动，随机上移或下移。 */
        if (!ground.isOccupied(position.getRow() - 1, position.getCol()) && !ground.isOccupied(position.getRow() + 1, position.getCol())) {
            int UpOrDown = (int) (Math.random() * 2);
            if (UpOrDown == 0) {
                ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                return;
            }
            else {
                ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                return;
            }
        }
        else {
            if (!ground.isOccupied(position.getRow() - 1, position.getCol())) {
                ground.moveCreatureTo(this, position.getRow() - 1, position.getCol());
                return;
            }
            else if (!ground.isOccupied(position.getRow() + 1, position.getCol())) {
                ground.moveCreatureTo(this, position.getRow() + 1, position.getCol());
                return;
            }
        }
        return;
    }

    protected Creature getTarget() {
        Creature target = null;
        double minCurHealth = 1000.1;
        if (ground.getPosition(position.getRow(), position.getCol() + 1) != null &&
                ground.getPosition(position.getRow(), position.getCol() + 1).getCreature() != null &&
                ground.getPosition(position.getRow(), position.getCol() + 1).getCreature().isEnemyTo(this)) {
            if (ground.getPosition(position.getRow(), position.getCol() + 1).getCreature().getCurHealth() < minCurHealth) {
                target = ground.getPosition(position.getRow(), position.getCol() + 1).getCreature();
                minCurHealth = target.getCurHealth();
            }
        }
        if (ground.getPosition(position.getRow() - 1, position.getCol()) != null &&
                ground.getPosition(position.getRow() - 1, position.getCol()).getCreature() != null &&
                ground.getPosition(position.getRow() - 1, position.getCol()).getCreature().isEnemyTo(this)) {
            if (ground.getPosition(position.getRow() - 1, position.getCol()).getCreature().getCurHealth() < minCurHealth) {
                target = ground.getPosition(position.getRow() - 1, position.getCol()).getCreature();
                minCurHealth = target.getCurHealth();
            }
        }
        if (ground.getPosition(position.getRow(), position.getCol() - 1) != null &&
                ground.getPosition(position.getRow(), position.getCol() - 1).getCreature() != null &&
                ground.getPosition(position.getRow(), position.getCol() - 1).getCreature().isEnemyTo(this)) {
            if (ground.getPosition(position.getRow(), position.getCol() - 1).getCreature().getCurHealth() < minCurHealth) {
                target = ground.getPosition(position.getRow(), position.getCol() - 1).getCreature();
                minCurHealth = target.getCurHealth();
            }
        }
        if (ground.getPosition(position.getRow() + 1, position.getCol()) != null &&
                ground.getPosition(position.getRow() + 1, position.getCol()).getCreature() != null &&
                ground.getPosition(position.getRow() + 1, position.getCol()).getCreature().isEnemyTo(this)) {
            if (ground.getPosition(position.getRow() + 1, position.getCol()).getCreature().getCurHealth() < minCurHealth) {
                target = ground.getPosition(position.getRow() + 1, position.getCol()).getCreature();
                minCurHealth = target.getCurHealth();
            }
        }
        return target;
    }

    protected void attack() {
        Creature target = getTarget();
        if (target == null)
            return;
        Trajectory trajectory = trajectoryFactory.createTrajectory(target, ground.getGrandpa().isDead, ta);
        if (trajectory == null)
            return;
        ground.addTrajectory(trajectory);
        /* unfinished */
    }

    /* public methods */

    public void getDamaged(double damage) {
        if (curHealth - damage > 0) {
            curHealth -= damage;
        }
        else {
            curHealth = 0;
            die();
        }
    }

    public double getConvergingCoefficient() { // 计算（作为被攻击者受到的）夹击加成。共边有全额加成，共顶点不共边有半额加成。
        double coefficient = 1;
        if (ground.getPosition(position.getRow() - 1, position.getCol()) != null &&
                ground.getPosition(position.getRow() - 1, position.getCol()).getCreature() != null &&
                ground.getPosition(position.getRow() - 1, position.getCol()).getCreature().isEnemyTo(this))
            coefficient *= (1 + ground.getPosition(position.getRow() - 1, position.getCol()).getCreature().convergingBonus);

        if (ground.getPosition(position.getRow() + 1, position.getCol()) != null &&
                ground.getPosition(position.getRow() + 1, position.getCol()).getCreature() != null &&
                ground.getPosition(position.getRow() + 1, position.getCol()).getCreature().isEnemyTo(this))
            coefficient *= (1 + ground.getPosition(position.getRow() + 1, position.getCol()).getCreature().convergingBonus);

        if (ground.getPosition(position.getRow(), position.getCol() - 1) != null &&
                ground.getPosition(position.getRow(), position.getCol() - 1).getCreature() != null &&
                ground.getPosition(position.getRow(), position.getCol() - 1).getCreature().isEnemyTo(this))
            coefficient *= (1 + ground.getPosition(position.getRow(), position.getCol() - 1).getCreature().convergingBonus);

        if (ground.getPosition(position.getRow(), position.getCol() + 1) != null &&
                ground.getPosition(position.getRow(), position.getCol() + 1).getCreature() != null &&
                ground.getPosition(position.getRow(), position.getCol() + 1).getCreature().isEnemyTo(this))
            coefficient *= (1 + ground.getPosition(position.getRow(), position.getCol() + 1).getCreature().convergingBonus);


        if (ground.getPosition(position.getRow() - 1, position.getCol() + 1) != null &&
                ground.getPosition(position.getRow() - 1, position.getCol() + 1).getCreature() != null &&
                ground.getPosition(position.getRow() - 1, position.getCol() + 1).getCreature().isEnemyTo(this))
            coefficient *= (1 + 0.5 * ground.getPosition(position.getRow() - 1, position.getCol() + 1).getCreature().convergingBonus);

        if (ground.getPosition(position.getRow() + 1, position.getCol() + 1) != null &&
                ground.getPosition(position.getRow() + 1, position.getCol() + 1).getCreature() != null &&
                ground.getPosition(position.getRow() + 1, position.getCol() + 1).getCreature().isEnemyTo(this))
            coefficient *= (1 + 0.5 * ground.getPosition(position.getRow() + 1, position.getCol() + 1).getCreature().convergingBonus);

        if (ground.getPosition(position.getRow() + 1, position.getCol() - 1) != null &&
                ground.getPosition(position.getRow() + 1, position.getCol() - 1).getCreature() != null &&
                ground.getPosition(position.getRow() + 1, position.getCol() - 1).getCreature().isEnemyTo(this))
            coefficient *= (1 + 0.5 * ground.getPosition(position.getRow() + 1, position.getCol() - 1).getCreature().convergingBonus);

        if (ground.getPosition(position.getRow() - 1, position.getCol() - 1) != null &&
                ground.getPosition(position.getRow() - 1, position.getCol() - 1).getCreature() != null &&
                ground.getPosition(position.getRow() - 1, position.getCol() - 1).getCreature().isEnemyTo(this))
            coefficient *= (1 + 0.5 * ground.getPosition(position.getRow() - 1, position.getCol() - 1).getCreature().convergingBonus);

        return coefficient;
    }

    @Override
    public void run() {
        while(true) {
            int sleepTime = 500; // ms
            try {
                TimeUnit.MILLISECONDS.sleep(sleepTime);
                synchronized (ground) {
                    if (isDead || !ground.isBattling())
                        break;
                    moveForward(ground.getNearestEnemy(this));
                    if (canAttack)
                        attack();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    @Override
    public String toString() { return getClass().getSimpleName() + "(" + getPosition().getRow() + "," + getPosition().getCol() + ")"; }

    /* getter and setter */

    public boolean isDead() { return isDead; }

    public void setCanAttack(boolean canAttack) { this.canAttack = canAttack; }

    public Image getImage() { return image; }

    public Image getTombImage() { return tombImage; }

    public Position getPosition() { return position; }

    public void setPosition(Position position) { this.position = position; }

    public double getCurHealth() { return curHealth; }

    public double getMaxHealth() { return maxHealth; }

    public double getDamage() { return damage; }

    public double getConvergingBonus() { return convergingBonus; }
}