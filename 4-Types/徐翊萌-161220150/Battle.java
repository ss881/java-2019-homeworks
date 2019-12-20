public class Battle {
    private Plot[][] battle;
    private int row, col;

    Battle(int x, int y) {
        battle = new Plot[x][y];
        this.row = x;
        this.col = y;
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++)
                battle[i][j] = new Plot();
    }

    public void setUnit(Unit unit, int x, int y) {
        if (battle[x][y].isEmpty()) {
            if (unit.getX() != -1)
                battle[unit.getX()][unit.getY()].setUnit(null);
            battle[x][y].setUnit(unit);
            unit.set(x, y);
        }
        else {
            battle[x][y].getUnit().set(-1, -1);
            if (unit.getX() != -1)
                battle[unit.getX()][unit.getY()].setUnit(null);
            battle[x][y].setUnit(unit);
            unit.set(x, y);
        }
    }

    public void showBattle() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (battle[i][j].isEmpty())
                    System.out.print("|____");
                else
                    System.out.printf("|%s", battle[i][j].getUnit().getName());
            }
            System.out.println("|");
        }
    }
}