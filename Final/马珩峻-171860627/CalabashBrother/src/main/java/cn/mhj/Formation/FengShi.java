package cn.mhj.Formation;

import cn.mhj.Creature.FightCreature;
import cn.mhj.Field.Position;
import cn.mhj.Field.Range;
import java.util.Vector;

public interface FengShi {
  static void lineUp(Vector<? extends FightCreature> soldiers, Range range) {
    int yStep = range.getYStep();
    int destinationX = range.getXMid();
    int destinationY = range.getYMin();
    int idx = 0;
    int interval = 1;
    int colNum = 0;
    int readyNum = 0;
    int length = Math.min((soldiers.size() + 1) / 2, Math.abs(range.getYMax() - range.getYMin()));
    while (idx < soldiers.size()) {
      if (idx < length) {
        soldiers.get(idx).moveTo(new Position(destinationX, destinationY));
        destinationY += yStep;
      } else {
        if (idx == length) destinationY -= 2 * yStep;
        if (idx % 2 == 1) {
          soldiers.get(idx).moveTo(new Position(destinationX - interval, destinationY));
        } else {
          soldiers.get(idx).moveTo(new Position(destinationX + interval, destinationY));
        }
        readyNum++;
        if (readyNum == 2) {
          readyNum = 0;
          destinationY -= yStep;
          colNum++;
          if (colNum >= interval) {
            interval++;
            colNum = 0;
          }
        }
      }
      idx++;
    }
  }
}
