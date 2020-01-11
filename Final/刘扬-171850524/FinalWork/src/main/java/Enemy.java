import javafx.scene.image.*;

public class Enemy extends Creature {
    public Enemy(String name) {
        this.name = name;
        if(name.equals("蛇精") == true) {
            this.HP = 150;
            this.damage = 20;
            this.creatureIMG = new ImageView(new Image("enemy1.png",
                    50, 50, true, true));
        }
        else if(name.equals("蝎子精") == true){
            this.HP = 200;
            this.damage = 30;
            this.creatureIMG = new ImageView(new Image("enemy2.png",
                    50, 50, true, true));
        }
        else {
            this.HP = 150;
            this.damage = 15;
            this.creatureIMG = new ImageView(new Image("enemy3.png",
                    50, 50, true, true));
        }
        this.alive = true;
        this.camp = -1;
        God.evilInc();
        Creature.pane.getChildren().add(creatureIMG);
    }
}