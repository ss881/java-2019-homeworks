package cn.edu.nju.huluwa.bullet;

import cn.edu.nju.huluwa.creature.Colour;
import cn.edu.nju.huluwa.creature.Creature;

public class Bullet {
    private int id;
    private Creature attacker;  // 攻击者
    private Colour color;   // 颜色
    private int x;   // x位置
    private int y;   // y位置
    private double speed;   // 速度，10ms中移动
    private double radians;   // 角度

    Bullet() {

    }

    public Creature getAttacker() {
        return attacker;
    }

    public void setAttacker(Creature attacker) {
        this.attacker = attacker;
    }

    public Colour getColor() {
        return color;
    }

    public void setColor(Colour color) {
        this.color = color;
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getRadians() {
        return radians;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Bullet{" +
                "attacker=" + attacker +
                ", color=" + color +
                ", x=" + x +
                ", y=" + y +
                ", speed=" + speed +
                ", radians=" + radians +
                '}';
    }

    public void setRadians(double radians) {
        this.radians = radians;
    }
}
