package cn.whh.formation;

import cn.whh.creature.Creature;

import java.util.ArrayList;

public class ChangSheFormation extends Formation {

    public ChangSheFormation(ArrayList<? extends Creature> camp, final int symbol)
    {
        super();
        if(symbol==0) //葫芦娃阵营
        {
            for(int i=0;i<7;i++)
            {
                creatures[i+1][0]=camp.get(i);
                creatures[i+1][0].setPosition(i+1,0);
            }

            creatures[8][0]=camp.get(7);
            creatures[8][0].setPosition(8,0);
        }

        else if(symbol==1) //蛇精阵营
        {
            for(int i=0;i<7;i++)
            {
                creatures[i+1][width-1]=camp.get(i);
                creatures[i+1][width-1].setPosition(i+1,width-1);
            }

            creatures[8][width-1]=camp.get(7);
            creatures[8][width-1].setPosition(8,width-1);
        }
    }


}
