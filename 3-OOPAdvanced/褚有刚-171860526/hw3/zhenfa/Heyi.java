package hw3.zhenfa;

import hw3.creature.Creature;
import hw3.creature.Huluwa;
import hw3.util.Position;

import java.util.ArrayList;

class Heyi extends Zhenfa {
    @Override
    public void buZhen(ArrayList<? extends Creature> creatures, Position leaderPos) {
        if(creatures.size() < 7) return;
        int x = leaderPos.getX();
        int y = leaderPos.getY();
        int idx = 0;
        creatures.get(idx++).setPosition(x, y);
        for(int i = 1; i <= 3; i++) {
            creatures.get(idx++).setPosition(x-i, y-i);
            creatures.get(idx++).setPosition(x+i, y-i);
        }
       while(idx < creatures.size()) {
          creatures.get(idx++).releasePosition();
       }
    }
}
