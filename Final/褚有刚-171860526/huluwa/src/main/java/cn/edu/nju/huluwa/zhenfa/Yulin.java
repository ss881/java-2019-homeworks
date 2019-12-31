package cn.edu.nju.huluwa.zhenfa;


import cn.edu.nju.huluwa.creature.Creature;
import cn.edu.nju.huluwa.position.Position;

import java.util.ArrayList;

class Yulin extends Zhenfa {
    @Override
    public void buZhen(Position[][] positions, ArrayList<? extends Creature> creatures, Position leaderPos) {
        if (creatures.size() < 10) return;
        int x = leaderPos.getX();
        int y = leaderPos.getY();
        int idx = 0;
        creatures.get(idx++).setPosition(positions[x][y]);
        creatures.get(idx++).setPosition(positions[x][ y + 2]);
        creatures.get(idx++).setPosition(positions[x][ y + 3]);
        x++;
        y++;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < i + 1; j++) {
                creatures.get(idx++).setPosition(positions[x + j][ y + i]);
            }
        }
        creatures.get(idx++).setPosition(positions[x][ y + 3]);
        while (idx < creatures.size()) {
            creatures.get(idx++).releasePosition();
        }
    }
}
