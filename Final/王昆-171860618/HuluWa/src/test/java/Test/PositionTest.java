package Test;
import static java.util.Collections.sort;
import static org.junit.Assert.*;

import huluwa.position.Position;
import org.junit.Test;
import java.util.*;

public class PositionTest{
    @Test
    public void testPositionEqual(){
        assertEquals(new Position(9000,2000),new Position(9000,2000));
        assertNotEquals(new Position(4,10),new Position(10,3));
    }
    @Test
    public void testSort(){
        ArrayList<Position> positionList = new ArrayList<>();
        Random r = new Random();
        for(int i=0;i<50;i++){
            int x = r.nextInt(100);
            int y = r.nextInt(100);
            positionList.add(new Position(x,y));
        }
        sort(positionList);
        int y = 0;
        for(Position p:positionList){
            assertTrue(p.getY() >= y);
            y = p.getY();
        }
    }
}
