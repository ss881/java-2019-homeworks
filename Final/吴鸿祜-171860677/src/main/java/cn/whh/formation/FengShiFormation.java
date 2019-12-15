package cn.whh.formation;

import cn.whh.creature.Creature;

import java.util.ArrayList;

public class FengShiFormation extends Formation {
    public FengShiFormation(ArrayList<? extends Creature> camp, final int symbol)
    {
        super();
        if(symbol==0) //葫芦娃阵营
        {
            for(int i=0;i<3;i++)
            {
                creatures[4][2-i]=camp.get(i);
                creatures[4][2-i].setPosition(4,2-i);
            }

            for(int i=3;i<7;i++)
            {
                if(i%2==0)
                {
                    creatures[5+(i-4)/2][1-(i-4)/2]=camp.get(i);
                    creatures[5+(i-4)/2][1-(i-4)/2].setPosition(5+(i-4)/2,1-(i-4)/2);
                }

                else
                {
                    creatures[4-i/2][2-i/2]=camp.get(i);
                    creatures[4-i/2][2-i/2].setPosition(4-i/2,2-i/2);
                }
            }

            creatures[1][1]=camp.get(7);
            creatures[1][1].setPosition(1,1);
        }

        else if(symbol==1) //蛇精阵营
        {
            for(int i=0;i<3;i++)
            {
                creatures[4][10+i]=camp.get(i);
                creatures[4][10+i].setPosition(4,10+i);
            }

            for(int i=3;i<7;i++)
            {
                if(i%2==0)
                {
                    creatures[5+(i-4)/2][11+(i-4)/2]=camp.get(i);
                    creatures[5+(i-4)/2][11+(i-4)/2].setPosition(5+(i-4)/2,11+(i-4)/2);
                }

                else
                {
                    creatures[4-i/2][10+i/2]=camp.get(i);
                    creatures[4-i/2][10+i/2].setPosition(4-i/2,10+i/2);
                }
            }

            creatures[1][width-1]=camp.get(7);
            creatures[1][width-1].setPosition(1,width-1);
        }
    }
}
