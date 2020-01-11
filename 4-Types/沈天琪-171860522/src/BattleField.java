public class BattleField {
    static final int SIZE = 16;
    static int[][] field = new int[SIZE][SIZE];

    BattleField() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                field[i][j] = 0;
    }

    public static void setField(int x, int y, int no) {
        if (x < SIZE && x >= 0 && y < SIZE && y >= 0)
            field[x][y] = no;
        else {
            System.out.println("wrong x or y");
            return;
        }
    }

    public static int getNo(int x, int y) {
        return field[x][y];
    }

    public static int getXByNo(int no) {
        int x1 = -1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                if (getNo(i, j) == no) {
                    x1 = i;
                    break;
                }
            if (x1 != -1)
                break;
        }
        return x1;
    }

    public static int getYByNo(int no) {
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
