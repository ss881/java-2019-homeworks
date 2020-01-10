package Formation; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

import java.util.Arrays;

import static org.junit.Assert.*;
/** 
* Formation Tester. 
* 
* @author <Authors name> 
* @since <pre>12. 13, 2019</pre>
* @version 1.0 
*/ 
public class FormationTest { 

@Before
public void before() throws Exception {
    System.out.println("Ready to test reading formation config file");
} 

@After
public void after() throws Exception {
    System.out.println("Test is done!");
} 

/** 
* 
* Method: init() 
* 
*/ 
@Test
public void testInit() throws Exception {
    Formation.init();
    System.out.println(Arrays.toString(Formation.getFormationStatus()));
    System.out.println(new Arrow().getFormation());
    System.out.println(new Crane().getFormation());
    System.out.println(new Fish().getFormation());
    System.out.println(new Goose().getFormation());
    System.out.println(new Moon().getFormation());
    System.out.println(new Snake().getFormation());
    System.out.println(new Square().getFormation());
    System.out.println(new Yoke().getFormation());
} 

} 
