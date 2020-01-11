package creature;

import exception.HuluwaOutOfNumException;
import space.Space;

public abstract class CreatureFactory {
    Space space;
    public CreatureFactory(Space s)
    {
        space=s;
    }
    abstract Creature create() throws HuluwaOutOfNumException;
}
