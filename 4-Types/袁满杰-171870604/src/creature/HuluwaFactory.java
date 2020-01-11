package creature;

import exception.HuluwaOutOfNumException;
import space.Space;

public class HuluwaFactory extends CreatureFactory {
    static int num=1;
    private static String[] namelist={"大","二","三","四","五","六","七"};

    public HuluwaFactory(Space s) {
        super(s);
    }

    public Creature create() throws HuluwaOutOfNumException {
        if(num<=7)
        {
            return new Huluwa(space,num,namelist[num++-1]);
        }else
        {
            throw new HuluwaOutOfNumException();
        }
    }
}
