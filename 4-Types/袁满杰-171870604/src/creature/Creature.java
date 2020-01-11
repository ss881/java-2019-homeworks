package creature;
import command.MoveCommand;
import space.Space;
import space.Tile;

import java.util.Random;

public abstract class Creature {
    Space space;
    Tile floor;
    String name;
    public Creature(Space s)
    {
        space=s;
    }
    public void setFloor(Tile floor){this.floor=floor;}
    public void show(){
        System.out.print(this.name);
    };
    public void born()
    {
        int map_size=space.getN();
        Random r=new Random();
        while(true){
            int x=r.nextInt(map_size);
            int y=r.nextInt(map_size);
            if(space.floor[x][y].get()==null){
                space.floor[x][y].set(this);
                this.floor=space.floor[x][y];
                break;
            }
        }
    }
    public void move_to(int x, int y){
        MoveCommand m=new MoveCommand(space,floor.getX(),floor.getY(),x,y);
        m.execute();
    }
    public void leave()
    {
        floor.set(null);
    }
}

