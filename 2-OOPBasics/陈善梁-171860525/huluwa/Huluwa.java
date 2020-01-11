package huluwa;

import creature.Creature;

/**
 * get next
 *
 */
interface LookBackAble<T>{
    T lookBack();
}

public class Huluwa extends Creature implements Comparable<Huluwa>,LookBackAble<Huluwa> {
    private String name,color;
    private int rank;
    private Huluwa[]huluwas;//contain myself

    public Huluwa(int rank,String name,String color,Huluwa[]huluwas){
        this.rank=rank;
        this.name=name;
        this.color=color;
        this.huluwas=huluwas;
    }

    public String getName(){
        return name;
    }

    public String getColor(){
        return  color;
    }

    @Override
    public int compareTo(Huluwa o) {
        return this.rank-o.rank;
    }

    @Override
    public Huluwa lookBack() {
        int pos=position.getPos();
        if(pos>=huluwas.length-1){
            return null;
        }
        else if(pos<0){
            throw new RuntimeException();//not initialized
        }
        else{
            return huluwas[pos+1];
        }
    }

    @Override public String toString(){
        return "Huluwa: name = "+name+",color = "+color+",pos= "+position.getPos();
    }
}
