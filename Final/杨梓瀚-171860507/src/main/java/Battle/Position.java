package Battle;

import Creature.Creature;

public class Position<T extends Creature> {
    private T holder;

    private int x;

    private int y;

    public Position(T h, int x, int y){
        holder = h;
        this.x = x;
        this.y = y;
    }

    public T getHolder() {
        return holder;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
}
