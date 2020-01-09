package calabashbrother;

import calabashbrother.Enemies.EnemySoldier;

class Enemies extends Soldiers<EnemySoldier> {

  Enemies(int number) throws Exception {
    super();
    if (number > MAX_NUM) {
      throw new Exception(
          String.format("Enemy number is too large,expect less than 20 but got %d", number));
    } else {
      this.number = number;
      int posX = Game.MAXMAPLENGTH - 2, posY = Game.MAXMAPLENGTH - 1;
      for (int i = 0; i < number; i++) {
        this.soldiers.addElement(new EnemySoldier(new Position(posX, posY), i));
        posX--;
        if (posX == Game.MAXMAPLENGTH - 12) {
          posY--;
          posX = Game.MAXMAPLENGTH - 2;
        }
      }
    }
  }

  static class EnemySoldier extends Unit {
    int index;

    EnemySoldier(Position position, int index) {
      super(position, UnitType.ENEMYSOLDIER);
      this.index = index;
    }

    //    @Override
    //    public void printOnMap() {
    //      System.out.print("E");
    //    }

    @Override
    public void showPosition() {
      System.out.printf("Enemy %d: %s\n", this.index, this.getPosition().toString());
    }
  }
}
