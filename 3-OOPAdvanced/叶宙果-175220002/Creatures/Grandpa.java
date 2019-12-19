package Creatures;

public class Grandpa extends Creature
{
    public Grandpa()
    {
        super(-1,-1,false);
        this.name="爷爷";
    }

    public Grandpa(int x,int y)
    {
        super(x,y,true);
        this.name="爷爷";
    }
}