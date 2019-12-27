package huluwa.util;


import huluwa.creature.*;

import java.util.ArrayList;
import java.util.Collections;

public class CreatureFactory {
    private CreatureFactory() {

    }

    public static ArrayList<Huluwa> createHuluwas() {
        ArrayList<Huluwa> huluwas = new ArrayList<>();
        huluwas.add(new Huluwa(1, "大娃"));
        huluwas.add(new Huluwa(2, "二娃"));
        huluwas.add(new Huluwa(3, "三娃"));
        huluwas.add(new Huluwa(4, "四娃"));
        huluwas.add(new Huluwa(5, "五娃"));
        huluwas.add(new Huluwa(6, "六娃"));
        huluwas.add(new Huluwa(7, "七娃"));
        Collections.shuffle(huluwas);
        return huluwas;
    }

    public static ArrayList<Minion> createMinions() {
        ArrayList<Minion> minions = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
//            minions.add(new Minion("喽啰#" + (i + 1)));
            minions.add(new Minion("小喽啰"));
        }
        return minions;
    }

    public static Grandpa createGrandpa() {
        return new Grandpa();
    }

    public static Scorpion createScorpion() {
        return new Scorpion();
    }

    public static Snake createSnake() {
        return new Snake();
    }
}
