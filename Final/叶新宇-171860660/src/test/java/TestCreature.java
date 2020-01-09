import org.junit.*;
import Property.*;
import BattleField.*;
import Item.*;

public class TestCreature {
    @Test
    public void testmoveTo() throws Exception{
        try {
            BattleField battlefield = new BattleField();
            Creature.setBattlefield(battlefield.getBattleField());
            CalabashBoy boy = new CalabashBoy(CalabashProperty.RED);
            Position old = new Position();
            Position target = new Position();
            old.setXH(0);
            old.setYH(1);
            target.setXH(5);
            target.setYH(5);
            boy.setPosition(old.getX(),old.getY());
            boy.moveTo(target);
            Assert.assertEquals(battlefield.getBattleField()[5][5].getHolder(), boy);
        }
        catch (RuntimeException e) {
            ;
        }

    }

    @Test
    public void testmoveToCenter() throws Exception {
        try {
            BattleField battlefield = new BattleField();
            Creature.setBattlefield(battlefield.getBattleField());
            CalabashBoy boy = new CalabashBoy(CalabashProperty.RED);
            Position pos = new Position();
            pos.setXH(0);
            pos.setYH(0);
            boy.setPosition(pos.getX(), pos.getY());
            boy.moveToCenter();
            Assert.assertEquals(8, boy.getPosition().getXH());
            Assert.assertEquals(5, boy.getPosition().getYH());
        }
        catch (RuntimeException e) {
            ;
        }
    }

    @Test
    public void testisDead() throws Exception {
        try {
            Lackey lackey = new Lackey();
            lackey.setHp(10);
            Assert.assertEquals(false, lackey.isDead());
            lackey.setHp(0);
            Assert.assertEquals(true, lackey.isDead());
        }
        catch (RuntimeException e) {
            ;
        }
    }
}
