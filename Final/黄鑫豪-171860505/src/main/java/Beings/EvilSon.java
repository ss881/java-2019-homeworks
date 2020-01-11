package Beings;

import Field.TwoDSpace;

public class EvilSon extends Creature {
    private static int health = 30;

    public EvilSon (TwoDSpace tds) {
        super(0, 0, health, tds, false,"evilson.png");
    }
}