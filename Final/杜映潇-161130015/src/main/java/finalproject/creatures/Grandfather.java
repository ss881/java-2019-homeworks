package finalproject.creatures;

public class Grandfather extends CalabashSide {
    private static Grandfather grandfather = new Grandfather();

    private Grandfather() {
        super("Grandfather");
    }

    public static Grandfather getGrandfatherObject() {
        return grandfather;
    }
}
