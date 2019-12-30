package WorldMap;

import Creatures.Creature;

public class Grid<T extends Creature> {
    private T creature;

    public Grid(T creature) {
        this.creature = creature;
    }

    public T get() {
        return creature;
    }
}
