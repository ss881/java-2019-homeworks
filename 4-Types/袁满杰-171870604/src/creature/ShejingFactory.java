package creature;

import space.Space;

public class ShejingFactory extends CreatureFactory {

    public ShejingFactory(Space s) {
        super(s);
    }

    @Override
    public  Creature create() {
        return new Shejing(space);
    }
}
