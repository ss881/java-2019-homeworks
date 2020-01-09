package cn.mhj.Formation;

import cn.mhj.Creature.FightCreature;
import cn.mhj.Field.Position;
import cn.mhj.Field.Range;
import java.util.Vector;

public interface YanYue {
  static void lineUp(Vector<? extends FightCreature> soldiers, Range range) {
    if (soldiers.size() < 15) {
      System.out.println(
          String.format("soldier number isn't enough, at least 15, but got %d", soldiers.size()));
      return;
    }
    int xStep = range.getXStep();
    int yStep = range.getYStep();
    int destinationX = range.getXMid() + xStep;
    int destinationY = range.getYMax() - yStep;
    int idx = 0;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        soldiers.get(idx).moveTo(new Position(destinationX - i * xStep, destinationY - j * yStep));
        idx++;
      }
    }
    destinationX = range.getXMid();
    destinationY = range.getYMax() - 3 * yStep;
    int interval = 2;
    int readyNum = 0;
    int colNum = 0;
    while (idx < soldiers.size()) {
      if (readyNum % 2 == 0) {
        soldiers.get(idx).moveTo(new Position(destinationX - interval * xStep, destinationY));
      } else {
        soldiers.get(idx).moveTo(new Position(destinationX + interval * xStep, destinationY));
      }
      idx++;
      readyNum++;
      if (readyNum == 2) {
        readyNum = 0;
        destinationY -= yStep;
        colNum++;
        if (colNum >= interval) {
          interval++;
          colNum = 0;
          destinationY += yStep;
        }
      }
    }
  }
}
