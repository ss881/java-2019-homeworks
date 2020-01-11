package huluwa.creature;

import huluwa.util.Position;

import java.util.ArrayList;

public interface Leader {
    void buZhen(Position[][] positions, ArrayList<? extends Creature> creatures);
}
