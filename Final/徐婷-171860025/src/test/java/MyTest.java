import battle.Ground;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MyTest
{
    @Test
    public void testGround(){
        Ground ground1 = Ground.getInstance();
        Ground ground2 = Ground.getInstance();
        assert ground1==ground2;
    }

    @Test
    public void testCell(){
        Ground ground1 = new Ground();
        Ground ground3 = Ground.getInstance();
        Ground ground2 = new Ground();
        Ground ground4 = Ground.getInstance();
        assert ground1==ground3;
        assert ground2==ground4;

    }
}