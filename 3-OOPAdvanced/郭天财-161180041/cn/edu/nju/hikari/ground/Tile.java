package ground;

import creature.*;


public class Tile{
    public Creature life;
    public int x, y;
    public boolean isTaken;
    
    Tile(int x, int y){
        this.x = x;
        this.y = y;
        life = new Creature();
        isTaken = false;
    }

    public void welcome(Creature life){
        this.life = life;
        isTaken = true;
    }
    
}