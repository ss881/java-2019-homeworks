//import MyMap;
package myfinal;
//import java.util.*;
//import java.lang.reflect.*;

public class Organism implements Runnable{
    MyMap pos;
    String name;

    Thread t;
    int isdead;

    int flag;
    public Organism() {
        name = "";
    }

    public  Organism(MyMap mypos, String myname) {
        this.pos = mypos;
        this.name = myname;
        isdead = 0;
        flag = 0;
    }

    public MyMap Getpos() {
        return pos;
    }

    public synchronized void changepos(int x, int y) {
        pos.x = x;
        pos.y = y;
    }
    public void battle()
    {
        flag = 1;
    }
    public void cheer() {
        if (this.getClass().getSimpleName().equals("Monsters"))// if(this.name()=="蛇精")
        {
            System.out.println("蛇精：蝎子精加油！");
        } else if (this.getClass().getSimpleName().equals("Organism")) {
            System.out.println("老爷爷：葫芦娃加油！");
        }
    }

    public <T extends Organism> int godorbad(T x) {
        String s = x.getClass().getSimpleName();
        if (s.equals("Monsters"))
            return 1; // 是坏人
        else
            return 0;
    }
    public void goforward(){
        if (this.godorbad(this)==1)
            this.changepos(this.Getpos().x-1, this.Getpos().y);
        else
            this.changepos(this.Getpos().x+1, this.Getpos().y);
    }
    // public static void main(String []args){
    // Organism brother1 = new Organism(new MyMap(2,3),"大娃");

    //@Override
    public void run() {
       System.out.println("Running"+name);


    }

    public void dead(){
        isdead = 1;
        name = name + "x";
    }
    public void start(){
        System.out.println("Starting"+name);
    }
    // System.out.println(brother1.Getpos().x);
    // }
}