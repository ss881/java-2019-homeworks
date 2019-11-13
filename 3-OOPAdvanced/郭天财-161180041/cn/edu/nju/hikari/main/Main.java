package main;

import creature.*;
import ground.*;
import static command.Command.*;

public class Main{
    static Ground ground = new Ground();
    static Grandpa grandpa = new Grandpa();
    static ScorpionField BadGuys = new ScorpionField();
    public static void main(String[] args){
        grandpa.giveCommand(RANDOM_SNAKE);
        Ground.printToScreen();
        grandpa.giveCommand(SNAKE);

        int cmd = randomCommand();
        //System.out.println(cmd);
        BadGuys.giveCommand(cmd);

        Ground.printToScreen();
        
              

    }
}