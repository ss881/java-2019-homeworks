package gamectrl;

import creature.Creature;

//  维护视场上的一个格子
public class Grid2D<T extends Creature> {
    private boolean occupied = false;

    public boolean isOccupied() {
        return occupied;
    }

    public boolean isFree() {
        return !occupied;
    }

    private void setFree() {
        occupied = false;
    }

    private void setOccupied() {
        occupied = true;
    }

    private String cliDisplayName = new String("  ");

    public void displayCli() {
        System.out.print(cliDisplayName);
    }

    T theCreature;

    public Grid2D() {

    }
    public T getTheCreature(){return theCreature;}
    public boolean setCreature(T newCreature) {
        if (isOccupied()) {
            return false;
        }
        theCreature = newCreature;
        cliDisplayName = theCreature.getName();
        setOccupied();
        return true;
    }

    public void removeCreature() {
        setFree();
        theCreature = null;
        cliDisplayName = new String("  ");
    }
}
