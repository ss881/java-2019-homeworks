package Battle; 

import Creature.CalabashBros;
import Creature.Grandpa;
import Creature.Scorpion;
import Creature.Snake;
import Formation.Formation;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* Battle Tester. 
* 
* @author <Authors name> 
* @since <pre>12ÔÂ 25, 2019</pre> 
* @version 1.0 
*/ 
public class BattleTest { 

@Before
public void before() throws Exception {
    System.out.println("Battle Test Start");
} 

@After
public void after() throws Exception {
    System.out.println("Battle Test Done");
} 

/** 
* 
* Method: setPosition(Position<?> p, int x, int y) 
* 
*/ 
@Test
public void testSetPosition() throws Exception { 
//TODO: Test goes here...

} 

/** 
* 
* Method: getPosition(int x, int y) 
* 
*/ 
@Test
public void testGetPosition() throws Exception { 
//TODO: Test goes here...
    Formation.init();
    Object lock = Fight.getLock();
    CalabashBros calabashBros = new CalabashBros(lock);
    Grandpa grandpa = new Grandpa(lock);
    Snake snake = new Snake(lock);
    calabashBros.queue();
    Scorpion scorpion = new Scorpion(lock);
    scorpion.init();
    for (int i = 0; i < Battle.getM(); i++){
        for (int j = 0; j < Battle.getN(); j++)
            if (Battle.getPosition(i, j) != null)
                Battle.getPosition(i, j).getHolder().print();
            else
                System.out.print('x');
        System.out.print('\n');
    }
} 

/** 
* 
* Method: getPositionByInstance(Creature creature) 
* 
*/ 
@Test
public void testGetPositionByInstance() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: battle() 
* 
*/ 
@Test
public void testBattle() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getNextPosition(Position<?> position) 
* 
*/ 
@Test
public void testGetNextPosition() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getN() 
* 
*/ 
@Test
public void testGetN() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getM() 
* 
*/ 
@Test
public void testGetM() throws Exception { 
//TODO: Test goes here... 
} 


} 
