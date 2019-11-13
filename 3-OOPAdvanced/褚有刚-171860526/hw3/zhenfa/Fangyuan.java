package hw3.zhenfa;

import hw3.creature.Creature;
import hw3.creature.Minion;
import hw3.util.Position;

import java.util.ArrayList;

class Fangyuan extends Zhenfa {
    @Override
    public void buZhen(ArrayList<? extends Creature> creatures, Position leaderPos) {
        if(creatures.size() < 8) return;
        // get leader's position
        int x = leaderPos.getX();
        int y = leaderPos.getY();
        int idx = 0;
        creatures.get(idx++).setPosition(x, y);
        for(int i = 1; i <= 2; i++) {
            creatures.get(idx++).setPosition(x+i, y+i);
            creatures.get(idx++).setPosition(x+i, y-i);
        }
        x += 4;
        creatures.get(idx++).setPosition(x, y);
        for(int i = 1; i <= 2; i++) {
            creatures.get(idx++).setPosition(x-i, y+i);
            creatures.get(idx++).setPosition(x-i, y-i);
        }
        // hide other creatures
        while(idx < creatures.size()) {
            creatures.get(idx++).releasePosition();
        }
    }
}
