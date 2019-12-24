package cn.edu.nju.huluwa.record;


import cn.edu.nju.huluwa.creature.Colour;
import cn.edu.nju.huluwa.creature.Creature;

public class Record {
    public enum RecordType {
        CREATE, // when startBattle begin, create a snapshot of each creature
        MOVE,   // when creature move, create a record
        HURT,    // when creature is hurt, create a record
        DEAD,
        UPGRADE,
        BULLET_CREATE,
        BULLET_REMOVE,
        BULLET_MOVE
    }

    public RecordType type;
    public int id;
    public int x;
    public int y;
    public double health;
    public double damage;
    public Colour colour;
    public Creature.State state;
    public long timeMillis;

    public Record() {
    }

    public Record(RecordType type, int id, int x, int y, double health, double damage, Creature.State state) {
        this.type = type;
        this.id = id;
        this.x = x;
        this.y = y;
        this.health = health;
        this.damage = damage;
        this.state = state;
        timeMillis = 0;
    }

    public long getTimeMillis() {
        return timeMillis;
    }

    public void setTimeMillis(long timeMillis) {
        this.timeMillis = timeMillis;
    }

    public RecordType getType() {
        return type;
    }

    public void setType(RecordType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public Creature.State getState() {
        return state;
    }

    public void setState(Creature.State state) {
        this.state = state;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Record{" +
                "type=" + type +
                ", id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", health=" + health +
                ", damage=" + damage +
                ", state=" + state +
                '}';
    }
}
