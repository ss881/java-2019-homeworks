package top.stqstq.maven_huluwa.being;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScorpionTest {
    Scorpion scorpion;

    @Before
    public void setUp() throws Exception {
        scorpion = new Scorpion();
    }

    @Test
    public void getSymbol() {
        assertEquals("C", scorpion.getSymbol());
    }

    @Test
    public void getRank() {
        assertEquals(10, scorpion.getRank());
    }

    @Test
    public void getName() {
        assertEquals("Scorpion", scorpion.getName());
    }

    @Test
    public void getFightAbility() {
        assertEquals(10, scorpion.getFightAbility());
    }

    @Test
    public void getCombatComp() {
        assertEquals(2, scorpion.getCombatComp());
    }

    @Test
    public void getAliveState() {
        assertEquals(true, scorpion.getAliveState());
        scorpion.setAliveState(false);
        assertEquals(false, scorpion.getAliveState());
    }
}