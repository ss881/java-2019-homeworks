package com.company;

public class Main {

    public static void main(String[] args) {
        for (int i = 1; i <= Map.MAP_SIZE; i++) {
            if (i % 3 == 0) {
                System.out.print("start");
            }
            else {
                System.out.print("*****");
            }
        }
        System.out.print("\n");
        Map map = new Map();
        Grandpa grandpa = new Grandpa(0, Map.MAP_SIZE / 2, "老爷爷", map);
//        map.showMap();
        grandpa.setGourdsLongSnake(2,0);
//        map.showMap();
        Scorpion scorpion = new Scorpion(Map.MAP_SIZE - 1, Map.MAP_SIZE / 3, "蝎子精", map);
        Creature snake = new Creature(Map.MAP_SIZE - 1,Map.MAP_SIZE / 2, "蛇精", map);

        scorpion.callLouLuo();
        map.showMap();
//        System.out.print("\n");
        scorpion.changeWildGoose();
        map.showMap();

//        System.out.println("***************************************************************");
        scorpion.changeWave();
        map.showMap();

//        System.out.println("***************************************************************");
        scorpion.changeCraneWing();
        map.showMap();
    }
}
