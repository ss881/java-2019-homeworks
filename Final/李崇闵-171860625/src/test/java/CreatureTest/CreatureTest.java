package CreatureTest;

import Command.Command;
import Creature.Creature;
import org.junit.Test;

import java.io.FileNotFoundException;

public class CreatureTest {
    @Test
    public void fightTest() throws FileNotFoundException, InterruptedException {
        Command command=new Command();
        command.Init();
        command.Start();
    }
}
