package creatures;
//import pos.*;

public class Grandpa extends Creature{
    public Grandpa(int x,int y, String n){
        super(x,y,n); 
        camp=Camp.HULUWA;
    }
    
    public void cheer(){
        System.out.println(name+": 葫芦娃加油！");
    }
}