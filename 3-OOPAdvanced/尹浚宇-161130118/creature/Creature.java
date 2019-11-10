package creature;

public abstract class Creature
{
    protected String name;
    // coordinate in 2-d map
    protected int x;
    protected int y;
    protected int camp; // -1:bad 0:neutral 1:good else:???
    protected String token; // what to show on map

    public Creature(String name, int camp, String token)
    {
        this.name = name; this.camp = camp; x = y = -1; this.token = token;
    }

    public String getName()
    {
        return name;
    }

    public void sayName()
    {
        System.out.print(name);
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setCoordinante(int x, int y)
    {
        this.x = x; this.y = y;
    }

    public int getCamp()
    {
        return camp;
    }

    public void sayCamp()
    {
        switch(camp)
        {
            case 0:
                System.out.print("neutral");
                break;
            case 1:
                System.out.print("good");
                break;
            case -1:
                System.out.print("bad");
                break;
            default:
                System.out.print("???");
                break;
        }
    }

    public String getToken()
    {
        return token;
    }
}
