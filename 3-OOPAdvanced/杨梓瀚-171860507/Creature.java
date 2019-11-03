enum KIND{
    scorpion, minion, grandpa, calaBro, snake
}

public class Creature {
    private int x;

    private int y;

    private KIND identity;

    Creature(KIND kind) {
        identity = kind;
    }

    public void setPosition(int xx, int yy) {
        x = xx;
        y = yy;
    }

    public void setWar(Creature[][] war) {
        war[x][y] = this;
    }

    public KIND getKind(){return identity;}
}
