package Item;

import javafx.scene.image.Image;
import java.util.Queue;
import Property.*;
import BattleField.Block;
import Behavior.Meet;

public abstract class Creature extends Item {
    private Position position = new Position();
    protected Image image;
    protected  int attack;
    protected  int denfense;
    protected  int hp;
    protected  int maxHP;
    protected  Identity id;
    protected int stepNum = 0;
    protected static Block[][] battlefield;
    protected static Queue<Meet> meet;
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDenfense() {
        return denfense;
    }

    public void setDenfense(int denfense) {
        this.denfense = denfense;
    }

    public float getHPRatio() {
        return (float) hp / (float)maxHP;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isDead() {
        return (hp <= 0);
    }

    public void die() {
        hp = 0;
    }

    public void setPosition(int x,int y) {
        position.setX(x);
        position.setY(y);
    }

    public Position getPosition() {
        return position;
    }

    public Identity getId() {
        return id;
    }
    public static void setMeet(Queue<Meet> queue) {
        meet = queue;
    }
    public static void setBattlefield(Block[][] field) {
        battlefield = field;
    }

    public abstract Image getImage();
    public abstract String getStatus();

    Position nextMove() {
        return null;
    };

    public void moveTo(Position pos) {
        battlefield[this.position.getXH()][this.position.getYH()].withdraw();
        battlefield[pos.getXH()][pos.getYH()].join(this);
        this.position.setX(pos.getX());
        this.position.setY(pos.getY());
    }

    public Position moveToCenter() {
        int i = position.getXH();
        int j = position.getYH();
        Position next = new Position(position.getX(), position.getY());
        if(i < 8)
            next.setXH(i + 1);
        else if(i > 8)
            next.setXH(i - 1);
        else if(j < 5)
            next.setYH(j + 1);
        else if(j > 5)
            next.setYH(j - 1);
        else
            next.setXH(i + 1);
        return next;
    }

    protected void fight(Position nextPos) {
        int i = nextPos.getXH();
        int j = nextPos.getYH();
        synchronized (battlefield) {
            if(battlefield[i][j].isEmpty())
                moveTo(nextPos);
            else {
                Creature creature = battlefield[i][j].getHolder();
                if(creature.getId() != id && creature.getId() != Identity.DEAD) {
                    synchronized (meet) {
                        meet.offer(new Meet(this, creature));
                    }
                    DeadCreature dead = new DeadCreature();
                    if(isDead()) {
                        battlefield[getPosition().getXH()][getPosition().getYH()].withdraw();
                        dead.setPosition(getPosition().getX(),getPosition().getY());
                        battlefield[getPosition().getXH()][getPosition().getYH()].join(dead);
                    }
                    else {
                        battlefield[i][j].withdraw();
                        dead.setPosition(nextPos.getX(), nextPos.getY());
                        battlefield[i][j].join(dead);
                    }
                }
            }
        }
    }
}