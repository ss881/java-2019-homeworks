package creature;

import space.Space;

public class XiezijingFactory extends CreatureFactory {
    public XiezijingFactory(Space s) {
        super(s);
    }

    @Override
    public Creature create() {
        return new Xiezijing(space);
    }
}
