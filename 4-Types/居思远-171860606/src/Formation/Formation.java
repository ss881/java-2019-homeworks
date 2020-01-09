package Formation;

public class Formation {
    private int number;
    protected int camp;
    protected int x[] = new int[10];
    protected int y[] = new int[10];

    Formation()
    {}

    public int getNumber()
    {return number;}
    protected void setNumber(int number)
    {this.number=number;}

    public int returnX(int n)
    {return x[n];}

    public int returnY(int n)
    {return y[n];}


}
