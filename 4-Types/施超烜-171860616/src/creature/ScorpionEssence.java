package creature;
public class ScorpionEssence extends Creature{
    public ScorpionEssence(){
        super(Race.ScorpionEssence);
        this.symbol="蝎";
    }
    @Override
    public void followOrder(Creature from,int x,int y){
        if(from.getRace()==Race.SnakeEssence){
            rushto(x, y);
        }
    }
}