package com.company;

public class Pips extends Character {
    private Identity identity;
    @Override
    public void init_(int col,int row,Color_of_Calabash coc){


        identity=Identity.PIPS;

    }
    @Override
            public void show(){
        System.out.print("P");
    }

}
