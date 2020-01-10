package creature;
public class Grandfather extends Creature{
    public Grandfather(){
        super(Race.Grandfather);
        this.symbol="爷";
    }
    public void makeOrder(Creature to,int x,int y){
        to.followOrder(this,x,y);
    }
    @Override
    public void followOrder(Creature from,int x,int y){

    }
    public void fight(){
        rushto(0, 0);
    }
}