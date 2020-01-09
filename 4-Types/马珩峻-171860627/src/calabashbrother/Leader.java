package calabashbrother;

import static java.lang.Math.abs;

import calabashbrother.Camp.Formation;
import calabashbrother.Camp.Range;

class Leader<T extends Unit> extends Unit {
  Leader(Position position, UnitType unitType) {
    super(position, unitType);
  }

  void orderFormation(Formation formation, Soldiers<T> soldiers, Range range) {
    soldiers.formationIsReady = false;
    int destinationX;
    int destinationY;
    int Idx;
    int interval;
    int colNum;
    int readyNum;
    int length;
    int xStep = (range.xMax - range.xMin) / abs((range.xMax - range.xMin));
    int yStep = (range.yMax - range.yMin) / abs((range.yMax - range.yMin));
    int xMid = Math.abs(range.xMax - range.xMin) / 2;
    int yMid = Math.abs(range.yMax - range.yMin) / 2;

    switch (formation) {
      case CS:
        destinationX = range.xMin;
        destinationY = range.yMin;
        for (T v : soldiers.soldiers) {
          v.setDestination(new Position(destinationX, destinationY));
          destinationX += xStep;
          if ((destinationX - range.xMax) * xStep > 0) {
            destinationX = range.xMin;
            destinationY += yStep;
          }
        }
        break;
      case FS:
        destinationX = xMid;
        destinationY = range.yMin;
        Idx = 0;
        interval = 1;
        colNum = 0;
        readyNum = 0;
        length = Math.min((soldiers.number + 1) / 2, Math.abs(range.yMax - range.yMin));
        while (Idx < soldiers.number) {
          if (Idx < length) {
            soldiers.soldiers.get(Idx).setDestination(new Position(destinationX, destinationY));
            destinationY += yStep;
          } else {
            if (Idx == length) destinationY -= 2 * yStep;
            if (Idx % 2 == 1) {
              soldiers
                  .soldiers
                  .get(Idx)
                  .setDestination(new Position(destinationX - interval, destinationY));
              readyNum++;
            } else {
              soldiers
                  .soldiers
                  .get(Idx)
                  .setDestination(new Position(destinationX + interval, destinationY));
              readyNum++;
            }
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
          Idx++;
        }
        break;
      case FY:
        destinationX = xMid;
        destinationY = range.yMin;
        interval = 0;
        Idx = 0;
        readyNum = 0;
        boolean intervalAdd = true;
        length = Math.min((soldiers.number + 3) / 2, Math.abs(range.yMax - range.yMin));

        while (Idx < soldiers.number) {
          if (interval == 0) {
            soldiers.soldiers.get(Idx).setDestination(new Position(destinationX, destinationY));
            destinationY += yStep;
            interval++;
          } else {
            if (Idx % 2 == 0) {
              soldiers
                  .soldiers
                  .get(Idx)
                  .setDestination(new Position(destinationX + interval, destinationY));

            } else {
              soldiers
                  .soldiers
                  .get(Idx)
                  .setDestination(new Position(destinationX - interval, destinationY));
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
          Idx++;
          if ((destinationY - range.yMin) * yStep >= length) {

            destinationX = range.xMin;
            destinationY = range.yMin;
            while (Idx < soldiers.number) {
              soldiers.soldiers.get(Idx).setDestination(new Position(destinationX, destinationY));
              destinationX += xStep;
              Idx++;
            }
          }
        }
        break;
      case HE:
        destinationX = range.xMin;
        destinationY = range.yMin;
        interval = 0;
        for (T v : soldiers.soldiers) {
          v.setDestination(new Position(destinationX, destinationY + yStep * interval));
          destinationX += xStep;
          interval = interval == 0 ? 1 : 0;
          if ((destinationX - range.xMax) * xStep > 0) {
            destinationX = range.xMin;
            destinationY += 2 * yStep;
            interval = 0;
          }
        }
        break;
      case HY:
        destinationX = xMid;
        destinationY = range.yMin;
        Idx = 0;
        interval = 0;
        colNum = 0;
        readyNum = 0;
        while (Idx < soldiers.number) {
          if (Idx == 0) {
            soldiers.soldiers.get(Idx).setDestination(new Position(destinationX, destinationY));
            destinationY += yStep;
            interval++;
          } else if (Idx % 2 == 1) {
            soldiers
                .soldiers
                .get(Idx)
                .setDestination(new Position(destinationX - interval, destinationY));
            readyNum++;
          } else {
            soldiers
                .soldiers
                .get(Idx)
                .setDestination(new Position(destinationX + interval, destinationY));
            readyNum++;
          }

          if (readyNum == 2) {
            readyNum = 0;
            destinationY += yStep;
            colNum++;
          }
          if (colNum > interval) {
            interval++;
            colNum = 0;
          }
          Idx++;
          if ((destinationY - range.yMax) * yStep > 0) {
            destinationX = range.xMin;
            destinationY = range.yMin;
            while (Idx < soldiers.number) {
              soldiers.soldiers.get(Idx).setDestination(new Position(destinationX, destinationY));
              destinationX += xStep;
              Idx++;
            }
          }
        }
        break;
      case YL:
        destinationX = xMid;
        destinationY = range.yMax - yStep;
        interval = 0;
        Idx = 0;
        readyNum = 0;
        int requireNum = 1;
        int remainNum = soldiers.number;
        while (Idx < soldiers.number) {
          if (requireNum < remainNum) {
            while (readyNum < requireNum) {
              soldiers.soldiers.get(Idx).setDestination(new Position(destinationX, destinationY));
              readyNum++;
              destinationX += xStep;
              remainNum--;
              Idx++;
            }
            readyNum = 0;
            requireNum += 2;
            destinationX -= xStep * (requireNum - 1);
            destinationY -= yStep;
          } else {
            if (remainNum > 0) {
              destinationX = xMid;
              soldiers.soldiers.get(Idx).setDestination(new Position(destinationX, destinationY));
              Idx++;
            }
            destinationX = range.xMin;
            destinationY = range.yMin;
            while (Idx < soldiers.number) {
              soldiers.soldiers.get(Idx).setDestination(new Position(destinationX, destinationY));
              destinationX += xStep;
              Idx++;
            }
          }
        }

        break;
      case YX:
        destinationX = range.xMin;
        destinationY = range.yMin;
        colNum = 0;
        int colMaxNum =
            (int) Math.ceil((float) soldiers.number / Math.abs(range.yMax - range.yMin));
        colMaxNum = Math.max(colMaxNum, 2);
        for (T v : soldiers.soldiers) {
          v.setDestination(new Position(destinationX, destinationY));
          destinationX += xStep;
          colNum++;
          if (colNum == colMaxNum) {
            destinationY += yStep;
            colNum = 0;
          }
        }
        break;
      case YY:
        if (soldiers.number < 15) {
          System.out.println(
              String.format(
                  "soldier number isn't enough, at least 15, but got %d", soldiers.number));
          break;
        }
        destinationX = xMid + xStep;
        destinationY = range.yMax - yStep;
        Idx = 0;

        for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
            soldiers
                .soldiers
                .get(Idx)
                .setDestination(new Position(destinationX - i * xStep, destinationY - j * yStep));
            Idx++;
          }
        }
        destinationX = xMid;
        destinationY = range.yMax -= 3 * yStep;
        interval = 2;
        readyNum = 0;
        colNum = 0;
        while (Idx < soldiers.number) {
          if (readyNum % 2 == 0) {
            soldiers
                .soldiers
                .get(Idx)
                .setDestination(new Position(destinationX - interval * xStep, destinationY));
          } else {
            soldiers
                .soldiers
                .get(Idx)
                .setDestination(new Position(destinationX + interval * xStep, destinationY));

            Idx++;
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
          break;
        }
    }
  }
}
