package creatures;

import interfaces.Moveable;
import interfaces.PositionChangable;
import interfaces.Swapable;



public abstract class Creature implements Moveable, Swapable<Creature>, PositionChangable {
    protected Position previousPosition;//previous position
    protected Position currentPosition;//current position
    static final int N=12;

    @Override
    public void moveTo(Position position){
        //set prepos and curpos
        setPreviousPosition(currentPosition);
        setCurrentPosition(new Position (position));
    }

    public Creature(){
        previousPosition=new Position(-1,-1);
        currentPosition=new Position(-1,-1);
    }

    public Position getCurrentPosition(){
        return currentPosition;
    }

    public Position getPreviousPosition(){
        return previousPosition;
    }

    @Override
    public void setPreviousPosition(Position position){
        this.previousPosition.x=position.x;
        this.previousPosition.y=position.y;
    }

    @Override
    public void setCurrentPosition(Position position){
        this.currentPosition.x=position.x;
        this.currentPosition.y=position.y;
    }

    @Override
    public void swapPosition(Creature creature){
        Position temp=this.currentPosition;
        this.currentPosition=creature.currentPosition;
        creature.currentPosition=temp;
    }

    @Override
    public String toString(){
        return "Creature:"+"Position=("+currentPosition.x+","+currentPosition.y+")"+"PrePosition=("+previousPosition.x+","+previousPosition.y+")";
    }

    public boolean notInMap(){
        return currentPosition.x==-1&&currentPosition.y==-1;
    }

    public boolean inMapBefore(){
        return previousPosition.x!=-1&&previousPosition.y!=-1;
    }

    public char getSymbol(){
        return 'C';
    }
}
