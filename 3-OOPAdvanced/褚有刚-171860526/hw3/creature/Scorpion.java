package hw3.creature;

import hw3.util.Position;
import hw3.zhenfa.Zhenfa;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Scorpion extends Creature{
    public Scorpion() {
        super("蝎子精", new ImageIcon("pics/蝎子精.jpg").getImage());
//        setImage();
    }

    public void buZhen(ArrayList<Minion> minions) {
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(this);
        creatures.addAll(minions);
        switch(new Random().nextInt(8)) {
            case 0:
                Zhenfa.HE_YI.buZhen(creatures, new Position(7, 5));
                break;
            case 1:
                Zhenfa.YAN_XING.buZhen(creatures, new Position(4, 6));
                break;
            case 2:
                Zhenfa.HENG_E.buZhen(creatures, new Position(6, 2));
                break;
            case 3:
                Zhenfa.CHANG_SHE.buZhen(creatures, new Position(5, 1));
                break;
            case 4:
                Zhenfa.YU_LIN.buZhen(creatures, new Position(5, 2));
                break;
            case 5:
                Zhenfa.FANG_YUAN.buZhen(creatures, new Position(5, 4));
                break;
            case 6:
                Zhenfa.YAN_YUE.buZhen(creatures, new Position(5, 5));
                break;
            case 7:
                Zhenfa.FENG_SHI.buZhen(creatures, new Position(7, 2));
                break;
            default: throw new RuntimeException(); // it will never reaches here
        }
    }
}
