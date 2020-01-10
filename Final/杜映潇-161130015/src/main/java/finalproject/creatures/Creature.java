package finalproject.creatures;

import finalproject.utils.GameLogger;
import finalproject.utils.Logger;

public abstract class Creature {
    private static class Position {
        private int x;
        private int y;
    }

    public enum Side {
        CalabashSide, GoblinSide
    }

    private Position position;
    private String name;
    private Side side;
    private boolean alive;

    public Creature(String name, Side side) {
        position = new Position();
        position.x = position.y = -1;
        this.name = name;
        this.side = side;
        alive = true;
    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }

    public void setX(int x) {
        this.position.x = x;
    }

    public void setY(int y) {
        this.position.y = y;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public String getName() {
        return name;
    }

    public void refresh() {
        alive = true;
    }

    public void kill() {
        alive = false;
        System.out.println(this + " is killed at (" + position.x + ", " + position.y + ")");
        Logger logger = GameLogger.getInstance(null);
        if (logger != null) {
            logger.add_log(this + "_KILLED_" + position.x + "_" + position.y + "\n");
        }
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public String toString() {
        return name;
    }

    public void to(int newX, int newY) {
        System.out.println(name + ": " + "(" + position.x + "," + position.y + ")" + "->" + "(" + newX + "," + newY + ")");
        Logger logger = GameLogger.getInstance(null);
        if (logger != null) {
            logger.add_log(name + "_FROM_" + position.x + "_" + position.y + "_TO_" + newX + "_" + newY + "\n");
        }
        position.x = newX;
        position.y = newY;
    }
}
