package cn.whh.map;
import cn.whh.creature.Creature;

public class Position {
    private int x;
    private int y;
    private Creature creature;

    public Position(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

    public void setX(int x) {this.x=x;}

    public void setY(int y) {this.y=y;}

    public int getX() {return x;}

    public int getY() {return y;}

    public void setCreature(Creature creature){this.creature=creature;}

    public Creature getCreature(){return creature;}
}
