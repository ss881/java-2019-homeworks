package calabashbrother;

public interface Move {
  enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
  }

  boolean moveToDestination();

  void stepTo(Direction direction);
}
