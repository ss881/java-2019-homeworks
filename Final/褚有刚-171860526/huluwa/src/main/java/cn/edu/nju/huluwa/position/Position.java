package cn.edu.nju.huluwa.position;

import cn.edu.nju.huluwa.creature.Creature;

public class Position {
    private int x;
    private int y;
    private Creature creature;
    private Position nextX;
    private Position nextY;
    private Position prevX;
    private Position prevY;

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

    public Creature getCreature() {
        return creature;
    }

    public void clearCreature() {
        creature = null;
    }

    public boolean hasCreature() {
        return creature != null;
    }

    public void setCreature(Creature creature) {
        assert (this.creature == null);
        this.creature = creature;
    }

    public Position getNextX() {
        return nextX;
    }

    public Position getNextY() {
        return nextY;
    }

    public void setNextX(Position nextX) {
        this.nextX = nextX;
    }

    public void setNextY(Position nextY) {
        this.nextY = nextY;
    }

    public Position getPrevX() {
        return prevX;
    }

    public void setPrevX(Position prevX) {
        this.prevX = prevX;
    }

    public Position getPrevY() {
        return prevY;
    }

    public void setPrevY(Position prevY) {
        this.prevY = prevY;
    }

    // for debug
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
