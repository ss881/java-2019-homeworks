package space;
import creature.*;

public class Cell <T extends Creature>{
    private int coordinateX;
    private int coordinateY;
    private T creatureOnTheCell;

    public Cell(){
        coordinateX=0;
        coordinateX=0;
        creatureOnTheCell=null;
    }
    public Cell(int x,int y){
        coordinateX=x;
        coordinateY=y;
        creatureOnTheCell=null;
    }

    public int getCoordinateX(){
        return coordinateX;
    }
    public int getCoordinateY(){
        return coordinateY;
    }
    public Creature getCreatureOnTheCell(){
        return creatureOnTheCell;
    }
    public void setCoordinateX(int x){
        coordinateX=x;
    }
    public void setCoordinateY(int y){
        coordinateY=y;
    }
    public void setCreatureOnTheCell(T newCreature){
        creatureOnTheCell=newCreature;
    }


    public boolean isCellEmpty(){
        if (creatureOnTheCell==null) return true;
        else return false;
    }
    public void removeCreatureOnTheCell(){
        creatureOnTheCell=null;
    }
}
