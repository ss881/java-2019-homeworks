package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreationTest {

    @Test
    public void attacked() {
        GroundMap map = new GroundMap(null, false, false, "");
        Creation target = new Creation(0, Creation.GRANDPA, 0, 0, map, map._file);
        target.setAttribute(Attribute.HEALTH, 100);
        target.setAttribute(Attribute.CONSTITUTION, 100);

        target.attacked(Judge.SUPPER_SUCCESS, 50);
        assertTrue("测试生物受到攻击时是否受伤",
                target.getAttribute(Attribute.HEALTH) < target.getAttribute(Attribute.CONSTITUTION));

        target.attacked(Judge.SUPPER_SUCCESS, 10000);
        assertFalse("测试生物受到攻击是否会造成死亡", target.getAlive());
        assertEquals("测试生物的血量是否会降为负数", 0, target.getAttribute(Attribute.HEALTH));
    }
}