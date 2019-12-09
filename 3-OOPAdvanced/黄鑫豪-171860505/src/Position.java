public class Position {
    private int posx;
    private int posy;

    public Position(int posx, int posy){
        this.posx = posx;
        this.posy =posy;
    }

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPos(int x, int y){
        posy = y;
        posx = x;
    }
}
