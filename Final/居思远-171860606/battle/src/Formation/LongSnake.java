package Formation;

import Creature.*;

public class LongSnake extends Formation {

    public LongSnake(Creature c)
    {
        setNumber(7);
        if(c.getClass()==Hulu.class)   //通过判断传入的creature是葫芦娃还是妖怪，确定要排的阵型在地图上的位置
        {
            int t=0;
            for(int jk=0;jk<7;jk++) {
                x[jk] = 1;
                y[jk] = 4 + jk;
                t++;
            }
        }
        else if(c.getClass()==Monster.class)
        {
            int t=0;
            for(int jk=0;jk<7;jk++) {
                x[jk] = 13;
                y[jk] = 4 + jk;
                t++;
            }
        }
    }



}
