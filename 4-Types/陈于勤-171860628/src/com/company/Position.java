package com.company;

public class Position<T extends Number> {
    private T x, y;
    int path;
    public Position(T x, T y) {
        this.x = x;
        this.y = y;
        path = 0;
    }
    public Position(T x, T y, int path) {
        this.x = x;
        this.y = y;
        this.path = path;
    }

    public void setPosition(T x, T y) {
        this.x = x;
        this.y = y;
    }
    public void setPosition(Position<T> position) {
        x = position.x;
        y = position.y;
    }
        public void setX(T x) {
        this.x = x;
    }
    public void setY(T y) {
        this.y = y;
    }
    public void setPath(int path) { this.path = path;}

    public void getPosition(T x, T y) {
        x = this.x;
        y = this.y;
    }
    public T getX() {
        return x;
    }
    public T getY() {
        return y;
    }

    public  Position<Integer> getIntegerPosition() {
        return new Position<>((Integer)x, (Integer)y);
    }
    public int getPath() { return path;}

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    public boolean isEqual(Position<T> position) {
        return x.equals(position.x) && y.equals(position.y);
    }
}
