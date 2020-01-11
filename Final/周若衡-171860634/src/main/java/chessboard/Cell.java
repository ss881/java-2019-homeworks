package chessboard;

import creature.Being;

public class Cell<T extends Being>{
    private int x;
    private int y;
    private T being;
    public Cell(int x, int y, T being) {
        this.x=x;
        this.y=y;
        this.being=being;
    }

    public Cell(){
        this.x=0;
        this.y=0;
        being=null;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
    public Being getBeing(){
        return being;
    }

    public void setBeing(T result){
        being=result;
    }

}
