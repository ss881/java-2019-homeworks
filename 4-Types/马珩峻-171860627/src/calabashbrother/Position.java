package calabashbrother;

import javafx.util.Pair;

public class Position {
  private int x, y;

  Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  int getX() {
    return this.x;
  }

  int getY() {
    return this.y;
  }

  Pair<Integer, Integer> getPos() {
    return new Pair<>(this.x, this.y);
  }

  void setX(int x) {
    this.x = x;
  }

  void setY(int y) {
    this.y = y;
  }

  void setPos(Pair<Integer, Integer> pair) {
    this.x = pair.getKey();
    this.y = pair.getValue();
  }

  public String toString() {
    return String.format("(%d, %d)", this.x, this.y);
  }

  void printInfo() {
    System.out.printf("(%d, %d)", this.x, this.y);
  }

  @Override
  public boolean equals(Object dst) {
    if (this == dst) return true;
    if (dst == null || this.getClass() != dst.getClass()) return false;

    Position that = (Position) dst;

    if (this.x != that.x) return false;
    return this.y == that.y;
  }
}
