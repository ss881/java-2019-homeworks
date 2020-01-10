package creature;
import space.Space;
import space.Tile;

public class Xiaolouluo extends Creature {
    int id;
    Xiaolouluo(Space s, int id,float[] init,int team_id){
        super(s,init,team_id);
        this.id=id;
        this.name="卒"+id;
    }
    public int getid(){return id;}
}
