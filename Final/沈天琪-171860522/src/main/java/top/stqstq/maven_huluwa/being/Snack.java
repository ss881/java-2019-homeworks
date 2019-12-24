package top.stqstq.maven_huluwa.being;

import top.stqstq.maven_huluwa.battlefiled.*;
import top.stqstq.maven_huluwa.involved.Involved;
import top.stqstq.maven_huluwa.lock.MyLock;

public class Snack implements IBeing, ICheer, IFighter {
    private int rank = 9;
    private int fightAbility = 4;
    private boolean alive = true;
    private final int combatComp = 2;//0 no combat 1 good 2 evil
    private String name = "Snack";

    public void print() {
        System.out.print('S');
    }

    public String getSymbol() {
        return "S";
    }

    public void walkTo(int x, int y) {
        int x1 = BattleField.getXByNo(rank);
        int y1 = BattleField.getYByNo(rank);
        if (x1 == x && y1 == y)
            return;
        if (x1 == -1) {
            //System.out.println("failed to find myself");
            return;
        }
        if (x1 != x && y1 != y) {
            //System.out.println("can't walk obliquely");
            return;
        }
        if (BattleField.getNo(x, y) != 0) {
            //System.out.println("target position is not empty");
            return;
        }
        BattleField.setField(x, y, rank);
        BattleField.setField(x1, y1, 0);
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public String cheer() {
        return getName() + " cheer for evils";
    }

    public int getFightAbility() {
        return fightAbility;
    }

    public int getCombatComp() {
        return combatComp;
    }

    public void setAliveState(boolean aliveState) {
        alive = aliveState;
    }

    public boolean getAliveState() {
        return alive;
    }

    public void run() {
        while (true) {
            MyLock.lock.lock();
            try {
                if (MyLock.state == 0)
                    return;
                int count1 = 0, count2 = 0;
                for (int i = 0; i < BattleField.SIZE; i++)
                    for (int j = 0; j < BattleField.SIZE; j++) {
                        if (Involved.a.get(BattleField.getNo(i, j)).getCombatComp() == 1)
                            count1++;
                        else if (Involved.a.get(BattleField.getNo(i, j)).getCombatComp() == 2)
                            count2++;
                    }
                if (count1 == 0 || count2 == 0) {
                    MyLock.state = 0;
                    return;
                }

                while (true) {//具体而言 这个循环就是为了等待外部线程有效输出棋盘后在执行棋盘操作
                    MyLock.printLock.lock();//这个锁是为了保证缓冲区的访问不出现问题
                    try {
                        if (MyLock.updateState == 0)//当前棋盘状态已被更新才进行操作
                        {
                            fightSingleStep();
                            MyLock.updateState = 1;
                            break;
                        } else
                            Thread.sleep(2);
                    } finally {
                        MyLock.printLock.unlock();
                    }
                }

                //fightSingleStep();
                // Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                MyLock.lock.unlock();
            }
        }
    }

    private int fightWith(int enemy) {
        int enemyFightAbility = Involved.a.get(enemy).getFightAbility();
        int diceNum = (int) (Math.random() * 10);//0~9
        //System.out.println(rank + " my fightAbility " + fightAbility + "  enemy " + enemyFightAbility + "  dicenum" + diceNum + "  result " + (diceNum + fightAbility - enemyFightAbility));
        if (diceNum + fightAbility - enemyFightAbility < 5)//I die
            return -1;
        else//enemy die
            return 1;
    }

    public void fightSingleStep() {
        if (alive == false) return;//0 还活着吗？
        int x1 = BattleField.getXByNo(rank);
        int y1 = BattleField.getYByNo(rank);
        int no = 0;//1 前方有无敌人？
        //System.out.println("this is " + rank + "'s turn");
        no = BattleField.getNo(x1, y1 - 1);
        if (Involved.a.get(no).getCombatComp() != 0//前方不是中立方
                && Involved.a.get(no).getAliveState()
                && combatComp != Involved.a.get(no).getCombatComp()) {//且不属于一个阵营
            if (fightWith(no) == -1) {
                alive = false;
                //BattleField.setField(x1, y1, 0);
                //Involved.printBf();
                return;
            }//i die
            else {//enemy die
                Involved.a.get(no).setAliveState(false);
                //if (combatComp == 1)
                //BattleField.setField(x1, y1 + 1, 0);
                //else if (combatComp == 2)
                //BattleField.setField(x1, y1 - 1, 0);
                //Involved.printBf();
                return;
            }
        }
        //2 上/下有无敌人
        if (x1 > 8) {//中点以下
            no = BattleField.getNo(x1 - 1, y1);
        } else {
            no = BattleField.getNo(x1 + 1, y1);
        }
        if (Involved.a.get(no).getCombatComp() != 0//前方不是中立方
                && Involved.a.get(no).getAliveState()
                && combatComp != Involved.a.get(no).getCombatComp()) {//且不属于一个阵营
            if (fightWith(no) == -1) {
                alive = false;
                //BattleField.setField(x1, y1, 0);
                //Involved.printBf();
                return;
            }//i die
            else {//enemy die
                Involved.a.get(no).setAliveState(false);
                //if (x1 > 8)
                //BattleField.setField(x1 - 1, y1, 0);
                //else if (x1 <= 8)
                //BattleField.setField(x1 + 1, y1, 0);
                //Involved.printBf();
                return;
            }
        }
        //3 前方可否前进？
        no = BattleField.getNo(x1, y1 - 1);
        if (!Involved.a.get(no).getAliveState() && y1 > 7) {
            //walkTo(x1, y1 - 1);
            //Involved.printBf();
            BattleField.setField(x1, y1 - 1, rank);
            BattleField.setField(x1, y1, no);
            return;
        }
        //4 上下可否前进？
        if (x1 > 8) {//中点以下
            no = BattleField.getNo(x1 - 1, y1);
            if (!Involved.a.get(no).getAliveState()) {
                //walkTo(x1, y1 + 1);
                BattleField.setField(x1 - 1, y1, rank);
                BattleField.setField(x1, y1, no);
                //Involved.printBf();
                return;
            }
        } else {
            no = BattleField.getNo(x1 + 1, y1);
            if (!Involved.a.get(no).getAliveState()) {
                //walkTo(x1, y1 + 1);
                BattleField.setField(x1 + 1, y1, rank);
                BattleField.setField(x1, y1, no);
                //Involved.printBf();
                return;
            }
        }
        //System.out.println(rank + "do nothing");
        //Involved.printBf();
    }

}
