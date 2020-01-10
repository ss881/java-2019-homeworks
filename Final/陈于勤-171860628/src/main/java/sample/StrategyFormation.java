package sample;
import javafx.util.Pair;

import java.util.*;

public class StrategyFormation{
    private String name;
    private int id;
    static private StrategyFormation[] strategyFormations = null;

    static public StrategyFormation getFormation(int i) {
        if (i < strategyFormations.length)
            return strategyFormations[i];
        return null;
    }
    public Vector<Pair<Integer, Integer>> getImplement(int width, int heigh, int number, boolean group) {
        int sign = (group == true) ? -1 : 1;
        switch (id) {
            case 0: return getHeYiZhen(width / 2 + width / 4 * sign, heigh * 3 / 5, number);
            case 1: return getYanXingZhen(width / 2 + width * 2 / 5 * sign, heigh / 4, number, group);
            case 2: return getChongEZhen(width / 2 + width / 4 * sign, heigh / 4, number);
            case 3: return getChangSheZhen(width / 2 + width / 4 * sign, heigh / 4, number);
            case 4: return getYuLinZhen(width / 2 + width / 4 * sign, heigh / 2 - 1, number, group);
            case 5: return getFangYuanZhen(width / 2 + width / 4 * sign, heigh * 2 / 5, number);
            case 6: return getYanYueZhen(width / 2 + width / 4 * sign, heigh / 2 - 1, number, group);
            case 7: return getFengShiZhen(width / 2 + width / 6 * sign, heigh / 2 - 1, number, group);
            default: return getChangSheZhen(width / 2 + width / 4 * sign, heigh / 4, number);
        }
    }
    private StrategyFormation(String name, int id) {
        this.name = name;
        this.id = id;
        if (strategyFormations == null) {
            strategyFormations = new StrategyFormation[id + 1];
            for (int i = 0; i < strategyFormations.length; i++)
                strategyFormations[i] = null;
            strategyFormations[id] = this;
        }
        else {
            if (strategyFormations.length <= id) {
                StrategyFormation[] temp = new StrategyFormation[id + 1];
                for (int i = strategyFormations.length; i < temp.length; i++)
                    temp[i] = null;
                for (int i = 0; i < strategyFormations.length; i++)
                    temp[i] = strategyFormations[i];
                temp[id] = this;
                strategyFormations = temp;
            }
            else
                strategyFormations[id] = this;
        }
    }
    public String toString() {
        return name;
    }
    public int toNumber() {
        return id;
    }

    public static final StrategyFormation
            HE_YI_ZHEN = new StrategyFormation("HeYiZhen", 0)
            , YAN_XING_ZHEN = new StrategyFormation("YanXingZhen", 1)
            , CHONG_E_ZHEN = new StrategyFormation("ChongEZhen", 2)
            , CHANG_SHE_ZHEN = new StrategyFormation("ChangSheZhen", 3)
            , YU_LIN_ZHEN = new StrategyFormation("YuLinZhen", 4)
            , FANG_YUAN_ZHEN = new StrategyFormation("FangYuanZhen", 5)
            , YAN_YUE_ZHEN = new StrategyFormation("YanYueZhen", 6)
            , FENG_SHI_ZHEN = new StrategyFormation("FengShiZhen", 7)
            ;
    public final static int HeYiZhen = 0, YanXingZhen = 1, ChongEZhen = 2, ChangSheZhen = 3,
                            YuLinZhen = 4, FangYuanZhen = 5, YanYueZhen = 6, FengShiZhen = 7;
    public final static int MAX_NUM = 8;

    //获得鹤翼阵的节点数组信息，阵型为V字形，
    // 传入的节点为指定最低点的位置，int整形指定顶点的个数
    private Vector<Pair<Integer, Integer>> getHeYiZhen(int pos_x, int pos_y, Integer members) {
        if (members <= 0)
            return null;
        Vector<Pair<Integer, Integer>> ret = new Vector<Pair<Integer, Integer>>();
        ret.add(new Pair<Integer, Integer>(pos_x, pos_y));
        for (int i = 0; i < members - 1; i++) {
            int nextx = pos_x - i / 2 - 1;
            int nexty = (i % 2 == 1) ? (pos_y - i / 2  - 1) : (pos_y + i / 2 + 1);
            ret.add(new Pair<Integer, Integer>(nextx, nexty));
        }
        if (ret.size() != members) {
            Exception e = new Exception("阵型错误" + ret.size());
            e.printStackTrace();
            System.exit(1);
        }
        return ret;
    }

    //获得雁行阵的节点数组信息，阵型为45度角的单斜行，
    // 传入的节点为指定上方顶点的位置，int整形指定顶点的个数
    //其中节点的第三个字段决定方向，小于零左高右低，大于零座低右高
    private Vector<Pair<Integer, Integer>> getYanXingZhen(int pos_x, int pos_y, Integer members, boolean group) {
        if (members <= 0)
            return null;
        Vector<Pair<Integer, Integer>> ret = new Vector<Pair<Integer, Integer>>();
        if (group == false) {
            for (int i = 0; i < members; i++) {
                ret.add(new Pair<Integer, Integer>(pos_x - i, pos_y + i));
            }
        }
        else {
            for (int i = 0; i < members; i++) {
                ret.add(new Pair<Integer, Integer>(pos_x + i, pos_y + i));
            }
        }
        if (ret.size() != members) {
            Exception e = new Exception("阵型错误" + ret.size());
            e.printStackTrace();
            System.exit(1);
        }
        return ret;
    }
    //获得衝轭阵的节点数组信息，阵型为一前一后的波浪纵行队伍
    // 传入的节点为指定最高的位置，int整形指定顶点的个数
    private Vector<Pair<Integer, Integer>> getChongEZhen(int pos_x, int pos_y, Integer members) {
        if (members <= 0)
            return null;
        Vector<Pair<Integer, Integer>> ret = new Vector<Pair<Integer, Integer>>();
        for (int i = 0; i < members; i++) {
            ret.add(new Pair<Integer, Integer>(pos_x - i % 2, pos_y + i));
        }
        if (ret.size() != members) {
            Exception e = new Exception("阵型错误" + ret.size());
            e.printStackTrace();
            System.exit(1);
        }
        return ret;
    }
    //获得长蛇阵的节点数组信息，阵型为1字形，
    // 传入的节点为指定最高的位置，int整形指定顶点的个数
    private Vector<Pair<Integer, Integer>> getChangSheZhen(int pos_x, int pos_y, Integer members) {
        if (members <= 0)
            return null;
        Vector<Pair<Integer, Integer>> ret = new Vector<Pair<Integer, Integer>>();
        for (int i = 0; i < members; i++) {
            ret.add(new Pair<Integer, Integer>(pos_x, pos_y + i));
        }
        if (ret.size() != members) {
            Exception e = new Exception("阵型错误" + ret.size());
            e.printStackTrace();
            System.exit(1);
        }
        return ret;
    }
    //获得鱼鳞阵的节点数组信息，阵型为椭圆凸形
    // 传入的节点为指定大将的位置（阵型最前方的中间位置），int整形指定顶点的个数
    //节点中的第三个字段决定阵型朝向，小于零朝右，大于零朝左
    private Vector<Pair<Integer, Integer>> getYuLinZhen(int pos_x, int pos_y, Integer members, boolean group) {
        if (members <= 0)
            return null;
        Vector<Pair<Integer, Integer>> ret =  new Vector<Pair<Integer, Integer>>();
        int sign = (group == false) ? 1 : -1;
        int multiply = (members - 1) / 16 + 1;
        for (int i = 0; i < members; i++) {
            if (i < multiply) {
                ret.add(new Pair<Integer, Integer>(pos_x, pos_y + i - multiply / 2));
            }
            else if (i < 4 * multiply) {
                if (i == members - 1)
                    ret.add(new Pair<Integer, Integer>(pos_x + sign * 2, pos_y));
                else
                    ret.add(new Pair<Integer, Integer>(pos_x + sign, pos_y + i - multiply * 3 / 2 - multiply));
            }
            else if (i < 9 * multiply) {
                if (i == members - 1)
                    ret.add(new Pair<Integer, Integer>(pos_x + sign * 3, pos_y));
                else
                    ret.add(new Pair<Integer, Integer>(pos_x + sign * 2, pos_y + i - multiply * 5 / 2 - 4 * multiply));
            }
            else {
                if (i == members - 1)
                    ret.add(new Pair<Integer, Integer>(pos_x + sign * 4, pos_y));
                else
                    ret.add(new Pair<Integer, Integer>(pos_x + sign * 3, pos_y + i - multiply * 7 / 2 - 9 * multiply));
            }
        }
        if (ret.size() != members) {
            Exception e = new Exception("阵型错误" + ret.size());
            e.printStackTrace();
            System.exit(1);
        }
        return  ret;
    }
    //获得方円阵的节点数组信息，阵型为转45度的正方形，
    // 传入的节点为指定最高点的位置，int整形指定顶点的个数(自动向下取整为四的倍数)
    private Vector<Pair<Integer, Integer>> getFangYuanZhen(int pos_x, int pos_y, Integer members) {
        if (members <= 0)
            return null;
        int length = (members + 3) / 4;
        Vector<Pair<Integer, Integer>> ret = new Vector<Pair<Integer, Integer>>();
        for (int i = 0; i < length; i++) {
            ret.add(new Pair<Integer, Integer>(pos_x + i, pos_y + i));
            ret.add(new Pair<Integer, Integer>(pos_x + length - i, pos_y + length + i));
            ret.add(new Pair<Integer, Integer>(pos_x - i, pos_y + 2 * length - i));
            ret.add(new Pair<Integer, Integer>(pos_x - length + i, pos_y + length - i));
        }
        if (ret.size() <= members) {
            Exception e = new Exception("阵型错误" + ret.size());
            e.printStackTrace();
            System.exit(1);
        }
        return ret;
    }
    //获得偃月阵的节点数组信息，阵型为大于小于号，
    // 传入的节点为指定顶尖点的位置，int整形指定顶点的个数
    //节点中的第三个字段决定阵型朝向，小于零顶尖朝左，大于零顶尖朝右
    private Vector<Pair<Integer, Integer>> getYanYueZhen(int pos_x, int pos_y, Integer members, boolean group) {
        if (members <= 0)
            return null;
        int singlelength = (members - 3) / 6;
        int rest = members - singlelength * 6 - 3;
        Vector<Pair<Integer, Integer>> ret = new Vector<Pair<Integer, Integer>>();
        int sign = (group == false) ? 1 : -1;
        int basicx = pos_x;
        pos_x = (rest <= 0) ? basicx : basicx + sign;
        for (int i = 0; i < 3 && i < members; i++) {
            ret.add(new Pair<Integer, Integer>(pos_x + i * sign, pos_y));
        }
        for (int i = 0; i < singlelength; i++) {
            ret.add(new Pair<Integer, Integer>(pos_x - i * sign, pos_y - (1 + i)));
            ret.add(new Pair<Integer, Integer>(pos_x - i * sign, pos_y + (1 + i)));
            ret.add(new Pair<Integer, Integer>(pos_x - (1 + i) * sign, pos_y - (1 + i)));
            ret.add(new Pair<Integer, Integer>(pos_x - (1 + i) * sign, pos_y + (1 + i)));
            ret.add(new Pair<Integer, Integer>(pos_x - (2 + i) * sign, pos_y - (1 + i)));
            ret.add(new Pair<Integer, Integer>(pos_x - (2 + i) * sign, pos_y + (1 + i)));
        }
        for (int i = 0; i < rest; i++) {
            int next = rest / 2 - i;
            ret.add(new Pair<Integer, Integer>(basicx + next * sign, pos_y - next));
        }
        if (ret.size() != members) {
            Exception e = new Exception("阵型错误" + ret.size());
            e.printStackTrace();
            System.exit(1);
        }
        return ret;
    }
    //获得锋矢阵的节点数组信息，阵型为箭矢形，
    // 传入的节点为指定箭头顶尖点的位置，int整形指定顶点的个数
    //节点中的第三个字段决定阵型朝向，小于零顶尖朝右，大于零顶尖朝左
    private Vector<Pair<Integer, Integer>> getFengShiZhen(int pos_x, int pos_y, Integer members, boolean group) {
        if (members <= 0)
            return null;
        Vector<Pair<Integer, Integer>> ret = new Vector<Pair<Integer, Integer>>();
        int length = (members + 3) / 4;
        int rest = members - length * 2;
        int sign = (group == false) ? 1 : -1;
        ret.add(new Pair<Integer, Integer>(pos_x, pos_y));
        for (int i = 0; i < length; i++) {
            ret.add(new Pair<Integer, Integer>(pos_x + (i + 1) * sign, pos_y + (i + 1)));
            ret.add(new Pair<Integer, Integer>(pos_x + (i + 1) * sign, pos_y - (i + 1)));
        }
        for (int i = 0; i < rest; i++) {
            ret.add(new Pair<Integer, Integer>(pos_x + (i + 1) * sign, pos_y));
        }
        if (ret.size() <= members) {
            Exception e = new Exception("阵型错误" + ret.size());
            e.printStackTrace();
            System.exit(1);
        }
        return ret;
    }
}