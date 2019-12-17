import java.util.ArrayList;
import java.lang.reflect.*;

public class Involved {
    private ArrayList<IBeing> a = new ArrayList<IBeing>();
    private BattleField bf=new BattleField();

    Involved() {
        a.add(new Being());
        String[] namesText = new String[]{"老大", "老二", "老三", "老四", "老五", "老六", "老七"};
        for (int i = 0; i < 7; i++)
            a.add(new Huluwa(namesText[i], i + 1));
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
        int x0 = 500;
        int x1, x2, t;
        for (int i = 0; i < x0; i++) {
            x1 = (int) (temp.length * Math.random());
            x2 = (int) (temp.length * Math.random());
            t = temp[x1];
            temp[x1] = temp[x2];
            temp[x2] = t;
        }
        for (int i = 0; i < temp.length; i++)
            BattleField.setField(temp[i], 0, i + 1);
        printBf();

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
                    printBf();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            }
        }
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
            if(i instanceof ICheer){
                if(i.getClass()==Elder.class){
                    Elder ii = (Elder) i;
                    cheerGeneric(ii);
                }else if (i.getClass() == Snack.class) {
                    Snack ii = (Snack) i;
                    cheerGeneric(ii);
                }
            }
        }
    }

    public <T extends ICheer> void cheerGeneric(T t) {
        System.out.println(t.cheer());
    }

    public void printBf() {
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
