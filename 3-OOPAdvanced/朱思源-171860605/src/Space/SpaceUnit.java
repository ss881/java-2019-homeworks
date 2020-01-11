package Space;
import Creatures.Creature;

public class SpaceUnit {
    private Creature creature;
    private boolean isFull;
    private Coordinate coordinate;
    SpaceUnit(Coordinate coordinate)
    {
        isFull=false;
        this.coordinate=coordinate;
    }
    boolean isFull()
    {
        return isFull;
    }
    void clear()
    {
        isFull=false;
    }
    void printUnit()
    {
        if(isFull)
        {
            System.out.print(creature.getIcon()+" ");
        }
        else{
            System.out.print("  ");
        }
    }
    boolean containCreature(Creature creature)
    {
        if(!isFull)
        {
            this.creature=creature;
            isFull=true;
            return true;
        }
        else return false;
    }
}
