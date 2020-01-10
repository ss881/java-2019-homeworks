package com.company;

public class Calabash extends Character {
    private Color_of_Calabash color;
    //葫芦娃的初始化
    private Identity identity;
    @Override
    public void init_(int col,int row,Color_of_Calabash coc){

        color=coc;
        identity=Identity.CALABASH;

    }

    @Override
    public void show(){

       switch(color){
           case RED:
               System.out.print("1");
               break;
           case ORANGE :
               System.out.print("2");
               break;
           case YELLOW:
               System.out.print("3");
               break;
           case  GREEN:
               System.out.print("4");
               break;
           case  CYAN:
               System.out.print("5");
               break;
           case BLUE:
               System.out.print("6");
               break;
           case PURPLE:
               System.out.print("7");
               break;

       }

    }
}
