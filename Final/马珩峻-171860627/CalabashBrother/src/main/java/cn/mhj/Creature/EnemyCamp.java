package cn.mhj.Creature;

import cn.mhj.Enum.FormationType;
import cn.mhj.Field.Battlefield;
import cn.mhj.Field.Position;
import cn.mhj.Field.Range;
import cn.mhj.Formation.Formation;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

public class EnemyCamp implements Camp {
  private static final int ENEMY_NUM = 15;

  private Snake snake;
  private Vector<Enemy> soldiers;
  private Range range;

  public EnemyCamp(Battlefield battlefield, Range range) throws Exception {
    this.snake = new Snake(battlefield);
    this.soldiers = new Vector<>();
    this.range = range;
    for (int i = 0; i < ENEMY_NUM; i++) {
      soldiers.addElement(new Enemy(new Position(0, 0), battlefield));
    }
    this.setFormation(FormationType.CS);
  }

  public void setEnemies(Vector<CalabashBoy> enemies) {
    Vector<FightCreature> temp = new Vector<>(enemies);

    for (Enemy soldier : soldiers) {
      soldier.setEnemies(temp);
    }
  }

  public void setLatch(CountDownLatch latch) {
    for (Enemy soldier : this.soldiers) {
      soldier.setLatch(latch);
    }
  }

  public Vector<Enemy> getSoldiers() {
    return soldiers;
  }

  @Override
  public void setFormation(FormationType formationType) {
    Formation.lineUp(formationType, soldiers, range);
  }

  @Override
  public boolean isLoss() {
    for (Enemy enemy : soldiers) {
      if (enemy.isAlive()) return false;
    }
    return true;
  }
}
