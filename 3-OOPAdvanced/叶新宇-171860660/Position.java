import javafx.util.*;
import java.util.*;

public class Position {
        private int posX;
        private int posY;

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

    /*
    public Pair<Integer, Integer> getPos() {
        return new Pair<>(posX, posY);
    }
    */

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
}
