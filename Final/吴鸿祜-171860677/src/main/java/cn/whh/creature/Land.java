package cn.whh.creature;
import javafx.scene.image.Image;


public class Land extends Creature {

    public Land()
    {
        this.aliveImage=new Image(this.getClass().getResourceAsStream("/land.png"));
    }

    public Land(Land land){super(land);}

    @Override
    public String toString() {
        return "this is Land";
    }

}
