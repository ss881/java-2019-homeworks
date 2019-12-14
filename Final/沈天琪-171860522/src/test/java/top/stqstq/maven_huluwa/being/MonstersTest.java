package top.stqstq.maven_huluwa.being;

import top.stqstq.maven_huluwa.battlefiled.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonstersTest {
    Monsters monsters;
    final int rank = 25;

    @Before
    public void setUp() throws Exception {
        monsters = new Monsters(rank);
    }

    @Test
    public void walkTo() {
        BattleField.setField(5, 5, rank);
        BattleField.setField(5, 6, 0);
        BattleField.setField(5, 7, 10);
        monsters.walkTo(5, 7);
        assertEquals(rank, BattleField.getNo(5, 5));
        assertEquals(10, BattleField.getNo(5, 7));
        monsters.walkTo(5, 6);
        assertEquals(rank, BattleField.getNo(5, 6));
        assertEquals(10, BattleField.getNo(5, 7));
        assertEquals(0, BattleField.getNo(5, 5));
    }

    @Test
    public void getRank() {
        assertEquals(rank, monsters.getRank());
    }

    @Test
    public void getName() {
        assertEquals("Monsters", monsters.getName());
    }

    @Test
    public void getFightAbility() {
        assertEquals(1, monsters.getFightAbility());
    }

    @Test
    public void getCombatComp() {
        assertEquals(2, monsters.getCombatComp());
    }

    @Test
    public void getSymbol() {
        assertEquals("M", monsters.getSymbol());
    }

    @Test
    public void getAliveState() {
        assertEquals(true, monsters.getAliveState());
        monsters.setAliveState(false);
        assertEquals(false, monsters.getAliveState());
    }
}