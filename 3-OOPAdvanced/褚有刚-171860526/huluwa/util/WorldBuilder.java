package huluwa.util;

import huluwa.creature.*;

import java.util.ArrayList;

public class WorldBuilder {
    public static HuluWorld buildHuluWorld(int row, int col) {
        HuluWorld huluWorld = new HuluWorld();

        // create creatures
        ArrayList<Huluwa> huluwas = CreatureFactory.createHuluwas();
        ArrayList<Minion> minions = CreatureFactory.createMinions();
        Grandpa grandpa = CreatureFactory.createGrandpa();
        Scorpion scorpion = CreatureFactory.createScorpion();
        Snake snake = CreatureFactory.createSnake();

        // initial creatures' external attributes
        Position[][] positions = new Position[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                positions[i][j] = new Position(i, j);
            }
        }
        grandpa.setPosition(positions[0][0]);
        snake.setPosition(positions[11][0]);
        grandpa.buZhen(positions, huluwas);
        scorpion.buZhen(positions, minions);


        // direct huluWorld
        huluWorld.setHuluwas(huluwas);
        huluWorld.setMinions(minions);
        huluWorld.setGrandpa(grandpa);
        huluWorld.setScorpion(scorpion);
        huluWorld.setSnake(snake);
        huluWorld.setPositions(positions);

        return huluWorld;
    }
}
