package creature;

import ground.*;

import java.util.Random;
 

public class Creature{
    protected String name;
    public int x, y;

    public Creature(){
        name = "";
        x = y = -1;
    }

    public Creature(String name){
        this.name = name;
        x = y = -1;
    }

    public void moveTo(Tile t){
        if(x >-1 && y > -1 && this.equals(Ground.tiles[x][y].life))leave();
        t.welcome(this);
        x = t.x;
        y = t.y;
    }

    public Creature seeWho(Tile t){
        return t.life;
    }

    public String getName(){
        return name;
    }

    protected void leave(){
        if(x == -1 || y == -1)return;
        Ground.tiles[x][y].isTaken = false;
    }

    protected boolean positionTaken(Tile t){
        return t.isTaken;
    }

    public void randomStand(){
        while(true){
            Random rand = new Random();
            int x = rand.nextInt(Ground.sizeOfTiles);
            int y = rand.nextInt(Ground.sizeOfTiles);
            Tile t = Ground.tiles[x][y];
            if(!positionTaken(t)){
                moveTo(t);
                break;
            }
        }
    }    
}