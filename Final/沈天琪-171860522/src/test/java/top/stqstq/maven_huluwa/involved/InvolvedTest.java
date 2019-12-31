package top.stqstq.maven_huluwa.involved;

import top.stqstq.maven_huluwa.battlefiled.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class InvolvedTest {
    Involved involved;

    @Test
    public void huluwaCome() {
        involved = new Involved();
        involved.huluwaCome();
        for (int i = 1; i < 8; i++)
            assertEquals(i, BattleField.getNo(11 - i, 0));
        assertEquals(8, BattleField.getNo(11, 0));
    }
}