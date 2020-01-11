package cn.mhj.Formation;

import cn.mhj.Creature.FightCreature;
import cn.mhj.Field.Position;
import cn.mhj.Field.Range;
import java.util.Vector;

public interface YanXing {
  static void lineUp(Vector<? extends FightCreature> soldiers, Range range) {
    int destinationX = range.getXMin();
    int destinationY = range.getYMin();
    int xStep = range.getXStep();
    int yStep = range.getYStep();
    int colNum = 0;
    int colMaxNum =
        (int) Math.ceil((float) soldiers.size() / Math.abs(range.getYMax() - range.getYMin()));
    colMaxNum = Math.max(colMaxNum, 2);
    for (FightCreature soldier : soldiers) {
      soldier.moveTo(new Position(destinationX, destinationY));
      destinationX += xStep;
      colNum++;
      if (colNum == colMaxNum) {
        destinationY += yStep;
        colNum = 0;
      }
    }
  }
}
