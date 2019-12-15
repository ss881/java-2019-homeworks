package cn.whh.formation;

import cn.whh.creature.Creature;

import java.util.ArrayList;

public class FangYuanFormation extends Formation {
    public FangYuanFormation(ArrayList<? extends Creature> camp, final int symbol)
    {
        super();
        if(symbol==0) //葫芦娃阵营
        {
            creatures[4][4]=camp.get(0);
            creatures[4][4].setPosition(4,4);
            creatures[3][3]=camp.get(1);
            creatures[3][3].setPosition(3,3);
            creatures[5][3]=camp.get(2);
            creatures[5][3].setPosition(5,3);
            creatures[2][2]=camp.get(3);
            creatures[2][2].setPosition(2,2);
            creatures[6][2]=camp.get(4);
            creatures[6][2].setPosition(6,2);
            creatures[3][1]=camp.get(5);
            creatures[3][1].setPosition(3,1);
            creatures[5][1]=camp.get(6);
            creatures[5][1].setPosition(5,1);

            creatures[4][0]=camp.get(7);
            creatures[4][0].setPosition(4,0);
        }

        else if(symbol==1) //蛇精阵营
        {
            creatures[4][width-5]=camp.get(0);
            creatures[4][width-5].setPosition(4,width-5);
            creatures[3][width-4]=camp.get(1);
            creatures[3][width-4].setPosition(3,width-4);
            creatures[5][width-4]=camp.get(2);
            creatures[5][width-4].setPosition(5,width-4);
            creatures[2][width-3]=camp.get(3);
            creatures[2][width-3].setPosition(2,width-3);
            creatures[6][width-3]=camp.get(4);
            creatures[6][width-3].setPosition(6,width-3);
            creatures[3][width-2]=camp.get(5);
            creatures[3][width-2].setPosition(3,width-2);
            creatures[5][width-2]=camp.get(6);
            creatures[5][width-2].setPosition(5,width-2);

            creatures[4][width-1]=camp.get(7);
            creatures[4][width-1].setPosition(4,width-1);
        }
    }
}
