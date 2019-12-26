package objinfo;

public class XPoint2D {
    int x, y;

    public XPoint2D() {
        x = y = 0;
    }

    public XPoint2D(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public void setX(int _x) {
        x = _x;
    }

    public void setY(int _y) {
        y = _y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override public String toString(){
        return new String("("+x+","+y+")");
    }
}
