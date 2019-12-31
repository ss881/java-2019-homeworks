package creatures;
//import pos.*;

public class Snake extends Creature{
    Snake(int x,int y, String n){
        super(x,y,n);
        camp=Camp.SNAKE;
    }
    public void cheer(){
        System.out.println(name+ ": 妖精加油!");
    }
    
}