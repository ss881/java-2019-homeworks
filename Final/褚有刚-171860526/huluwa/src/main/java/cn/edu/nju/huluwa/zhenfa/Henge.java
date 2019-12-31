package cn.edu.nju.huluwa.zhenfa;


import cn.edu.nju.huluwa.creature.Creature;
import cn.edu.nju.huluwa.position.Position;

import java.util.ArrayList;

class Henge extends Zhenfa {
    @Override
    public void buZhen(Position[][] positions, ArrayList<? extends Creature> creatures, Position leaderPos) {
        if(creatures.size() < 6) return;
        int x = leaderPos.getX();
        int y = leaderPos.getY();
        int idx = 0;
        for (int i = 0; i < 6; i++) {
            creatures.get(idx++).setPosition(positions[x+i/3][y-i/3+i%3*2]);
        }
        while(idx < creatures.size()) {
            creatures.get(idx++).releasePosition();
        }
    }

}
