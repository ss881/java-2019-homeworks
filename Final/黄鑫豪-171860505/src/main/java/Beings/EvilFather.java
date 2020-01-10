package Beings;

import Field.TwoDSpace;

public class EvilFather extends Creature {
    private static int health = 30;

    public EvilFather(TwoDSpace tds) {
        super(0, 0, health, tds,false,"evilfather.png");
    }
}