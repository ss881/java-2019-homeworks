public class Creature {
    private int x;
    private int y;
    private String name;

    public Creature(String name) {
        x = y = -1;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return name;
    }

    public void to(int newX, int newY) {
        System.out.println(name + ": " + "(" + x + "," + y + ")" + "->" + "(" + newX + "," + newY + ")");
        x = newX;
        y = newY;
    }
}
