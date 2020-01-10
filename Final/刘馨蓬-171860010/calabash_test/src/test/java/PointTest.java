import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.assertEquals;

/** 
* Point Tester. 
* 
* @author <Authors name> 
* @since <pre>12�� 29, 2019</pre> 
* @version 1.0 
*/ 
public class PointTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getX() 
* 
*/ 
@Test
public void testGetX() throws Exception { 
//TODO: Test goes here...
    Point temp=new Point();
    assertEquals(0,temp.getX());
} 

/** 
* 
* Method: setX(int x) 
* 
*/ 
@Test
public void testSetX() throws Exception { 
//TODO: Test goes here...
    Point temp=new Point();
    temp.setX(8);
    assertEquals(8,temp.getX());
} 

/** 
* 
* Method: getY() 
* 
*/ 
@Test
public void testGetY() throws Exception { 
//TODO: Test goes here...
    Point temp=new Point();
    assertEquals(0,temp.getY());
} 

/** 
* 
* Method: setY(int y) 
* 
*/ 
@Test
public void testSetY() throws Exception { 
//TODO: Test goes here...
    Point temp=new Point();
    temp.setY(8);
    assertEquals(8,temp.getY());
} 


} 
