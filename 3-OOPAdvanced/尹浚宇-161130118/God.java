import creature.*;

public class God
{
    private final int NR_CALA = 7;
    private final int NR_PAWN = 6;

    public World createWorld()
    {
        return new World();
    }

    public CalabashBrother createCalabashBrother(String name, int camp, String token, String color, int order)
    {
        return new CalabashBrother(name, camp, token, color, order);
    }

    public CalabashBrother[] createCalaBros()
    {
        CalabashBrother[] CalaArr = new CalabashBrother[NR_CALA];
        CalaArr[0] = createCalabashBrother("大娃", 1, "B1", "红色", 0);
        CalaArr[1] = createCalabashBrother("二娃", 1, "B2", "橙色", 1);
        CalaArr[2] = createCalabashBrother("三娃", 1, "B3", "黄色", 2);
        CalaArr[3] = createCalabashBrother("四娃", 1, "B4", "绿色", 3);
        CalaArr[4] = createCalabashBrother("五娃", 1, "B5", "青色", 4);
        CalaArr[5] = createCalabashBrother("六娃", 1, "B6", "蓝色", 5);
        CalaArr[6] = createCalabashBrother("七娃", 1, "B7", "紫色", 6);
        return CalaArr;
    }

    public Pawn createPawn(String name, int camp, String token)
    {
        return new Pawn(name, camp, token);
    }

    public Pawn[] createPawns()
    {
        Pawn[] PawnArr = new Pawn[NR_PAWN];
        PawnArr[0] = createPawn("卒一", -1, "P1");
        PawnArr[1] = createPawn("卒二", -1, "P2");
        PawnArr[2] = createPawn("卒三", -1, "P3");
        PawnArr[3] = createPawn("卒四", -1, "P4");
        PawnArr[4] = createPawn("卒五", -1, "P5");
        PawnArr[5] = createPawn("卒六", -1, "P6");
        return PawnArr;
    }

    public Grandpa createGrandpa()
    {
        return new Grandpa("爷爷", 1, "GP");
    }

    public Scorpion creataScorpion()
    {
        return new Scorpion("蝎子精", -1, "SP");
    }

    public Snake creataSnake()
    {
        return new Snake("蛇妖", -1, "SK");
    }

    public void randPutCalaBro(World world, CalabashBrother[] CalaArr)
    {
        java.util.Random r = new java.util.Random();
        for (int i = 0; i < NR_CALA; i++)
        {
            int x, y;
            while (true)
            {
                x = r.nextInt(world.getMapSize());
                y = r.nextInt(world.getMapSize());
                if (world.isEmpty(x, y))
                    break;
            }
            world.putCreature(x, y, CalaArr[i]);
        }
        System.out.println("test randput:");
        world.printMap();
    }

    public void clearWorld(World world)
    {
        world.clearMap();
    }

    public void HuLuChangShe(World world, CalabashBrother[] CalaArr)
    {
        for (int i = 0; i < NR_CALA; i++)
            world.putCreature(i + 3,0, CalaArr[i]);
    }

    public void enemyHeYi(World world, Pawn[] PawnArr, Scorpion scorpion)
    {
        world.putCreature(6,11, scorpion);
        for (int i = 0; i < NR_PAWN; i++)
        {
            if (i < 3)
                world.putCreature(i + 3,14 - i, PawnArr[i]);
            else if (i >= 3)
                world.putCreature(i + 4,i + 9, PawnArr[i]);
        }
    }

    public void enemyYanXing(World world, Pawn[] PawnArr, Scorpion scorpion)
    {
        world.putCreature(6,11, scorpion);
        for (int i = 0; i < NR_PAWN; i++)
        {
            if (i < 3)
                world.putCreature(i + 3,14 - i, PawnArr[i]);
            else if (i >= 3)
                world.putCreature(i + 4,13 - i, PawnArr[i]);
        }
    }

    public void enemyHengE(World world, Pawn[] PawnArr, Scorpion scorpion)
    {
        world.putCreature(6,13, scorpion);
        for (int i = 0; i < NR_PAWN; i++)
        {
            if (i < 3)
                world.putCreature(i + 3,14 - (i % 2), PawnArr[i]);
            else if (i >= 3)
                world.putCreature(i + 4,13 + (i % 2), PawnArr[i]);
        }
    }

    public int enemyFormation(int enemyFormation, World world, Pawn[] PawnArr, Scorpion scorpion)
    {
        switch (enemyFormation)
        {
            case 0:
                enemyHeYi(world, PawnArr, scorpion);
                return 0;
            case 1:
                enemyYanXing(world, PawnArr, scorpion);
                return 1;
            case 2:
                enemyHengE(world, PawnArr, scorpion);
                return 2;
            default:
                System.out.println("unknown formation!");
                return enemyFormation;
        }
    }

    public void cheer(World world, Grandpa grandpa, Snake snake, int enemyFormation)
    {
        world.putCreature(6, 1, grandpa);
        switch (enemyFormation)
        {
            case 0:
                world.putCreature(6, 14, snake);
                break;
            case 1:
                world.putCreature(6, 14, snake);
                break;
            case 2:
                world.putCreature(6, 14, snake);
                break;
            default:
                System.out.println("unknown formation!");
                break;
        }
        grandpa.cheer();
        snake.cheer();
    }

}
