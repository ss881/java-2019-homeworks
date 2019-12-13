public class Scorpion implements IBeing {
    private int rank = 10;
    private String name = "Scorpion";

    public void print() {
        System.out.print('C');
    }

    public void walkTo(int x, int y) {
        int x1 = BattleField.getXByNo(rank);
        int y1 = BattleField.getYByNo(rank);
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
        if (BattleField.getNo(x, y) != 0) {
            System.out.println("target position is not empty");
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
                if (BattleField.getNo(i, j) >= 10)
                    BattleField.setField(i, j, 0);

        switch (mode) {
            case 0:
                BattleField.setField(7, 8, 10);
                BattleField.setField(8, 9, 11);
                BattleField.setField(9, 10, 12);
                BattleField.setField(10, 11, 13);
                BattleField.setField(9, 12, 14);
                BattleField.setField(8, 13, 15);
                BattleField.setField(7, 14, 16);
                break;
            case 1:
                BattleField.setField(10, 10, 10);
                BattleField.setField(9, 11, 11);
                BattleField.setField(8, 12, 12);
                BattleField.setField(7, 13, 13);
                BattleField.setField(6, 14, 14);
                break;
            case 2:
                BattleField.setField(6, 8, 10);
                BattleField.setField(8, 8, 11);
                BattleField.setField(10, 8, 12);
                BattleField.setField(5, 9, 13);
                BattleField.setField(7, 9, 14);
                BattleField.setField(9, 9, 15);
                break;
            case 3:
                BattleField.setField(7, 15, 10);
                BattleField.setField(8, 15, 11);
                BattleField.setField(9, 15, 12);
                BattleField.setField(10, 15, 13);
                BattleField.setField(11, 15, 14);
                BattleField.setField(12, 15, 15);
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
                BattleField.setField(10, 12, 19);
                break;
            case 5:
                BattleField.setField(8, 8, 10);
                BattleField.setField(9, 9, 11);
                BattleField.setField(10, 10, 12);
                BattleField.setField(9, 11, 13);
                BattleField.setField(8, 12, 14);
                BattleField.setField(7, 9, 15);
                BattleField.setField(6, 10, 16);
                BattleField.setField(7, 11, 17);
                break;
            case 6:
                BattleField.setField(7, 8, 10);
                BattleField.setField(7, 9, 11);
                BattleField.setField(7, 10, 12);
                BattleField.setField(8, 8, 13);
                BattleField.setField(8, 9, 14);
                BattleField.setField(8, 10, 15);
                BattleField.setField(9, 8, 16);
                BattleField.setField(9, 9, 17);
                BattleField.setField(9, 10, 18);//
                BattleField.setField(6, 10, 19);
                BattleField.setField(6, 11, 20);
                BattleField.setField(5, 12, 21);
                BattleField.setField(5, 11, 22);//
                BattleField.setField(10, 10, 23);
                BattleField.setField(10, 11, 24);
                BattleField.setField(11, 11, 25);
                BattleField.setField(11, 12, 26);
                break;
            case 7:
                BattleField.setField(6, 10, 10);
                BattleField.setField(8, 10, 11);
                BattleField.setField(7, 10, 12);
                BattleField.setField(9, 10, 13);
                BattleField.setField(10, 10, 14);
                BattleField.setField(11, 10, 15);//
                BattleField.setField(7, 9, 16);
                BattleField.setField(8, 8, 17);//
                BattleField.setField(7, 11, 18);
                BattleField.setField(8, 12, 19);
                break;
        }
    }
}
