package creature;

import space.Space;

public class ShejingFactory extends CreatureFactory {
    public ShejingFactory(Space s) {
        super(s);
    }

    @Override
    public Creature create() {
        Creature res = new Shejing(space, info[8], 1);
        res.setImage("image/shejing.png");
        return res;
    }
}
