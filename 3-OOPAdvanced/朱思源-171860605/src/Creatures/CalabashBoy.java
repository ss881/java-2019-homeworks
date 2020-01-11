package Creatures;

public class CalabashBoy extends Creature
{
    private String color;
    private int name_rank;
    private int color_rank;
    public CalabashBoy(String name, String color, int name_rank, int color_rank)
    {
        super(name,""+name_rank);
        this.color=color;
        this.name_rank=name_rank;
        this.color_rank=color_rank;
    }
    public String getColor()
    {
        return color;
    }
    public int getName_rank()
    {
        return name_rank;
    }
    public int getColor_rank()
    {
        return color_rank;
    }
}
