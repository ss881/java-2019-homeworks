package Beings;

import Field.TwoDSpace;

public class Grandpa extends Creature {
    private static int health = 30;

    public Grandpa(TwoDSpace tds) {
        super(0, 0, health, tds, true,"grandpa.png");
    }
}