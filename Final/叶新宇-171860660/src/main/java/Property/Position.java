package Property;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        x = 0;
        y = 0;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXH() {
        return x / 60;
    }

    public int getYH() {
        return y / 60;
    }

    public void setXH(int xh) {
        x = xh * 60;
    }

    public void setYH(int yh) {
        y = yh * 60;
    }

    public String toString() {
        return "" + x + " " + y;
    }
}
