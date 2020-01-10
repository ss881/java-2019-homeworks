package cn.whh.formation;

import cn.whh.creature.Creature;

import java.util.ArrayList;

public class HengEFormation extends Formation {
    public HengEFormation(ArrayList<? extends Creature> camp, final int symbol)
    {
        super();
        if(symbol==0) //葫芦娃阵营
        {
            for(int i=0;i<7;i++)
            {
                creatures[i+1][(i+1)%2]=camp.get(i);
                creatures[i+1][(i+1)%2].setPosition(i+1,(i+1)%2);
            }

            creatures[8][0]=camp.get(7);
            creatures[8][0].setPosition(8,0);
        }

        else if(symbol==1) //蛇精阵营
        {
            for(int i=0;i<7;i++)
            {
                creatures[i+1][11+i%2]=camp.get(i);
                creatures[i+1][11+i%2].setPosition(i+1,11+i%2);
            }

            creatures[8][12]=camp.get(7);
            creatures[8][12].setPosition(8,12);
        }
    }
}
