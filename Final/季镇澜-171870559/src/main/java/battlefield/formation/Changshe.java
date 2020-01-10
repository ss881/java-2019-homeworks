package battlefield.formation;

import battlefield.BattleField;
import battlefield.Position;
import creature.Creature;

import java.util.ArrayList;

public class Changshe extends Formation {
    @Override
    public void arrange(BattleField bf, ArrayList<? extends Creature> subordinate, Position leaderPos, boolean isGood) {
        assert (subordinate.size() < 7);
        int variant;
        if (isGood) {
            variant = 1;
        } else {
            variant = -1;
        }
        int x = leaderPos.getX() + 3 * variant;
        int y = leaderPos.getY() + 2;
        int idx = 0;

        for (int i = 0; i < 7; i += variant) {
            subordinate.get(idx++).setPosition(x, y + i);
        }
    }
}
