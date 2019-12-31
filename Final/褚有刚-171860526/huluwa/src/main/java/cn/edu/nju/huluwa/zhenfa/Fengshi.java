package cn.edu.nju.huluwa.zhenfa;


import cn.edu.nju.huluwa.creature.Creature;
import cn.edu.nju.huluwa.position.Position;

import java.util.ArrayList;

class Fengshi extends Zhenfa {
    @Override
    public void buZhen(Position[][] positions, ArrayList<? extends Creature> creatures, Position leaderPos) {
        // this method is only suitable for the creatures whose size is no less than 5
        if(creatures.size() < 12) return;
        int x = leaderPos.getX();
        int y = leaderPos.getY();
        int idx = 0;
        creatures.get(idx++).setPosition(positions[x][y]);
        for(int i = 1; i <= 3; i++) {
            creatures.get(idx++).setPosition(positions[x-i][y+i]);
            creatures.get(idx++).setPosition(positions[x+i][y+i]);
        }
        for(int i = 1; i <= 5; i++) {
            creatures.get(idx++).setPosition(positions[x][y+i]);
        }
        while(idx < creatures.size()) {
            creatures.get(idx++).releasePosition();
        }
    }

}

