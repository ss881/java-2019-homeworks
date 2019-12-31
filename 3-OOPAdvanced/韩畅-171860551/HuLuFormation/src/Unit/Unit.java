package Unit;

import Ground.Ground;
import Ground.Position;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Unit {
    String name;
    int force;
    // 阵营 0 为正， 其他为黑
    int myCondition;
    public static Ground myGr;
    // 这里的condition是为了以后的扩展做的铺垫
    // 很显然，如果做成游戏，单位没有血量怎么想都不太可能
    // 而myGr作为static类型，很符合单位和承载其的地面之间的关系
    // 所有的单位共享同一片的地面

    public Unit() {
        force = -1;
        myCondition = 100;
        myGr = null;
        name = "untitled";
    }
    // 以防意外的默认初始构造函数
    public Unit(String n, int f, int cond) {
        name = n;
        force = f;
        myCondition = cond;
        myGr = null;
    }

    public Unit(String n, int f, int cond, Ground g) {
        name = n;
        force = f;
        myCondition = cond;
        myGr = g;
    }
    // 真正使用的构造方法


    protected void changeForce(int f) {
        force = f;
    }
    // 同样的铺垫——为以后七娃可能的叛变做准备

    public int getForce() {
        return force;
    }
    // 告诉你我的阵营

    public int getMyCondition() {
        return myCondition;
    }
    // 告诉你我的状态

    public String getMyInfo(String grOutlook, boolean myName, boolean myforce, boolean myCond, boolean mySeq, boolean myColor) {
        if (myName) {
            grOutlook = grOutlook.concat(String.format("|%-12s", name));
            //System.out.printf("|%-12s", name);
        }
        if (myforce) {
            grOutlook = grOutlook.concat(String.format("|%-12s", force == 0 ? "DECENT" : "DARK"));
            //System.out.printf("|%-12s", force == 0 ? "DECENT" : "DARK");
        }
        if (myCond) {
            grOutlook = grOutlook.concat(String.format("|%-12d", myCondition));
            //System.out.printf("|%-12d", myCondition);
        }
        if (myColor) {
            grOutlook = grOutlook.concat(String.format("%-13s", "| - - - - - -"));
            //System.out.printf("|%-12d", myCondition);
        }
        if (mySeq) {
            grOutlook = grOutlook.concat(String.format("%-13s", "| - - - - - -"));
            //System.out.printf("|%-12d", myCondition);
        }
        return grOutlook;
    }
    // 返回格式化的自身信息，其接口显示了需要返回什么样的字符串信息

    public Position whereAmI() {
        if (myGr == null)
            return null;
        else if (!myGr.isThisUnitOnTheGround(this))
            return null;
        else {
            Position tem = new Position(0, 0);
            myGr.getUnitPos(this, tem);
            return tem;
        }
    }
    // 与地面类进行交互，从而得知自己的位置——其实有一个模拟观察地面的过程

    public boolean standOut() {
        if (!myGr.isThisUnitOnTheGround(this))
            return true;
            // 我确实走下去了
        else{
            Position myPos = new Position(-1, -1);
            myGr.getUnitPos(this, myPos);
            if (myPos.xx() == 0 || myPos.yy() == 0 || myPos.xx() == myGr.getLen()-1 ||myPos.yy() == myGr.getWid()-1){
                myGr.standOut(this);
                return true;
            }
            // 检查是否处于边界位置，从而判断能否走下去
        }


        // 之前没有返回，则证明在地面上且没有能够在边界
        for (int i = 0; i < myGr.getWid(); ++i) {
            if (findYourPathTo(new Position(0, i))) {
                myGr.standOut(this);
                return true;
                // 如果能走到这样的边界，那就走下去，并返回可以走下去的标志
            }
        }
        for (int i = 0; i < myGr.getWid(); ++i) {
            if (findYourPathTo(new Position(myGr.getLen() - 1, i))) {
                myGr.standOut(this);
                return true;
                // 同理，走到下边界也行
            }
        }
        return false;
    }

    public boolean findYourPathTo(Position dstPos) {
        if (myGr == null)
            return false;
            // 我都不知道地面是啥，咋走
        else {
            if (!myGr.isThisUnitOnTheGround(this))
                return myGr.moveUnitToThePos(this, dstPos.xx(), dstPos.yy());
                // 待改正，不应该让gr的类放上去后再调用本身的寻路，思路上多绕了一圈
            else {
                int gr[][] = myGr.getGroundObstacles();
                // 这个就是直接看地面上的单位情况了
               
                Position myPos = whereAmI();
                // 顺便找找自己位子
                if (myPos.xx() == dstPos.xx() && myPos.yy() == dstPos.yy()){
                    return true;
                }
                // 自己本来就在目的地上，不用走了直接就OK

                gr[myPos.xx()][myPos.yy()] = 0;
                // 把自己所在的位置设置为0步可达的状态
                if (gr[dstPos.xx()][dstPos.yy()] < 0)
                    return false;
                // 如果自己本来不在目的地上，但是目的地上却有一个单位阻挡，那就不能移动过去了

                // 来自数据结构课的无情眼神
                // 老师，这里如果要真模拟现实的寻路，我可能需要模拟一个大脑视觉处理系统
                // 我的电脑可能得跑死
                // 还是且让我用广搜寻路吧
                Queue<Position> myQue = new LinkedList<Position>();
                myQue.offer(myPos);
                while (!myQue.isEmpty()) {
                    Position temPos = myQue.poll();
                    int temx = temPos.xx();
                    int temy = temPos.yy();
                    int val = gr[temx][temy] + 1;
                    // 代表步数的变量

                    if (temx - 1 >= 0 && gr[temx - 1][temy] > val) {
                        gr[temx - 1][temy] = val;
                        myQue.offer(new Position(temx - 1, temy));
                    }
                    if (temx + 1 < myGr.getLen() && gr[temx + 1][temy] > val) {
                        gr[temx + 1][temy] = val;
                        myQue.offer(new Position(temx + 1, temy));
                    }
                    if (temy - 1 >= 0 && gr[temx][temy - 1] > val) {
                        gr[temx][temy - 1] = val;
                        myQue.offer(new Position(temx, temy - 1));
                    }
                    if (temy + 1 < myGr.getWid() && gr[temx][temy + 1] > val) {
                        gr[temx][temy + 1] = val;
                        myQue.offer(new Position(temx, temy + 1));
                    }
                    // 朝四个方向寻路，有路的就压入队列
                }

                int dstx = dstPos.xx();
                int dsty = dstPos.yy();
                if (gr[dstx][dsty] == 0x10000) {
                    return false;
                    // 如果发现目的地还是一个恐怖的无限步的值，那显然直接走走不到，先返回一个无法走到的变量
                } else {
                    // int step = gr[dstx][dsty];
                    Stack<Position> steps = new Stack<Position>();
                    steps.push(dstPos);
                    // 用一个栈从目标点反向寻路

                    while (gr[steps.peek().xx()][steps.peek().yy()] > 1) {
                        int temx = steps.peek().xx();
                        int temy = steps.peek().yy();
                        int val = gr[temx][temy] - 1;

                        if (temx - 1 >= 0 && gr[temx - 1][temy] == val) {
                            steps.push(new Position(temx - 1, temy));
                            continue;
                        } else if (temx + 1 < myGr.getLen() && gr[temx + 1][temy] == val) {
                            steps.push(new Position(temx + 1, temy));
                            continue;
                        } else if (temy - 1 >= 0 && gr[temx][temy - 1] == val) {
                            steps.push(new Position(temx, temy - 1));
                            continue;
                        } else if (temy + 1 < myGr.getWid() && gr[temx][temy + 1] == val) {
                            steps.push(new Position(temx, temy + 1));
                            continue;
                        }
                        // 找上一步能走到哪
                        // 找到一个以后就继续循环了
                    }
                    while (!steps.empty()) {
                        Position temPos = steps.pop();
                        myGr.moveUnitToThePos(this, temPos.xx(), temPos.yy());
                    }
                    // 于是一步步走过去就好
                    return true;
                }
            }
        }
    }

}
