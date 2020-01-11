package main;

import Creature.Creature;

public class Position<T extends Creature> {
    private T holder;

    public Position(T h){
        holder = h;
    }

    public T getHolder() {
        return holder;
    }
}
