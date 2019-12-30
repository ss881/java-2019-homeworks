package cn.edu.nju.huluwa.zhenfa;


import cn.edu.nju.huluwa.creature.Creature;
import cn.edu.nju.huluwa.position.Position;

import java.util.ArrayList;

class Changshe extends Zhenfa {
    @Override
    public void buZhen(Position[][] positions, ArrayList<? extends Creature> creatures, Position leaderPos) {
        if(creatures.size() < 7) return;
        int x = leaderPos.getX();
        int y = leaderPos.getY();
        int idx = 0;
        for (int i = 0; i < 7; i++) {
           creatures.get(idx++).setPosition(positions[x][y+i]);
        }
        while(idx < creatures.size()) {
            creatures.get(idx++).releasePosition();
        }
    }
}
