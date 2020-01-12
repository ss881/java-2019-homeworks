package hulubro.map;

import hulubro.life.Life;

import java.io.Serializable;

public class Move implements Serializable {
    public int x1,y1;
    public int x2,y2;
    public boolean alive;
    //public Life life;
    Move(int x1,int y1,int x2,int y2,boolean alive){
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        this.alive=alive;
        //this.life=life;
    }
}
