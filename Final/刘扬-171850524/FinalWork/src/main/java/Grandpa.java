import javafx.scene.image.*;

public class Grandpa extends Creature{
    public Grandpa() {
        this.name = "爷爷";
        this.HP = 100;
        this.damage = 15;
        this.alive = true;
        this.camp = 1;
        this.creatureIMG = new ImageView(new Image("grandpa.png",
                50, 50, true, true));
        God.justInc();
        Creature.pane.getChildren().add(creatureIMG);
    }
}
