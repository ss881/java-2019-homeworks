package BattleField;

import javafx.scene.image.Image;
import Item.Creature;

public class Block{
    private Creature holder = null;
    private boolean isEmpty = true;

    public void join(Creature creature) {
        isEmpty = false;
        this.holder = creature;
        assert(this.holder != null);
    }

    public void withdraw() {
        isEmpty = true;
        holder = null;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public Creature getHolder() {
        return holder;
    }

    public Image getImage() {
        if(holder == null)
            return null;
        else
            return holder.getImage();
    }
}