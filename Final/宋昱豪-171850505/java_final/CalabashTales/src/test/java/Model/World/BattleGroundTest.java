package Model.World;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class BattleGroundTest {

    BattleGround b;
    @Before
    public void Set() throws FileNotFoundException, UnsupportedEncodingException {
        File file=new File("D:/存档.txt");
        b=new BattleGround(file);
    }
    @Test
    public void clear() throws FileNotFoundException, UnsupportedEncodingException , IOException {
        System.out.print("clear");
        BattleGround.ground[4][5].SetALL(true,null);
        assertEquals(BattleGround.ground[4][5].GetIsOccupied(),true);
        File file=new File("D://n.txt");
        b.clear(file);
        assertEquals(BattleGround.ground[4][5].GetIsOccupied(),false);
    }

    @Test
    public void outcome() {
        String x=b.outcome(true);
        assertEquals(x,"win\t");
        String y=b.outcome(false);
        assertEquals(y,"fail\t");
    }

    @Test
    public void setend() {
        b.setend(true);
        assertEquals(b.end,true);
        b.setend(false);
        assertEquals(b.end,false);
    }
}