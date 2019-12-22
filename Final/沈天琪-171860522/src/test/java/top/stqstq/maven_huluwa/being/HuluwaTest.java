package top.stqstq.maven_huluwa.being;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HuluwaTest {
    Huluwa huluwa;
    final String name = "laoda";
    final int rank = 7;
    final int fightAbility = 5;

    @Before
    public void setUp() throws Exception {
        huluwa = new Huluwa(name, rank, fightAbility);
    }

    @Test
    public void getRank() {
        assertEquals(rank, huluwa.getRank());
    }

    @Test
    public void getName() {
        assertEquals(name, huluwa.getName());
    }

    @Test
    public void getFightAbility() {
        assertEquals(fightAbility, huluwa.getFightAbility());
    }

    @Test
    public void getCombatComp() {
        assertEquals(1, huluwa.getCombatComp());
    }

    @Test
    public void getAliveState() {
        assertEquals(true, huluwa.getAliveState());
        huluwa.setAliveState(false);
        assertEquals(false, huluwa.getAliveState());
    }

    @Test
    public void getSymbol() {
        assertEquals("" + rank, huluwa.getSymbol());
    }
}