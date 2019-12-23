package Creatures;

public abstract class Creature {
    private static class Position {
        private int x;
        private int y;
    }

    private Position position;
    private String name;

    public Creature(String name) {
        position = new Position();
        position.x = position.y = -1;
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }

    public void to(int newX, int newY) {
        System.out.println(name + ": " + "(" + position.x + "," + position.y + ")" + "->" + "(" + newX + "," + newY + ")");
        position.x = newX;
        position.y = newY;
    }
}
