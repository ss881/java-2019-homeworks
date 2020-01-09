package finalproject.worldmap;

import finalproject.creatures.*;

import java.util.ArrayList;
import java.util.List;

public abstract class World {
    private Grid<Creature>[][] worldMap;
    private List<CalabashSide> calabashSides;
    private List<GoblinSide> goblinSides;

    public World(Grid<Creature>[][] worldMap) {
        this.worldMap = worldMap;
        for (int i = 0; i < worldMap.length; i++) {
            for (int j = 0; j < worldMap.length; j++) {
                this.worldMap[i][j] = new Grid<>();
            }
        }
        calabashSides = new ArrayList<>();
        goblinSides = new ArrayList<>();
    }

    public synchronized void clear() {
        calabashSides.clear();
        goblinSides.clear();
        for (int i = 0; i < worldMap.length; i++) {
            for (int j = 0; j < worldMap.length; j++) {
                worldMap[i][j] = new Grid<>();
            }
        }
    }

    public int getSIZE() {
        return worldMap.length;
    }

    public synchronized Creature getCreature(int x, int y) {
        assert (worldMap[x][y] != null);
        return worldMap[x][y].get();
    }

    public synchronized boolean hasGrave(int x, int y) {
        return worldMap[x][y].hasGrave();
    }

    public synchronized <T extends Creature> void place(T creature, int x, int y) {
        creature.setX(x);
        creature.setY(y);
        worldMap[x][y].setCreature(creature);
        if (creature instanceof CalabashSide) {
            calabashSides.add((CalabashSide) creature);
        } else {
            goblinSides.add((GoblinSide) creature);
        }
    }

    public synchronized void move(int fromX, int fromY, int toX, int toY) {
        if (worldMap[fromX][fromY].get() == null || worldMap[toX][toY].get() != null) {
            assert (false);
            throw new IllegalArgumentException();
        } else {
            Creature creature = worldMap[fromX][fromY].get();
            worldMap[fromX][fromY].remove();
            creature.to(toX, toY);
            worldMap[toX][toY].setCreature(creature);
        }
    }

    public synchronized void kill(int x, int y) {
        if (worldMap[x][y].get() == null) {
            throw new IllegalArgumentException();
        } else {
            worldMap[x][y].kill();
        }
    }

    public synchronized int numAliveCalabashSides() {
        int count = 0;
        for (CalabashSide calabashSideCreature : calabashSides) {
            if (calabashSideCreature instanceof Grandfather) {
                continue;
            } else {
                count = calabashSideCreature.isAlive() ? count + 1 : count;
            }
        }
        return count;
    }

    public synchronized int numAliveGoblinSides() {
        int count = 0;
        for (GoblinSide goblinSideCreature : goblinSides) {
            if (goblinSideCreature instanceof Snake) {
                continue;
            } else {
                int x = goblinSideCreature.getX();
                int y = goblinSideCreature.getY();
                count = goblinSideCreature.isAlive() ? count + 1 : count;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Grid<Creature>[] row : worldMap) {
            for (Grid<Creature> grid : row) {
                if (grid.get() == null) {
                    if (grid.hasGrave()) {
                        res.append(String.format("%-12s", "X"));
                    } else {
                        res.append(String.format("%-12s", "O"));
                    }
                } else {
                    if (grid.hasGrave()) {
                        res.append(String.format("%-12s", grid.get().toString() + "_X"));
                    } else {
                        res.append(String.format("%-12s", grid.get().toString()));
                    }
                }
            }
            res.append("\n");
        }
        res.append("=========================================================================");
        return res.toString();
    }

    /* Unit test */
    public static void main(String[] args) {
    }
}
