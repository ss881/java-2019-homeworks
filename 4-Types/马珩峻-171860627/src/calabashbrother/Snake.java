package calabashbrother;

import calabashbrother.Enemies.EnemySoldier;

public class Snake extends Leader<EnemySoldier> {

  Snake(Position position) {
    super(position, UnitType.SNACK);
  }

  //  @Override
  //  public void printOnMap() {
  //    System.out.print("S");
  //  }
}
