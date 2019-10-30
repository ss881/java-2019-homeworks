package calabashbrother;

import calabashbrother.CalabashBrother.CalabashBoy;
import calabashbrother.Enemies.EnemySoldier;

public class Game {

  public enum Formation {
    HY(1, "鹤翼"),
    YX(2, "雁行"),
    HE(3, "衡轭"),
    CS(4, "长蛇"),
    YL(5, "鱼鳞"),
    FY(6, "方円"),
    YY(7, "偃月"),
    FS(8, "锋矢");

    public final int formationValue;

    public final String chinese;

    Formation(int formationValue, String chinese) {
      this.formationValue = formationValue;
      this.chinese = chinese;
    }

    @Override
    public String toString() {
      return this.chinese;
    }
  }

  static final int MAXMAPLENGTH = 18;
  private static final int ENEMYNUM = 19;
  Unit[][] map = new Unit[MAXMAPLENGTH][MAXMAPLENGTH];
  Grandfather grandfather;
  Snake snake;
  CalabashBrother calabashBrother;
  Enemies enemies;

  Game() {
    for (int i = 0; i < MAXMAPLENGTH; i++) {
      for (int j = 0; j < MAXMAPLENGTH; j++) {
        map[i][j] = null;
      }
    }

    grandfather = new Grandfather(new Position(0, 0));
    snake = new Snake(new Position(MAXMAPLENGTH - 1, MAXMAPLENGTH - 1));
    calabashBrother = new CalabashBrother();
    enemies = new Enemies(ENEMYNUM);

    renewMap();
  }

  boolean hasUnit(int x, int y) {
    return map[x][y] != null;
  }

  void renewMap() {
    for (int i = 0; i < MAXMAPLENGTH; i++) {
      for (int j = 0; j < MAXMAPLENGTH; j++) {
        map[i][j] = null;
      }
    }
    map[grandfather.getPosition().getX()][grandfather.getPosition().getY()] = grandfather;
    map[snake.getPosition().getX()][snake.getPosition().getY()] = snake;

    for (CalabashBoy v : calabashBrother.calabashBoys) {
      map[v.getPosition().getX()][v.getPosition().getY()] = v;
    }

    for (EnemySoldier v : enemies.enemySoldiers) {
      map[v.getPosition().getX()][v.getPosition().getY()] = v;
    }
  }

  void show() {
    System.out.println("--------------------");
    for (Unit[] i : map) {
      for (Unit j : i) {
        if (j == null) System.out.printf(" ");
        else j.printOnMap();
      }
      System.out.println();
    }
    System.out.println("--------------------");
  }

  void run() {
    boolean isReady = false;
    while (!isReady) {
      isReady = true;
      for (CalabashBoy v : calabashBrother.calabashBoys) {
        if (!v.moveToDestination()) {
          isReady = false;
        }
      }
      for (EnemySoldier v : enemies.enemySoldiers) {
        if (!v.moveToDestination()) {
          isReady = false;
        }
      }

      renewMap();
      show();
    }
  }

  void testCase() {
    show();
    grandfather.orderFormation(Formation.CS, calabashBrother);
    snake.orderFormation(Formation.HE, enemies);
    run();
    snake.orderFormation(Formation.YL, enemies);
    run();
  }

  public static void main(String[] argvs) {
    Game game = new Game();
    game.testCase();
  }
}
