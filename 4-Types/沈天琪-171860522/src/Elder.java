public class Elder implements IBeing, ICheer {
    private int rank = 8;
    private String name = "Elder";

    public void print() {
        System.out.print('E');
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

    public String cheer() {
        return getName() + " cheer for goods";
    }
}
