package hw3.zhenfa;

import hw3.creature.Creature;
import hw3.util.Position;

import java.util.ArrayList;

public abstract class Zhenfa {
    public static final Zhenfa HE_YI = new Heyi();
    public static final Zhenfa YAN_XING = new Yanxing();
    public static final Zhenfa HENG_E = new Henge();
    public static final Zhenfa CHANG_SHE = new Changshe();
    public static final Zhenfa YU_LIN = new Yulin();
    public static final Zhenfa FANG_YUAN = new Fangyuan();
    public static final Zhenfa YAN_YUE = new Yanyue();
    public static final Zhenfa FENG_SHI = new Fengshi();

    // package access
    // there is no need to create an instance outside
    Zhenfa() {
    }

    public abstract void buZhen(ArrayList<? extends Creature> creatures, Position leaderPos);
}
