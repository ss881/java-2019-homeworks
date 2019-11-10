package creatures;
//import pos.*;

enum HuluwaColor{RED,ORANGE,YELLOW,GREEN,CYAN,BLUE,PURPLE};

public class Huluwa extends Creature{
    private int rank;
    private HuluwaColor color;
    public Huluwa(int x,int y, String n, int r, HuluwaColor c){
        super(x,y,n);
        rank=r;
        color=c;
        camp=Camp.HULUWA;
    }
    public int getRank(){
        return rank;
    }

    public HuluwaColor getColor(){
        return color;
    }
}

