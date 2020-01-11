package ground;
import creatures.*;

public class Cell {
    private Creature creature;
    public Cell(){}
    public Cell(Creature creature){this.creature = creature;}
    public void setCreature(Creature c){
        creature = c;
    }
    public String getName(){
        if(creature!=null&& creature.getState()!=State.DEAD)
            return creature.getName();
        return "    ";
    }
}
