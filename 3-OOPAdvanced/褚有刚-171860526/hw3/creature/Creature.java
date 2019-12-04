package hw3.creature;

import hw3.util.Position;

import javax.swing.*;
import java.awt.*;

public abstract class Creature {
    private String name;
    private Position position;
    private Image image;    // image should be set by

    public Creature(String name, Image image) {
        this.name = name;
        this.image = image;
        position = new Position(-1, -1);   // no position at begin
    }

    public boolean hasPosition() {
        return position.isValid();
    }

    // (-1, -1) is not a valid position
    // more correctly, negative position is invalid
    public void releasePosition() {
       setPosition(-1, -1);
    }

    public Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    // sometimes use position is convenient
    // but sometimes (x, y) coord is more convenient
    // although it gets harder to extends to 3 dimension
    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPosition(int x, int y) {
        this.position = new Position(x, y);
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

//  REFACTOR
//    // this method is only allowed be invoked by subclass
//    // and the most important part is that every derived class should
//    // invoke it to set their image so that it's appearance will be showed on the window
//    protected void setImage(Image image) {
//        this.image = image;
//    }
}
