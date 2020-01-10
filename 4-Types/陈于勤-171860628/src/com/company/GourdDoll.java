package com.company;

public class GourdDoll<T extends Integer> extends Creature<T>{
    public GourdDoll(Position<T> position, String name, Ground ground) {
        super(position, name, ground);
    }
}
