package finalproject.worldmap;

import finalproject.creatures.Creature;

public class Grid<T extends Creature> {
    private T creature;
    private boolean grave; /* If grave is true, then this grid has a grave on it */

    public Grid() {
        creature = null;
        grave = false;
    }

    public Grid(T creature) {
        this.creature = creature;
        grave = false;
    }

    public T get() {
        return creature;
    }

    public synchronized void setCreature(T creature) {
        assert (this.creature == null);
        this.creature = creature;
    }

    public synchronized boolean hasGrave() {
        return grave;
    }

    public synchronized void remove() {
        creature = null;
    }

    public synchronized void kill() {
        creature.kill();
        creature = null;
        grave = true;
    }

}
