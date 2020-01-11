package cn.mhj.Formation;

import cn.mhj.Creature.FightCreature;
import cn.mhj.Field.Position;
import cn.mhj.Field.Range;
import java.util.Vector;

public interface ChangShe {
  static void lineUp(Vector<? extends FightCreature> soldiers, Range range) {
    int destinationX = range.getXMin();
    int destinationY = range.getYMin();
    int xStep = range.getXStep();
    int yStep = range.getYStep();
    for (FightCreature soldier : soldiers) {
      soldier.moveTo(new Position(destinationX, destinationY));
      destinationX += xStep;
      if ((destinationX - range.getXMax()) * xStep > 0) {
        destinationX = range.getXMin();
        destinationY += yStep;
      }
    }
  }
}
