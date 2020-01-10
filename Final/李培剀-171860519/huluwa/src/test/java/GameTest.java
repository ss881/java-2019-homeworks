
import creature.*;
import formulation.Formulation;
import ground.Ground;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class GameTest {

    private Ground ground = new Ground(null);

    @Test
    public void formSortTest() {
        Method[] methods = new Formulation(new Ground(null)).getSortedDeclaredMethods();
        assertEquals("transformToHeyi", methods[0].getName());
        assertEquals("transformToYanxing", methods[1].getName());
        assertEquals("transformToChonge", methods[2].getName());
        assertEquals("transformToChangshe", methods[3].getName());
        assertEquals("transformToYulin", methods[4].getName());
        assertEquals("transformToFangyuan", methods[5].getName());
        assertEquals("transformToYanyue", methods[6].getName());
        assertEquals("transformToFengshi", methods[7].getName());
    }

    @Test
    public void methodsTest() {
        try {
            Generator generator = new Generator();
            generator.genHuluwa(ground, 1, 1, Color.red);
            generator.genScorpion(ground, 5, 8);
            generator.genCreep(ground, 4, 6);

            Huluwa huluwa = (Huluwa) ground.getHuluwas().get(0);
            Scorpion scorpion = (Scorpion) ground.getCreeps().get(0);
            Creep creep = (Creep) ground.getCreeps().get(1);

            Method isEnemyTo = Creature.class.getDeclaredMethod("isEnemyTo", Creature.class);
            isEnemyTo.setAccessible(true);

            assertTrue((Boolean) isEnemyTo.invoke(huluwa, scorpion));
            assertTrue((Boolean) isEnemyTo.invoke(huluwa, creep));
            assertFalse((Boolean) isEnemyTo.invoke(scorpion, creep));

            assertEquals(creep, ground.getNearestEnemy(huluwa));
            assertTrue(ground.isMovable(huluwa, scorpion));
            assertTrue(ground.isMovable(huluwa, creep));
            assertTrue(ground.isOccupied(-1, 2));
            assertTrue(ground.isOccupied(0, -4));
            assertTrue(ground.isOccupied(20, 2));
            assertTrue(ground.isOccupied(2, 28));
            assertFalse(ground.isOccupied(0, 0));
            assertTrue(ground.isOccupied(1, 1));
            assertTrue(ground.isOccupied(5, 8));
            assertTrue(ground.isOccupied(4, 6));

            ground.moveCreatureTo(huluwa, 4, 8);

            assertFalse(ground.isOccupied(1, 1));
            assertTrue(ground.isOccupied(4,8));
            assertEquals(scorpion, ground.getNearestEnemy(huluwa));

            Method die = Creature.class.getDeclaredMethod("die");
            die.setAccessible(true);
            die.invoke(huluwa);

            assertTrue(huluwa.isDead());
            assertFalse(ground.isOccupied(4, 8));
            assertEquals(2, ground.whoWins());

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
    }

}
