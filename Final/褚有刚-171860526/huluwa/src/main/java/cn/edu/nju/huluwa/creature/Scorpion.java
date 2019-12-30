package cn.edu.nju.huluwa.creature;

import cn.edu.nju.huluwa.position.Position;
import cn.edu.nju.huluwa.huluworld.HuluWorld;
import cn.edu.nju.huluwa.zhenfa.Zhenfa;

import java.util.ArrayList;
import java.util.Random;

public class Scorpion extends Creature implements Leader {
    private Zhenfa prevForm;
    private Position prevPos;
    public Scorpion(HuluWorld huluWorld, Colour color) {
        super(huluWorld, "蝎子精", color);
//        super("蝎子精", new ImageIcon("pics/蝎子精.jpg").getImage());
    }

    @Override
    public void buZhen(Position[][] positions, ArrayList<? extends Creature> minions) {
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(this);
        creatures.addAll(minions);
        switch (new Random().nextInt(8)) {
            case 0:
                prevForm = Zhenfa.HE_YI;
                prevPos = new Position(7, 5);
                Zhenfa.HE_YI.buZhen(positions, creatures, new Position(7, 5));
                break;
            case 1:
                prevForm = Zhenfa.YAN_XING;
                prevPos = new Position(4, 6);
                Zhenfa.YAN_XING.buZhen(positions, creatures, new Position(4, 6));
                break;
            case 2:
                prevForm = Zhenfa.HENG_E;
                prevPos = new Position(6, 2);
                Zhenfa.HENG_E.buZhen(positions, creatures, new Position(6, 2));
                break;
            case 3:
                prevForm = Zhenfa.CHANG_SHE;
                prevPos = new Position(5, 1);
                Zhenfa.CHANG_SHE.buZhen(positions, creatures, new Position(5, 1));
                break;
            case 4:
                prevForm = Zhenfa.YU_LIN;
                prevPos = new Position(5, 2);
                Zhenfa.YU_LIN.buZhen(positions, creatures, new Position(5, 2));
                break;
            case 5:
                prevForm = Zhenfa.FANG_YUAN;
                prevPos = new Position(5, 4);
                Zhenfa.FANG_YUAN.buZhen(positions, creatures, new Position(5, 4));
                break;
            case 6:
                prevForm = Zhenfa.YAN_YUE;
                prevPos = new Position(5, 5);
                Zhenfa.YAN_YUE.buZhen(positions, creatures, new Position(5, 5));
                break;
            case 7:
                prevForm = Zhenfa.FENG_SHI;
                prevPos = new Position(7, 2);
                Zhenfa.FENG_SHI.buZhen(positions, creatures, new Position(7, 2));
                break;
            default:
                throw new RuntimeException(); // it will never reaches here
        }
    }

    @Override
    public void setInitialPos(Position[][] positions, ArrayList<? extends Creature> minions) {
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(this);
        creatures.addAll(minions);
        if(prevForm != null) prevForm.buZhen(positions, creatures, prevPos);
        else Zhenfa.HENG_E.buZhen(positions, creatures, new Position(6, 2));
    }

    @Override
    public boolean isEnemy(Creature creature) {
        return creature instanceof Grandpa || creature instanceof Huluwa;
    }
}
