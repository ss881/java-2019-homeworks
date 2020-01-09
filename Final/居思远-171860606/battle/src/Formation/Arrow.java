package Formation;
import sample.Main;
import Creature.*;

public class Arrow extends Formation {

    public Arrow(Creature c)
    {
        setNumber(7);
        if(c.getClass()==Hulu.class)   //通过判断传入的creature是葫芦娃还是妖怪，确定要排的阵型在地图上的位置
        {
            x[0]=1;y[0]=7;
            x[1]=2;y[1]=7;
            x[2]=3;y[2]=7;
            x[3]=2;y[3]=6;
            x[4]=2;y[4]=8;
            x[5]=1;y[5]=5;
            x[6]=1;y[6]=9;
        }
        else if(c.getClass()==Monster.class)
        {
            int t=0;
            x[0]=13;y[0]=7;
            x[1]=12;y[1]=7;
            x[2]=11;y[2]=7;
            x[3]=12;y[3]=6;
            x[4]=12;y[4]=8;
            x[5]=13;y[5]=5;
            x[6]=13;y[6]=9;
        }

    }



}
