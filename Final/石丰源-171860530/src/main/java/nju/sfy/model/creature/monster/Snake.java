package nju.sfy.model.creature.monster;

import nju.sfy.model.creature.CheerUp;

public class Snake extends Monster implements CheerUp {
    //(String name, int health, int attackValue, int damageArea, int moveSpeed)
    public Snake(){
        super("蛇精", 100, 50, 1, 1000);
    }
    @Override
    public void cheerUp() {
        System.out.println("蛇精：小的们给我上，把这群家伙都给我抓起来!!!");
    }
}

