package cn.mhj.Field;

import cn.mhj.Creature.FightCreature;
import org.junit.Assert;
import org.junit.Test;

public class UnitTest {

  @Test
  public void testUnit() throws Exception {
    Unit<FightCreature> unit = new Unit<>(null);
    FightCreature fightCreature = new FightCreature(0, null, null);
    unit.addCreature(fightCreature);
    Assert.assertTrue(unit.hasCreature());
    fightCreature.beKilled();
    Assert.assertFalse(unit.hasCreature());
    unit.removeCreature(fightCreature);
    Assert.assertFalse(unit.hasCreature());
  }
}
