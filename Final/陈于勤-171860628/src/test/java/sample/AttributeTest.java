package sample;

import java.util.Random;

import static org.junit.Assert.*;

public class AttributeTest {

    @org.junit.Test
    public void changeAttribute() {
        Random random = new Random(System.nanoTime());
        for (int i = 0; i < 100; i++) {
            Attribute attribute = new Attribute(random);
            attribute.changeAttribute(Attribute.HEALTH, -100);
            attribute.changeAttribute(Attribute.ATTACK, -100);
            attribute.changeAttribute(Attribute.SPEED, -100);
            attribute.changeAttribute(Attribute.INTELLECT, -100);
            attribute.changeAttribute(Attribute.CONSTITUTION, -100);

            assertEquals(0, attribute.getAttribute(Attribute.HEALTH));
            assertEquals(1, attribute.getAttribute(Attribute.ATTACK));
            assertEquals(1, attribute.getAttribute(Attribute.SPEED));
            assertEquals(1, attribute.getAttribute(Attribute.INTELLECT));
            assertEquals(1, attribute.getAttribute(Attribute.CONSTITUTION));
        }
    }
}