package creature;
import space.Space;
import space.Tile;

public class Huluwa extends Creature {
    int id;
    public Huluwa(Space s, int id, String name,float[] init,int team_id){
        super(s,init,team_id);
        this.name=name;
        this.id=id;
    }
    public int getid(){return id;}
}
