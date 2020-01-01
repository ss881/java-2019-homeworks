package item.Emissions;

import item.Emission;
import item.Item;
import location.Direction;

/*
* 被发射出来的子弹
 */
public class EmissionBullet extends item.Finished.FinishedBullet implements Emission {
    private Item emitter;

    public EmissionBullet(Item emitter) {
        this.emitter = emitter;
    }

    public EmissionBullet(Item emitter, int damage, Direction direction, int speed) {
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
        return super.interactTypeCheck(item) & item != getEmitter();
    }
}
