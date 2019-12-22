package WorldMap;

import Creatures.Creature;

public abstract class World {
    private Grid[][] worldMap;

    public World(Grid[][] worldMap) {
        this.worldMap = worldMap;
    }

    public int getSIZE() {
        return worldMap.length;
    }

    public Creature get(int x, int y) {
        if (worldMap[x][y] == null)
            return null;
        else
            return worldMap[x][y].get();
    }

    public <T extends Creature> void place(T creature, int x, int y) {
        creature.setX(x);
        creature.setY(y);
        worldMap[x][y] = new Grid<>(creature);

    }

    public void move(int fromX, int fromY, int toX, int toY) {
        if (worldMap[fromX][fromY] == null || worldMap[toX][toY] != null)
            throw new IllegalArgumentException();
        else {
            Creature creature = worldMap[fromX][fromY].get();
            worldMap[fromX][fromY] = null;
            creature.to(toX, toY);
            worldMap[toX][toY] = new Grid<>(creature);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Grid[] row : worldMap) {
            for (Grid grid : row) {
                if (grid == null) {
                    res.append(String.format("%-12s", "O"));
                } else {
                    res.append(String.format("%-12s", grid.get().toString()));
                }
            }
            res.append("\n");
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    /* Unit test */
    public static void main(String[] args) {
    }
}
