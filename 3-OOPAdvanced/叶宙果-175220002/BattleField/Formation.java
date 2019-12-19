package BattleField;

import java.util.ArrayList;

import Creatures.Huluwa;
import Creatures.Monsters;
import Creatures.Scorpion;

public class Formation
{
    public void Formationforbad(Space filed,Scorpion scorpion,Monsters[] monsters,int index)
    {
        
        switch(index)
        {
            case 0:         //HeYi鹤翼
            {
                scorpion.setto(7,10,filed);
                monsters[0].setto(6, 11, filed);
                monsters[1].setto(8, 11, filed);
                monsters[2].setto(5, 12, filed);
                monsters[3].setto(9, 12, filed);
                monsters[4].setto(4, 13, filed);
                monsters[5].setto(10, 13, filed);
               
                break;
            }
            case 1:         //YanXing雁行
            {
                scorpion.setto(4,14,filed);
                monsters[0].setto(5, 13, filed);
                monsters[1].setto(6, 12, filed);
                monsters[2].setto(7, 11, filed);
                monsters[3].setto(8, 10, filed);
                monsters[4].setto(9, 9, filed);
                monsters[5].setto(10, 8, filed);
                break;
            }
            case 2:         //ChongE衝轭
            {
                scorpion.setto(7,10,filed);
                monsters[0].setto(5, 10, filed);
                monsters[1].setto(9, 10, filed);
                monsters[2].setto(4, 11, filed);
                monsters[3].setto(6, 11, filed);
                monsters[4].setto(8, 11, filed);
                monsters[5].setto(10, 11, filed);
                break;
            }
            case 3:         //ChangShe长蛇
            {
                scorpion.setto(4,11,filed);
                //filed.showthemap(15, 15);
                //System.out.println("------------------------------------");
                for(int i=5;i<10;i++)
                {
                    monsters[i-5].setto(i, 11, filed);
                    //filed.showthemap(15, 15);
                    //System.out.println("------------------------------------");
                }
                break;
            }
            case 4:         //YuLin鱼鳞
            {
                scorpion.setto(7,7,filed);
                monsters[0].setto(6, 8, filed);
                monsters[1].setto(5, 11, filed);
                monsters[2].setto(4, 10, filed);
                monsters[3].setto(7, 9, filed);
                monsters[4].setto(6, 10, filed);
                monsters[5].setto(8, 10, filed);
                monsters[6].setto(7, 11, filed);
                monsters[7].setto(9, 10, filed);
                //monsters[8].setto(10, 10, filed);
                break;
            }
            case 5:         //FangYuan方円
            {
                scorpion.setto(7,7,filed);
                monsters[0].setto(6, 8, filed);
                monsters[1].setto(8, 8, filed);
                monsters[2].setto(5, 9, filed);
                monsters[3].setto(9, 9, filed);
                monsters[4].setto(4, 10, filed);
                monsters[5].setto(10, 10, filed);
                monsters[6].setto(5, 11, filed);
                monsters[7].setto(9, 11, filed);
                monsters[8].setto(6, 12, filed);
                monsters[9].setto(8, 12, filed);
                monsters[10].setto(7, 13, filed);
                break;
            }
            case 6:         //YanYue偃月
            {
                scorpion.setto(7,7,filed);
                monsters[0].setto(6, 7, filed);
                monsters[1].setto(8, 7, filed);
                monsters[2].setto(6, 8, filed);
                monsters[3].setto(7, 8, filed);
                monsters[4].setto(8, 8, filed);
                monsters[5].setto(5, 9, filed);
                monsters[6].setto(6, 9, filed);
                monsters[7].setto(9, 9, filed);
                monsters[8].setto(8, 9, filed);
                monsters[9].setto(4, 10, filed);
                monsters[10].setto(5, 10, filed);
                monsters[11].setto(9, 10, filed);
                monsters[12].setto(10, 10, filed);
                monsters[13].setto(4, 11, filed);
                monsters[14].setto(10, 11, filed);
                monsters[15].setto(3, 12, filed);
                monsters[16].setto(11, 12, filed);
                break;
            }
            case 7:         //FengShi锋矢
            {
                scorpion.setto(7,7,filed);
                monsters[0].setto(6, 8, filed);
                monsters[1].setto(7, 8, filed);
                monsters[2].setto(8, 8, filed);
                monsters[3].setto(5, 9, filed);
                monsters[4].setto(7, 9, filed);
                monsters[5].setto(9, 9, filed);
                monsters[6].setto(4, 10, filed);
                monsters[7].setto(7, 10, filed);
                monsters[8].setto(10, 10, filed);
                monsters[9].setto(7, 11, filed);
                break;
            }
        }
    }


    public void Formationforgood(Space filed,ArrayList<Huluwa> huluwas,int index)
    {
        switch(index)
        {
            case 3:     //ChangShe长蛇
            {
                for(int i=0;i<7;i++)
                {
                    huluwas.get(i).setto(i+4, 1, filed);
                    //filed.showthemap(15, 15);
                    //System.out.println("------------------------------------");
                }
                break;
            }
        }
    
    }
}