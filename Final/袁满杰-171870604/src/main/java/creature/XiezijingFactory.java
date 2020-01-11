package creature;

import space.Space;

public class XiezijingFactory extends CreatureFactory {
    public XiezijingFactory(Space s) {
        super(s);
    }

    @Override
    public Creature create() {
        Creature res = new Xiezijing(space, info[9], 1);
        res.setImage("image/xiezijing.png");
        return res;
    }
}
