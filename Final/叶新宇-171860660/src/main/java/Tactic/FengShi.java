package Tactic;

import Property.Position;

public class FengShi implements TacticMaker {
    public Position[] getPos() {
        Position[] positions = {
                new Position(7 * 60,4 * 60), new Position(6 * 60,3 * 60), new Position(5 * 60,2 * 60),
                new Position(6 * 60,5 * 60), new Position(5 * 60,6 * 60), new Position(6 * 60,4 * 60),
                new Position(5 * 60,4 * 60), new Position(4 * 60,4 * 60)
        };
        return positions;
    }

    public String showName() {
        return "摆出了锋矢阵";
    }
}