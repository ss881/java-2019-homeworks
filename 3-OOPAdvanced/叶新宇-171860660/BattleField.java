import java.util.*;

public class BattleField {
    public Creature[][] info;
    private int size;

    BattleField(int x) {
        size = x;
        info = new Creature[x][x];
        for(int i = 0; i < x; i++)
            for(int j = 0; j < x; j++)
                info[i][j] = null;
    }

    /*
    public boolean isAvailable(Position p) {
        if(p.getPosX() >= size || p.getPosX() < 0 || p.getPosY() >= size || p.getPosY() < 0)
            return false;
        if(info[p.getPosX()][p.getPosY()] == null)
            return true;
        else
            return false;
    }
    */

    public int getSize() {
        return size;
    }

    public boolean isAvailable(int x, int y) {
        if(x >= size || x < 0 || y >= size || y < 0)
            return false;
        if(info[x][y] == null)
            return true;
        else
            return false;
    }

    public void printBattleField() {
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(isAvailable(i, j))
                    System.out.print('■');
                else
                    info[i][j].printMark();
                System.out.print(' ');
            }
            System.out.print('\n');
        }
    }

    public void joinBattleField(Creature c) {
        info[c.getPos().getPosX()][c.getPos().getPosY()] = c;
    }

    public void withdraw(Position p) {
        info[p.getPosX()][p.getPosY()] = null;
    }

}
