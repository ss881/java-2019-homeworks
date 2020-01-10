package creature;

import exception.HuluwaOutOfNumException;
import space.Space;

public class XiaolouluoFactory extends CreatureFactory {
    static int num=0;

    public XiaolouluoFactory(Space s) {
        super(s);
    }
    public XiaolouluoFactory(Space s,int x) {
        super(s);
        num=x;
    }
    @Override
    public Creature create() {
        return new Xiaolouluo(space,num++);
    }
}
