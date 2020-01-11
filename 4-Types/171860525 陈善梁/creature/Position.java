package creature;

public class Position{
    public int x;
    public int y;
    public Position(){
        x = -1;
        y = -1;
    }
    public Position(int x,int y){
        this.x = x;
        this.y = y;
    }
    public Position(Position p){
        this.x=p.x;
        this.y=p.y;
    }

    public boolean myEualsTo(Position p){
        return (this.x==p.x&&this.y==p.y);
    }
}