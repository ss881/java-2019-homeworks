package command;

import java.util.Random;

public class Command{
    public final static int 
    RANDOM_SNAKE = 1,
    SNAKE = 2,
    WING = 3,
    GOOSE = 4,
    PUNCH = 5,
    FISH_SCALE = 6,
    SQUARE = 7,
    MOON = 8,
    BEE = 9;

    public static int randomCommand(){
        return new Random().nextInt(7)+3;
    }
}

