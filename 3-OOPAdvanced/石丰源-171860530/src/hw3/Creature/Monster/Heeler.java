package hw3.Creature.Monster;

public class Heeler extends Monster{
    private static int numHeelers = 1;
    public Heeler(){
        super("喽啰" + Integer.toString(numHeelers));
        numHeelers++;
    }
}
