package top.stqstq.maven_huluwa.being;

import top.stqstq.maven_huluwa.battlefiled.*;

public class Being implements IBeing {
    private int rank = 0;
    private int fightAbility = 0;
    private boolean alive = false;
    private final int combatComp = 0;//0 no combat 1 good 2 evil
    private String name = "Being";

    public void print() {
        System.out.print('-');
    }

    public void walkTo(int x, int y) {
        int x1 = BattleField.getXByNo(rank);
        int y1 = BattleField.getYByNo(rank);
        if (x1 == x && y1 == y)
            return;
        if (x1 == -1) {
            //System.out.println("failed to find myself");
            return;
        }
        if (x1 != x && y1 != y) {
            //System.out.println("can't walk obliquely");
            return;
        }
        if (BattleField.getNo(x, y) != 0) {
            //System.out.println("target position is not empty");
            return;
        }
        BattleField.setField(x, y, rank);
        BattleField.setField(x1, y1, 0);
    }

    public void run() {
        ;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public int getFightAbility() {
        return fightAbility;
    }

    public int getCombatComp() {
        return combatComp;
    }

    public void setAliveState(boolean aliveState) {
        alive = aliveState;
    }

    public boolean getAliveState() {
        return alive;
    }

    public String getSymbol() {
        return " ";
    }

}
