package creature;

import java.util.Comparator;

public abstract class Creature {

    private Position position;

    private static class Position{
        private int x;
        private int y;
        Position(){
            x=y=-1;
        }
        Position(int x,int y){
            this.x=x; this.y=y;
        }
        void setX(final int x){
            this.x=x;
        }
        void setY(final int y){
            this.y=y;
        }
        int getX(){return x;}
        int getY(){return y;}
    }
    public int getX(){return this.position.getX();}
    public int getY(){return this.position.getY();}
    public Creature(){
        position=new Position();
    }
    public Creature(final int x,final int y){
        position=new Position(x,y);
    }
    public void stay(final int xPos,final int yPos){
        this.position.setX(xPos);
        this.position.setY(yPos);
    }
    public void walkTo(final int xOffset,final int yOffset){
        this.position.setX(getX()+xOffset);
        this.position.setY(getY()+yOffset);
    }
    public String getIdentification(){
        return this.toString();
    }
}
