package behave;


public class MoveBehave extends Behave {
    private int dx;
    private int dy;

    public MoveBehave(int sleepTime,int dx,int dy) {
        super(sleepTime);
        this.dx=dx;
        this.dy=dy;
    }
    @Override
    public Object clone() {
        MoveBehave temp = null;
        temp = (MoveBehave) super.clone();
        return temp;
    }
    public int getDx(){return dx;}
    public int getDy(){return dy;}
}
