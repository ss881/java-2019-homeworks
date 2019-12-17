package com.company;

import javax.swing.plaf.nimbus.State;

enum State_of_Block {NO,WITHDRAW,EXSIST}
public class Block <T extends Character> {
    private T character;
    private State_of_Block state;

    public Block(){

            state= State_of_Block.NO;
    }
    public void init_(T chara,int col,int row,Color_of_Calabash coc){
        character=chara;
        state= State_of_Block.EXSIST;
        character.init_(col,row,coc);
    }
    public void set_character(T a){
        character=a;
    }
    public void set_state(State_of_Block a){
        state=a;
    }
    public T ret_character(){
        return character;
    }
    public State_of_Block ret_state( ){
        return state;
    }
    public void withdraw_state()
    {
        state=State_of_Block.WITHDRAW;
    }
    public void Change_place(Block later){
        Block a=new Block();
        a=this;
        this.character=(T) later.ret_character();
        this.state=State_of_Block.EXSIST;
        later=a;
    }
    public void show(){
        if(state==State_of_Block.EXSIST)
            character.show();
        else
            System.out.print(" ");
    }
}
