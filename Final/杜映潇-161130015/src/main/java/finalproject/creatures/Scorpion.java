package finalproject.creatures;

public class Scorpion extends GoblinSide {
    private static Scorpion scorpion = new Scorpion();

    private Scorpion() {
        super("Scorpion");
    }


    public static Scorpion getScorpionObject() {
        return scorpion;
    }
}
