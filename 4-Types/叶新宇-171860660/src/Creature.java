import java.util.*;

public abstract class Creature {
    /*
    enum Type {
        CALABASHBOY, GRANDFATHER, SCORPION, SNAKE, MINION
    }
    protected Type id;
    */
    //应用反射机制后，身份属性将会被废弃

    protected String name;
    protected Position pos;
    //每个生物都有个坐标

    public Position getPos() {
        return pos;
    }

    public void setPos(int x, int y) {
        pos.setPos(x, y);
    }

    public void setPos(Position p) {
        pos.setPos(p);
    }
    /*
    public void printMark() {
        if(id == Type.CALABASHBOY)
            System.out.print('c');
        else if(id == Type.GRANDFATHER)
            System.out.print('g');
        else if(id == Type.SCORPION)
            System.out.print('S');
        else if(id == Type.SNAKE)
            System.out.print('s');
        else if(id == Type.MINION)
            System.out.print('m');
    }
    */
    //使用反射替代了原先的printMark函数

    /*
    public boolean move(BattleField b, Position p) {
        if(pos == null)
            return false;
        int x = pos.getPosX();
        int y = pos.getPosY();
        if(b.isAvailable(x, y)) {
            b.withdraw(pos);
            pos.setPosX(p.getPosX());
            pos.setPosY(p.getPosY());
            b.joinBattleField(this);
            return true;
        }
        else
            return false;
    }
    */

    public boolean join(BattleField b, Position p) {
        int x = p.getPosX();
        int y = p.getPosY();
        pos = new Position(x, y);
        pos.setHolder(this);
        if(b.isAvailable(x, y)) {
            b.joinBattleField(this.pos);
            return true;
        }
        else
            return false;
    }

    public boolean withdraw(BattleField b) {
        if(pos == null)
            return false;
        int x = pos.getPosX();
        int y = pos.getPosY();
        if(!b.isAvailable(x, y)) {
            b.withdraw(pos);
            pos.setHolder(null);
            pos.setPos(new Position(-1, -1));
            return true;
        }
        else
            return false;
    }
}

class CalabashBoy extends Creature {
    private String color;
    CalabashBoy(String n, String c) {
        ///id = Type.CALABASHBOY;
        name = n;
        color = c;
    }
}

class Grandfather extends Creature {
    Grandfather(String n) {
        //id = Type.GRANDFATHER;
        name = n;
    }
}

class Scorpion extends Creature {
    Scorpion(String n) {
        //id = Type.SCORPION;
        name = n;
    }
}

class Snake extends Creature {
    Snake(String n) {
        //id = Type.SNAKE;
        name = n;
    }
}

class Minion extends Creature {
    Minion(String n) {
        //id = Type.MINION;
        name = n;
    }
}