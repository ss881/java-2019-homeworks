import javafx.util.*;
import java.util.*;

public class Position<T extends Creature> {
    private int posX;
    private int posY;
    private T holder;

    Position(int x, int y) {
        posX = x;
        posY = y;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    private void setPosX(int x) {
        posX = x;
    }
    private void setPosY(int y) {
        posY = y;
    }

    public void setPos(int x, int y) {
        setPosX(x);
        setPosY(y);
    }

    public void setPos(Position p) {
        setPosX(p.getPosX());
        setPosY(p.getPosY());
    }

    public T getHolder() {
        return holder;
    }

    public void setHolder(T h) {
        holder = h;
    }

    public boolean join(BattleField b) {
        if(b.isAvailable(posX, posY)) {
            b.joinBattleField(this);
            return true;
        }
        else
            return false;
    }

    public boolean withdraw(BattleField b) {
        if(!b.isAvailable(posX, posY)) {
            b.withdraw(this);
            return true;
        }
        else
            return false;
    }
}
