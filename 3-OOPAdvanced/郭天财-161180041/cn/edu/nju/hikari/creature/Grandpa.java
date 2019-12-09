package creature;

import ground.*;
//import creature.*;

public class Grandpa extends Creature{
    public static SevenBros bros = new SevenBros();
    public Grandpa(){
        super("爷爷");
        super.moveTo(Ground.tiles[4][0]);
    }

    public void giveCommand(int cmd){
        switch(cmd){
            case 1:bros.randomSnake();break;
            case 2:bros.snake();break;
            default:bros.randomSnake();
        }
    }

    

    

}