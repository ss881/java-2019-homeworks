//import MyMap;
package types.src;

import java.util.*;
import java.lang.reflect.*;

public class Organism {
    MyMap pos;
    String name;

    public Organism() {
        name = "";
    }

    public Organism(MyMap mypos, String myname) {
        this.pos = mypos;
        this.name = myname;
    }

    public MyMap Getpos() {
        return pos;
    }

    public void changepos(int x, int y) {
        pos.x = x;
        pos.y = y;
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
    // public static void main(String []args){
    // Organism brother1 = new Organism(new MyMap(2,3),"大娃");

    // System.out.println(brother1.Getpos().x);
    // }
}