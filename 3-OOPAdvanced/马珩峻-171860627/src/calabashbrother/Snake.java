package calabashbrother;

import calabashbrother.Enemies.EnemySoldier;
import calabashbrother.Game.Formation;

public class Snake extends Unit {

  Snake(Position position) {
    super(position, UnitType.SNACK);
  }

  void orderFormation(Formation formation, Enemies enemies) {
    int destinationX;
    int destinationY;
    int enemyIdx;
    int interval;
    int cowNum;
    switch (formation) {
      case CS:
        destinationX = Game.MAXMAPLENGTH - 2;
        destinationY = Game.MAXMAPLENGTH - 3;
        for (EnemySoldier v : enemies.enemySoldiers) {
          v.setDestination(new Position(destinationX, destinationY));
          destinationX--;
          if (destinationX < 0) {
            destinationX = Game.MAXMAPLENGTH - 2;
            destinationY = Game.MAXMAPLENGTH - 1;
          }
        }
        break;
      case FS:
        destinationX = Game.MAXMAPLENGTH - 2;
        destinationY = Game.MAXMAPLENGTH - 6;
        enemyIdx = 0;
        interval = 0;
        cowNum = 0;
        while (enemyIdx < enemies.numbers) {
          if (enemyIdx < 9) {
            enemies.enemySoldiers[enemyIdx].setDestination(
                new Position(destinationX, destinationY));
            destinationX--;
            if (enemyIdx == 9) {
              destinationX += 2;
              interval += 1;
            }
          } else if (enemyIdx % 2 == 1) {
            enemies.enemySoldiers[enemyIdx].setDestination(
                new Position(destinationX, destinationY - interval));
          } else {
            enemies.enemySoldiers[enemyIdx].setDestination(
                new Position(destinationX, destinationY + interval));
            destinationX++;
            cowNum++;
            if (cowNum >= interval) {
              interval++;
              cowNum = 0;
            }
          }
          enemyIdx++;
        }
        break;
      case FY:
        destinationX = Game.MAXMAPLENGTH - 2;
        destinationY = Game.MAXMAPLENGTH - 6;
        interval = 0;
        enemyIdx = 0;
        boolean intervalAdd = true;

        for (EnemySoldier v : enemies.enemySoldiers) {
          if (interval == 0) {
            v.setDestination(new Position(destinationX, destinationY));
            destinationX--;
            interval++;
          } else if (destinationY != Game.MAXMAPLENGTH - 1) {
            if (enemyIdx % 2 == 1) {
              v.setDestination(new Position(destinationX, destinationY - interval));
            } else {
              v.setDestination(new Position(destinationX, destinationY + interval));
              destinationX--;

              if (interval == 3) intervalAdd = false;

              if (intervalAdd) interval++;
              else interval--;
            }
          } else {
            v.setDestination(new Position(destinationX, destinationY));
            destinationX--;
          }
          if (destinationX == Game.MAXMAPLENGTH - 9) {
            destinationY = Game.MAXMAPLENGTH - 1;
            destinationX = Game.MAXMAPLENGTH - 2;
          }
          enemyIdx++;
        }
        break;
      case HE:
        destinationX = Game.MAXMAPLENGTH - 2;
        destinationY = Game.MAXMAPLENGTH - 3;
        interval = 0;
        for (EnemySoldier v : enemies.enemySoldiers) {
          v.setDestination(new Position(destinationX, destinationY - interval));
          destinationX--;
          if (destinationY != Game.MAXMAPLENGTH - 1) {
            interval = (interval == 0) ? 1 : 0;
          }
          if (destinationX < 0) {
            destinationX = Game.MAXMAPLENGTH - 2;
            destinationY = Game.MAXMAPLENGTH - 1;
            interval = 0;
          }
        }
        break;
      case HY:
        destinationX = Game.MAXMAPLENGTH - 2;
        destinationY = Game.MAXMAPLENGTH - 6;
        enemyIdx = 0;
        interval = 0;
        cowNum = 0;
        while (enemyIdx < enemies.numbers) {
          if (enemyIdx == 0) {
            enemies.enemySoldiers[enemyIdx].setDestination(
                new Position(destinationX, destinationY));
            destinationX--;
            interval++;
          } else if (enemyIdx % 2 == 1) {
            enemies.enemySoldiers[enemyIdx].setDestination(
                new Position(destinationX, destinationY - interval));
          } else {
            enemies.enemySoldiers[enemyIdx].setDestination(
                new Position(destinationX, destinationY + interval));
            destinationX--;
            cowNum++;
            if (cowNum > interval) {
              interval++;
              cowNum = 0;
            }
          }
          enemyIdx++;
        }
        break;
      case YL:
        destinationX = Game.MAXMAPLENGTH - 2;
        destinationY = Game.MAXMAPLENGTH - 3;
        interval = 0;
        enemyIdx = 0;
        while (enemyIdx < enemies.numbers) {
          if (enemyIdx < 16) {
            enemies.enemySoldiers[enemyIdx].setDestination(
                new Position(destinationX, destinationY - interval));
            interval++;
            if (3 - Math.abs((Game.MAXMAPLENGTH - 5) - destinationX) < interval) {
              destinationX--;
              interval = 0;
            }
          } else {
            enemies.enemySoldiers[enemyIdx].setDestination(
                new Position(destinationX, destinationY));
            destinationX--;
          }
          enemyIdx++;
          if (enemyIdx == 16) {
            destinationX = Game.MAXMAPLENGTH - 4;
            destinationY = Game.MAXMAPLENGTH - 2;
          }
        }
        break;
      case YX:
        destinationX = Game.MAXMAPLENGTH - 2;
        destinationY = Game.MAXMAPLENGTH - 6;
        interval = 0;
        cowNum = 0;
        for (EnemySoldier v : enemies.enemySoldiers) {
          v.setDestination(new Position(destinationX, destinationY + interval));
          destinationX--;
          if (destinationY != Game.MAXMAPLENGTH - 1) {
            cowNum++;
          }
          if (cowNum == 3) {
            interval++;
            cowNum = 0;
          }
          if (destinationX < 0) {
            destinationX = Game.MAXMAPLENGTH - 2;
            destinationY = Game.MAXMAPLENGTH - 1;
            interval = 0;
          }
        }
        break;
      case YY:
        destinationX = Game.MAXMAPLENGTH - 5;
        destinationY = Game.MAXMAPLENGTH - 7;
        enemyIdx = 0;
        int i = 0, j = 0;
        while (enemyIdx < enemies.numbers) {
          if (i < 2) {
            if (j < 3) {
              enemies.enemySoldiers[enemyIdx].setDestination(
                  new Position(destinationX, destinationY));
              destinationX--;
              j++;
            }
            if (j == 3) {
              j = 0;
              destinationY++;
              destinationX = Game.MAXMAPLENGTH - 5 + i;
              i++;
            }
          } else if (i == 2) {
            if (j < 5) {
              enemies.enemySoldiers[enemyIdx].setDestination(
                  new Position(destinationX, destinationY));
              destinationX--;
              j++;
            }
            if (j == 5) {
              j = 0;
              i++;
              destinationX = Game.MAXMAPLENGTH - 6;
              destinationY++;
            }
          } else if (i == 3) {
            switch (j) {
              case 0:
                enemies.enemySoldiers[enemyIdx].setDestination(
                    new Position(destinationX + 3, destinationY));
                break;
              case 1:
                enemies.enemySoldiers[enemyIdx].setDestination(
                    new Position(destinationX + 2, destinationY));
                break;
              case 2:
                enemies.enemySoldiers[enemyIdx].setDestination(
                    new Position(destinationX - 2, destinationY));
                break;
              case 3:
                enemies.enemySoldiers[enemyIdx].setDestination(
                    new Position(destinationX - 3, destinationY));
                break;
            }
            j++;
            if (j > 3) {
              i++;
              j = 0;
              destinationY++;
            }
          } else if (i == 4) {
            if (j == 0) {
              enemies.enemySoldiers[enemyIdx].setDestination(
                  new Position(destinationX + 3, destinationY));
              j++;
            } else if (j == 1) {
              enemies.enemySoldiers[enemyIdx].setDestination(
                  new Position(destinationX - 3, destinationY));
              i++;
              j = 0;
              destinationY++;
            }
          } else if (i == 5) {
            if (j == 0) {
              enemies.enemySoldiers[enemyIdx].setDestination(
                  new Position(destinationX + 4, destinationY));
              j++;
            } else if (j == 1) {
              enemies.enemySoldiers[enemyIdx].setDestination(
                  new Position(destinationX - 4, destinationY));
              i++;
              destinationY = Game.MAXMAPLENGTH - 1;
              destinationX = Game.MAXMAPLENGTH - 2;
            }
          } else {
            enemies.enemySoldiers[enemyIdx].setDestination(
                new Position(destinationX, destinationY));
            destinationX--;
          }
          enemyIdx++;
        }
        break;
    }
  }

  @Override
  public void printOnMap() {
    System.out.printf("S");
  }
}
