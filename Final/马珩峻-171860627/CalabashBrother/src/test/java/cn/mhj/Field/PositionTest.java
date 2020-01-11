package cn.mhj.Field;

import org.junit.Assert;
import org.junit.Test;

public class PositionTest {

  @Test
  public void testEquals() {
    Position position1 = new Position(0, 0);
    Position position2 = new Position(0, 0);
    Position position3 = new Position(0, 1);
    Assert.assertEquals(position1, position2);
    Assert.assertNotEquals(position1, position3);
  }
}
