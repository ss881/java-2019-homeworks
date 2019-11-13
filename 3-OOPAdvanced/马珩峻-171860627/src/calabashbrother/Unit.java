package calabashbrother;

public abstract class Unit implements Move {
  enum UnitType {
    GRANDFATHER,
    CALABASHBOY,
    SNACK,
    ENEMYSOLDIER
  }

  private Position position;
  private Position destination;
  private UnitType type;
  private boolean isready;

  Unit(Position position, UnitType type) {
    this.position = position;
    this.type = type;
    this.isready = true;
  }

  Position getPosition() {
    return this.position;
  }

  void setDestination(Position destination) {
    if (!position.equals(destination)) {
      this.destination = destination;
      this.isready = false;
    } else {
      this.isready = true;
    }
  }

  @Override
  public boolean moveToDestination() {
    if (!isready) {
      if (destination.getX() - position.getX() < 0) {
        stepTo(Direction.UP);
      } else if (destination.getX() - position.getX() > 0) {
        stepTo(Direction.DOWN);
      } else if (destination.getY() - position.getY() < 0) {
        stepTo(Direction.LEFT);
      } else if (destination.getY() - position.getY() > 0) {
        stepTo(Direction.RIGHT);
      }
      if (position.equals(destination)) {
        isready = true;
        return true;
      }
      return false;
    }
    return true;
  }

  @Override
  public void stepTo(Direction direction) {
    switch (direction) {
      case UP:
        position.setX(position.getX() - 1);
        break;
      case DOWN:
        position.setX(position.getX() + 1);
        break;
      case LEFT:
        position.setY(position.getY() - 1);
        break;
      case RIGHT:
        position.setY(position.getY() + 1);
        break;
    }
  }

  public abstract void printOnMap();
}
