package cn.edu.nju.huluwa.zhenfa;


import cn.edu.nju.huluwa.creature.Creature;
import cn.edu.nju.huluwa.position.Position;

import java.util.ArrayList;

class Fangyuan extends Zhenfa {
    @Override
    public void buZhen(Position[][] positions, ArrayList<? extends Creature> creatures, Position leaderPos) {
        if(creatures.size() < 8) return;
        // get leader's position
        int x = leaderPos.getX();
        int y = leaderPos.getY();
        int idx = 0;
        creatures.get(idx++).setPosition(positions[x][y]);
        for(int i = 1; i <= 2; i++) {
            creatures.get(idx++).setPosition(positions[x+i][y+i]);
            creatures.get(idx++).setPosition(positions[x+i][y-i]);
        }
        x += 4;
        creatures.get(idx++).setPosition(positions[x][y]);
        for(int i = 1; i <= 2; i++) {
            creatures.get(idx++).setPosition(positions[x-i][y+i]);
            creatures.get(idx++).setPosition(positions[x-i][y-i]);
        }
        // hide other creatures
        while(idx < creatures.size()) {
            creatures.get(idx++).releasePosition();
        }
    }
}
