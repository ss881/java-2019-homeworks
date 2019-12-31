package creatures;



public class Creature{
    protected int coordX;
    protected int coordY;
    protected State state;
    protected int health;  //生命值
    protected String name;
    protected Camp camp;
    public Creature(int x, int y, String n){
        coordX=x;
        coordY=y;
        name=n;
        health=100;
        state=State.LIVE;
    }
    public Creature(){
        coordX=0;
        coordY=0;
        name="unknown name";
        health=100;
        state=State.LIVE;
    }

    public void moveTo(int toX, int toY){
        System.out.println(name+" move from ("+coordX+","+coordY+")"+" to (" +toX+","+toY+")");
        coordX=toX;
        coordY=toY;
    }
    public void setPos(int x, int y){
        coordX=x;
        coordY=y;
    }
    public int getCoordX(){
        return coordX;
    }
    public int getCoordY(){
        return coordY;
    }
    public int getHealth(){
        return health;
    }
    public Camp getCamp(){
        return camp;
    }

    public String getName(){
        return name;
    }
    public State getState(){
        return state;
    }
}

