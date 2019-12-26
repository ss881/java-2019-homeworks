package creature;

import javafx.scene.image.Image;

import java.util.Random;

final public class EvilLolo extends Creature {
    public int appearanceType;
    EvilLolo(int id) {
        super();
        Random r=new Random();
        int i=r.nextInt(3)+1;
        appearanceType=i;
        setApperrance(new Image("images/lolo-" + i + ".png"), new Image("images/lolo-"+i+" - 副本.png"));
        isEvil=true;
        setName("*.");
        setId(id);
    }
}
