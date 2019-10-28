package creatures;

public class Huluwa extends Creature implements Comparable<Huluwa> {
    private String name,color;
    private int rank;
    private int index;//when lookBack,need to know where self is (in huluwa[] array)

    Huluwa(){}
    Huluwa(int rank,String name,String color){
        super();
        this.rank=rank;
        this.name=name;
        this.color=color;
    }

    public void setIndex(int index){
        this.index=index;
    }

    @Override
    public int compareTo(Huluwa o) {
        return this.rank-o.rank;
    }

    public Huluwa lookBack(Huluwa[]huluwas){
        if(index<huluwas.length-1){
            return huluwas[index+1];
        }
        else{
            return null;
        }
    }

    public void swapWithHuluwa(Huluwa huluwa){//self cannot change sel in array ,but can change index and position
        int temp=this.index;
        this.index=huluwa.index;
        huluwa.index=temp;
        this.previousPosition=this.currentPosition;
        huluwa.previousPosition=huluwa.currentPosition;
        swapPosition(huluwa);//swap currentPosition
    }

    @Override
    public char getSymbol(){
        return 'H';
    }

    @Override
    public String toString(){
        return "Huluwa "+" name:"+name+",color="+color+",Position=("+currentPosition.x+","+currentPosition.y+")";
    }
}
