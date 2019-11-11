package Creatures;
import Space.Coordinate;
import Space.SpaceUnit;
import Space.SpaceFlat;

public class Creature {
    private String name;
    private String icon;
    private Coordinate cur_coordinate;
    public Creature()
    {
        name="unknown";
        icon = "?";
        cur_coordinate=new Coordinate(-1,-1);
    }
    public Creature(String name, String icon)
    {
        this.name=name;
        this.icon=icon;
        cur_coordinate=new Coordinate(-1,-1);
    }
    public String getName()
    {
        return name;
    }
    public String getIcon()
    {
        return icon;
    }
    public Coordinate getCur_coordinate()
    {
        return cur_coordinate;
    }
    public void moveTo(Coordinate destination)
    {
        cur_coordinate=destination;
    }
}