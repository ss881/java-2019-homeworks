package Item;

import java.util.ArrayList;
import java.util.Arrays;
import BattleField.Block;
import Property.CalabashProperty;
import Property.Position;
import Tactic.*;

public class CalabashCrew {
    private ArrayList<CalabashBoy> calabashbrothers;
    private Grandfather grandfather;
    private static int aliveCount = 8;
    public CalabashCrew() {
        aliveCount = 8;
        calabashbrothers = new ArrayList<CalabashBoy>();
        calabashbrothers.addAll(Arrays.asList( new CalabashBoy(CalabashProperty.RED),
                new CalabashBoy(CalabashProperty.ORANGE), new CalabashBoy(CalabashProperty.YELLOW),
                new CalabashBoy(CalabashProperty.GREEN), new CalabashBoy(CalabashProperty.BLUE),
                new CalabashBoy(CalabashProperty.CYAN), new CalabashBoy(CalabashProperty.PURPLE)));
        grandfather = new Grandfather();
    }

    public static boolean allDead() {
        synchronized (CalabashCrew.class) {
            return (aliveCount == 0);
        }
    }

    public static void die() {
        synchronized (CalabashCrew.class) {
            aliveCount--;
        }
    }

    public String changeTactic(TacticMaker tm, Block[][] battlefield) {
        String tName = tm.showName();
        Position[] positions = tm.getPos();
        for(CalabashBoy i : calabashbrothers)
            i.setPosition(positions[calabashbrothers.indexOf(i)].getX(), positions[calabashbrothers.indexOf(i)].getY());
        grandfather.setPosition(positions[7].getX(),positions[7].getY());

        for(CalabashBoy i : calabashbrothers) {
            Position pos = i.getPosition();
            battlefield[pos.getXH()][pos.getYH()].join(i);
        }
        Position pos = grandfather.getPosition();
        battlefield[pos.getXH()][pos.getYH()].join(grandfather);
        return tName;
    }

    public ArrayList<CalabashBoy> getCalabashbrothers() {
        return calabashbrothers;
    }

    public Grandfather getGrandfather() {
        return grandfather;
    }
}
