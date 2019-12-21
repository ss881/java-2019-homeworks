package creature;

import space.Space;

public class LaoyeyeFactory extends CreatureFactory {

    public LaoyeyeFactory(Space s) {
        super(s);
    }

    @Override
    public Creature create()
    {
        return new Laoyeye(space);
    }
}
