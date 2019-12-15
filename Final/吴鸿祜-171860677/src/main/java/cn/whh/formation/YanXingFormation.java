package cn.whh.formation;

import cn.whh.creature.Creature;

import java.util.ArrayList;

public class YanXingFormation extends Formation {
    public YanXingFormation(ArrayList<? extends Creature> camp, final int symbol)
    {
        super();
        if(symbol==0) //葫芦娃阵营
        {
            creatures[1][3]=camp.get(0);
            creatures[1][3].setPosition(1,3);
            creatures[2][3]=camp.get(1);
            creatures[2][3].setPosition(2,3);
            creatures[3][2]=camp.get(2);
            creatures[3][2].setPosition(3,2);
            creatures[4][2]=camp.get(3);
            creatures[4][2].setPosition(4,2);
            creatures[5][1]=camp.get(4);
            creatures[5][1].setPosition(5,1);
            creatures[6][1]=camp.get(5);
            creatures[6][1].setPosition(6,1);
            creatures[7][0]=camp.get(6);
            creatures[7][0].setPosition(7,0);

            creatures[4][0]=camp.get(7);
            creatures[4][0].setPosition(4,0);
        }

        else if(symbol==1)
        {
            creatures[1][width-4]=camp.get(0);
            creatures[1][width-4].setPosition(1,width-4);
            creatures[2][width-4]=camp.get(1);
            creatures[2][width-4].setPosition(2,width-4);
            creatures[3][width-3]=camp.get(2);
            creatures[3][width-3].setPosition(3,width-3);
            creatures[4][width-3]=camp.get(3);
            creatures[4][width-3].setPosition(4,width-3);
            creatures[5][width-2]=camp.get(4);
            creatures[5][width-2].setPosition(5,width-2);
            creatures[6][width-2]=camp.get(5);
            creatures[6][width-2].setPosition(6,width-2);
            creatures[7][width-1]=camp.get(6);
            creatures[7][width-1].setPosition(7,width-1);

            creatures[4][width-1]=camp.get(7);
            creatures[4][width-1].setPosition(4,width-1);

        }
    }
}
