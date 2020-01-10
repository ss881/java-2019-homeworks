package Tactic;

import Property.Position;

public class YuLin implements TacticMaker {
    public Position[] getPos() {
        Position[] positions = {
                new Position(5 * 60,4 * 60), new Position(5 * 60,3 * 60), new Position(4 * 60,5 * 60),
                new Position(4 * 60,4 * 60), new Position(4 * 60,3 * 60), new Position(4 * 60,2 * 60),
                new Position(3 * 60,4 * 60), new Position(3 * 60,3 * 60)
        };
        return positions;
    }

    public String showName() {
        return "摆出了鱼鳞阵";
    }
}