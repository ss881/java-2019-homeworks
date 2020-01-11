import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CreatureTest extends TestCase {
    @Test
    public void testCreature() {
        World world = new World();
        Creature.world = world;
        Creature a = new Creature();
        //测试基础的函数 setxxx tellxxx
        a.setName("被测试者");
        assertTrue(a.tellName().equals("被测试者"));
        //测试函数 findEnemy
        a.setCamp(1);
        Creature b = new Creature();
        b.setCamp(-1);
        a.x = 3; a.y = 7;
        b.x = 9; b.y = 1;
        Creature.world.map[3][7] = a;
        Creature.world.map[9][1] = b;
        Node res1 = a.findEnemy();
        assertTrue(res1.x != -1 && res1.y != -1);
        //测试函数attack
        a.setHP(100); b.setHP(200);
        a.setDamage(99); b.setDamage(18);
        a.attack(b); b.attack(a);
        assertTrue(a.tellHP() == 82 && b.tellHP() == 101);
    }
    @Test
    public void testRoad() {
        //测试自动寻找最短路的类
        Road road = new Road(new World());
        road.world.map[1][1] = new Creature();
        road.world.map[1][2] = new Creature();
        road.world.map[1][3] = new Creature();
        road.world.map[2][1] = new Creature();
        road.world.map[3][1] = new Creature();
        road.world.map[3][2] = new Creature();
        road.world.map[3][3] = new Creature();
        road.world.map[2][0] = new Creature();
        road.world.map[2][2] = new Creature();
        ArrayList<Node> res = road.findRoad(new Node(2,0), new Node(2,2));
        assertEquals(11,res.size());
    }
}
