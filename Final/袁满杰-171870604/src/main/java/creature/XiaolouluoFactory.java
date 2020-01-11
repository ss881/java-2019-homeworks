package creature;

import exception.HuluwaOutOfNumException;
import space.Space;

import java.util.Random;

public class XiaolouluoFactory extends CreatureFactory {
    static int num = 0;

    public XiaolouluoFactory(Space s) {
        super(s);
    }

    public XiaolouluoFactory(Space s, int x) {
        super(s);
        num = x;
    }

    @Override
    public Creature create() {
        Creature res = new Xiaolouluo(space, num++, info[10], 1);
        Random r=new Random();
        res.setImage("image/"+(r.nextInt(3)+1)+"xiaolouluo.png");
        return res;
    }
}
