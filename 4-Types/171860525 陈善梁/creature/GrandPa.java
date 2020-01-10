package creature;

import factory.HuluwaFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GrandPa extends Creature {
    static final int N=12;//size of map

    public ArrayList<Creature> initialize(){//返回葫芦娃list
        String[]names={"老大","老二","老三","老四","老五","老六","老七"};
        String[]colors={"红","橙","黄","绿","青","蓝","紫"};
        ArrayList<Creature> huluwas = new ArrayList<>();
        HuluwaFactory huluwaFactory = new HuluwaFactory();//运用工厂模式创建葫芦娃
        for(int i=0;i<7;++i){
            huluwas.add(huluwaFactory.generate(i+1,names[i],colors[i]));
        }
        shuffle(huluwas);//打乱
        sortHuluwa(huluwas);
        return huluwas;
    }

    //shuffle huluwas,and set their posotions(according to their index in shuffled array)
    public void shuffle(ArrayList<Creature> huluwas){
        ArrayList<Creature> list=new ArrayList<>(huluwas);
        Collections.shuffle(list);
        int i=0;

        //set the position of huluwa,according to their index in array
        int spaceUpY=(N-7)/2;

        for(Creature c:list){
            huluwas.set(i,c);
            Huluwa h = (Huluwa)huluwas.get(i);
            h.setIndex(i);//index in array
            h.setCurrentPosition(spaceUpY+i,N/6);//N/6=2
            ++i;
        }
    }

    public void sortHuluwa(ArrayList<Creature> huluwas){
        for(int i=huluwas.size()-1;i>=1;--i){//put ith huluwa in the right place
            for(int j=0;j<i;++j){
                Huluwa h = (Huluwa) huluwas.get(j);
                Huluwa nextHuluwa=h.lookBack(huluwas);//get next  huluwa
                if((nextHuluwa!=null)&&(nextHuluwa.compareTo(h)<0)){
                    h.swapWithHuluwa(nextHuluwa);
                    Huluwa temp=h;
                    huluwas.set(j,nextHuluwa);
                    huluwas.set(j + 1,temp);
                }
            }
        }
        for(Creature c :huluwas){
            c.setPreviousPosition(-1,-1);
        }
        //set self
        setCurrentPosition(6,1);
    }

//    public static  void main(String[]args){
//        GrandPa grandPa = new GrandPa();
//        ArrayList<Creature> creatures = grandPa.initialize();
//        for(Creature c : creatures){
//            System.out.println(c);
//        }
//    }

}
