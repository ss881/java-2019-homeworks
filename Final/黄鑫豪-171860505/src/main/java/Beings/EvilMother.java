package Beings;

import Field.TwoDSpace;

public class EvilMother extends Creature {
    private static int health = 30;

    public EvilMother(TwoDSpace tds) {
        super(0, 0, health, tds, false,"evilmother.png");
    }
}
