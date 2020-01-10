package cn.mhj.Creature;

import cn.mhj.Field.Position;
import java.lang.reflect.Method;
import java.util.Vector;
import javafx.scene.canvas.Canvas;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

public class FightCreatureTest {

  @Test
  public void testSearchNearestEnemy() throws Exception {
    FightCreature fightCreature = new FightCreature(5, new Position(0, 0), null);
    Vector<FightCreature> enemies = new Vector<>();
    enemies.add(new FightCreature(0, new Position(1, 1), null));
    enemies.add(new FightCreature(0, new Position(1, 2), null));
    enemies.add(new FightCreature(0, new Position(2, 2), null));
    fightCreature.setEnemies(enemies);
    Method method = fightCreature.getClass().getDeclaredMethod("searchNearestEnemy");
    method.setAccessible(true);
    Pair<FightCreature, Integer> result =
        (Pair<FightCreature, Integer>) method.invoke(fightCreature);

    Assert.assertEquals(Integer.valueOf(2), result.getValue());
    Assert.assertEquals(enemies.get(0), result.getKey());
  }

  @Test
  public void testBeKilled() throws Exception {
    FightCreature fightCreature = new FightCreature(0, null, null);
    Assert.assertTrue(fightCreature.isAlive());
    fightCreature.beKilled();
    Assert.assertFalse(fightCreature.isAlive());
  }

  @Test
  public void testFightWithEnemy() throws Exception {
    Canvas canvas = new Canvas(900, 675);
    FightCreature fightCreature1 = new FightCreature(0, null, null);
    FightCreature fightCreature2 = new FightCreature(1, null, null);
    Method method =
        fightCreature1.getClass().getDeclaredMethod("fightWithEnemy", FightCreature.class);
    method.setAccessible(true);
    method.invoke(fightCreature1, fightCreature2);
    Assert.assertFalse(fightCreature1.isAlive());
    Assert.assertTrue(fightCreature2.isAlive());
    Assert.assertEquals(0, fightCreature2.getPower());
  }
}
