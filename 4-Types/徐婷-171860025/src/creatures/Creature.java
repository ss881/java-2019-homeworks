package creatures;

public abstract class Creature {
    protected String name;
    protected int coordX;
    protected  int coordY;
    protected State state=State.LIVE;
    public Creature(String name){
        this.name = name;
        //this.state = State.LIVE;
    }
    public Creature() {
        this.name = "";
    }
    public Creature(String name, int x, int y){
        this.name = name;
        coordX = x;
        coordY = y;
    }
    public void setState(State state){
        this.state = state;
    }
    public State getState(){return state;}
    public void setPos(int x, int y){
        coordX = x;
        coordY = y;
    }
    public int getCoordX(){
        return coordX;
    }
    public int getCoordY(){
        return coordY;
    }
    public String getName(){
        return name;
    }


}
