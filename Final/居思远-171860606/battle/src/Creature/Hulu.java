package Creature;

public class Hulu extends Creature
{
    private String color;
    private int rank;
    public Hulu(int id,int rank,String name,String color)
    {
        setId(id);
        setName(name);
        setRank(rank);
        this.color=color;
        alive = true;
        setCamp(0);
        rush = true;
    }

    public void setRank(int rank)
    {this.rank=rank;}
    public int getRank()
    {return rank;}
}


