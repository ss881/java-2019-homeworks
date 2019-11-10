package hw3.zhenfa;

import hw3.creature.Creature;
import hw3.util.Position;

import java.util.ArrayList;

class Changshe extends Zhenfa {
    @Override
    public void buZhen(ArrayList<? extends Creature> creatures, Position leaderPos) {
        if(creatures.size() < 7) return;
        int x = leaderPos.getX();
        int y = leaderPos.getY();
        int idx = 0;
        for (int i = 0; i < 7; i++) {
           creatures.get(idx++).setPosition(x, y+i);
        }
        while(idx < creatures.size()) {
            creatures.get(idx++).releasePosition();
        }
    }
}
