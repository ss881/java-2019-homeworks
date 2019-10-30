package myorganism;
import java.util.Vector;

class Monster extends Organism {
    public Monster(int x,int y)
    {
        type=Species.MONSTER;
        pos_x=x;
        pos_y=y;
    }
    public void Return(Organism[][] map,Vector<Monster> monsters)
    {
        monsters.add(0,this);
        map[pos_x][pos_y]=null;
        queuing=true;//表示刚归队，等待重新部署
    }
    public void exitMap(Organism[][] map) {
        if (pos_x >= 0 && pos_y >= 0) {
            System.out.printf("Monster: (%d,%d)-->离场\n", pos_x, pos_y);
            pos_y = pos_x = -1;
            queuing = false;
        }
    }

}

class Scorpion extends Organism
{
    public Scorpion(int x,int y)
    {
        type= Species.SCORPION;
        pos_x=x;
        pos_y=y;
    }
    public void Return(Organism[][] map,Vector<Scorpion> scorpions)
    {
        scorpions.add(0,this);
        map[pos_x][pos_y]=null;
        queuing=true;

    }
    public void exitMap(Organism[][] map)
    {
        if(pos_x>=0&&pos_y>=0)
        {
            System.out.printf("Scorpion: (%d,%d)-->离场\n",pos_x,pos_y);
            pos_y=pos_x=-1;
            queuing=false;
        }
    }
}
class Snack extends Organism {
    public Snack(int x,int y)
    {
        type= Species.SNAKE;
        pos_x=x;
        pos_y=y;
    }

}