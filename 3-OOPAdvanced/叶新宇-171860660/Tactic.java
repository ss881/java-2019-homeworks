import javafx.util.*;
import java.util.*;

public class Tactic {
    Position refpoint;
    int number;

    Tactic(Position p, int n) {
        refpoint = p;
        number = n;
    }

    public List<Position> ChangShe() {
        List<Position> points;
        points = new ArrayList<>();
        points.add(refpoint);
        for (int i = 1; i < number; i++)
            points.add(new Position(refpoint.getPosX() + i, refpoint.getPosY()));
        return points;
    }

    public List<Position> HeYi() {
        List<Position> points;
        points = new ArrayList<>();
        points.add(refpoint);
        for(int i = 1; i <= number / 2; i++) {
            points.add(new Position(refpoint.getPosX() + i, refpoint.getPosY() + i));
        }
        for(int j = number / 2 + 1; j < number; j++) {
            points.add(new Position(refpoint.getPosX() + number - j - 1, refpoint.getPosY() + j));
        }
        return points;
    }

    public List<Position> YanXing() {
        List<Position> points;
        points = new ArrayList<>();
        points.add(refpoint);
        for (int i = 1; i < number; i++)
            points.add(new Position(refpoint.getPosX() - i, refpoint.getPosY() + i));
        return points;
    }

    public List<Position> ChongE() {
        List<Position> points;
        points = new ArrayList<>();
        points.add(refpoint);
        for (int i = 1; i < number; i++)
            points.add(new Position(refpoint.getPosX() + i, refpoint.getPosY() + (i % 2)));
        return points;
    }

    public List<Position> FangYuan() {
        List<Position> points;
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

    public List<Position> FengShi() {
        List<Position> points;
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

    public List<Position> YanYue() {
        List<Position> points;
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

    public List<Position> YuLin() {
        List<Position> points;
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
