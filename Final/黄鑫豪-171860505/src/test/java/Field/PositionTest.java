package Field;

import org.junit.Test;
import static org.junit.Assert.*;

public class PositionTest {
    @Test
    public void testInitialize() {
        Position position = new Position(0, 0);
        assertTrue(position.isEmpty());
    }

    @Test
    public void testSetCreature() {
        Position position = new Position(0, 0);
        assertNull(position.getHolder());
    }
}