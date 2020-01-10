package com.company;

public class Main {

    public static void main(String[] args) {
        int times = 3;
        for (int i = 1; i <= Ground.GROUBD_SIZE; i++) {
            if (i % 3 == 0) {
                System.out.print("start");
            }
            else {
                System.out.print("********");
            }
        }
        System.out.print("\n");
        Ground<Integer> ground = new Ground<Integer>();
        Grandpa<Integer> grandpa = new Grandpa<Integer>(
                new Position<Integer>(0, Ground.GROUBD_SIZE / 2), "grandpa", ground);
//        map.showMap();
        grandpa.setGourds();
//        map.showMap();
        Scorpion<Integer> scorpion = new Scorpion<Integer>(
                new Position<Integer>(Ground.GROUBD_SIZE - 1, Ground.GROUBD_SIZE / 3),
                "scorp", ground);
        Creature snake = new Creature(
                new Position<Integer>(Ground.GROUBD_SIZE - 1, Ground.GROUBD_SIZE / 2)
                , "snake", ground);

//        scorpion.callLouLuo();
        scorpion.callLouLuo();
        ground.showGround();
        try {
            grandpa.setGourdsStrategy(
                    new Position<Integer>(2, 0), StrategyFormation.CHANG_SHE_ZHEN);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        ground.showGround();
//        StrategyFormation strategy = StrategyFormation.getTheFormation(6);
        StrategyFormation strategy = StrategyFormation.getRandomFormation();
        while (times-- >= 0) {
            try {
                scorpion.setLouluoStrategy(
                        ground.getNewAddress(
                                Ground.GROUBD_SIZE / 2 ,
                                Ground.GROUBD_SIZE * 2 / 3,
                                Ground.GROUBD_SIZE / 3,
                                Ground.GROUBD_SIZE / 2),
                        strategy);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            strategy = strategy.getNextFormation();
            ground.showGround();
        }
//        System.out.print("\n");
//        scorpion.changeWildGoose();
//        map.showMap();

//        System.out.println("***************************************************************");
//        scorpion.changeWave();
//        map.showMap();

//        System.out.println("***************************************************************");
//        scorpion.changeCraneWing();
//        map.showMap();
    }
}
