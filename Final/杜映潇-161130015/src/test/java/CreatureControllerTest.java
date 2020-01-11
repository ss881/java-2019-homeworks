import finalproject.controller.CreatureController;
import finalproject.creatures.CalabashBrother;
import finalproject.creatures.Creature;
import finalproject.creatures.Goblin;
import finalproject.worldmap.CalabashWorld;
import finalproject.worldmap.World;
import org.junit.*;

import java.lang.reflect.Method;

public class CreatureControllerTest {
    private static CreatureController creatureController = null;
    private static Creature calabashSideCreature = null;
    private static Creature goblinSideCreature = null;
    private static World world = null;

    @BeforeClass
    public static void initial() {
        world = new CalabashWorld();
        calabashSideCreature = new CalabashBrother("1");
        goblinSideCreature = new Goblin();
        creatureController = CreatureController.getInstance(world);
    }

    @Before
    public void setUp() {
        world.clear();
    }

    @After
    public void tearDown() {
        world.clear();
    }

    @Test
    public void testRowEnemyMove() throws Exception {
        Method rowEnemyMove = creatureController.getClass().getDeclaredMethod("rowEnemyMove", Creature.class,
                int.class, int.class);
        rowEnemyMove.setAccessible(true);

        /* Test for case when there is no enemy */
        world.place(calabashSideCreature, 0, 1);
        int res = (int) rowEnemyMove.invoke(creatureController, calabashSideCreature, 0, 1);
        Assert.assertEquals(0, res);

        /* Test for case when there is one enemy */
        world.place(goblinSideCreature, 0, 3);
        res = (int) rowEnemyMove.invoke(creatureController, calabashSideCreature, 0, 1);
        Assert.assertEquals(1, res);

        world.clear();
        world.place(calabashSideCreature, 0, 3);
        world.place(goblinSideCreature, 0, 1);
        res = (int) rowEnemyMove.invoke(creatureController, calabashSideCreature, 0, 3);
        Assert.assertEquals(-1, res);
    }

    @Test
    public void testColumnEnemyMove() throws Exception {
        Method columnEnemyMove = creatureController.getClass().getDeclaredMethod("columnEnemyMove", Creature.class,
                int.class, int.class);
        columnEnemyMove.setAccessible(true);

        /* Test for case when there is no enemy */
        world.place(calabashSideCreature, 0, 1);
        int res = (int) columnEnemyMove.invoke(creatureController, calabashSideCreature, 0, 1);
        Assert.assertEquals(0, res);

        /* Test for case when there is one enemy */
        world.place(goblinSideCreature, 3, 1);
        res = (int) columnEnemyMove.invoke(creatureController, calabashSideCreature, 0, 1);
        Assert.assertEquals(1, res);

        world.clear();
        world.place(calabashSideCreature, 3, 1);
        world.place(goblinSideCreature, 0, 1);
        res = (int) columnEnemyMove.invoke(creatureController, calabashSideCreature, 3, 1);
        Assert.assertEquals(-1, res);
    }

    @Test
    public void testNextMove() throws Exception {
        Method nextMove = creatureController.getClass().getDeclaredMethod("nextMove", Creature.class);
        nextMove.setAccessible(true);

        world.place(calabashSideCreature, 0, 1);
        world.place(goblinSideCreature, 0, 2);
        int[] res = (int[]) nextMove.invoke(creatureController, calabashSideCreature);
        int[] expected = new int[]{0, 2};
        Assert.assertArrayEquals(expected, res);

        world.clear();
        world.place(calabashSideCreature, 3, 3);
        world.place(goblinSideCreature, 3, 1);
        res = (int[]) nextMove.invoke(creatureController, calabashSideCreature);
        expected = new int[]{3, 2};
        Assert.assertArrayEquals(expected, res);
    }
}
