package hw3.zhenfa;

import hw3.creature.Creature;
import hw3.util.Position;

import java.util.ArrayList;

class Yanyue extends Zhenfa {
    @Override
    public void buZhen(ArrayList<? extends Creature> creatures, Position leaderPos) {
        if(creatures.size() < 17) return;
        int x = leaderPos.getX();
        int y = leaderPos.getY();
        int idx = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                creatures.get(idx++).setPosition(x+i, y-j);
            }
        }
        y--;
        for(int i = 0; i < 2; i++) {
            for (int j = 1; j <= 2; j++) {
                creatures.get(idx++).setPosition(x + i + j, y + 1 + j);
                creatures.get(idx++).setPosition(x + i + j, y - 1 - j);
            }
        }
        while(idx < creatures.size()) {
            creatures.get(idx++).releasePosition();
        }
    }
}
