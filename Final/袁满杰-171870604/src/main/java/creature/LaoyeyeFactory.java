package creature;

import space.Space;

public class LaoyeyeFactory extends CreatureFactory {

    public LaoyeyeFactory(Space s) {
        super(s);
    }

    @Override
    public Creature create() {
        Laoyeye res = new Laoyeye(space, info[0], 0);
        res.setImage("image/laoyeye.png");
        return res;
    }
}
