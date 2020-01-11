package creature;
import space.Space;
import space.Tile;

public class Huluwa extends Creature {
    int id;
    public Huluwa(Space s, int id, String name){
        super(s);
        this.name=name;
        this.id=id;
    }
    public int getId(){return id;}
}
