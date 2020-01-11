package Tactic;

import Property.Position;

public class ChongE implements TacticMaker {
    public Position[] getPos() {
        Position[] positions = {
                new Position(5 * 60,1 * 60), new Position(4 * 60,2 * 60), new Position(5 * 60,3 * 60),
                new Position(4 * 60,4 * 60), new Position(5 * 60,5 * 60), new Position(4 * 60,6 * 60),
                new Position(5 * 60,7 * 60), new Position(4 * 60,8 * 60)
        };
        return positions;
    }

    public String showName() {
        return "摆出了冲轭阵";
    }
}