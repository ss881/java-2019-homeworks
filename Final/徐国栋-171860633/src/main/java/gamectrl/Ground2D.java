package gamectrl;

import annotation.Description;
import creature.Creature;

@SuppressWarnings("unchecked")
@Description(comment = "维护地图，用于快速索引指定坐标上是否有人")
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
        System.out.println(toString());
    }
    @Override public String toString(){
        return new String("地图大小=("+w+"x"+h+")");
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
        //System.out.println(target.getPosition().toString());
        grids[x][y].setCreature(target);
    }

    public Creature getCreature(int x, int y){
        //System.out.println("(x,y)=("+x+","+y+")");
        Creature c = grids[x][y].getTheCreature();
        return c;
    }

    public void acceptMove(Creature[] input, int n) {
        for (int i = 0; i < n; i++) {
            set(input[i]);
        }
    }

    public void acceptMove(Creature input) {
        set(input);
    }
    public void remove(Creature target){
        int x = target.getPosition().getX(), y = target.getPosition().getY();
        grids[x][y].removeCreature();
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
