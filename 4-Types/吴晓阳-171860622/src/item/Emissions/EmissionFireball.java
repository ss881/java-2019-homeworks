package item.Emissions;

import item.Emission;
import item.Finished.FinishedFireball;
import item.Item;
import location.Direction;

/*
* 作为被item发射出来的 火球
 */
public class EmissionFireball extends FinishedFireball implements Emission {
    private Item emitter;

    public EmissionFireball(Item emitter) {
        this.emitter = emitter;
    }

    public EmissionFireball(Item emitter, int damage, Direction direction, int speed) {
        super(damage, direction, speed);
        this.emitter = emitter;
    }

    @Override
    public Item getEmitter() {
        return emitter;
    }

    @Override
    public void setEmitter(Item emitter) {
        this.emitter = emitter;
    }

    @Override
    protected boolean interactTypeCheck(Item item) {
        return super.interactTypeCheck(item) && item != getEmitter();
    }
}
