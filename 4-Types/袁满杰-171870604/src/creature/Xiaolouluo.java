package creature;
import space.Space;
import space.Tile;

public class Xiaolouluo extends Creature {
    int id;
    Xiaolouluo(Space s, int id){
        super(s);
        this.id=id;
        this.name="卒";
    }
    public int getId(){return id;}
}
