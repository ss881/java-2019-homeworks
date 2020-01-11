package nju.sfy.model.creature.monster;

import nju.sfy.model.creature.Creature;

public class Monster extends Creature {
    public Monster(){
        super();
    }
    public Monster(String name){
        super(name);
    }
    public Monster(String name, int health, int attackValue, int damageArea, int moveSpeed){
        super(name, health, attackValue, damageArea, moveSpeed);
    }
}