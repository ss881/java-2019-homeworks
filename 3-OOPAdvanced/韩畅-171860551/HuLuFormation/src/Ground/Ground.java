package Ground;

import java.util.Vector;

import Unit.Unit;

// 大写地面类，承载所有需要在地面上移动变阵的单位
public class Ground {
    OnePieceOfGround[][] ground;
    // 实际内部用一个二维数组实现
    int wid;
    int len;
    // 记录其长度宽度
    Vector<Unit> unitOnTheGround;
    // 通过一个vector结构快速记录所有的地面上的单位

    String grOutlook;

    // 通过一个String类的属性，模拟地面对外的外观，实际上算是输出的缓冲区
    public Ground(int w, int l) {
        wid = w;
        len = l;
        ground = new OnePieceOfGround[len][wid];
        grOutlook = new String("");

        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < wid; ++j) {
                ground[i][j] = new OnePieceOfGround();
            }
        }

        unitOnTheGround = new Vector<Unit>();
    }

    // 初始化的构造函数
    public void showGround(boolean myName, boolean myforce, boolean myCond, boolean mySeq, boolean myColor) {
        // System.out.println(
        // "——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
        grOutlook = new String("\n");
        grOutlook = grOutlook.concat(String.valueOf(
                "——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————\n"));
        // 逐行将需要输出的信息输入到grOutlook这个缓冲区中
        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < wid; ++j) {
                grOutlook = ground[i][j].show(grOutlook, myName, false, false, true, false, false);
            }
            // System.out.print("\n");
            grOutlook = grOutlook.concat("\n");
            for (int j = 0; j < wid; ++j) {
                grOutlook = ground[i][j].show(grOutlook, false, myforce, false, false, false, false);
            }
            grOutlook = grOutlook.concat("\n");
            // System.out.print("\n");
            for (int j = 0; j < wid; ++j) {
                grOutlook = ground[i][j].show(grOutlook, false, false, myCond, false, false, false);
            }
            // System.out.print("\n");
            grOutlook = grOutlook.concat("\n");
            for (int j = 0; j < wid; ++j) {
                grOutlook = ground[i][j].show(grOutlook, false, false, false, false, mySeq, false);
            }
            // System.out.print("\n");
            grOutlook = grOutlook.concat("\n");
            for (int j = 0; j < wid; ++j) {
                grOutlook = ground[i][j].show(grOutlook, false, false, false, false, false, myColor);
            }
            // System.out.print("\n");
            grOutlook = grOutlook.concat("\n");
        }
        // System.out.println(
        // "——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
        grOutlook = grOutlook.concat(String.valueOf(
                "——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————\n"));

        System.out.print(grOutlook);
        grOutlook = "";
        // 每次输出后，将缓冲区置空
    }

    // 返回一个地面的简单情况，显示某位置是否有某派系的障碍物
    // 如果没有，默认先将地面值（象征走到该位置需要的步数）置为无限步，方便单位自身进行寻路操作
    public int[][] getGroundObstacles() {
        int[][] gr = new int[len][wid];
        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < wid; ++j) {
                if (ground[i][j].isThisGroundAvailable())
                    gr[i][j] = 0x10000;
                else {
                    if (ground[i][j].unitOnTheGround.getForce() == 0)
                        gr[i][j] = -1;
                    else
                        gr[i][j] = -2;
                }
            }
        }
        return gr;
    }

    // 找到某个单位在地面上的坐标并返回
    // 实际上地面没有这样的功能，但是单位作为其调用者，可以通过视觉进行局部定位
    // 也即严谨来说，地面应该返回给单位一个其视野范围内的投影，之后单位自行查找自身的位置
    // 但是其过程实现略为繁复，有失部分效率性，以后的修正版可以考虑改过来
    public void getUnitPos(Unit u, Position pos) {
        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < wid; ++j) {
                if (ground[i][j].unitOnTheGround == u) {
                    pos.x = i;
                    pos.y = j;
                    return;
                }
            }
        }
        pos = null;
    }

    // 这个其实也应该返回一个单位序列让该单位自己寻找
    // 但是过于繁琐，容后慢慢实现
    public boolean isThisUnitOnTheGround(Unit u) {
        for (int i = 0; i < unitOnTheGround.size(); ++i) {
            if (unitOnTheGround.elementAt(i) == u)
                return true;
        }
        return false;
    }

    // 获得宽度
    public int getWid() {
        return wid;
    }

    // 获得长度
    public int getLen() {
        return len;
    }

    // 获得单位数量
    public int getUnitNum() {
        return unitOnTheGround.size();
    }

    // 某个单位移动出地面的函数
    public boolean standOut(Unit u) {
        if (isThisUnitOnTheGround(u)) {
            Position pos = new Position(-1, -1);
            getUnitPos(u, pos);
            if (pos.x == -1)
                return false;
            ground[pos.xx()][pos.yy()].standOut();
            unitOnTheGround.remove(u);

            // 理论上应该在这里判断是否能走下去
            String printStr = new String();
            printStr = u.getMyInfo(printStr, true, true, true, true, true);
            printStr = printStr.concat("has move out of the ground");
            System.out.print(printStr);
            // 打印一下调试信息
            return true;
        } else
            return false;
    }

    public boolean moveUnitToThePos(Unit u, int x, int y) {
        if (!ground[x][y].isThisGroundAvailable())
            return false;
        if (isThisUnitOnTheGround(u)) {
            Position pos = new Position(0, 0);
            getUnitPos(u, pos);
            ground[pos.xx()][pos.yy()].standOut();
            ground[x][y].standOnIt(u);
            // 理论上应该判断一下是否能够进行移动
            String printStr = new String();
            printStr = printStr.concat("The Unit :");
            printStr = u.getMyInfo(printStr, true, true, true, true, true);
            printStr = printStr
                    .concat(String.format("has move from (%d, %d) to: (%d, %d)\n", pos.xx(), pos.yy(), x, y));
            System.out.print(printStr);
            // 每次的移动，都需要刷新一下视图
            showGround(true, true, true, true, true);
            return true;
        } else {

            for (int i = 0; i < wid; ++i) {
                if (ground[0][i].isThisGroundAvailable()) {
                    ground[0][i].standOnIt(u);
                    unitOnTheGround.add(u);

                    // 同样，刷新视野
                    String printStr = new String();
                    printStr = printStr.concat("The Unit :");
                    printStr = u.getMyInfo(printStr, true, true, true, true, true);
                    printStr = printStr.concat(String.format("has stood on the ground: (%d, %d)\n", 0, i));
                    System.out.print(printStr);
                    showGround(true, true, true, true, true);

                    // 能走到相应的位置上时，就走上去
                    // 否则，不要动，从原位置走下去
                    if (u.findYourPathTo(new Position(x, y)))
                        return true;
                    else {
                        u.standOut();
                    }
                }
            }
            return true;
        }
    }
}

// 一片可以承载一个单位的地面类
class OnePieceOfGround {
    Unit unitOnTheGround;
    // 一个地面上可以站一个单位
    public OnePieceOfGround() {
        unitOnTheGround = null;
    }
    // 默认构造函数
    public boolean isThisGroundAvailable() {
        if (unitOnTheGround == null)
            return true;
        else
            return false;
    }
    // 这片地面是否可以再站一个人了

    public boolean standOnIt(Unit u) {
        if (!isThisGroundAvailable())
            return false;
        else {
            unitOnTheGround = u;
            return true;
        }
    }
    // 将单位移动到该位置上

    public Unit standOut() {
        Unit tem = unitOnTheGround;
        unitOnTheGround = null;
        return tem;
    }
    // 将单位移动出该块地面
    // 同时返回该单位

    public String show(String grOutlook, boolean myName, boolean myforce, boolean myCond, boolean isVoid, boolean mySeq,
            boolean myColor) {
        if (!isThisGroundAvailable()) {
            return unitOnTheGround.getMyInfo(grOutlook, myName, myforce, myCond, mySeq, myColor);
        } else {
            if (isVoid) {
                grOutlook = grOutlook.concat(String.format("%-13s", "|------------"));
                // System.out.printf("%-13s", "|------------");
            } else {
                grOutlook = grOutlook.concat(String.format("%-13s", "|            "));
                // System.out.printf("%-13s", "| ");
            }
            return grOutlook;
        }
    }
    // 展示一下自己这片地面
}