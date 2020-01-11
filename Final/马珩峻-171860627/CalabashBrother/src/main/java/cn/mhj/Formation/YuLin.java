package cn.mhj.Formation;

import cn.mhj.Creature.FightCreature;
import cn.mhj.Field.Position;
import cn.mhj.Field.Range;
import java.util.Vector;

public interface YuLin {
  static void lineUp(Vector<? extends FightCreature> soldiers, Range range) {
    int xStep = range.getXStep();
    int yStep = range.getYStep();
    int xMid = Math.abs(range.getXMax() - range.getXMin()) / 2;
    int destinationX = xMid;
    int destinationY = range.getYMax() - yStep;
    int idx = 0;
    int readyNum = 0;
    int requireNum = 1;
    int remainNum = soldiers.size();
    while (idx < soldiers.size()) {
      if (requireNum < remainNum) {
        while (readyNum < requireNum) {
          soldiers.get(idx).moveTo(new Position(destinationX, destinationY));
          readyNum++;
          destinationX += xStep;
          remainNum--;
          idx++;
        }
        readyNum = 0;
        requireNum += 2;
        destinationX -= xStep * (requireNum - 1);
        destinationY -= yStep;
      } else {
        if (remainNum > 0) {
          destinationX = xMid;
          soldiers.get(idx).moveTo(new Position(destinationX, destinationY));
          idx++;
        }
        //        destinationX = range.getXMin();
        //        destinationY = range.getYMin();
        destinationY -= yStep;
        while (idx < soldiers.size()) {
          soldiers.get(idx).moveTo(new Position(destinationX, destinationY));
          destinationX += xStep;
          idx++;
        }
      }
    }
  }
}
