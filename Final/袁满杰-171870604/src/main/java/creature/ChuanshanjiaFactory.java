package creature;

import space.Space;

public class ChuanshanjiaFactory extends CreatureFactory {
    static int num = 0;

    public ChuanshanjiaFactory(Space s) {
        super(s);
    }

    public ChuanshanjiaFactory(Space s, int x) {
        super(s);
        num = x;
    }

    @Override
    public Creature create() {
        Creature res = new Chuanshanjia(space, num++, info[11], 0);
        res.setImage("image/chuanshanjia.png");
        return res;
    }
}
