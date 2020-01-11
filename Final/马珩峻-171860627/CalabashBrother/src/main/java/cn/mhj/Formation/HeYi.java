package cn.mhj.Formation;

import cn.mhj.Creature.FightCreature;
import cn.mhj.Field.Position;
import cn.mhj.Field.Range;
import java.util.Vector;

public interface HeYi {
  static void lineUp(Vector<? extends FightCreature> soldiers, Range range) {
    int xStep = range.getXStep();
    int yStep = range.getYStep();
    int destinationX = range.getXMid();
    int destinationY = range.getYMin();
    int idx = 0;
    int interval = 0;
    int colNum = 0;
    int readyNum = 0;
    while (idx < soldiers.size()) {
      if (idx == 0) {
        soldiers.get(idx).moveTo(new Position(destinationX, destinationY));
        destinationY += yStep;
        interval++;
      } else if (idx % 2 == 1) {
        soldiers.get(idx).moveTo(new Position(destinationX - interval, destinationY));
        readyNum++;
      } else {
        soldiers.get(idx).moveTo(new Position(destinationX + interval, destinationY));
        readyNum++;
      }

      if (readyNum == 2) {
        readyNum = 0;
        destinationY += yStep;
        colNum++;
      }
      if (colNum >= interval) {
        interval++;
        colNum = 0;
      }
      idx++;
      if ((destinationY - range.getYMax()) * yStep > 0) {
        destinationX = range.getXMin();
        destinationY = range.getYMin();
        while (idx < soldiers.size()) {
          soldiers.get(idx).moveTo(new Position(destinationX, destinationY));
          destinationX += xStep;
          idx++;
        }
      }
    }
  }
}
