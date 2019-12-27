public class Huluwa implements IBeing {
    private int rank = -1;
    private String name = "NULL";

    Huluwa(String n, int r) {
        name = n;
        rank = r;
    }

    public void print() {
        System.out.print(rank);
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
}
