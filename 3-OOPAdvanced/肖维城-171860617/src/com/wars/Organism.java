package com.wars;

public abstract class Organism {
    public char ch;
    public int x,y;

    public void setPos(int px,int py){
        x=px;y=py;
    }

    public void moveto(int dx,int dy){
        x=dx;y=dy;
    }

    boolean comparePos(int px,int py){
        if(px==x&&py==y){
            return true;
        }
        else return false;
    }
}
