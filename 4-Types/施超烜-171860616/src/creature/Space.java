package creature;
public class Space extends Creature{
    public Space(){
        super(Race.Space);
        this.symbol="  ";
    }
    @Override
    public void followOrder(Creature from,int x,int y){
        rushto(x, y);
    }
}