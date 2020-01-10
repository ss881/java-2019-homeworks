package com.company;
import java.util.*;

public class Grandpa<T extends Integer> extends Creature<T> {
    private int gourdsnum = 7;
    private GourdDoll<T>[] gourds = null;

    public Grandpa(Position<T> position, String name, Ground<T> ground) {super(position, name, ground);}
    public Grandpa(Position<T> position, String name, Ground<T> ground, int gourdsnum) {
        super(position, name, ground);
        this.gourdsnum = gourdsnum;
    }
    public void setGourds() {
        if (gourds != null) return;
        System.out.println("老爷爷：孩子们，你们在哪啊？");
        boolean[] allgourds = new boolean[gourdsnum];
        gourds = new GourdDoll[gourdsnum];
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
            Position<T> nextposition = ground.getNewAddress(0, Ground.GROUBD_SIZE, 0, Ground.GROUBD_SIZE);
            switch(gourd) {
                case 0 : gourds[i] = new GourdDoll<T>(nextposition, "Doll1st", ground); break;
                case 1 : gourds[i] = new GourdDoll<T>(nextposition, "Doll2nd", ground); break;
                case 2 : gourds[i] = new GourdDoll<T>(nextposition, "Doll3rd", ground); break;
                case 3 : gourds[i] = new GourdDoll<T>(nextposition, "Doll4th", ground); break;
                case 4 : gourds[i] = new GourdDoll<T>(nextposition, "Doll5th", ground); break;
                case 5 : gourds[i] = new GourdDoll<T>(nextposition, "Doll6th", ground); break;
                case 6 : gourds[i] = new GourdDoll<T>(nextposition, "Doll7th", ground); break;
            }
//            map.showMap();
        }
    }
    public void setGourdsStrategy(Position<T> position, StrategyFormation strategy)
    throws Exception {
        System.out.println("老爷爷：变换队形->" + strategy + position);
        position.setPath(-1);
        try {
            ground.setGroupStrategyFormation("GourdDoll", null, strategy, position, gourdsnum);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }
}
