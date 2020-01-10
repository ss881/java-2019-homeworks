package cn.mhj.Formation;

import cn.mhj.Creature.FightCreature;
import cn.mhj.Field.Position;
import cn.mhj.Field.Range;
import java.util.Vector;

public interface HengE {
  static void lineUp(Vector<? extends FightCreature> soldiers, Range range) {
    int destinationX = range.getXMin();
    int destinationY = range.getYMin();
    int xStep = range.getXStep();
    int yStep = range.getYStep();
    int interval = 0;

    for (FightCreature soldier : soldiers) {
      soldier.moveTo(new Position(destinationX, destinationY + yStep * interval));
      destinationX += xStep;
      interval = interval == 0 ? 1 : 0;
      if ((destinationX - range.getXMax()) * xStep > 0) {
        destinationX = range.getXMin();
        destinationY += 2 * yStep;
        interval = 0;
      }
    }
  }
}
