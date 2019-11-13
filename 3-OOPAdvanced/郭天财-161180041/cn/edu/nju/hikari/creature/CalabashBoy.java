package creature;

import java.util.Random;

import ground.*;
import creature.Creature;

public class CalabashBoy extends Creature{
    private int Rank;
    
    public CalabashBoy(int Rank){
        this.Rank = Rank;
        switch(Rank){
            case 1: name = "老大"; break;
            case 2: name = "老二"; break;
            case 3: name = "老三"; break;
            case 4: name = "老四"; break;
            case 5: name = "老五"; break;
            case 6: name = "老六"; break;
            case 7: name = "老七"; break;
            default: name = "老大";
        }
    }

    @Override
    public void randomStand(){
        while(true){
            Random rand = new Random();
            int x = 1;
            int y = rand.nextInt(7) + 7;
            if(!positionTaken(Ground.tiles[x][y])){
                moveTo(Ground.tiles[x][y]);
                break;
            }
        }
    }  

    private CalabashBoy LookBack(){
        if(Ground.tiles[x][y+1].life instanceof CalabashBoy)
            return (CalabashBoy) Ground.tiles[x][y+1].life;
        return new CalabashBoy(1);
    }

    private boolean CmpWithNext(){
        CalabashBoy cala = LookBack();
        return Rank < cala.Rank;
    }

    public void SwapWithNext(){
        if(CmpWithNext()) return;
        CalabashBoy next = LookBack();
        super.moveTo(Ground.tiles[x][y+1]);
        next.moveTo(Ground.tiles[x][y-1]);
    }

    
}