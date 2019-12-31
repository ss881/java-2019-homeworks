package Creatures;

public class Grandfather extends Creature {
    private static Grandfather grandfather = new Grandfather();

    private Grandfather() {
        super("Grandfather");
    }

    public static Grandfather getGrandfatherObject() {
        return grandfather;
    }
}
