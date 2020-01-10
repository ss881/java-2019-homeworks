import org.junit.*;
import Property.*;
import Behavior.*;
import Item.*;

public class TestHit {
    @Test
    public void testHit() {
        try {
            Bullet bullet = new Bullet("", BulletType.MONSTER, Direction.LEFT,null,null);
            CalabashBoy boy = new CalabashBoy(CalabashProperty.BLUE);
            Hit bullethit = new Hit(bullet, boy);
            Assert.assertEquals(CalabashProperty.BLUE.getMaxHP(), boy.getHp());
        }
        catch (RuntimeException e) {
            ;
        }
    }

    @Test
    public void testHit2() {
        try {
            Bullet bullet = new Bullet("", BulletType.CALABASH, Direction.RIGHT,null,null);
            Lackey lackey = new Lackey();
            Hit bullethit = new Hit(bullet, lackey);
            Assert.assertEquals(260, lackey.getHp());
        }
        catch (RuntimeException e) {
            ;
        }
    }

    @Test
    public void testHit3() {
        try {
            Bullet bullet = new Bullet("", BulletType.STINGER, Direction.LEFT,null,null);
            Grandfather grandfather = new Grandfather();
            int hitTimes = 0;
            while(!grandfather.isDead()) {
                Hit hit = new Hit(bullet, grandfather);
                hitTimes++;
            }
            Assert.assertEquals(5, hitTimes);
        }
        catch (RuntimeException e) {
            ;
        }
    }
}
