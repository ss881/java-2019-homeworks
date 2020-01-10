package battlefield.formation;

import battlefield.BattleField;
import battlefield.Position;
import creature.Creature;

import java.util.ArrayList;

public class Heyi extends Formation {
    @Override
    public void arrange(BattleField bf, ArrayList<? extends Creature> subordinate, Position leaderPos, boolean isGood) {
        assert (subordinate.size() < 7);
        int variant;
        if (isGood) {
            variant = 1;
        } else {
            variant = -1;
        }
        int x = leaderPos.getX() + 6 * variant;
        int y = leaderPos.getY() + 5;
        int idx = 0;

        subordinate.get(idx++).setPosition(x, y);
        for (int i = 1; i <= 4; i++) {
            subordinate.get(idx++).setPosition(x - i * variant, y + i * variant);
            subordinate.get(idx++).setPosition(x - i * variant, y - i * variant);
        }

    }
}
