package huluwa.zhenfa;


import huluwa.creature.Creature;
import huluwa.util.Position;

import java.util.ArrayList;

class Yanxing extends Zhenfa{
    @Override
    public void buZhen(Position[][] positions, ArrayList<? extends Creature> creatures, Position leaderPos) {
        if(creatures.size() < 5) return;
        int x = leaderPos.getX();
        int y = leaderPos.getY();
        int idx = 0;
        for (int i = 0; i < 5; i++) {
           creatures.get(idx++).setPosition(positions[x+i][y-i]);
        }
        while(idx < creatures.size()) {
            creatures.get(idx++).releasePosition();
        }
    }
}
