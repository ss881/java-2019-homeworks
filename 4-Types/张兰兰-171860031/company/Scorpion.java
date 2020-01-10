package com.company;

public class Scorpion extends Character {
    private Identity identity;
    @Override
    public void init_(int col,int row,Color_of_Calabash coc){


        identity=Identity.SCORPION;

    }
    @Override
    public void show(){
        System.out.print("C");
    }
}
