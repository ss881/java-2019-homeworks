package team;

import creature.HuluwaFactory;
import creature.LaoyeyeFactory;
import exception.HuluwaOutOfNumException;
import space.Space;

public class HeroTeamFactory extends TeamFactory {
    public HeroTeamFactory(Space s) {
        super(s);
    }

    @Override
    public HeroTeam create() throws HuluwaOutOfNumException {
        HeroTeam res=new HeroTeam(space);
        res.add(new LaoyeyeFactory(space).create());
        res.add(new HuluwaFactory(space,1).create());
        for(int i=0;i<6;i++)
        {
            res.add(new HuluwaFactory(space).create());
        }
        return res;
    }
}
