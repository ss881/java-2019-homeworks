import org.junit.*;
import BattleField.*;
import Item.*;

public class TestBlock {
    @Test
    public void testjoin() throws Exception {
        try {
            Block b = new Block();
            Lackey lackey = new Lackey();
            lackey.setPosition(0, 0);
            b.join(lackey);
            Assert.assertEquals(false, b.isEmpty());
            Assert.assertEquals(lackey, b.getHolder());
        }
        catch (RuntimeException e) {
            ;
        }
    }

    @Test
    public void testwithdraw() throws Exception {
        try {
            Block b = new Block();
            Lackey lackey = new Lackey();
            lackey.setPosition(0, 0);
            b.join(lackey);
            b.withdraw();
            Assert.assertEquals(true, b.isEmpty());
        }
        catch (RuntimeException e) {
            ;
        }
    }
}
