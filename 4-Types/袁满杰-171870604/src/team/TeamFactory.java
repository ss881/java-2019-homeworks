package team;

import exception.HuluwaOutOfNumException;
import space.Space;

public abstract class TeamFactory {
    Space space;
    public TeamFactory(Space s)
    {
        space=s;
    }
    abstract Team create() throws HuluwaOutOfNumException;
}
