package battlefield;

import creature.Creature;

public class Position {
    private int x;
    private int y;
    private boolean hasCreature;
    private Creature creature;

    public Position() {
        x = 0;
        y = 0;
        hasCreature = false;
        creature = null;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.hasCreature = false;
        creature = null;
    }

    public Position(Position p) {
        x = p.x;
        y = p.y;
        hasCreature = p.hasCreature;
        creature = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getState() {
        return hasCreature;
    }

    public boolean isEmpty() {
        return !hasCreature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
        hasCreature = true;
    }

    public Creature getCreature() {
        return creature;
    }

    public void clear() {
        hasCreature = false;
        creature = null;
    }
}
