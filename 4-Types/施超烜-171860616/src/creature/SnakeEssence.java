package creature;
public class SnakeEssence extends Creature{
    public SnakeEssence(){
        super(Race.SnakeEssence);
        this.symbol="蛇";
    }
    @Override
    public void followOrder(Creature from,int x,int y){

    }
    public void fight(int maxnum){
        rushto(maxnum,0);
    }
    public void makeOrder(Creature to,int x,int y){
        to.followOrder(this, x, y);
    }
}