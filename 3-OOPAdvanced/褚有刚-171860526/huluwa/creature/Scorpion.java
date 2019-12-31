package huluwa.creature;


import huluwa.util.Position;
import huluwa.zhenfa.Zhenfa;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Scorpion extends Creature{
    public Scorpion() {
        super("蝎子精", new ImageIcon("pics/蝎子精.jpg").getImage());
    }

    public void buZhen(Position[][] positions, ArrayList<Minion> minions) {
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(this);
        creatures.addAll(minions);
        switch(new Random().nextInt(8)) {
            case 0:
                Zhenfa.HE_YI.buZhen(positions, creatures, new Position(7, 5));
                break;
            case 1:
                Zhenfa.YAN_XING.buZhen(positions, creatures, new Position(4, 6));
                break;
            case 2:
                Zhenfa.HENG_E.buZhen(positions, creatures, new Position(6, 2));
                break;
            case 3:
                Zhenfa.CHANG_SHE.buZhen(positions, creatures, new Position(5, 1));
                break;
            case 4:
                Zhenfa.YU_LIN.buZhen(positions, creatures, new Position(5, 2));
                break;
            case 5:
                Zhenfa.FANG_YUAN.buZhen(positions, creatures, new Position(5, 4));
                break;
            case 6:
                Zhenfa.YAN_YUE.buZhen(positions, creatures, new Position(5, 5));
                break;
            case 7:
                Zhenfa.FENG_SHI.buZhen(positions, creatures, new Position(7, 2));
                break;
            default: throw new RuntimeException(); // it will never reaches here
        }
    }
}
