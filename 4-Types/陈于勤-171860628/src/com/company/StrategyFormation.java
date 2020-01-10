package com.company;
import java.util.*;

public class StrategyFormation {
    private String name;
    private int id;
    static private StrategyFormation[] strategyFormations = null;

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

    static public StrategyFormation getRandomFormation() {
        Random rand = new Random(new Date().getTime());
        int pos = 0;
        do {
            pos = rand.nextInt(strategyFormations.length);
        } while (strategyFormations[pos] == null);
        return strategyFormations[pos];
    }
    static public StrategyFormation getTheFormation(int i) {
        if (i < strategyFormations.length)
            return strategyFormations[i];
        return null;
    }
    public StrategyFormation getNextFormation() {
        for (int i = id + 1; i < strategyFormations.length; i++) {
            if (strategyFormations[i] != null)
                return strategyFormations[i];
        }
        for (int i = 0; i <= id; i++) {
            if (strategyFormations[i] != null)
                return strategyFormations[i];
        }
        return this;
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

    //获得鹤翼阵的节点数组信息，阵型为V字形，
    // 传入的节点为指定最低点的位置，int整形指定顶点的个数
    public static Position<Integer>[] getHeYiZhen(Position<Integer> position, Integer members) {
        if (members <= 0)
            return null;
        Position<Integer>[] ret = new Position[members];
        int x = position.getX();
        int y = position.getY();
        ret[members - 1] = position;
        for (int i = 0; i < members - 2; i++) {
            int nextx = x - i / 2 - 1;
            int nexty = (i % 2 == 1) ? (y - i / 2  - 1) : (y + i / 2 + 1);
            ret[i] = new Position<Integer>(nextx, nexty);
        }
        return ret;
    }

    //获得雁行阵的节点数组信息，阵型为45度角的单斜行，
    // 传入的节点为指定上方顶点的位置，int整形指定顶点的个数
    //其中节点的第三个字段决定方向，小于零左高右低，大于零座低右高
    public static Position<Integer>[] getYanXingZhen(Position<Integer> position, Integer members) {
        if (members <= 0)
            return null;
        Position<Integer>[] ret = new Position[members];
        int x = position.getX();
        int y = position.getY();
        if (position.getPath() >= 0) {
            for (int i = 0; i < members; i++) {
                ret[i] = new Position<Integer>(x - i, y + i);
            }
        }
        else {
            for (int i = 0; i < members; i++) {
                ret[i] = new Position<Integer>(x + i, y + i);
            }
        }
        return ret;
    }
    //获得衝轭阵的节点数组信息，阵型为一前一后的波浪纵行队伍
    // 传入的节点为指定最高的位置，int整形指定顶点的个数
    public static Position<Integer>[] getChongEZhen(Position<Integer> position, Integer members) {
        if (members <= 0)
            return null;
        Position<Integer>[] ret = new Position[members];
        int x = position.getX();
        int y = position.getY();
        for (int i = 0; i < members; i++) {
            ret[i] = new Position<Integer>(x - i % 2, y + i);
        }
        return ret;
    }
    //获得长蛇阵的节点数组信息，阵型为1字形，
    // 传入的节点为指定最高的位置，int整形指定顶点的个数
    public static Position<Integer>[] getChangSheZhen(Position<Integer> position, Integer members) {
        if (members <= 0)
            return null;
        Position<Integer>[] ret = new Position[members];
        int x = position.getX();
        int y = position.getY();
        for (int i = 0; i < members; i++) {
            ret[i] = new Position<Integer>(x, y + i);
        }
        return ret;
    }
    //获得鱼鳞阵的节点数组信息，阵型为椭圆凸形
    // 传入的节点为指定大将的位置（阵型最前方的中间位置），int整形指定顶点的个数
    //节点中的第三个字段决定阵型朝向，小于零朝右，大于零朝左
    public static Position<Integer>[] getYuLinZhen(Position<Integer> position, Integer members) {
        if (members <= 0)
            return null;
        Position<Integer>[] ret =  new Position[members];
        int x = position.getX();
        int y = position.getY();
        int sign = (position.getPath() >= 0) ? 1 : -1;
        int multiply = (members - 1) / 16 + 1;
        for (int i = 0; i < members; i++) {
            if (i < multiply) {
                ret[i] = new Position<Integer>(x, y + i - multiply / 2);
            }
            else if (i < 4 * multiply) {
                if (i == members - 1)
                    ret[i] = new Position<Integer>(x + sign * 2, y);
                else
                    ret[i] = new Position<Integer>(x + sign, y + i - multiply * 3 / 2 - multiply);
            }
            else if (i < 9 * multiply) {
                if (i == members - 1)
                    ret[i] = new Position<Integer>(x + sign * 3, y);
                else
                    ret[i] = new Position<Integer>(x + sign * 2, y + i - multiply * 5 / 2 - 4 * multiply);
            }
            else {
                if (i == members - 1)
                    ret[i] = new Position<Integer>(x + sign * 4, y);
                else
                    ret[i] = new Position<Integer>(x + sign * 3, y + i - multiply * 7 / 2 - 9 * multiply);
            }
        }
        return  ret;
    }
    //获得方円阵的节点数组信息，阵型为转45度的正方形，
    // 传入的节点为指定最高点的位置，int整形指定顶点的个数(自动向下取整为四的倍数)
    public static Position<Integer>[] getFangYuanZhen(Position<Integer> position, Integer members) {
        if (members <= 0)
            return null;
        int length = members / 4;
        Position<Integer>[] ret = new Position[length * 4];
        int x = position.getX();
        int y = position.getY();
        for (int i = 0; i < length; i++) {
            ret[i] = new Position<Integer>(x + i, y + i);
            ret[length + i] = new Position<Integer>(x + length - i, y + length + i);
            ret[length * 2 + i] = new Position<Integer>( x - i, y + 2 * length - i);
            ret[length * 3 + i] = new Position<Integer>(x - length + i, y + length - i);
        }
        return ret;
    }
    //获得偃月阵的节点数组信息，阵型为大于小于号，
    // 传入的节点为指定顶尖点的位置，int整形指定顶点的个数
    //节点中的第三个字段决定阵型朝向，小于零顶尖朝左，大于零顶尖朝右
    public static Position<Integer>[] getYanYueZhen(Position<Integer> position, Integer members) {
        if (members <= 0)
            return null;
        int singlelength = (members - 3) / 6;
        int rest = members - singlelength * 6 - 3;
        Position<Integer>[] ret = new Position[members];
        int sign = (position.getPath() >= 0) ? 1 : -1;
        int basicx = position.getX();
        int x = (rest <= 0) ? basicx : basicx + sign;
        int y = position.getY();
        for (int i = 0; i < 3 && i < members; i++) {
            ret[i] = new Position<Integer>(x + i * sign, y);
        }
        for (int i = 0; i < singlelength; i++) {
            ret[2 + i] =
                    new Position<Integer>(x - i * sign, y - (1 + i));
            ret[2 + singlelength + i] =
                    new Position<Integer>(x - i * sign, y + (1 + i));
            ret[2 + singlelength * 2 + i] =
                    new Position<Integer>(x - (1 + i) * sign, y - (1 + i));
            ret[2 + singlelength * 3 + i] =
                    new Position<Integer>(x - (1 + i) * sign, y + (1 + i));
            ret[2 + singlelength * 4 + i] =
                    new Position<Integer>(x - (2 + i) * sign, y - (1 + i));
            ret[2 + singlelength * 5 + i] =
                    new Position<Integer>(x - (2 + i) * sign, y + (1 + i));
        }
        for (int i = 0; i < rest; i++) {
            int next = rest / 2 - i;
            ret[2 + singlelength * 6 + i] =
                    new Position<Integer>(basicx + next * sign, y - next);
        }
        return ret;
    }
    //获得锋矢阵的节点数组信息，阵型为箭矢形，
    // 传入的节点为指定箭头顶尖点的位置，int整形指定顶点的个数
    //节点中的第三个字段决定阵型朝向，小于零顶尖朝右，大于零顶尖朝左
    public static Position<Integer>[] getFengShiZhen(Position<Integer> position, Integer members) {
        if (members <= 0)
            return null;
        Position<Integer>[] ret = new Position[members];
        int length = (members - 1) / 4;
        int rest = members - length * 2;
        int x = position.getX();
        int y = position.getY();
        int sign = (position.getPath() >= 0) ? 1 : -1;
        ret[members - 1] = position;
        for (int i = 0; i < length; i++) {
            ret[i] = new Position<Integer>(x + (i + 1) * sign, y + (i + 1));
            ret[length + i] = new Position<Integer>(x + (i + 1) * sign, y - (i + 1));
        }
        for (int i = 0; i < rest; i++) {
            ret[length * 2 + i] = new Position<Integer>(x + (i + 1) * sign, y);
        }
        return ret;
    }
}