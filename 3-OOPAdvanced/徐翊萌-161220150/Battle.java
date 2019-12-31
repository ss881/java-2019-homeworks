public class Battle {
    private Unit[][] battle;
    private int row, col;

    Battle(int x, int y) {
        battle = new Unit[x][y];
        this.row = x;
        this.col = y;
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++)
                battle[i][j] = null;
    }

    public void setUnit(Unit unit, int x, int y) {
        if (battle[x][y] == null) {
            if (unit.getX() != -1)
                battle[unit.getX()][unit.getY()] = null;
            battle[x][y] = unit;
            unit.set(x, y);
        }
        else {
            battle[x][y].set(-1, -1);
            if (unit.getX() != -1)
                battle[unit.getX()][unit.getY()] = null;
            battle[x][y] = unit;
            unit.set(x, y);
        }
    }

    public void showBattle() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (battle[i][j] == null)
                    System.out.print("|____");
                else
                    System.out.printf("|%s", battle[i][j].getName());
            }
            System.out.println("|");
        }
    }
}