package nju.sfy.model.creature;

public class Grandpa extends Creature implements CheerUp {
    public Grandpa(){
        super("爷爷", 100, 20, 1, 1000);
    }
    @Override
    public void cheerUp() {
        System.out.println("爷爷：孩子们加油，打败妖怪!!!");
    }
}

