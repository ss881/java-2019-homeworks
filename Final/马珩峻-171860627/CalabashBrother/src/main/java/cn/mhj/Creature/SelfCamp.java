package cn.mhj.Creature;

import cn.mhj.Enum.FormationType;
import cn.mhj.Field.Battlefield;
import cn.mhj.Field.Position;
import cn.mhj.Field.Range;
import cn.mhj.Formation.Formation;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

public class SelfCamp implements Camp {
  public static final int CALABASH_NUM = 7;

  private Grandpa grandpa;
  private Vector<CalabashBoy> calabashBrother;
  private Range range;

  public SelfCamp(Battlefield battlefield, Range range) throws Exception {
    this.grandpa = new Grandpa(battlefield);
    this.calabashBrother = new Vector<>();
    this.range = range;
    for (int i = 0; i < CALABASH_NUM; i++) {
      calabashBrother.addElement(new CalabashBoy(i, new Position(0, 0), battlefield));
    }

    this.setFormation(FormationType.CS);
  }

  public void setEnemies(Vector<Enemy> enemies) {

    Vector<FightCreature> temp = new Vector<>(enemies);

    for (CalabashBoy calabashBoy : calabashBrother) {
      calabashBoy.setEnemies(temp);
    }
  }

  public void setLatch(CountDownLatch latch) {
    for (CalabashBoy calabashBoy : calabashBrother) {
      calabashBoy.setLatch(latch);
    }
  }

  public Vector<CalabashBoy> getCalabashBrother() {
    return calabashBrother;
  }

  @Override
  public void setFormation(FormationType formationType) {
    Formation.lineUp(formationType, calabashBrother, range);
  }

  @Override
  public boolean isLoss() {
    for (CalabashBoy calabashBoy : calabashBrother) {
      if (calabashBoy.isAlive()) {
        return false;
      }
    }
    return true;
  }
}
