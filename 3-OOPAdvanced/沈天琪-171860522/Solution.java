import java.beans.BeanInfo;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        System.out.println("program start");
        Involved t = new Involved();
        t.huluwaCome();
        t.huluwaSort();
        t.elderCome();
        t.snackCome();
        for (int i = 0; i < 8; i++) {
            t.scorpionOrder(i);
            t.cheerTogether();
        }
        System.out.println("program end");
    }
}

class Being {
    static BattleField bf;
    protected int rank = 0;

    public void print() {
        System.out.print('-');
    }

    public int getRank() {
        return rank;
    }

    public void walkTo(int x, int y) {
        int x1 = -1, y1 = -1;
        for (int i = 0; i < bf.SIZE; i++) {
            for (int j = 0; j < bf.SIZE; j++)
                if (bf.getNo(i, j) == rank) {
                    x1 = i;
                    y1 = j;
                    break;
                }
            if (x1 != -1)
                break;
        }
        if (x1 == x && y1 == y)
            return;
        if (x1 == -1) {
            System.out.println("failed to find myself");
            return;
        }
        if (x1 != x && y1 != y) {
            System.out.println("can't walk obliquely");
            return;
        }
        if (bf.getNo(x, y) != 0) {
            System.out.println("target position is not empty");
            return;
        }
        bf.setField(x, y, bf.getNo(x1, y1));
        bf.setField(x1, y1, 0);
    }
}

class Huluwa extends Being {
    private String name;
    //private int rank;

    Huluwa(String n, int r) {
        name = n;
        rank = r;
    }

    @Override
    public void print() {
        System.out.print(rank);
    }
}

class Elder extends Being {
    Elder(int r) {
        rank = r;
    }

    @Override
    public void print() {
        System.out.print('E');
    }

    public void cheer() {
        System.out.println("cheer for huluwa");
    }
}

class Snack extends Being {
    Snack(int r) {
        rank = r;
    }

    @Override
    public void print() {
        System.out.print('S');
    }

    public void cheer() {
        System.out.println("cheer for evils");
    }


}

class Scorpion extends Being {
    /*
     * 他包含了指挥的职责
     * */
    Scorpion(int r) {
        rank = r;
    }

    @Override
    public void print() {
        System.out.print('C');
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
        for (int i = 0; i < Being.bf.SIZE; i++)
            for (int j = 0; j < Being.bf.SIZE; j++)
                if (bf.getNo(i, j) >= 10)
                    bf.setField(i, j, 0);

        switch (mode) {
            case 0:
                bf.setField(7, 8, 10);
                bf.setField(8, 9, 11);
                bf.setField(9, 10, 12);
                bf.setField(10, 11, 13);
                bf.setField(9, 12, 14);
                bf.setField(8, 13, 15);
                bf.setField(7, 14, 16);
                break;
            case 1:
                bf.setField(10, 10, 10);
                bf.setField(9, 11, 11);
                bf.setField(8, 12, 12);
                bf.setField(7, 13, 13);
                bf.setField(6, 14, 14);
                break;
            case 2:
                bf.setField(6, 8, 10);
                bf.setField(8, 8, 11);
                bf.setField(10, 8, 12);
                bf.setField(5, 9, 13);
                bf.setField(7, 9, 14);
                bf.setField(9, 9, 15);
                break;
            case 3:
                bf.setField(7, 15, 10);
                bf.setField(8, 15, 11);
                bf.setField(9, 15, 12);
                bf.setField(10, 15, 13);
                bf.setField(11, 15, 14);
                bf.setField(12, 15, 15);
                break;
            case 4:
                bf.setField(6, 12, 10);
                bf.setField(8, 12, 11);
                bf.setField(7, 13, 12);
                bf.setField(8, 10, 13);
                bf.setField(8, 14, 14);
                bf.setField(9, 9, 15);
                bf.setField(9, 11, 16);
                bf.setField(9, 13, 17);
                bf.setField(9, 15, 18);
                bf.setField(10, 12, 19);
                break;
            case 5:
                bf.setField(8, 8, 10);
                bf.setField(9, 9, 11);
                bf.setField(10, 10, 12);
                bf.setField(9, 11, 13);
                bf.setField(8, 12, 14);
                bf.setField(7, 9, 15);
                bf.setField(6, 10, 16);
                bf.setField(7, 11, 17);
                break;
            case 6:
                bf.setField(7, 8, 10);
                bf.setField(7, 9, 11);
                bf.setField(7, 10, 12);
                bf.setField(8, 8, 13);
                bf.setField(8, 9, 14);
                bf.setField(8, 10, 15);
                bf.setField(9, 8, 16);
                bf.setField(9, 9, 17);
                bf.setField(9, 10, 18);//
                bf.setField(6, 10, 19);
                bf.setField(6, 11, 20);
                bf.setField(5, 12, 21);
                bf.setField(5, 11, 22);//
                bf.setField(10, 10, 23);
                bf.setField(10, 11, 24);
                bf.setField(11, 11, 25);
                bf.setField(11, 12, 26);
                break;
            case 7:
                bf.setField(6, 10, 10);
                bf.setField(8, 10, 11);
                bf.setField(7, 10, 12);
                bf.setField(9, 10, 13);
                bf.setField(10, 10, 14);
                bf.setField(11, 10, 15);//
                bf.setField(7, 9, 16);
                bf.setField(8, 8, 17);//
                bf.setField(7, 11, 18);
                bf.setField(8, 12, 19);
                break;
        }
    }

}

class Monsters extends Being {
    Monsters(int r) {
        rank = r;
    }

    @Override
    public void print() {
        System.out.print('M');
    }
}

class Involved {
    private ArrayList<Being> a = new ArrayList<>();
    private Elder e;
    private Snack sn;
    private Scorpion sc;

    /*
     * a中 0 为being
     * 1 2 3 4 5 6 7 为各个葫芦娃（老大到老七）
     * 8 爷爷
     * 9 蛇
     * 10 蝎子
     * 11 12 13 14 15。。。 喽啰们
     * */
    Involved() {
        Being b = new Being();
        b.bf = new BattleField();

        a.add(new Being());
        String[] namesText = new String[]{"老大", "老二", "老三", "老四", "老五", "老六", "老七"};
        for (int i = 0; i < 7; i++)
            a.add(new Huluwa(namesText[i], i + 1));
        e = new Elder(8);
        a.add(e);
        sn = new Snack(9);
        a.add(sn);
        sc = new Scorpion(10);
        a.add(sc);
        for (int i = 11; i < Being.bf.SIZE * 3; i++)
            a.add(new Monsters(i));
        /*
        a.add(new Huluwa());
        a.get(0).print();
        a.get(1).print();
        a.get(0).bf.setField(0,0,2);
        System.out.print(a.get(1).bf.getNo(0,0));
        printBf();
        */
    }

    public void huluwaSort() {
        for (int i = 0; i < 7; i++)
            a.get(Being.bf.getNo(i + 4, 0)).walkTo(4 + i, 2);
        printBf();
        for (int i = 1; i <= 7; i++) {
            int r = a.get(i).getRank();
            int x1 = Being.bf.getXByNo(r);
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
            Being.bf.setField(temp[i], 0, i + 1);
        printBf();
    }

    //public void scorpionCome() {
    //Being.bf.setField(0, 0, 10);
    //}
    public void scorpionOrder(int mode) {
        sc.orderMonsters(mode);
        printBf();
    }

    public void elderCome() {
        Being.bf.setField(0, 0, 8);
    }

    public void cheerTogether() {
        e.cheer();
        sn.cheer();
    }

    public void snackCome() {
        Being.bf.setField(15, 15, 9);
    }

    public void printBf() {
        int L = Being.bf.SIZE;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                a.get(Being.bf.getNo(i, j)).print();
                System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
    }
}

class BattleField {
    final int SIZE = 16;
    int[][] field = new int[SIZE][SIZE];

    BattleField() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                field[i][j] = 0;
    }

    public void setField(int x, int y, int no) {
        if (x < SIZE && x >= 0 && y < SIZE && y >= 0)
            ;
        else {
            System.out.println("wrong x or y");
            return;
        }
        field[x][y] = no;
    }

    public int getNo(int x, int y) {
        return field[x][y];
    }

    public int getXByNo(int no) {
        int x1 = -1, y1 = -1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                if (getNo(i, j) == no) {
                    x1 = i;
                    y1 = j;
                    break;
                }
            if (x1 != -1)
                break;
        }
        return x1;
    }

    public int getYByNo(int no) {
        int x1 = -1, y1 = -1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                if (getNo(i, j) == no) {
                    x1 = i;
                    y1 = j;
                    break;
                }
            if (x1 != -1)
                break;
        }
        return y1;
    }

}
