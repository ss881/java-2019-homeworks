package nju.sfy.model.creature.monster;

public class Heeler extends Monster{
    private static int numHeelers = 1;
    public Heeler(){
        super("小喽啰" + Integer.toString(numHeelers), 100, 20, 1, 1000);
        numHeelers++;
    }
}
