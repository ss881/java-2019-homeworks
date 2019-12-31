package cn.edu.nju.huluwa.creature;


import cn.edu.nju.huluwa.huluworld.HuluWorld;

import java.util.ArrayList;
import java.util.Collections;

public class CreatureFactory {
    private CreatureFactory() {

    }

    public static ArrayList<Huluwa> createHuluwas(HuluWorld huluWorld) {
        ArrayList<Huluwa> huluwas = new ArrayList<>();
        huluwas.add(new Huluwa(huluWorld, 1, "大娃", Colour.RED));
        huluwas.add(new Huluwa(huluWorld, 2, "二娃", Colour.ORANGE));
        huluwas.add(new Huluwa(huluWorld, 3, "三娃", Colour.YELLOW));
        huluwas.add(new Huluwa(huluWorld, 4, "四娃", Colour.GREEN));
        huluwas.add(new Huluwa(huluWorld, 5, "五娃", Colour.CYAN));
        huluwas.add(new Huluwa(huluWorld, 6, "六娃", Colour.BLUE));
        huluwas.add(new Huluwa(huluWorld, 7, "七娃", Colour.PURPLE));
        Collections.shuffle(huluwas);
        return huluwas;
    }

    public static ArrayList<Minion> createMinions(HuluWorld huluWorld) {
        ArrayList<Minion> minions = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
//            minions.add(new Minion("喽啰#" + (i + 1)));
            minions.add(new Minion(huluWorld, "小喽啰", Colour.DARKGRAY));
        }
        return minions;
    }

    public static Grandpa createGrandpa(HuluWorld huluWorld) {
        return new Grandpa(huluWorld, Colour.MAGENTA);
    }

    public static Scorpion createScorpion(HuluWorld huluWorld) {
        return new Scorpion(huluWorld, Colour.DARkBLUE);
    }

    public static Snake createSnake(HuluWorld huluWorld) {
        return new Snake(huluWorld, Colour.DARKGREEN);
    }
}
