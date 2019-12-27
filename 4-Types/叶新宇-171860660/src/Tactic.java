import javafx.util.*;
import java.util.*;

public class Tactic {
    Position refpoint;
    int number;

    Tactic(Position p, int n) {
        refpoint = p;
        number = n;
    }

    public ArrayList<Position> ChangShe() {
        ArrayList<Position> points;
        points = new ArrayList<>();
        points.add(refpoint);
        for (int i = 1; i < number; i++)
            points.add(new Position(refpoint.getPosX() + i, refpoint.getPosY()));
        return points;
    }

    public ArrayList<Position> HeYi() {
        ArrayList<Position> points;
        points = new ArrayList<>();
        points.add(refpoint);
        for(int i = 1; i <= number / 2; i++) {
            points.add(new Position(refpoint.getPosX() - i, refpoint.getPosY() + i));
        }
        for(int j = number / 2 + 1; j < number; j++) {
            points.add(new Position(refpoint.getPosX() + j - number / 2, refpoint.getPosY() + j - number / 2));
        }
        return points;
    }

    public ArrayList<Position> YanXing() {
        ArrayList<Position> points;
        points = new ArrayList<>();
        points.add(refpoint);
        for (int i = 1; i < number; i++)
            points.add(new Position(refpoint.getPosX() - i, refpoint.getPosY() + i));
        return points;
    }

    public ArrayList<Position> ChongE() {
        ArrayList<Position> points;
        points = new ArrayList<>();
        points.add(refpoint);
        for (int i = 1; i < number; i++)
            points.add(new Position(refpoint.getPosX() + i, refpoint.getPosY() + (i % 2)));
        return points;
    }

    public ArrayList<Position> FangYuan() {
        ArrayList<Position> points;
        points = new ArrayList<>();
        points.add(refpoint);
        points.add(new Position(refpoint.getPosX() + 1, refpoint.getPosY() + 1));
        points.add(new Position(refpoint.getPosX() - 1, refpoint.getPosY() + 1));
        points.add(new Position(refpoint.getPosX() + 2, refpoint.getPosY() + 2));
        points.add(new Position(refpoint.getPosX() - 2, refpoint.getPosY() + 2));
        points.add(new Position(refpoint.getPosX() + 1, refpoint.getPosY() + 3));
        points.add(new Position(refpoint.getPosX() - 1, refpoint.getPosY() + 3));
        return points;
    }

    public ArrayList<Position> FengShi() {
        ArrayList<Position> points;
        points = new ArrayList<>();
        points.add(refpoint);
        points.add(new Position(refpoint.getPosX() + 1, refpoint.getPosY() + 1));
        points.add(new Position(refpoint.getPosX() - 1, refpoint.getPosY() + 1));
        points.add(new Position(refpoint.getPosX() + 2, refpoint.getPosY() + 2));
        points.add(new Position(refpoint.getPosX() - 2, refpoint.getPosY() + 2));
        points.add(new Position(refpoint.getPosX(), refpoint.getPosY() + 1));
        points.add(new Position(refpoint.getPosX(), refpoint.getPosY() + 2));
        return points;
    }

    public ArrayList<Position> YanYue() {
        ArrayList<Position> points;
        points = new ArrayList<>();
        points.add(refpoint);
        points.add(new Position(refpoint.getPosX() + 1, refpoint.getPosY() + 1));
        points.add(new Position(refpoint.getPosX() + 2, refpoint.getPosY() + 1));
        points.add(new Position(refpoint.getPosX() + 3, refpoint.getPosY() + 1));
        points.add(new Position(refpoint.getPosX() + 4, refpoint.getPosY()));
        points.add(new Position(refpoint.getPosX() + 1, refpoint.getPosY() + 2));
        points.add(new Position(refpoint.getPosX() + 3, refpoint.getPosY() + 2));
        return points;
    }

    public ArrayList<Position> YuLin() {
        ArrayList<Position> points;
        points = new ArrayList<>();
        points.add(refpoint);
        points.add(new Position(refpoint.getPosX() + 1, refpoint.getPosY() + 1));
        points.add(new Position(refpoint.getPosX() - 1, refpoint.getPosY() + 1));
        points.add(new Position(refpoint.getPosX() + 2, refpoint.getPosY() + 2));
        points.add(new Position(refpoint.getPosX() - 2, refpoint.getPosY() + 2));
        points.add(new Position(refpoint.getPosX() + 1, refpoint.getPosY() + 2));
        points.add(new Position(refpoint.getPosX() - 1, refpoint.getPosY() + 2));
        return points;
    }
}
