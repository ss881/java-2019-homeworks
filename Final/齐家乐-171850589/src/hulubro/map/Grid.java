package hulubro.map;

import hulubro.life.Life;

import java.io.Serializable;

public class Grid<T extends Life> implements Serializable {
    private boolean empty;
    public Life life;
    public final int x,y;
    public Grid(int x,int y){
        this.x=x;
        this.y=y;
        this.empty=true;
        this.life=null;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
    public void setName(String name){
        life.name=name;
    }

    public  void LifeIn(Life life){
        this.life=life;
        this.empty=false;
        life.setGrid(this);
    }
    Life LifeOut(){
        Life temp=this.life;
        this.life=null;
        this.empty=true;
        return temp;
    }
    public boolean empty(){
        return empty;
    }
    public Life get_life(){
        return life;
    }
}
