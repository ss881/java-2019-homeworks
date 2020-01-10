package sample;

import javafx.scene.canvas.Canvas;
import javafx.util.Pair;
import org.junit.Test;


import static org.junit.Assert.*;

public class GroundMapTest {

    @Test
    public void selectCreations() {
//        Canvas canvas = new Canvas();
        GroundMap map = new GroundMap(null, false, false, "");
        Creation target = new Creation(0, Creation.GRANDPA, 10, 10, map, map._file);
        map.addCreaiont(target);
        Creation add;
        for (int i = 1; i < 8; i++) {
            add = new Creation(i * 4 + 1, Creation.DOLLONE, 10 + i, 10, map, map._file);
            add.setAlive(true); map.addCreaiont(add);
            add = new Creation(i * 4 + 2, Creation.DOLLONE, 10, 10 - i, map, map._file);
            add.setAlive(false); map.addCreaiont(add);
            add = new Creation(i * 4 + 3, Creation.SOLDIER, 10 + i / 2, 10 + i - i / 2, map, map._file);
            add.setAlive(true); map.addCreaiont(add);
            add = new Creation(i * 4 + 4, Creation.SOLDIER, 10 - (i - i / 2), 10 - i / 2, map, map._file);
            add.setAlive(false); map.addCreaiont(add);
        }
        for (int i = 1; i < 8; i++) {
            assertEquals("1:" + i, i, map.selectCreations(target, i, target.getGroup(), true).size());
            assertEquals("2:" + i, i, map.selectCreations(target, i, !target.getGroup(), true).size());
            assertEquals("3:" + i, i, map.selectCreations(target, i, target.getGroup(), false).size());
            assertEquals("4:" + i, i, map.selectCreations(target, i, !target.getGroup(), false).size());
        }
    }

    @Test
    public void selectNextStep() {
//        Canvas canvas = new Canvas();
        GroundMap map = new GroundMap(null, false, false, "");
        Creation target = new Creation(Creation.GRANDPA, 0, 10, 10, map, map._file);
        map.addCreaiont(target); map.setSpecial(0);
        Creation add;
        add = new Creation(1, Creation.DOLLONE, 11, 10, map, map._file);
        add.setAlive(true); map.addCreaiont(add);
        add = new Creation(2, Creation.DOLLONE, 9, 10, map, map._file);
        add.setAlive(false); map.addCreaiont(add);
        add = new Creation(3, Creation.DOLLONE, 10, 11, map, map._file);
        add.setAlive(true); map.addCreaiont(add);

        //测试不会向死去的生物前进
        add = new Creation(4, Creation.SOLDIER, 0, 0, map, map._file);
        add.setAlive(false); map.addCreaiont(add);
        assertNull("测试不会向死去的生物前进", map.selectNextStep(target));

        //测试不会向同伴前进
        add = new Creation(5, Creation.DOLLONE, 0, 1, map, map._file);
        add.setAlive(true); map.addCreaiont(add);
        assertNull("测试不会向同伴前进", map.selectNextStep(target));

        //测试会正确查找活着的生物
        add = new Creation(6, Creation.SOLDIER, 1, 0, map, map._file);
        add.setAlive(true); map.addCreaiont(add);
        Pair<Integer, Integer> step = map.selectNextStep(target);
        assertEquals("测试会正确查找活着的生物", 10, step.getKey().intValue());
        assertEquals("测试会正确查找活着的生物", 9, step.getValue().intValue());

        //测试被包围注时，会返回空值null
        add = new Creation(7, Creation.DOLLONE, 10, 9, map, map._file);
        add.setAlive(true); map.addCreaiont(add);
        assertNull("测试被包围注时，会返回空值null", map.selectNextStep(target));
    }
}