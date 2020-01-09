package battlefield.formation;

import battlefield.BattleField;
import battlefield.Position;
import creature.Creature;

import java.util.ArrayList;

public abstract class Formation {
    public static final Formation HE_YI=new Heyi();
    public static final Formation CHANG_SHE=new Changshe();

    Formation(){}

    public abstract void arrange(BattleField bf, ArrayList<? extends Creature> subordinate, Position leaderPos, boolean isGood);

}
