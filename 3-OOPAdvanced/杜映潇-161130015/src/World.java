import java.util.ArrayList;

public class World {
    private static final int SIZE = 12;

    /* At any position, if the element is null, then there is no Calabash Brother */
    private Creature[][] worldMap;
    private ArrayList<CalabashBrother> brothers; /* Contains all Calabash Brothers in the world map */
    private ArrayList<Goblin> goblins; /* Contains all goblins on the map */
    private Grandfather grandfather;
    private Scorpion scorpion;
    private Snake snake;

    public World() {
        worldMap = new Creature[SIZE][SIZE];
        brothers = new ArrayList<>();
        goblins = new ArrayList<>();
    }

    public int getSIZE() {
        return worldMap.length;
    }

    public Creature get(int x, int y) {
        return worldMap[x][y];
    }

    public void place(Creature creature, int x, int y) {
        if (creature == null)
            throw new IllegalArgumentException();
        else {
            /* Place a new creature on the world map */
            if (creature instanceof CalabashBrother)
                brothers.add((CalabashBrother) creature);
            else if (creature instanceof Goblin)
                goblins.add((Goblin) creature);
            else if (creature instanceof Grandfather)
                grandfather = (Grandfather) creature;
            else if (creature instanceof Scorpion)
                scorpion = (Scorpion) creature;
            else if (creature instanceof Snake)
                snake = (Snake) creature;
            else
                throw new IllegalArgumentException(); /* We don't know what has been put on the map */
            creature.setX(x);
            creature.setY(y);
            worldMap[x][y] = creature;
        }
    }

    public void exchange(Creature creatureOne, Creature creatureTwo) {
        if (creatureOne == null || creatureTwo == null)
            throw new IllegalArgumentException();
        else {
            int oneX = creatureOne.getX(), oneY = creatureOne.getY();
            int twoX = creatureTwo.getX(), twoY = creatureTwo.getY();
            creatureOne.to(twoX, twoY);
            creatureTwo.to(oneX, oneY);
            worldMap[oneX][oneY] = creatureTwo;
            worldMap[twoX][twoY] = creatureOne;
        }
    }

    public void move(int fromX, int fromY, int toX, int toY) {
        if (worldMap[fromX][fromY] == null || worldMap[toX][toY] != null)
            throw new IllegalArgumentException();
        else {
            Creature creature = worldMap[fromX][fromY];
            worldMap[fromX][fromY] = null;
            creature.to(toX, toY);
            worldMap[toX][toY] = creature;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Creature[] row : worldMap) {
            for (Creature creature : row) {
                if (creature == null) {
                    res.append(String.format("%-12s", "O"));
                } else {
                    res.append(String.format("%-12s", creature.toString()));
                }
            }
            res.append("\n");
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    /* Unit test */
    public static void main(String[] args) {
        World world = new World();
        Creature calabashBrother = new CalabashBrother(1);
        world.place(calabashBrother, 0, 0);
        Creature creature = world.get(0, 0);
        if (creature instanceof CalabashBrother)
            System.out.println("HHH");
    }
}
