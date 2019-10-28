package creature;

public class Creature implements Moveable,Swapable<Creature> {//Generices
    public Position previousPosition;//previous position
    public Position currentPosition;//current position
    static final int N=12;

    @Override
    public void moveTo(int x,int y){
        //set prepos and curpos
        setPreviousPosition(currentPosition.x,currentPosition.y);
        setCurrentPosition(x,y);
    }

    public Creature(){
        previousPosition=new Position(-1,-1);
        currentPosition=new Position(-1,-1);
    }

    public void setPreviousPosition(int x,int y){
        this.previousPosition.x=x;
        this.previousPosition.y=y;
    }

    public void setCurrentPosition(int x,int y){
        this.currentPosition.x=x;
        this.currentPosition.y=y;
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

//    public char getSymbol(){
//        return 'C';
//    }
}
