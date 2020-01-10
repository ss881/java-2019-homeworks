package com.company;

public class XiaoBing<T extends Integer> extends Creature<T>{
    public XiaoBing(Position<T> position, String name, Ground ground) {
        super(position, name, ground);
    }
}
