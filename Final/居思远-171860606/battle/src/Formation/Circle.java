package Formation;
import sample.Main;
import Creature.*;

public class Circle extends Formation {

    public Circle(Creature c)
    {
        setNumber(7);
        if(c.getClass()==Hulu.class)   //通过判断传入的creature是葫芦娃还是妖怪，确定要排的阵型在地图上的位置
        {
            x[0]=1;y[0]=6;
            x[1]=1;y[1]=8;
            x[2]=2;y[2]=5;
            x[3]=2;y[3]=9;
            x[4]=3;y[4]=6;
            x[5]=3;y[5]=8;
            x[6]=3;y[6]=7;
        }
        else if(c.getClass()==Monster.class)
        {
            int t=0;
            x[0]=13;y[0]=6;
            x[1]=13;y[1]=8;
            x[2]=12;y[2]=5;
            x[3]=12;y[3]=9;
            x[4]=11;y[4]=6;
            x[5]=11;y[5]=8;
            x[6]=11;y[6]=7;
        }

    }



}
