package huluwa.util;

import huluwa.creature.Creature;

public class Position {
    private int x;
    private int y;
    private Creature creature;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        creature = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void clearCreature() {
        creature = null;
    }

    public boolean hasCreature() {
        return creature != null;
    }

    public void setCreature(Creature creature) {
        assert(this.creature == null);
        this.creature = creature;
    }

    // for debug
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
