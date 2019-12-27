package huluwa.creature;

import huluwa.util.Position;

import java.awt.*;

public abstract class Creature {
    private String name;
    private Position position;
    private Image image;

    public Creature(String name, Image image) {
        this.name = name;
        this.image = image;
        position = null;
//        position = new Position(-1, -1);   // no position at begin
    }

    public boolean hasPosition() {
        return position != null;
//        return position.isValid();
    }

    public void releasePosition() {
        if(position != null) {
            position.clearCreature();
            position = null;
        }
    }

    public Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setPosition(Position position) {
        // clear creature previous
        if(this.position != null) {
            this.position.clearCreature();
        }
        position.setCreature(this);
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void swapPosWith(Creature c) {
        Position p = c.getPosition();
        c.setPosition(getPosition());
        setPosition(p);
    }

    // for debug
    @Override
    public String toString() {
        return name + "@" + position;
    }

}
