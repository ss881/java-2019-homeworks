import org.junit.*;
import Property.*;
import Behavior.*;
import Item.*;

public class TestMeet {
    @Test
    public void testMeet() throws Exception {
        try {
            CalabashBoy boy = new CalabashBoy(CalabashProperty.RED);
            Lackey lackey = new Lackey();
            long start = System.currentTimeMillis();
            Meet fight = new Meet(boy, lackey);
            long end = System.currentTimeMillis();
            Assert.assertTrue((end-start) < 150);
        }
        catch (RuntimeException e) {
            ;
        }
    }

    @Test
    public void testMeet2() throws Exception {
        try {
            CalabashBoy boy = new CalabashBoy(CalabashProperty.RED);
            Lackey lackey = new Lackey();
            Meet fight = new Meet(boy, lackey);
            Assert.assertTrue(lackey.isDead());
        }
        catch (RuntimeException e) {
            ;
        }
    }

    @Test
    public void testMeet3() throws Exception {
        try {
            Scorpion sc = new Scorpion();
            Grandfather grandfather = new Grandfather();
            Meet fight = new Meet(sc, grandfather);
            Assert.assertTrue(grandfather.isDead());
        }
        catch (RuntimeException e) {
            ;
        }
    }
}
