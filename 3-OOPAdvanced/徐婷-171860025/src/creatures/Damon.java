//妖精  蝎子+喽啰
package creatures;


public class Damon extends Creature {
    public Damon(int x,int y, String n){
        super(x,y,n);
        camp=Camp.SNAKE;
    }
    public void setDead(){
        health=0;
        state=State.DEAD;
    }
    public void setLive(){
        state=State.LIVE;
        health=100;
    }
}
