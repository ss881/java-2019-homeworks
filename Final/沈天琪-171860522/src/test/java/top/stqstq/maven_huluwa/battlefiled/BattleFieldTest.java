package top.stqstq.maven_huluwa.battlefiled;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BattleFieldTest {

    @Before
    public void setUp() throws Exception {
        BattleField battleField = new BattleField();
        BattleField.setField(10, 11, 255);
        BattleField.setField(255, 255, 254);
    }

    @After
    public void tearDown() throws Exception {
        BattleField.setField(10, 11, 0);
    }

    @Test
    public void getNo() {
        assertEquals(255, BattleField.getNo(10, 11));
    }

    @Test
    public void getXByNo() {
        assertEquals(10, BattleField.getXByNo(255));
        assertEquals(-1, BattleField.getXByNo(254));
    }

    @Test
    public void getYByNo() {
        assertEquals(11, BattleField.getYByNo(255));
        assertEquals(-1, BattleField.getYByNo(254));
    }
}