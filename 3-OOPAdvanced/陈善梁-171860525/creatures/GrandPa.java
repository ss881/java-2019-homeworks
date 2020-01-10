package creatures;

import interfaces.Shuffleable;
import interfaces.Sortable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GrandPa extends Creature implements Sortable<Huluwa>, Shuffleable<Huluwa> {
    static final int N=12;//size of map

    public Huluwa[] initialize(){//返回葫芦娃数组
        String[]names={"老大","老二","老三","老四","老五","老六","老七"};
        String[]colors={"红","橙","黄","绿","青","蓝","紫"};
        Huluwa[]huluwas=new Huluwa[7];
        for(int i=0;i<huluwas.length;++i){
            huluwas[i]=new Huluwa(i+1,names[i],colors[i]);
        }
        shuffle(huluwas);//打乱
        return huluwas;
    }

    @Override
    //shuffle huluwas,and set their posotions(according to their index in shuffled array)
    public void shuffle(Huluwa[]huluwas){
        ArrayList<Huluwa> list=new ArrayList<>(Arrays.asList(huluwas));
        Collections.shuffle(list);
        int i=0;

        //set the position of huluwa,according to their index in array
        int spaceUpY=(N-7)/2;
        for(Huluwa h:list){
            huluwas[i]=h;
            huluwas[i].setIndex(i);//index in array
            huluwas[i].setCurrentPosition(new Position (spaceUpY+i,N/6));//N/6=2
            ++i;
        }
    }

    @Override
    public void sort(Huluwa[]huluwas){
        for(int i=huluwas.length-1;i>=1;--i){//put ith huluwa in the right place
            for(int j=0;j<i;++j){
                Huluwa nextHuluwa=huluwas[j].lookBack(huluwas);//get next  huluwa
                if((nextHuluwa!=null)&&(nextHuluwa.compareTo(huluwas[j])<0)){
                    huluwas[j].swapWithHuluwa(nextHuluwa);
                    Huluwa temp=huluwas[j];
                    huluwas[j]=nextHuluwa;
                    huluwas[j+1]=temp;
                }
            }
        }
        //set self
        setCurrentPosition(new Position (6,1));
    }

    @Override
    public char getSymbol(){
        return 'G';
    }

}
