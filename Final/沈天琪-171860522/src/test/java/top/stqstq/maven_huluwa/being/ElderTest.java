package top.stqstq.maven_huluwa.being;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElderTest {

    Elder elder;

    @Before
    public void setUp() throws Exception {
        elder = new Elder();
    }

    @Test
    public void getRank() {
        assertEquals(8, elder.getRank());
    }

    @Test
    public void getName() {
        assertEquals("Elder", elder.getName());
    }

    @Test
    public void getFightAbility() {
        assertEquals(0, elder.getFightAbility());
    }

    @Test
    public void getCombatComp() {
        assertEquals(1, elder.getCombatComp());
    }

    @Test
    public void getAliveState() {
        assertEquals(true, elder.getAliveState());
        elder.setAliveState(false);
        assertEquals(false, elder.getAliveState());
    }

    @Test
    public void getSymbol() {
        assertEquals("E", elder.getSymbol());
    }
}