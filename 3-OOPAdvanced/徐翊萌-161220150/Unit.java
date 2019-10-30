interface leader {
    void cheer();
    void instruction();
}

abstract public class Unit {
    private int x;
    private int y;
    Unit() {
        x = -1;
        y = -1;
    }
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() { return x; }
    public int getY() { return y; }
    public abstract String getName();
}