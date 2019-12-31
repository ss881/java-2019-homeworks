package administer;

import creature.Goblin;
import creature.Scorpion;
import creature.Snake;
import util.ImageType;

import java.util.ArrayList;

public class BadCreatureAdminister {
    private ArrayList<Goblin> goblins =new ArrayList<>();
    private Snake snake;
    private Scorpion scorpion;

    public BadCreatureAdminister(){
        for(int i=0;i<6;i++)
            goblins.add(new Goblin(-1,-1, ImageType.GOBLIN,50,true,10,"小怪"+i+"号",3400,9+i));
        snake =new Snake(-1,-1, ImageType.SNAKE,70,true,20,"蛇精",2300,17);
        scorpion=new Scorpion(-1,-1, ImageType.SCORPION,50,true,30,"蝎子精",2800,18);
    }

    public ArrayList<Goblin> getGoblins(){
        return goblins;
    }
    public Snake getSnake(){
        return snake;
    }
    public Scorpion getScorpion(){
        return scorpion;
    }
    public void initial(){
        goblins.clear();
        for(int i=0;i<6;i++)
            goblins.add(new Goblin(-1,-1, ImageType.GOBLIN,50,true,10,"小怪"+i+"号",3400,9+i));
        snake =new Snake(-1,-1, ImageType.SNAKE,70,true,20,"蛇精",2300,17);
        scorpion=new Scorpion(-1,-1, ImageType.SCORPION,50,true,30,"蝎子精",2800,18);
    }

    public boolean isAllDie(){
        for(Goblin monster: goblins){
            if(monster.isAlive())
                return false;
        }
        if(snake.isAlive())
            return false;
        if(scorpion.isAlive())
            return false;
        return true;
    }
}
