package hw4.creature.monster;

import hw4.creature.CheerUp;

public class Snake extends Monster implements CheerUp {
    public Snake(){
        super("蛇精");
    }
    @Override
    public void cheerUp() {
        System.out.println("蛇精：小的们给我上，把这群家伙都给我抓起来!!!");
    }
}
