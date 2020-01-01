package cn.edu.nju.huluwa.bullet;

import cn.edu.nju.huluwa.creature.Creature;
import cn.edu.nju.huluwa.position.Position;
import cn.edu.nju.huluwa.record.Record;

import static cn.edu.nju.huluwa.Config.*;

public class BulletFactory {
    private static int bulletId = 0;
    private BulletFactory() {

    }

    // attacker向position方向发动攻击
    public static synchronized Bullet newBullet(Creature attacker, Position to) {
        Position from = attacker.getPosition();
        Bullet bullet = new Bullet();
        bullet.setId(bulletId++);
        bullet.setAttacker(attacker);
        bullet.setX(from.getX() * GRID_WIDTH + GRID_WIDTH / 2);
        bullet.setY(from.getY() * GRID_HEIGHT + GRID_HEIGHT / 2);
        double dx = (to.getX() - from.getX()) * GRID_WIDTH;
        double dy = (to.getY() - from.getY()) * GRID_HEIGHT;
        double radians = 0;
        if (dx == 0) {
            radians = dy > 0 ? Math.toRadians(90.0) : Math.toRadians(-90.0);
            if (dy == 0)
                radians = 0;
        } else if (dx > 0) {
            radians = Math.atan(dy / dx);
        } else {
            radians = Math.PI - Math.atan(dy / -dx);
//            radians = Math.atan(dy / dx);
        }
        bullet.setRadians(radians);
        bullet.setColor(attacker.getColor());
        bullet.setSpeed(BULLET_SPEED);
        return bullet;
    }

    public static Bullet newBullet(Record record) {
        Bullet bullet = new Bullet();
        bullet.setId(record.getId());
        bullet.setX(record.getX());
        bullet.setY(record.getY());
        bullet.setColor(record.getColour());
        return bullet;
    }
}
