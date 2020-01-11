package calabashbrother;

import calabashbrother.CalabashBrother.CalabashBoy;
import calabashbrother.Camp.Formation;
import calabashbrother.Camp.Range;
import calabashbrother.Enemies.EnemySoldier;
import java.util.Vector;

public class Game {

  static final int MAXMAPLENGTH = 18;
  private static final int ENEMYNUM = 18;
  private Unit[][] map = new Unit[MAXMAPLENGTH][MAXMAPLENGTH];
  private Camp<Grandfather, CalabashBoy> goodGuys;
  private Camp<Snake, EnemySoldier> badGuys;

  private Game() {
    goodGuys =
        new Camp<>(
            new Grandfather(new Position(0, 0)),
            new CalabashBrother(),
            new Range(0, MAXMAPLENGTH - 1, 1, MAXMAPLENGTH / 2 - 1));
    try {
      badGuys =
          new Camp<>(
              new Snake(new Position(MAXMAPLENGTH - 1, MAXMAPLENGTH - 1)),
              new Enemies(ENEMYNUM),
              new Range(MAXMAPLENGTH - 1, 0, MAXMAPLENGTH - 2, MAXMAPLENGTH / 2));
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(-1);
    }
    try {
      renewMap();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(-1);
    }
  }

  boolean hasUnit(int x, int y) {
    return map[x][y] != null;
  }

  private void renewMap() throws Exception {
    for (int i = 0; i < MAXMAPLENGTH; i++) {
      for (int j = 0; j < MAXMAPLENGTH; j++) {
        map[i][j] = null;
      }
    }
    Vector<Camp> camps = new Vector<>();
    camps.addElement(this.goodGuys);
    camps.addElement(this.badGuys);

    for (var camp : camps) {
      this.map[camp.leader.getPosition().getX()][camp.leader.getPosition().getY()] = camp.leader;
      for (var soldier : camp.soldiers.soldiers) {
        if (soldier instanceof Unit) {
          this.map[((Unit) soldier).getPosition().getX()][((Unit) soldier).getPosition().getY()] =
              (Unit) soldier;
        } else {
          throw new Exception(
              String.format(
                  "Object in map is not right type, expect Unit but got %s",
                  soldier.getClass().getName()));
        }
      }
    }
  }

  private void show() {
    System.out.println("--------------------");
    for (Unit[] i : map) {
      for (Unit j : i) {
        if (j == null) System.out.print(" ");
        else {
          //          j.printOnMap();
          String unitType = j.getClass().getSimpleName();
          if (j instanceof CalabashBoy) {
            j.printOnMap();
          } else {
            System.out.printf("%c", unitType.charAt(0));
          }
        }
      }
      System.out.println();
    }
    System.out.println("--------------------");
  }

  private void run() {
    boolean isReady = false;
    while (!isReady) {
      isReady = goodGuys.lineUp() && badGuys.lineUp();

      try {
        renewMap();
      } catch (Exception e) {
        System.out.println(e.getMessage());
        System.exit(-1);
      }
      show();
    }
  }

  private void testCase() {
    this.goodGuys.changeFormation(Formation.CS);
    this.badGuys.changeFormation(Formation.HY);
    run();

    this.badGuys.changeFormation(Formation.FS);
    run();
  }

  public static void main(String[] argvs) {
    Game game = new Game();
    game.testCase();
  }
}
