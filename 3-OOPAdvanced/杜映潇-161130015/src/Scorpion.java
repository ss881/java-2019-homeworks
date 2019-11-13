public class Scorpion extends Creature {
    private static Scorpion scorpion = new Scorpion();

    private Scorpion() {
        super("Scorpion");
    }

    public static Scorpion getScorpionObject() {
        return scorpion;
    }
}
