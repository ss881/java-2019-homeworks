package creature;

import javafx.scene.image.Image;
import objinfo.XPoint2D;

final public class SnakeEssence extends Creature {
    public SnakeEssence() {
        super();
        setApperrance(new Image("images/sj.png"),new Image("images/sj - 副本.png"));
        isEvil=true;
        setName("Ss");
        setPosition(new XPoint2D(ctrl.col-1, 0));
    }

    public void sayComeOn() {
        System.out.println("（画外音）" + getName() + ": 我是蛇精，我在给我的蝎子精加油");
    }
}
