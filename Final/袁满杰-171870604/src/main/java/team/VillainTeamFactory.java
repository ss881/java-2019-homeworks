package team;

import creature.ShejingFactory;
import creature.XiezijingFactory;
import space.Space;

public class VillainTeamFactory extends TeamFactory {
    public VillainTeamFactory(Space s) {
        super(s);
    }

    @Override
    public VillainTeam create() {
        VillainTeam res=new VillainTeam(space);
        res.add(new XiezijingFactory(space).create());
        res.add(new ShejingFactory(space).create());
        return res;
    }
}
