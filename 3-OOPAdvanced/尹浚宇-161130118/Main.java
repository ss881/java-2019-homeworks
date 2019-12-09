import creature.*;

public class Main
{
    public static void main(String[] args)
    {
        God god = new God();
        World world = god.createWorld();

        System.out.println("god creates creatures");
        CalabashBrother[] CalaArr = god.createCalaBros();
        Pawn[] PawnArr = god.createPawns();
        Grandpa grandpa = god.createGrandpa();
        Scorpion scorpion = god.creataScorpion();
        Snake snake = god.creataSnake();

        System.out.println("god puts Calabash Brothers on map");
        god.randPutCalaBro(world, CalaArr);
        god.clearWorld(world);
        god.HuLuChangShe(world, CalaArr);

        System.out.println("god puts pawns on map");
        god.enemyFormation(0, world, PawnArr, scorpion);
        System.out.println("god puts cheer leader on map");
        god.cheer(world, grandpa, snake, 0);
        world.printMap();

        System.out.println("god repeats");
        god.clearWorld(world);
        god.HuLuChangShe(world, CalaArr);

        god.enemyFormation(1, world, PawnArr, scorpion);
        god.cheer(world, grandpa, snake, 1);
        world.printMap();

        god.clearWorld(world);
        god.HuLuChangShe(world, CalaArr);

        god.enemyFormation(2, world, PawnArr, scorpion);
        god.cheer(world, grandpa, snake, 2);
        world.printMap();
    }
}
