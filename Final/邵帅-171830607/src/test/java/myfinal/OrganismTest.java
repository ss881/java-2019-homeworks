package myfinal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import myfinal.*;
/**
 * Unit test for simple App.
 */
public class OrganismTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testOrganism()
    {
        Organism rabbit = new Organism(new MyMap(1,1),"兔子");
        assertEquals(rabbit.name,"兔子");
        //assertTrue( true );
    }
}
