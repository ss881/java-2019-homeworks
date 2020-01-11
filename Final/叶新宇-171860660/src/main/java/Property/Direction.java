package Property;

public enum Direction {
    LEFT(-15,0),
    RIGHT(15,0),
    RIGHT_DOWN(9,12),
    RIGHT_UP(12,-9);

    final private int deltaX;
    final private int deltaY;

    Direction(int x, int y) {
        deltaX = x;
        deltaY = y;
    }

    public int getDeltaX() {
        return deltaX;
    }
    public int getDeltaY() {
        return deltaY;
    }
}
