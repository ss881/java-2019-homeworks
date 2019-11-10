package hw3.zhenfa;

import hw3.creature.Creature;
import hw3.creature.Minion;
import hw3.util.Position;

import java.util.ArrayList;

class Fengshi extends Zhenfa {
    @Override
    public void buZhen(ArrayList<? extends Creature> creatures, Position leaderPos) {
        // this method is only suitable for the creatures whose size is no less than 5
        if(creatures.size() < 12) return;
        int x = leaderPos.getX();
        int y = leaderPos.getY();
        int idx = 0;
        creatures.get(idx++).setPosition(x, y);
        for(int i = 1; i <= 3; i++) {
            creatures.get(idx++).setPosition(x-i, y+i);
            creatures.get(idx++).setPosition(x+i, y+i);
        }
        for(int i = 1; i <= 5; i++) {
            creatures.get(idx++).setPosition(x, y+i);
        }
        while(idx < creatures.size()) {
            creatures.get(idx++).releasePosition();
        }
    }

    public static void main(String[] args) {
//        ArrayList<Minion> minions = new ArrayList<>();
//        for(int i = 0; i < 8; i++) {
//            minions.add(new Minion("喽啰#" + (i+1)));
//        }
//        Zhenfa.FENG_SHI.buZhen(minions, new Position(10, 10));
//        System.out.println(minions);
//
    }
}

