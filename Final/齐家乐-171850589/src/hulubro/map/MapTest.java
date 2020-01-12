package hulubro.map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void distance() {
        Map map = new Map();
        int result = map.distance(12, 2,8,4);
        Assert.assertEquals("出错！", 6, result);
    }
}