package com.company;

import javax.swing.plaf.nimbus.State;

enum State_of_Block {NO,WITHDRAW,EXSIST}
public class Block {
    private Character character;
    private State_of_Block state;

    public Block(){
           character=new Character();
            state= State_of_Block.NO;
    }
    public void init_(Character chara,int col,int row,Identity identity,Picture picture,Color_of_Calabash coc){
        character=chara;
        state= State_of_Block.EXSIST;
        character.init_(col,row,identity,picture,coc);
    }
    public void set_character(Character a){
        character=a;
    }
    public void set_state(State_of_Block a){
        state=a;
    }
    public Character ret_character(){
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
        this.character=later.ret_character();
        this.state=State_of_Block.EXSIST;
        later=a;
    }
    public Picture ret_pic(){
        if(state==State_of_Block.EXSIST)
            return character.ret_pic();
        else
            return Picture.BLOCK;
    }
}
