package com.company;

public class Grandpa extends Character {
    private Identity identity;
    @Override
    public void init_(int col,int row,Color_of_Calabash coc){


        identity=Identity.GRANDPA;

    }
    @Override
    public void show(){
        System.out.print("G");
    }
}
