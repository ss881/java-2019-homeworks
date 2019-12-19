package top.stqstq.maven_huluwa.involved;

import top.stqstq.maven_huluwa.battlefiled.BattleField;
import top.stqstq.maven_huluwa.being.*;
import top.stqstq.maven_huluwa.lock.MyLock;

import java.util.ArrayList;
import java.lang.reflect.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Involved {
    public static ArrayList<IBeing> a = new ArrayList<IBeing>();
    private BattleField bf = new BattleField();
    private MyLock myLock = new MyLock();

    public Involved() {
        a.add(new Being());
        String[] namesText = new String[]{"老大", "老二", "老三", "老四", "老五", "老六", "老七"};
        int[] fightAbilityGroup = new int[]{8, 7, 6, 6, 5, 4, 3};
        for (int i = 0; i < 7; i++)
            a.add(new Huluwa(namesText[i], i + 1, fightAbilityGroup[i]));
        a.add(new Elder());//8
        a.add(new Snack());//9
        a.add(new Scorpion());//10
        for (int i = 11; i < 11 + BattleField.SIZE * 2; i++)
            a.add((new Monsters(i)));
    }

    public void huluwaSort() {
        for (int i = 0; i < 7; i++)
            a.get(BattleField.getNo(i + 4, 0)).walkTo(i + 4, 2);
        printBf();
        for (int i = 1; i <= 7; i++) {
            int rank = a.get(i).getRank();
            int x1 = BattleField.getXByNo(rank);
            a.get(i).walkTo(x1, 1);
            printBf();
            a.get(i).walkTo(3 + i, 1);
            printBf();
            a.get(i).walkTo(3 + i, 0);
            printBf();
        }
    }

    public void huluwaCome() {
        int[] temp = {4, 5, 6, 7, 8, 9, 10};
        /*int x0 = 500;
        int x1, x2, t;
        for (int i = 0; i < x0; i++) {
            x1 = (int) (temp.length * Math.random());
            x2 = (int) (temp.length * Math.random());
            t = temp[x1];
            temp[x1] = temp[x2];
            temp[x2] = t;
        }*/
        for (int i = 0; i < temp.length; i++)
            BattleField.setField(temp[i], 0, 7 - i);
        BattleField.setField(11, 0, 8);
    }

    public void elderAndSnackCome() {
        for (IBeing i : a) {
            if (i.getClass() == Elder.class)
                BattleField.setField(0, 0, i.getRank());
            else if (i.getClass() == Snack.class)
                BattleField.setField(15, 15, i.getRank());
        }
    }

    public void scorpionOrder(int mode) {
        for (IBeing i : a) {
            if (i.getClass() == Scorpion.class) {
                try {
                    i.getClass().getMethod("orderMonsters", int.class).invoke(i, mode);
                    //printBf();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            }
        }
    }

    public void restoreState() {
        for (IBeing i : a) {
            if (i.getRank() != 0)
                i.setAliveState(true);
            if (i.getCombatComp() != 0) {
                int rank = i.getRank();
                if (BattleField.getXByNo(rank) != -1) {
                    int x = BattleField.getXByNo(rank), y = BattleField.getYByNo(rank);
                    BattleField.setField(x, y, 0);
                }
            }
        }
        MyLock.state = 1;
        //MyLock.lock.unlock();
    }

    public ExecutorService warMultithreading() {
        int count = 0;
        for (int i = 0; i < BattleField.SIZE; i++)
            for (int j = 0; j < BattleField.SIZE; j++)
                if (a.get(BattleField.getNo(i, j)).getCombatComp() != 0)
                    count++;
        int[] allWarriors = new int[count];
        int jj = 0;
        for (int i = 0; i < BattleField.SIZE; i++)
            for (int j = 0; j < BattleField.SIZE; j++)
                if (a.get(BattleField.getNo(i, j)).getCombatComp() != 0) {
                    allWarriors[jj] = BattleField.getNo(i, j);
                    jj++;
                }
        if (jj != count)
            System.err.println("waring! strange wrong");
        //-----这样 需要处理的内容在allWarriors内
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++)
            exec.execute(a.get(allWarriors[i]));
        exec.shutdown();

        /*Logger logger = new Logger();
        logger.createLoggerFile();
        while (exec.isTerminated() == false) {
            MyLock.printLock.lock();
            try {
                Thread.sleep(3);
                if (MyLock.updateState == 1) {//need update
                    printBf(123);
                    logger.writeLoggerFile(turnMap2String());
                    MyLock.updateState = 0;
                } else {
                    ;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                MyLock.printLock.unlock();
            }
        }
        logger.closeLoggerFile();
        System.out.println("war finished");//*/
        return exec;
    }

    private String turnMap2String() {
        StringBuilder ret = new StringBuilder();
        for (int[] is : BattleField.field)
            for (int i : is)
                ret.append(a.get(i).getSymbol());
        return ret.toString();
    }

    public void war() {
        int count = 0;
        for (int i = 0; i < BattleField.SIZE; i++)
            for (int j = 0; j < BattleField.SIZE; j++)
                if (a.get(BattleField.getNo(i, j)).getCombatComp() != 0)
                    count++;
        int[] allWarriors = new int[count];
        int jj = 0;
        for (int i = 0; i < BattleField.SIZE; i++)
            for (int j = 0; j < BattleField.SIZE; j++)
                if (a.get(BattleField.getNo(i, j)).getCombatComp() != 0) {
                    allWarriors[jj] = BattleField.getNo(i, j);
                    jj++;
                }
        if (jj != count)
            System.err.println("waring! strange wrong");
        int count1 = 0, count2 = 0;
        do {
            count1 = 0;
            count2 = 0;
            for (int i = 0; i < count; i++) {
                if (a.get(allWarriors[i]) instanceof IFighter) {
                    if (a.get(allWarriors[i]).getAliveState()) {
                        if (a.get(allWarriors[i]).getCombatComp() == 1)
                            count1++;
                        else if (a.get(allWarriors[i]).getCombatComp() == 2)
                            count2++;
                    }
                    IFighter fighter = (IFighter) a.get(allWarriors[i]);
                    fighter.fightSingleStep();
                }
            }
        } while (count1 != 0 && count2 != 0);
        System.out.println("war finished");
    }

    public void cheerTogether() {
        for (IBeing i : a) {
            /*if (i.getClass() == Elder.class) {
                if (i instanceof ICheer) {
                    Elder ii = (Elder) i;
                    cheerGeneric(ii);
                }
            } else if (i.getClass() == Snack.class) {
                if (i instanceof ICheer) {
                    Snack ii = (Snack) i;
                    cheerGeneric(ii);
                }
            }*/
            if (i instanceof ICheer) {
                /*if (i.getClass() == Elder.class) {
                    Elder ii = (Elder) i;
                    cheerGeneric(ii);
                } else if (i.getClass() == Snack.class) {
                    Snack ii = (Snack) i;
                    cheerGeneric(ii);
                }*/
                ICheer ic = (ICheer) i;
                cheerGeneric(ic);
            }
        }
    }

    public <T extends ICheer> void cheerGeneric(T t) {
        System.out.println(t.cheer());
    }

    public static void printBf() {
        int LIMIT = BattleField.SIZE;
        System.out.println("****************************************\n");
        System.out.println("");
        for (int i = 0; i < LIMIT; i++) {
            for (int j = 0; j < LIMIT; j++) {
                a.get(BattleField.getNo(i, j)).print();
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public static void printBf(int xxx) {
        int LIMIT = BattleField.SIZE;
        System.err.println("****************************************");
        for (int i = 0; i < LIMIT; i++) {
            for (int j = 0; j < LIMIT; j++) {
                a.get(BattleField.getNo(i, j)).print();
                System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("\n\n");
    }

    void test() {
        System.out.println("test of Involved");

        System.out.println("part1");
        int ii = 0;
        for (IBeing i : a) {
            if (i.getRank() != ii)
                System.out.println("error in " + ii);
            ii++;
            //System.out.println(i.getClass());
        }
        System.out.println("part1  end");
        System.out.println("part2");
        BattleField.setField(5, 5, 10086);
        //System.out.println("part21");
        System.out.println(BattleField.getXByNo(10086));
        System.out.println(BattleField.getXByNo(10086));
        System.out.println(BattleField.getXByNo(100860));
        BattleField.field[5][5] = 0;
        printBf();
        BattleField.setField(5, 5, 1);
        BattleField.setField(6, 5, 8);
        printBf();
        System.out.println("part2  end");
        System.out.println("end of test");
    }
}
