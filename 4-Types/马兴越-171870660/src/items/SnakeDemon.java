package items;/*
 * 蛇精。总指挥。
 */
import exceptions.NoSpaceForFormationException;
import field.*;
import formations.*;

public class SnakeDemon extends Living implements Leader{
    private ScorpionDemon scorpionDemon;

    public SnakeDemon(Position pos, Field field_, int followCount_) {
        super(pos, field_);
        scorpionDemon=new ScorpionDemon(field_.rightRandomPosition(),field_,followCount_);
//        scorpionDemon=new items.ScorpionDemon(new field.Position(9,0),field_,followCount_);
        field_.addLiving(scorpionDemon,field_.rightRandomPosition());
    }

    @Override
    public String toString(){
        return "Sna";
    }

    @Override
    public <T extends Formation> void embattleFormation(Class<T> formType) {
        try{
            scorpionDemon.embattleFormation(formType);
        }
        catch (NoSpaceForFormationException e){
            System.out.println(e.toString());
        }
    }
}
