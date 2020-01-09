package cn.mhj.Formation;

import cn.mhj.Creature.FightCreature;
import cn.mhj.Field.Position;
import cn.mhj.Field.Range;
import java.util.Vector;

public interface FangYuan {
  static void lineUp(Vector<? extends FightCreature> soldiers, Range range) {
    int xStep = range.getXStep();
    int yStep = range.getYStep();
    int destinationX = range.getXMid();
    int destinationY = range.getYMin();
    int interval = 0;
    int idx = 0;
    int readyNum = 0;
    boolean intervalAdd = true;
    int length = Math.min((soldiers.size() + 3) / 2, Math.abs(range.getYMax() - range.getYMin()));

    while (idx < soldiers.size()) {
      if (interval == 0) {
        soldiers.get(idx).moveTo(new Position(destinationX, destinationY));
        destinationY += yStep;
        interval++;
      } else {
        if (idx % 2 == 0) {
          soldiers.get(idx).moveTo(new Position(destinationX + interval, destinationY));

        } else {
          soldiers.get(idx).moveTo(new Position(destinationX - interval, destinationY));
        }
        readyNum++;

        if (readyNum == 2) {
          readyNum = 0;
          destinationY += yStep;
          if (interval == length / 2) {
            intervalAdd = false;
            if (length % 2 == 0) interval++;
          }
          if (intervalAdd) interval++;
          else interval--;
        }
      }
      idx++;
      if ((destinationY - range.getYMin()) * yStep >= length) {

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
