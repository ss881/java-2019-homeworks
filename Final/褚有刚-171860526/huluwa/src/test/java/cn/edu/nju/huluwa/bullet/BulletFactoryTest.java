package cn.edu.nju.huluwa.bullet;

import cn.edu.nju.huluwa.creature.CreatureFactory;
import cn.edu.nju.huluwa.creature.Grandpa;
import cn.edu.nju.huluwa.position.Position;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class BulletFactoryTest {

    @Test
    public void newBullet() {
        Grandpa grandpa = CreatureFactory.createGrandpa(null);
        Random random = new Random();
        grandpa.setPosition(new Position(0, 0));
        for (int i = 0; i < 10000; i++) {
            int x = random.nextInt(100) + 1;
            int y = random.nextInt(100) + 1;
            Bullet bullet = BulletFactory.newBullet(grandpa, new Position(x, y));
            assertEquals(Math.atan((double) y / x), bullet.getRadians(), 1e-6);
        }
        Position origin = new Position(0, 0);
        for(int i = 0; i < 1000; i++) {
            int x = random.nextInt(100) + 1;
            int y = random.nextInt(100) + 1;
            grandpa.setPosition(new Position(x, y));
            Bullet bullet = BulletFactory.newBullet(grandpa, origin);
            assertEquals(Math.PI - Math.atan((double) y / -x), bullet.getRadians(), 1e-6);
        }
    }
}