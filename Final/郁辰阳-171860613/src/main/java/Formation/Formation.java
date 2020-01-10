package Formation;

import java.util.ArrayList;
import Position.Position;

public class Formation {

    public static Integer max = 19;
    public String name;
    public Position leader;
    public ArrayList<Position> pos = new ArrayList<>();

    public Formation(String name) {
        this.name = name;
        switch(name) {
            case "鹤翼":
                pos.add(new Position(3, 1));
                pos.add(new Position(2, 2));
                pos.add(new Position(4, 2));
                pos.add(new Position(1, 3));
                pos.add(new Position(5, 3));
                pos.add(new Position(0, 4));
                pos.add(new Position(6, 4));
                break;
            case "雁行":
                pos.add(new Position(0, 0));
                pos.add(new Position(1, 1));
                pos.add(new Position(2, 2));
                pos.add(new Position(3, 3));
                pos.add(new Position(4, 4));
                pos.add(new Position(5, 5));
                pos.add(new Position(6, 6));
                break;
            case "长蛇":
                pos.add(new Position(0,3));
                pos.add(new Position(1,3));
                pos.add(new Position(2,3));
                pos.add(new Position(3,3));
                pos.add(new Position(4,3));
                pos.add(new Position(5,3));
                pos.add(new Position(6,3));
                break;
            case "衡轭":
                pos.add(new Position(3, 1));
                pos.add(new Position(1, 2));
                pos.add(new Position(0, 3));
                pos.add(new Position(3, 2));
                pos.add(new Position(2, 3));
                pos.add(new Position(5, 2));
                pos.add(new Position(4, 3));
                pos.add(new Position(6, 3));
                break;
            case "偃月":
                pos.add(new Position(3, 0));
                pos.add(new Position(2, 0));
                pos.add(new Position(4, 0));
                pos.add(new Position(3, 1));
                pos.add(new Position(2, 1));
                pos.add(new Position(4, 1));
                pos.add(new Position(3, 2));
                pos.add(new Position(2, 2));
                pos.add(new Position(4, 2));
                pos.add(new Position(1, 2));
                pos.add(new Position(5, 2));
                pos.add(new Position(1, 3));
                pos.add(new Position(5, 3));
                pos.add(new Position(0, 3));
                pos.add(new Position(6, 3));
                pos.add(new Position(0, 4));
                pos.add(new Position(6, 4));
                pos.add(new Position(-1, 5));
                pos.add(new Position(7, 5));
                break;
            case "方门":
                pos.add(new Position(3, 1));
                pos.add(new Position(2, 2));
                pos.add(new Position(4, 2));
                pos.add(new Position(1, 3));
                pos.add(new Position(5, 3));
                pos.add(new Position(2, 4));
                pos.add(new Position(4, 4));
                pos.add(new Position(3, 5));
                break;
            case "锋矢":
                pos.add(new Position(3, 1));
                pos.add(new Position(2, 2));
                pos.add(new Position(3, 2));
                pos.add(new Position(4, 2));
                pos.add(new Position(1, 3));
                pos.add(new Position(3, 3));
                pos.add(new Position(5, 3));
                pos.add(new Position(0, 4));
                pos.add(new Position(3, 4));
                pos.add(new Position(6, 4));
                break;
            case "鱼鳞":
                pos.add(new Position(3, 1));
                pos.add(new Position(2, 2));
                pos.add(new Position(1, 3));
                pos.add(new Position(3, 3));
                pos.add(new Position(5, 3));
                pos.add(new Position(0, 4));
                pos.add(new Position(2, 4));
                pos.add(new Position(4, 4));
                pos.add(new Position(6, 4));
                pos.add(new Position(3, 5));
                break;
            default: break;
        }
    }
}
