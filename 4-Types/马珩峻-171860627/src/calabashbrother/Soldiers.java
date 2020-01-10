package calabashbrother;

import java.util.Vector;

class Soldiers<T extends Unit> {
  static final int MAX_NUM = 20;
  int number;
  Vector<T> soldiers;
  boolean formationIsReady;

  Soldiers() {
    number = 0;
    this.soldiers = new Vector<T>();
    formationIsReady = true;
  }

  void lineUp() {
    this.formationIsReady = true;
    for (T soldier : this.soldiers) {
      this.formationIsReady = soldier.moveToDestination() & this.formationIsReady;
    }
  }
}
