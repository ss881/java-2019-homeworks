import javafx.scene.image.*;

public class Boy extends Creature {
    private String color = "";
    private int rank = 0;

    public Boy(String name, String color, int rank) {
        this.rank = rank;
        this.name = name;
        this.color = color;
        this.creatureIMG = new ImageView(new Image("boy"+rank+".png",
                50, 50, true, true));
        this.HP = 150;
        this.damage = 20;
        this.alive = true;
        this.camp = 1;
        God.justInc();
        Creature.pane.getChildren().add(creatureIMG);
    }
    public void setRank(int rank) { this.rank = rank; }
    public void setColor(String color) { this.color = color; }
    public int tellRank() { return rank; }
    public String tellColor() { return color; }
}