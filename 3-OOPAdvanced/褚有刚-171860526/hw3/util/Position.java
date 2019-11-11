package hw3.util;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isValid() {
        return x != -1 && y != -1;
    }

    // for debug
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
