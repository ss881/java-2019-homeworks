package finalproject.controller;

import finalproject.creatures.Creature;
import finalproject.worldmap.World;

import java.util.Arrays;
import java.util.Random;

public class CreatureController implements CharacterController {
    private static final double CALABASHSIDEWINPROBABILITY = 0.6; /* For now, just use this (FIXME) */
    private World world;
    private static CreatureController creatureController = null;

    /* Singleton */
    private CreatureController(World world) {
        this.world = world;
    }

    public static synchronized CreatureController getInstance(World world) {
        if (creatureController == null) {
            creatureController = new CreatureController(world);
        }
        return creatureController;
    }

    private synchronized int rowEnemyMove(Creature creature, int x, int y) {
        /* Determine whether there is enemy on the same row, if so,
         * return the corresponding move of the closest one, else return 0
         */
        int worldSize = world.getSIZE();
        int columnIndex = 0;
        int rightIndex = worldSize * 2;
        int leftIndex = -worldSize;
        for (int i = y + 1; i < worldSize - 1; i++) {
            /* Here the boundary is worldSize - 1 because we will not go into the first and last column */
            if (world.getCreature(x, i) != null && world.getCreature(x, i).getSide() != creature.getSide()) {
                rightIndex = i;
                break;
            }
        }
        for (int i = y - 1; i > 0; i--) {
            if (world.getCreature(x, i) != null && world.getCreature(x, i).getSide() != creature.getSide()) {
                leftIndex = i;
                break;
            }
        }
        columnIndex = (rightIndex - y) < (y - leftIndex) ? 1 : -1;
        if (rightIndex == worldSize * 2 && leftIndex == -worldSize) {
            columnIndex = 0;
        }
        return columnIndex;
    }

    private synchronized int columnEnemyMove(Creature creature, int x, int y) {
        /* Determine whether there is enemy on the same column, if so,
         * return the move towards the closest one, else return 0
         */
        int worldSize = world.getSIZE();
        int rowIndex = 0;
        int aboveIndex = -worldSize;
        int belowIndex = worldSize * 2;
        for (int i = x + 1; i < worldSize; i++) {
            if (world.getCreature(i, y) != null && world.getCreature(i, y).getSide() != creature.getSide()) {
                belowIndex = i;
                break;
            }
        }
        for (int i = x - 1; i >= 0; i--) {
            if (world.getCreature(i, y) != null && world.getCreature(i, y).getSide() != creature.getSide()) {
                aboveIndex = i;
                break;
            }
        }
        rowIndex = (belowIndex - x) < (x - aboveIndex) ? 1 : -1;
        if (aboveIndex == -worldSize && belowIndex == worldSize * 2) {
            rowIndex = 0;
        }
        return rowIndex;
    }

    private synchronized int[] randomMove(int x, int y) {
        int[] nextPos = new int[2];
        Arrays.fill(nextPos, -1);
        Random random = new Random();

        int[] availableX = {-1, 1};
        int[] availiableY = {-1, 1};
        int[] randomRangeX = {0, 2};
        int[] randomRangeY = {0, 2};
        randomRangeX[0] = x == 0 ? 1 : 0;
        randomRangeX[1] = x == world.getSIZE() - 1 ? 1 : 2;
        randomRangeY[0] = y <= 1 ? 1 : 0;
        randomRangeY[1] = y >= world.getSIZE() - 2 ? 1 : 2;

        if (randomRangeX[0] == randomRangeX[1]) {
            nextPos[0] = x;
        } else {
            int index = random.nextInt(randomRangeX[1] - randomRangeX[0]) + randomRangeX[0];
            nextPos[0] = x + availableX[index];
        }
        if (randomRangeY[0] == randomRangeY[1]) {
            nextPos[1] = y;
        } else {
            nextPos[1] = y + availiableY[random.nextInt(randomRangeY[1] - randomRangeY[0]) + randomRangeY[0]];
        }
        return nextPos;
    }

    private synchronized int[] nextMove(Creature creature) {
        /* Return the next move of the given creature (specified by coordinate x and y)
         * The return value is an array of length 2, which specify the next move x and y value
         */
        int[] nextPos = new int[2];
        Arrays.fill(nextPos, -1);
        int x = creature.getX();
        int y = creature.getY();
        if (world.getCreature(x, y) == null) {
            /* The given creature might be dead already, check first */
            assert (false);
            nextPos[0] = -1;
            nextPos[1] = -1;
        } else {
            int rowMove = rowEnemyMove(creature, x, y);
            int columnMove = columnEnemyMove(creature, x, y);
            if (rowMove != 0) {
                nextPos[0] = x;
                nextPos[1] = y + rowMove;
            } else if (columnMove != 0) {
                nextPos[0] = x + columnMove;
                nextPos[1] = y;
            } else {
                nextPos = randomMove(x, y);
            }
        }
        return nextPos;
    }

    private boolean fightWith(Creature thisCreature, Creature thatCreature) {
        /* this.creature fight with that creature
         * Return true if win, false if lose */
        Random random = new Random();
        double randomNumber = random.nextDouble();

        /* Console output */
        System.out.println(thisCreature + " fight with " + thatCreature);

        double threshold = CALABASHSIDEWINPROBABILITY;
        if (thisCreature.getSide() == Creature.Side.GoblinSide) {
            threshold = 1.0 - threshold;
        }
        if (randomNumber < threshold) {
            System.out.println("Winner is: " + thisCreature);
            world.kill(thatCreature.getX(), thatCreature.getY());
            System.out.println("Number of remaining creatures in Calabash side is: " + world.numAliveCalabashSides());
            System.out.println("Number of remaining creatures in Goblin side is: " + world.numAliveGoblinSides());
            return true;
        } else {
            System.out.println("Winner is: " + thatCreature);
            world.kill(thisCreature.getX(), thisCreature.getY());
            System.out.println("Number of remaining creatures in Calabash side is: " + world.numAliveCalabashSides());
            System.out.println("Number of remaining creatures in Goblin side is: " + world.numAliveGoblinSides());
            return false;
        }
    }

    private void makeMove(Creature srcCreature, int x, int y) {
        Creature destCreature = world.getCreature(x, y);
        if (destCreature != null) {
            if (destCreature.getSide() == srcCreature.getSide()) {
                /* Case where the our teammate stands on our target grid */
                return;
            } else {
                /* Case where our enemy stands on our target grid, so we fight with it */
                if (fightWith(srcCreature, destCreature)) {
                    world.move(srcCreature.getX(), srcCreature.getY(), x, y);
                } else {
                    return;
                }
            }
        } else {
            world.move(srcCreature.getX(), srcCreature.getY(), x, y);
        }
    }

    @Override
    public boolean step(Creature creature) {
        if (!creature.isAlive()) {
            return false;
        }
        if (creature.getSide() == Creature.Side.CalabashSide && world.numAliveGoblinSides() <= 0) {
            return false;
        }
        if (creature.getSide() == Creature.Side.GoblinSide && world.numAliveCalabashSides() <= 0) {
            return false;
        }
        int[] nextPos = nextMove(creature);
        if (nextPos[0] == -1 && nextPos[1] == -1) {
            return false;
        }
        makeMove(creature, nextPos[0], nextPos[1]);
        return true;
    }
}
