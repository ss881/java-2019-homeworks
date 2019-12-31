package calabashbrother;

public class Enemies {

  private static final int MAXNUM = 20;
  int numbers;
  EnemySoldier[] enemySoldiers;

  Enemies(int numbers) {
    this.numbers = numbers;
    enemySoldiers = new EnemySoldier[numbers];
    int posX = Game.MAXMAPLENGTH - 2, posY = Game.MAXMAPLENGTH - 1;
    for (int i = 0; i < numbers; i++) {
      enemySoldiers[i] = new EnemySoldier(new Position(posX, posY));
      posX--;
      if (posX == Game.MAXMAPLENGTH - 12) {
        posY--;
        posX = Game.MAXMAPLENGTH - 2;
      }
    }
  }

  public class EnemySoldier extends Unit {
    EnemySoldier(Position position) {
      super(position, UnitType.ENEMYSOLDIER);
    }

    @Override
    public void printOnMap() {
      System.out.printf("E");
    }
  }
}
