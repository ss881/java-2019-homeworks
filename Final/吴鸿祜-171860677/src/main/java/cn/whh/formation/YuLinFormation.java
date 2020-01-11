package cn.whh.formation;

import cn.whh.creature.Creature;

import java.util.ArrayList;

public class YuLinFormation extends Formation {
    public YuLinFormation(ArrayList<? extends Creature> camp, final int symbol)
    {
        super();
        if(symbol==0) //葫芦娃阵营
        {
            creatures[4][3]=camp.get(0);
            creatures[4][3].setPosition(4,3);
            creatures[5][2]=camp.get(1);
            creatures[5][2].setPosition(5,2);
            creatures[3][2]=camp.get(2);
            creatures[3][2].setPosition(3,2);
            creatures[3][1]=camp.get(3);
            creatures[3][1].setPosition(3,1);
            creatures[4][1]=camp.get(4);
            creatures[4][1].setPosition(4,1);
            creatures[5][1]=camp.get(5);
            creatures[5][1].setPosition(5,1);
            creatures[4][0]=camp.get(6);
            creatures[4][0].setPosition(4,0);

            creatures[4][2]=camp.get(7);
            creatures[4][2].setPosition(4,2);
        }

        else if(symbol==1) //蛇精阵营
        {
            creatures[4][width-4]=camp.get(0);
            creatures[4][width-4].setPosition(4,width-4);
            creatures[5][width-3]=camp.get(1);
            creatures[5][width-3].setPosition(5,width-3);
            creatures[3][width-3]=camp.get(2);
            creatures[3][width-3].setPosition(3,width-3);
            creatures[3][width-2]=camp.get(3);
            creatures[3][width-2].setPosition(3,width-2);
            creatures[4][width-2]=camp.get(4);
            creatures[4][width-2].setPosition(4,width-2);
            creatures[5][width-2]=camp.get(5);
            creatures[5][width-2].setPosition(5,width-2);
            creatures[4][width-1]=camp.get(6);
            creatures[4][width-1].setPosition(4,width-1);

            creatures[4][width-3]=camp.get(7);
            creatures[4][width-3].setPosition(4,width-3);
        }
    }
}
