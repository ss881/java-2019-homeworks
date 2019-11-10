package hw3.zhenfa;

import hw3.creature.Creature;
import hw3.util.Position;

import java.util.ArrayList;

class Henge extends Zhenfa {
    @Override
    public void buZhen(ArrayList<? extends Creature> creatures, Position leaderPos) {
        if(creatures.size() < 6) return;
        int x = leaderPos.getX();
        int y = leaderPos.getY();
        int idx = 0;
        for (int i = 0; i < 6; i++) {
            creatures.get(idx++).setPosition(x+i/3, y-i/3+i%3*2);
        }
        while(idx < creatures.size()) {
            creatures.get(idx++).releasePosition();
        }
    }

    public static void main(String[] args) {
//        ArrayList<Huluwa> bros = new ArrayList<>();
//        String[] s = {
//                "大娃", "二娃", "三娃", "四娃", "五娃"
//        };
//        for (String s1 : s) {
//            bros.add(new Huluwa(s1));
//        }
//        System.out.println(bros);
//        Zhenfa.HENG_E.buZhen(bros, new Position(10, 10));
//        System.out.println(bros);
    }
}
