package com.company;
import java.util.*;

public class Grandpa extends Creature {
    private int gourdsnum = 7;
    private Creature[] gourds = null;

    public Grandpa(int x, int y, String name, Map map) {super(x, y, name, map);}
    public Grandpa(int x, int y, String name, Map map, int gourdsnum) {
        super(x, y, name, map);
        this.gourdsnum = gourdsnum;
    }
    public void setGourdsLongSnake(int x, int y) {
        if (gourds != null) return;
        System.out.println("老爷爷：孩子们，你们在哪啊？");
        boolean[] allgourds = new boolean[gourdsnum];
        gourds = new Creature[gourdsnum];
        for (int i = 0; i < allgourds.length; i++) {
            allgourds[i] = false;
        }
        Random rand = new Random();

        for (int i = 0; i < gourds.length; i++) {
            int gourd;
            do {
                gourd = rand.nextInt(7);
            } while (allgourds[gourd] == true);
            allgourds[gourd] = true;
            switch(gourd) {
                case 0 : gourds[i] = new Creature(x, y + i, "大娃", map); break;
                case 1 : gourds[i] = new Creature(x, y + i, "二娃", map); break;
                case 2 : gourds[i] = new Creature(x, y + i, "三娃", map); break;
                case 3 : gourds[i] = new Creature(x, y + i, "四娃", map); break;
                case 4 : gourds[i] = new Creature(x, y + i, "五娃", map); break;
                case 5 : gourds[i] = new Creature(x, y + i, "六娃", map); break;
                case 6 : gourds[i] = new Creature(x, y + i, "七娃", map); break;
            }
//            map.showMap();
        }
    }
}
