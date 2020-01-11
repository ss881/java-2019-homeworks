package creature;

import battlefield.BattleField;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.Assert.*;

public class LeaderTest {
    private static BattleField battleField;
    private static Lock lock;

    @BeforeClass
    public static void setUp(){
        battleField=new BattleField();
        lock=new ReentrantLock();
    }

    @Test
    public void setPosition() {
        Leader leader=new Leader("elder",battleField,true,lock);
        leader.setPosition(1,2);
        assertEquals(leader,battleField.theField[1][2].getCreature());
    }

    @Test
    public void checkDead() {
        Leader leader=new Leader("elder",battleField,true,lock);
        assert (leader.checkDead()==1);
    }

    @Test(timeout = 50)
    public void chooseDirection() {
        Leader leader=new Leader("elder",battleField,true,lock);
        leader.chooseDirection();
    }
}