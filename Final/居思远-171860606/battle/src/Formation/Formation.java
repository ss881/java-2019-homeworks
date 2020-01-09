package Formation;

import Creature.*;
import sample.Main;

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

    public void lay(Creature c)
    {
        if(c.getClass()== Hulu.class)   //通过判断传入的creature是葫芦娃还是妖怪，确定要排的阵型在地图上的位置
        {
            for (int jk = 0; jk < 7; jk++)
                Main.hulu[jk].place(returnX(jk), returnY(jk));
        }
        else if(c.getClass()== Monster.class)
        {
            for (int jk = 0; jk < 7; jk++)
                Main.monster[jk].place(returnX(jk), returnY(jk));
        }

    }



}
