package main;

import creature.*;
import ground.*;
import command.Command;

public class Test{
    public static void main(String[] args){
        Grandpa grandpa = new Grandpa();
        ScorpionField BadGuys = new ScorpionField();
        grandpa.giveCommand(Command.RANDOM_SNAKE);
        Ground.printToScreen();
        grandpa.giveCommand(Command.SNAKE);
        BadGuys.wing();
        Ground.printToScreen();
    }
}