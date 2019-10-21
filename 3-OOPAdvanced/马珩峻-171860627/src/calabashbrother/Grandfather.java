package calabashbrother;

import calabashbrother.CalabashBrother.CalabashBoy;
import calabashbrother.Game.Formation;

public class Grandfather extends Unit {

  Grandfather(Position position) {
    super(position, UnitType.GRANDFATHER);
  }

  void orderFormation(Formation formation, CalabashBrother calabashBrother) {
    switch (formation) {
      case CS:
        int destinationX = 1;
        for (CalabashBoy v : calabashBrother.calabashBoys) {
          v.setDestination(new Position(destinationX, 1));
          destinationX++;
        }
        break;
      case FS:
        break;
      case FY:
        break;
      case HE:
        break;
      case HY:
        break;
      case YL:
        break;
      case YX:
        break;
      case YY:
        break;
    }
  }

  @Override
  public void printOnMap() {
    System.out.printf("F");
  }
}
