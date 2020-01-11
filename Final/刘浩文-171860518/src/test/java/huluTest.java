package test.java;
import main.java.sample.Creature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;

public class huluTest {
    private PrintStream ps = null;
    private ByteArrayOutputStream bs = null;

    @Before
    public void redirect() {

        bs = new ByteArrayOutputStream();
        ps = System.out;
        System.setOut(new PrintStream(bs));

    }

    @SuppressWarnings("unchecked")
    @Test
    public void Test1()
    {
        new Creature();
        Creature test=new Creature(0,0,0,'T',true,1,1,1);
        test.decideDirect(true);
        String s= "0000011111111111111122222333333333333333";
        assertEquals(s,bs.toString().trim().replace("\r",""));//windows to mac
    }
    @Test
    public void Test2()
    {
        new Creature();
        Creature test0=new Creature(0,14,0,'T',true,1,1,1);
        test0.decideDirect(true);
        String s0= "0000011111111111111122222222222222233333";
        assertEquals(s0,bs.toString().trim().replace("\r",""));//windows to mac
    }

    @Test
    public void Test3()
    {
        new Creature();
        Creature test0=new Creature(14,14,0,'T',true,1,1,1);
        test0.decideDirect(true);
        String s0= "0000000000000001111122222222222222233333";
        assertEquals(s0,bs.toString().trim().replace("\r",""));//windows to mac
    }

    @Test
    public void Test4()
    {
        new Creature();
        Creature test0=new Creature(7,7,0,'T',true,1,1,1);
        test0.decideDirect(true);
        String s0= "0000000000111111111122222222223333333333";
        assertEquals(s0,bs.toString().trim().replace("\r",""));//windows to mac
    }


    @After
    public void remove() {
        System.setOut(ps);
    }
}
