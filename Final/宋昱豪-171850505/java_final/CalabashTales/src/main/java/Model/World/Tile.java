package Model.World;

public class Tile {
    private boolean isOccupied;
    private Lives who;
    private Position position;
    public Tile()
    {
        isOccupied=false;
        who=null;
    }
    public Tile(Position x)
    {
        isOccupied=false;
        who=null;
        position =x;
    }
    public void SetALL(boolean is, Lives x)
    {
        isOccupied=is;
        who=x;
    }
    public Lives GetWho()
    {
        return who;
    }
    public boolean GetIsOccupied()
    {
        return isOccupied;
    }
    public void SetWho(Lives x)
    {
        who=x;
    }
    public void SetIsOccupied(boolean x)
    {
        isOccupied=x;
    }

}
