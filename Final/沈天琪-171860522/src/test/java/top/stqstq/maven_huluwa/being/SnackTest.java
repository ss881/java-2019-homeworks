package top.stqstq.maven_huluwa.being;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SnackTest {
    Snack snack;

    @Before
    public void setUp() throws Exception {
        snack = new Snack();
    }

    @Test
    public void getSymbol() {
        assertEquals("S", snack.getSymbol());
    }

    @Test
    public void getRank() {
        assertEquals(9, snack.getRank());
    }

    @Test
    public void getName() {
        assertEquals("Snack", snack.getName());
    }

    @Test
    public void getFightAbility() {
        assertEquals(4, snack.getFightAbility());
    }

    @Test
    public void getCombatComp() {
        assertEquals(2, snack.getCombatComp());
    }

    @Test
    public void getAliveState() {
        assertEquals(true, snack.getAliveState());
        snack.setAliveState(false);
        assertEquals(false, snack.getAliveState());
    }
}