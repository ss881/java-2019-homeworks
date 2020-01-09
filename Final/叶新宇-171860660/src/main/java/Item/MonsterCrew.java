package Item;

import java.util.ArrayList;
import BattleField.Block;
import Property.Position;
import Tactic.*;

public class MonsterCrew {
    private ArrayList<Lackey> lackeys = new ArrayList<Lackey>();
    Scorpion scorpion;
    Snake snake;
    private static int aliveCount = 10;
    public MonsterCrew() {
        aliveCount = 10;
        for(int i = 0; i < 8; i++)
            lackeys.add(new Lackey());
        scorpion = new Scorpion();
        snake = new Snake();
        scorpion.setPosition(15 * 60,3 * 60);
        snake.setPosition(15 * 60,5 * 60);
    }

    public static boolean allDead() {
        synchronized (MonsterCrew.class) {
            return (aliveCount == 0);
        }
    }

    public static void die() {
        synchronized (MonsterCrew.class) {
            aliveCount--;
        }
    }

    public String changeTactic(TacticMaker tm, Block[][] battlefield) {
        String tName = tm.showName();
        Position[] positions = tm.getPos();
        for(Lackey i : lackeys)
            i.setPosition(900 - positions[lackeys.indexOf(i)].getX(), positions[lackeys.indexOf(i)].getY());

        for(Lackey i : lackeys) {
                Position pos = i.getPosition();
                battlefield[pos.getXH()][pos.getYH()].join(i);
        }
        battlefield[15][3].join(scorpion);
        battlefield[15][5].join(snake);
        return tName;
    }

    public ArrayList<Lackey> getLackeys() {
        return lackeys;
    }

    public Snake getSnake() {
        return snake;
    }

    public Scorpion getScorpion() {
        return scorpion;
    }
}
