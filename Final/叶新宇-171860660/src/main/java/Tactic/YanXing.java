package Tactic;

import Property.Position;

public class YanXing implements TacticMaker {
    public Position[] getPos() {
        Position[] positions = {
                new Position(7 * 60,0 * 60), new Position(6 * 60,1 * 60), new Position(5 * 60,2 * 60),
                new Position(4 * 60,3 * 60), new Position(3 * 60,4 * 60), new Position(2 * 60,5 * 60),
                new Position(1 * 60,6 * 60), new Position(0 * 60,7 * 60)
        };
        return positions;
    }

    public String showName() {
        return "摆出了雁行阵";
    }
}