package ground;

import creature.Creature;

import java.util.ArrayList;

public class Position {

    private static final int IMAGE_SIZE = 32;
    private final int row, col;

    private Creature creature;
    private ArrayList<Creature> deadCreatures;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
        this.creature = null;
        this.deadCreatures = new ArrayList<>();
    }

    public void removeCreature() { creature = null; }

    public void removeDeadCreatures() { deadCreatures.clear(); }

    public void addDeadCreature() { deadCreatures.add(0, creature); creature = null; }

    boolean isOccupied() { return creature != null; }

    /* getter and setter */

    public int getCol() { return col; }

    public int getRow() { return row; }

    public double getX() { return col * IMAGE_SIZE; }

    public double getY() { return row * IMAGE_SIZE; }

    public int getImageSize() { return IMAGE_SIZE; }

    public void setCreature(Creature creature) { this.creature = creature; }

    public Creature getCreature() { return creature; }

    public ArrayList<Creature> getDeadCreatures() { return deadCreatures; }
}
