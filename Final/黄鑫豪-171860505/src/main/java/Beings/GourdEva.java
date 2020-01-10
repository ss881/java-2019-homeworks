package Beings;

import Field.TwoDSpace;

public class GourdEva extends Creature {
    private static int[] health = {30, 30, 30, 30, 30, 30, 30};

    public GourdEva(GourdColor c, TwoDSpace tds) {
        super(0, 0, health[c.ordinal()], tds, true, (c.ordinal() + 1) + ".png");
    }
}
