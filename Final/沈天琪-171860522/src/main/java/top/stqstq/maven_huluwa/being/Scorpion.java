package top.stqstq.maven_huluwa.being;

import top.stqstq.maven_huluwa.battlefiled.*;
import top.stqstq.maven_huluwa.involved.Involved;
import top.stqstq.maven_huluwa.lock.MyLock;

public class Scorpion implements IBeing, IFighter {
    private int rank = 10;
    private int fightAbility = 10;
    private boolean alive = true;
    private final int combatComp = 2;//0 no combat 1 good 2 evil
    private String name = "Scorpion";

    public void print() {
        System.out.print('C');
    }

    public String getSymbol() {
        return "C";
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

    public void orderMonsters(int mode) {
        /*
         * 这里需要改变的：>=10的rank
         * mode
         * 0 鹤翼
         * 1 雁行
         * 2 衡轭
         * 3 长蛇
         * 4 鱼鳞
         * 5 方阵
         * 6 偃月
         * 7 锋矢
         * */
        // firstly, clear the battleground
        for (int i = 0; i < BattleField.SIZE; i++)
            for (int j = 0; j < BattleField.SIZE; j++)
                if (BattleField.getNo(i, j) >= 9)
                    BattleField.setField(i, j, 0);

        switch (mode) {
            case 0:
                BattleField.setField(7, 9, 10);
                BattleField.setField(8, 10, 11);
                BattleField.setField(9, 11, 12);
                BattleField.setField(10, 12, 13);
                BattleField.setField(9, 13, 14);
                BattleField.setField(8, 14, 15);
                BattleField.setField(7, 15, 9);
                break;
            case 1:
                BattleField.setField(10, 11, 10);
                BattleField.setField(9, 12, 11);
                BattleField.setField(8, 13, 12);
                BattleField.setField(7, 14, 13);
                BattleField.setField(6, 15, 9);
                break;
            case 2:
                BattleField.setField(6, 14, 10);
                BattleField.setField(8, 14, 11);
                BattleField.setField(10, 14, 12);
                BattleField.setField(5, 15, 13);
                BattleField.setField(7, 15, 14);
                BattleField.setField(9, 15, 9);
                break;
            case 3:
                BattleField.setField(7, 15, 10);
                BattleField.setField(8, 15, 11);
                BattleField.setField(9, 15, 12);
                BattleField.setField(10, 15, 13);
                BattleField.setField(11, 15, 14);
                BattleField.setField(12, 15, 9);
                break;
            case 4:
                BattleField.setField(6, 12, 10);
                BattleField.setField(8, 12, 11);
                BattleField.setField(7, 13, 12);
                BattleField.setField(8, 10, 13);
                BattleField.setField(8, 14, 14);
                BattleField.setField(9, 9, 15);
                BattleField.setField(9, 11, 16);
                BattleField.setField(9, 13, 17);
                BattleField.setField(9, 15, 18);
                BattleField.setField(10, 12, 9);
                break;
            case 5:
                BattleField.setField(8, 11, 10);
                BattleField.setField(9, 12, 11);
                BattleField.setField(10, 13, 12);
                BattleField.setField(9, 14, 13);
                BattleField.setField(8, 15, 14);
                BattleField.setField(7, 12, 15);
                BattleField.setField(6, 13, 16);
                BattleField.setField(7, 14, 9);
                break;
            case 6:
                BattleField.setField(7, 11, 10);
                BattleField.setField(7, 12, 11);
                BattleField.setField(7, 13, 12);
                BattleField.setField(8, 11, 13);
                BattleField.setField(8, 12, 14);
                BattleField.setField(8, 13, 15);
                BattleField.setField(9, 11, 16);
                BattleField.setField(9, 12, 17);
                BattleField.setField(9, 13, 18);//
                BattleField.setField(6, 13, 19);
                BattleField.setField(6, 14, 20);
                BattleField.setField(5, 15, 21);
                BattleField.setField(5, 14, 22);//
                BattleField.setField(10, 13, 23);
                BattleField.setField(10, 14, 24);
                BattleField.setField(11, 14, 25);
                BattleField.setField(11, 15, 9);
                break;
            case 7:
                BattleField.setField(6, 13, 10);
                BattleField.setField(8, 13, 11);
                BattleField.setField(7, 13, 12);
                BattleField.setField(9, 13, 13);
                BattleField.setField(10, 13, 14);
                BattleField.setField(11, 13, 15);//
                BattleField.setField(7, 12, 16);
                BattleField.setField(8, 11, 17);//
                BattleField.setField(7, 14, 18);
                BattleField.setField(8, 15, 9);
                break;
        }
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
                        if (Involved.a.get(BattleField.getNo(i, j)).getCombatComp() == 1 && Involved.a.get(BattleField.getNo(i, j)).getAliveState())
                            count1++;
                        else if (Involved.a.get(BattleField.getNo(i, j)).getCombatComp() == 2 && Involved.a.get(BattleField.getNo(i, j)).getAliveState())
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
}
