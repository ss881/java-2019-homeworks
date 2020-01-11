package creature;
public class Wannabe extends Creature{
    public Wannabe(){
        super(Race.Wannabe);
        this.symbol="蛤";
    }
    @Override
    public void followOrder(Creature from,int x,int y){
        if(from.getRace()==Race.ScorpionEssence||from.getRace()==Race.SnakeEssence){
            rushto(x, y);
        }
    }
}