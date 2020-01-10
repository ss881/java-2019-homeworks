package com.company;

public class Snake extends Character {
    private Identity identity;
    @Override
    public void init_(int col,int row,Color_of_Calabash coc){


        identity=Identity.SNAKE;

    }
    @Override
    public void show(){
        System.out.print("N");
    }
}
