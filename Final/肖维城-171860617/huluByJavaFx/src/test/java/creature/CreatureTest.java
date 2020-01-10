package creature;

import org.junit.Test;

public class CreatureTest {

    @Test
    public void HuluBrosTest(){
        HuluBros hb = new HuluBros();
        hb.print();
    }

    @Test
    public void MonstersTest(){
        Monsters ms = new Monsters(8);
        ms.print();
    }
}
