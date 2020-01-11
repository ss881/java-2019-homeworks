package cn.edu.nju.huluwa.creature;


import cn.edu.nju.huluwa.position.Position;

import java.util.ArrayList;

public interface Leader {
    void buZhen(Position[][] positions, ArrayList<? extends Creature> creatures);
    void setInitialPos(Position[][] positions, ArrayList<? extends Creature> creatures);
}
