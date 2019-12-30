package items;/*
 * 蛇精。总指挥。
 */
import field.*;
import formations.*;

public class SnakeDemon extends Living {
    private ScorpionDemon scorpionDemon;

    public SnakeDemon(Position pos, Field field_, int followCount_) {
        super(pos, field_);
        scorpionDemon=new ScorpionDemon(field_.rightRandomPosition(),field_,followCount_);
//        scorpionDemon=new items.ScorpionDemon(new field.Position(9,0),field_,followCount_);
        field_.addLiving(scorpionDemon,field_.rightRandomPosition());
    }

    public void standAsSwing(){
        scorpionDemon.standAsSwing();
    }

    public void standAsArrow(){
        scorpionDemon.standAsArrow();
    }

    @Override
    public String toString(){
        return "Sna";
    }
}
