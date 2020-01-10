package chessboard;

public class Position {
    private int x;
    private int y;

    public Position() {
        x = -1;
        y = -1;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position p){
        x=p.getX();
        y=p.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int i) {
        x = i;
    }

    public void setY(int i) {
        y = i;
    }

    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append(" ("+x+","+y+") ");
        return buffer.toString();
    }

    public Position add(Position dxy){
        return new Position(x+dxy.getX(),y+dxy.getY());
    }
    public boolean equal(Position p){
        return (x==p.getX())&&(y==p.getY());
    }

}