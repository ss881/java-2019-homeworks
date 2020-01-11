package location;

import creature.Creature;


public class Position {

    public static final int P_WIDTH = 60;
    public static final int P_HEIGHT = 60;
    public int x,y;
    public Creature holder;

    public Position(int x,int y){
        this.x = x;
        this.y = y;
        holder = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        //return super.toString();
        //return "x = "+x+"y = "+y;
        Integer Ix = new Integer(x);
        Integer Iy = new Integer(y);
        return "x="+Ix+" y="+Iy;
        //return x+' '+y;
    }
}
