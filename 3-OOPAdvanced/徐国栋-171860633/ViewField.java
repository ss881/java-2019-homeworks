
// 因为命令行不支持随机打印，显示类需要知道对象的完整信息
public class ViewField {
    private int w, h;

    ViewField() {
        w = h = 15;
        allocMap();
    }

    ViewField(int _wh) {
        setW(_wh);
        setH(_wh);
        allocMap();
    }

    ViewField(int _w, int _h) {
        setW(_w);
        setH(_h);
        allocMap();
    }

    void setW(int _w) {
        w = _w;
    }

    void setH(int _h) {
        h = _h;
    }

    int getW() {
        return w;
    }

    int getH() {
        return h;
    }

    private Creature[][] indexes;

    private void allocMap() {
        indexes = new Creature[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                indexes[i][j] = new Creature();
            }
        }
        clearMap();
    }

    public void clearMap() {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                indexes[i][j].name = "  ";
            }
        }
    }

    private void set(Creature target) {
        int x = target.getPosition().getX(), y = target.getPosition().getY();
        indexes[x][y].name = target.name;
    }

    public void acceptMove(Creature[] input, int n) {
        for (int i = 0; i < n; i++) {
            set(input[i]);
        }
    }

    public void acceptMove(Creature input) {
        set(input);
    }

    public void print() {
        for (int j = 0; j < w; j++)
            System.out.print("---");
        System.out.println();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(indexes[j][i].name+" ");
            }
            System.out.println();
        }
        for (int j = 0; j < w; j++)
            System.out.print("---");
        System.out.println();
    }
}