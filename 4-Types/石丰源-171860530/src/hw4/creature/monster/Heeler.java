package hw4.creature.monster;

public class Heeler extends Monster{
    private static int numHeelers = 1;
    public Heeler(){
        super("小喽啰" + Integer.toString(numHeelers));
        numHeelers++;
    }
}
