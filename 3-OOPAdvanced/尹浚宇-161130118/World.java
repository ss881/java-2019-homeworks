import creature.*;

public class World
{
    private final int MAP_SIZE = 15;
    private Creature[][] map = new Creature[MAP_SIZE][MAP_SIZE];

    public int getMapSize()
    {
        return MAP_SIZE;
    }

    public boolean isEmpty(int x, int y)
    {
        if (map[x][y] == null)
            return true;
        return false;
    }

    private boolean checkPosition(int x, int y)
    {
        if (x >= MAP_SIZE || y >= MAP_SIZE || x < 0 || y < 0)
        {
            System.out.println("position(x, y) doesn't exist on map!");
            return false;
        }
        return true;
    }

    public void putCreature(int x, int y, Creature c)
    {
        if (!checkPosition(x, y))
            return;
        if (map[x][y] == null)
        {
            map[x][y] = c;
            c.setCoordinante(x, y);
        }
        else
            System.out.println("position(x, y) has already been occupied!");
    }

    public Creature getCreature(int x, int y)
    {
        if (!checkPosition(x, y))
            return null;
        return map[x][y];
    }

    public void clearMap()
    {
        for (int i = 0; i < MAP_SIZE; i++)
            for (int j = 0; j < MAP_SIZE; j++)
            {
                if (map[i][j] != null)
                {
                    map[i][j].setCoordinante(-1, -1);
                    map[i][j] = null;
                }
            }
    }

    public void printMap()
    {
        for (int i = 0; i < MAP_SIZE; i++)
        {
            for (int j = 0; j < MAP_SIZE; j++)
            {
                if (map[i][j] != null)
                    System.out.print(map[i][j].getToken() + " ");
                else
                    System.out.print("-- ");
            }
            System.out.println();
        }
    }
}
