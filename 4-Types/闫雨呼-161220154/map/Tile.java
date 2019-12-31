package map;
import creature.Creature;

//地砖类
public class Tile {
    public Creature creature;
    public Position position;
    Tile(int x,int y){
        creature=null;
        position=new Position(x,y);
    }
}
