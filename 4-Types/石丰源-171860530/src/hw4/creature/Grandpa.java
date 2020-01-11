package hw4.creature;

public class Grandpa extends Creature implements CheerUp {
    public Grandpa(){
        super("爷爷");
    }
    @Override
    public void cheerUp() {
        System.out.println("爷爷：孩子们加油，打败妖怪!!!");
    }
}
