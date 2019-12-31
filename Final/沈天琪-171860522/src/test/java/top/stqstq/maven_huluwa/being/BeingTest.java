package top.stqstq.maven_huluwa.being;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BeingTest {

    Being being;

    @Before
    public void setUp() throws Exception {
        being = new Being();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getRank() {
        assertEquals(0, being.getRank());
    }

    @Test
    public void getName() {
        assertEquals("Being", being.getName());
    }

    @Test
    public void getFightAbility() {
        assertEquals(0, being.getFightAbility());
    }

    @Test
    public void getCombatComp() {
        assertEquals(0, being.getCombatComp());
    }

    @Test
    public void getAliveState() {
        assertEquals(false, being.getAliveState());
    }

    @Test
    public void getSymbol() {
        assertEquals(" ", being.getSymbol());
    }
}