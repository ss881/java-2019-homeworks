package top.stqstq.maven_huluwa.being;

public interface IBeing extends Runnable{

    void print();

    int getRank();

    void walkTo(int x, int y);

    String getName();

    int getFightAbility();

    int getCombatComp();

    void setAliveState(boolean aliveState);

    boolean getAliveState();

    String getSymbol();
}
