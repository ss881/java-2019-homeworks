package interfaces;

import creatures.Position;

public interface PositionChangable {
    void setPreviousPosition(Position position);
    void setCurrentPosition(Position position);
}
