package chessboard;

import chessman.creature.Creature;

public class Grid {
    //a grid, a creature

    private boolean existCreature;
    private Creature creatureInGrid;

    public boolean setCreature(Creature creature) {
        if (existCreature) {
            return false;
        } else {
            existCreature = true;
            creatureInGrid = creature;
            return true;
        }
    }

    public void clearCreature() {
        existCreature = false;
    }

    public boolean existCreature() { return existCreature; }

    @Override
    public String toString() {
        return creatureInGrid.toString();
    }

    public Creature getCreatrue() {
        return creatureInGrid;
    }
}