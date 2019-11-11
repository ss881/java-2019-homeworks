package hw3.zhenfa;

import hw3.creature.Creature;
import hw3.util.Position;

import javax.crypto.SecretKey;
import java.util.ArrayList;

class Yulin extends Zhenfa {
    @Override
    public void buZhen(ArrayList<? extends Creature> creatures, Position leaderPos) {
        if(creatures.size() < 10) return;
        int x = leaderPos.getX();
        int y = leaderPos.getY();
        int idx = 0;
        creatures.get(idx++).setPosition(x, y);
        creatures.get(idx++).setPosition(x, y+2);
        creatures.get(idx++).setPosition(x, y+3);
        x++; y++;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < i+1; j++) {
                creatures.get(idx++).setPosition(x+j, y+i);
            }
        }
        creatures.get(idx++).setPosition(x, y+3);
        while(idx < creatures.size()) {
           creatures.get(idx++).releasePosition();
        }
    }
}
