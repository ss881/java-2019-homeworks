package cn.mhj.Field;

public class Position {
  private int x, y;

  public Position() {
    this.x = 0;
    this.y = 0;
  }

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public String toString() {
    return String.format("(%d, %d)", this.x, this.y);
  }

  public void printInfo() {
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
