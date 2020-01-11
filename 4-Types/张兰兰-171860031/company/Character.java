package com.company;

import javax.swing.plaf.nimbus.State;


enum State_of_Character {WAIT, ALIVE, DEAD}

public class Character {
    private State_of_Character state;
    private int col;
    private int row;

    private Picture pic;
    private Color_of_Calabash coc;
    public Character(){
        state= State_of_Character.ALIVE;
    }
    public Character(State_of_Character state,int col,int row){
        this.state=state;
        this.col=col;
        this.row=row;


    }
    public void init_(int col,int row,Color_of_Calabash coc){
        this.state=State_of_Character.ALIVE;
        this.col=col;
        this.row=row;

        this.coc=coc;
    }
    public void change_state(State_of_Character state){this.state=state;}
    public void show(){
        System.out.print(" ");
    }


}
