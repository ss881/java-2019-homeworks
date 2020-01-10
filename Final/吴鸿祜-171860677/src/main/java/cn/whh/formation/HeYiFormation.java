package cn.whh.formation;

import cn.whh.creature.Creature;

import java.util.ArrayList;

public class HeYiFormation extends Formation {
    public HeYiFormation(ArrayList<? extends Creature> camp, final int symbol)
    {
        super();
        if(symbol==0)
        {
            creatures[4][3]=camp.get(0);
            creatures[4][3].setPosition(4,3);

            for(int i=1;i<7;i++)
            {
                if(i%2==1)
                {
                    creatures[4-(i+1)/2][3-(i+1)/2]=camp.get(i);
                    creatures[4-(i+1)/2][3-(i+1)/2].setPosition(4-(i+1)/2,3-(i+1)/2);
                }

                else
                {
                    creatures[4+i/2][3-i/2]=camp.get(i);
                    creatures[4+i/2][3-i/2].setPosition(4+i/2,3-i/2);
                }
            }

            creatures[4][0]=camp.get(7);
            creatures[4][0].setPosition(4,0);
        }

        else if(symbol==1)
        {
            creatures[4][width-4]=camp.get(0);
            creatures[4][width-4].setPosition(4,width-4);

            for(int i=1;i<7;i++)
            {
                if(i%2==1)
                {
                    creatures[4-(i+1)/2][width-4+(i+1)/2]=camp.get(i);
                    creatures[4-(i+1)/2][width-4+(i+1)/2].setPosition(4-(i+1)/2,width-4+(i+1)/2);
                }

                else
                {
                    creatures[4+i/2][width-4+i/2]=camp.get(i);
                    creatures[4+i/2][width-4+i/2].setPosition(4+i/2,width-4+i/2);
                }
            }

            creatures[4][width-1]=camp.get(7);
            creatures[4][width-1].setPosition(4,width-1);
        }
    }
}
