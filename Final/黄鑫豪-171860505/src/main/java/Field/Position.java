package Field;

import Beings.Creature;

public class Position<T extends Creature> {
    private int posX;
    private int posY;
    private T holder;

    Position(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        holder = null;
    }

    public void setHolder(T holder) {
        if (this.holder != null) {
            System.err.print("Error at Position (" + posX + "," + posY + ")");
            return;
        }
        this.holder = holder;
    }

    public T getHolder() {
        return holder;
    }

    void init() {
        this.holder = null;
    }

    boolean isEmpty() {
        return this.holder == null;
    }
}
