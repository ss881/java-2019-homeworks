package grandPa;

import creature.Creature;
import huluwa.Huluwa;
import position.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

interface ShuffleAble<T>{
    void shuffle(T[]ts);
}

interface InitializeAble<T>{
    T[] initialize();
}

interface BubbleSortAble<T>{
    void bubbleSort(T[]ts);
}

interface BinarySortAble<T>{
    void binarySort(T[]ts);
}

public class GrandPa extends Creature implements ShuffleAble<Huluwa>,
        InitializeAble<Huluwa>,
        BubbleSortAble<Huluwa>,
        BinarySortAble<Huluwa> {
    GrandPa(){
        super();
    }

    private int findPlace(ArrayList<Huluwa>list,int start,int end,Huluwa h){
        //[start,end] is sorted,insert h into it,find where it should be
        int left=start;
        int right=end;
        int mid=(left+right)/2;
        while(left<=right){
            mid=(left+right)/2;
            if(list.get(mid).compareTo(h)==0){
                //equals,insert h in mid position
                return mid+1;
            }
            else if(list.get(mid).compareTo(h)>0){
                right=mid-1;
            }
            else{
                left=mid+1;
            }
        }
        return left;
    }

    @Override
    public Huluwa[] initialize(){//huluwas not local variable,lowering coupling
        String[]names={"老大","老二","老三","老四","老五","老六","老七"};
        String[]colors={"红","橙","黄","绿","青","蓝","紫"};
        Huluwa[]huluwas=new Huluwa[7];//create huluwas
        for(int i=0;i<7;++i){
            huluwas[i]=new Huluwa(i,names[i],colors[i],huluwas);
        }
        return huluwas;
    }

    @Override
    public void shuffle(Huluwa[]huluwas){
        ArrayList<Huluwa> list=new ArrayList<>(Arrays.asList(huluwas));
        Collections.shuffle(list);//打乱
        int i=0;
        for(Huluwa h:list){
            huluwas[i]=h;
//            huluwas[i].setIdx(i);//necessary?
            huluwas[i].moveTo(new Position(i));//下标也就是位置
            ++i;
        }
    }

    @Override
    public void bubbleSort(Huluwa[] huluwas) {
        for(int i=huluwas.length-1;i>=1;--i){
            //put the ith huluwa to right place
            for(int j=0;j<=i-1;++j){
                //compare jth and (j+1)th
                Huluwa huluwaI=huluwas[j];
                Huluwa next=huluwaI.lookBack();
                if(next!=null&&huluwaI.compareTo(next)>0){
//                    System.out.print(huluwas[j].getName()+" switch position with "+huluwas[j+1].getName()
//                            +",after excahnge: ");
                    System.out.print(huluwas[j].getName()+"->"+(j+1)+","+huluwas[j+1].getName()+"->"+j+"  current:");
                    huluwaI.switchWith(next);
                    Huluwa temp=huluwas[j];
                    huluwas[j]=huluwas[j+1];
                    huluwas[j+1]=temp;
                    showAllHuluwaName(huluwas);
                }
            }
        }
    }

    public void showAllHuluwaName(Huluwa[]huluwas){
        for(Huluwa huluwa:huluwas){
            System.out.print(huluwa.getName()+" ");
        }
        System.out.println();
    }

    public void showAllHuluwaColor(Huluwa[]huluwas){
        for(Huluwa huluwa:huluwas){
            System.out.print(huluwa.getColor()+" ");
        }
        System.out.println();
    }

    @Override
    public void binarySort(Huluwa[] huluwas) {
        ArrayList<Huluwa>list=new ArrayList<>(Arrays.asList(huluwas));
        for(int i=1;i<list.size();++i){
            //put the ith element in the right place
            //find right place in 0~i
            Huluwa h=list.get(i);
            int putIndex=findPlace(list,0,i-1,h);
            if(putIndex!=i){
                for(int j=putIndex;j<=i-1;++j){
                    list.get(j).moveTo(new Position(j+1));
                    System.out.print(list.get(j).getName()+"->"+(j+1)+", ");
                }
                h.moveTo(new Position(putIndex));//腾出了位置
                System.out.print(h.getName()+"->"+putIndex);
                list.remove(i);
                list.add(putIndex,h);

                System.out.print("  current:");
                for(Huluwa huluwa:list){
                    System.out.print(huluwa.getName()+" ");
                }
                System.out.println();

            }
        }
        System.arraycopy(list.toArray(),0,huluwas,0,huluwas.length);
//        System.out.println("");
    }

    public static void main(String[]args){
        GrandPa grandPa=new GrandPa();
        Huluwa[]huluwas =grandPa.initialize();

        System.out.println("before bubble sort:");
        grandPa.shuffle(huluwas);
        grandPa.showAllHuluwaName(huluwas);
        grandPa.bubbleSort(huluwas);
        System.out.println("after bubble sort:");
        grandPa.showAllHuluwaName(huluwas);

        System.out.println("\nbefore binary sort:");
        grandPa.shuffle(huluwas);
        grandPa.showAllHuluwaName(huluwas);
        grandPa.binarySort(huluwas);
        System.out.println("after binary sort:");
        grandPa.showAllHuluwaColor(huluwas);
    }
}
