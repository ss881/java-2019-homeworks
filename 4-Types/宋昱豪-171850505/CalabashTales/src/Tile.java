public class Tile {
    private boolean isOccupied;
    private Lives who;


    public Tile()
    {
        isOccupied=false;
        who=null;
    }
    public Tile(boolean is, Lives x)
    {
        isOccupied=is;
        who=x;
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
    public void SetWho(Lives x)
    {
        who=x;
    }
    public void SetIsOccupied(boolean x)
    {
        isOccupied=x;
    }
    public boolean GetIsOccupied()
    {
        return isOccupied;
    }
}
