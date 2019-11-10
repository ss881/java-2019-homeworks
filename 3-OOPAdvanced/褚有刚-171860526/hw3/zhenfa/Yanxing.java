package hw3.zhenfa;

import hw3.creature.Creature;
import hw3.util.Position;

import java.util.ArrayList;

class Yanxing extends Zhenfa{
    @Override
    public void buZhen(ArrayList<? extends Creature> creatures, Position leaderPos) {
        if(creatures.size() < 5) return;
        int x = leaderPos.getX();
        int y = leaderPos.getY();
        int idx = 0;
        for (int i = 0; i < 5; i++) {
           creatures.get(idx++).setPosition(x+i, y-i);
        }
        while(idx < creatures.size()) {
            creatures.get(idx++).releasePosition();
        }
    }
}
