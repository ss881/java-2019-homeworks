public class Position<T extends Creature> {
    private int posX;
    private int posY;
    private T holder;

    public Position(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosy() {
        return posY;
    }

    public void setPosX(int x){
        posX = x;
    }

    public void setPosY(int y){
        posY = y;
    }

    public void setPos(int x, int y){
        posY = y;
        posX = x;
    }

    public void setHolder(T holder){
        this.holder = holder;
    }

    public T getHolder(){
        return holder;
    }
}
