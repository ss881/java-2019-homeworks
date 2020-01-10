package gamectrl;

import creature.Creature;

// 因为命令行不支持随机打印，显示类需要知道对象的完整信息
@SuppressWarnings("unchecked")
public class Ground2D {
    private int w, h;

    public Ground2D() {
        w = h = 15;
        allocMap();
    }

    public Ground2D(int _wh) {
        setW(_wh);
        setH(_wh);
        allocMap();
    }

    public Ground2D(int _w, int _h) {
        setW(_w);
        setH(_h);
        allocMap();
    }

    public void setW(int _w) {
        w = _w;
    }

    public void setH(int _h) {
        h = _h;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    //private Creature[][] indexes;
    // 用泛型改写
    private Grid2D[][] grids;
    public Grid2D[][] getGrids(){return grids;}

    private void allocMap() {
        grids = new Grid2D[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                grids[i][j] = new Grid2D();
            }
        }
        clearMap();
    }

    public void clearMap() {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                grids[i][j].removeCreature();
            }
        }
    }

    private void set(Creature target) {
        int x = target.getPosition().getX(), y = target.getPosition().getY();
        grids[x][y].setCreature(target);
    }

    public void acceptMove(Creature[] input, int n) {
        for (int i = 0; i < n; i++) {
            set(input[i]);
        }
    }

    public void acceptMove(Creature input) {
        set(input);
    }

    public void displayCli() {
        for (int j = 0; j < w; j++)
            System.out.print("---");
        System.out.println();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                grids[j][i].displayCli();
                System.out.print(" ");
            }
            System.out.println();
        }
        for (int j = 0; j < w; j++)
            System.out.print("---");
        System.out.println();
    }
}
