public class XPoint2D {
    int x, y;

    XPoint2D() {
        x = y = 0;
    }

    XPoint2D(int _x, int _y) {
        x = _x;
        y = _y;
    }

    void setX(int _x) {
        x = _x;
    }

    void setY(int _y) {
        y = _y;
    }

    public int getX(){return x;}
    public int getY(){return y;}
}