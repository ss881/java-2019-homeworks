package war;

import creature.HuluBros;
import creature.Monsters;
import location.Position;
import org.junit.Test;

import java.util.ArrayList;

import static war.BattleField.FIELD_HEIGHT;
import static war.BattleField.FIELD_WIDTH;

public class FieldTest {

    @Test
    public void testFormation(){
        Formation f = new Formation();
        HuluBros hb = new HuluBros();
        Monsters ms = new Monsters(f.mCount);

        hb.print();
        ms.print();

        ArrayList<Position> posList = new ArrayList<Position>();

        for(int i=0; i<FIELD_HEIGHT; i++){
            for(int j=0; j<FIELD_WIDTH; j++){
                posList.add(new Position(i,j));
            }
        }
        f.setFormation(hb,posList);
        f.setFormation(ms,posList);

        System.out.println("after formation");

        hb.print();
        ms.print();
    }
}
