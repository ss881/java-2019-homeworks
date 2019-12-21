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
        for(int i=0;i<7;i++)
        {
            res.add(new HuluwaFactory(space).create());
        }
        return res;
    }
}
