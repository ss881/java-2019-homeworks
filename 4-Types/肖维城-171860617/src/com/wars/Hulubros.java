package com.wars;

public class Hulubros {
    Hulu h1=new Hulu('A');
    Hulu h2=new Hulu('B');
    Hulu h3=new Hulu('C');
    Hulu h4=new Hulu('D');
    Hulu h5=new Hulu('E');
    Hulu h6=new Hulu('F');
    Hulu h7=new Hulu('G');
    Hulu hl[]=new Hulu[7];//使用数组为了方便操作

    public Hulubros(){//set pos randomly

        h1.setPos(1,2);
        h2.setPos(7,4);
        h3.setPos(3,4);
        h4.setPos(11,6);
        h5.setPos(5,3);
        h6.setPos(8,5);
        h7.setPos(13,3);
        hl[0]=h1;
        hl[1]=h2;
        hl[2]=h3;
        hl[3]=h4;
        hl[4]=h5;
        hl[5]=h6;
        hl[6]=h7;
    }

    public void sort(int x,int y){//sort a line by h1's pos
        if(!h1.comparePos(x, y)){
            h1.moveto(x,y);
        }
        if(!h2.comparePos(x - 1, y)){
            h2.moveto(x-1,y);
        }
        if(!h3.comparePos(x - 2, y)){
            h3.moveto(x-2,y);
        }
        if(!h4.comparePos(x - 3, y)){
            h4.moveto(x-3,y);
        }
        if(!h5.comparePos(x-4,y)){
            h5.moveto(x-4,y);
        }
        if(!h6.comparePos(x - 5, y)){
            h6.moveto(x-5,y);
        }
        if(!h7.comparePos(x - 6, y)){
            h7.moveto(x-6,y);
        }
    }

    class Hulu extends Organism{
        public Hulu(char c){
            ch=c;
        }
    }
}

