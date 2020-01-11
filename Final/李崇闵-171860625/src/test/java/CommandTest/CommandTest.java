package CommandTest;

import Command.Command;
import org.junit.Test;

import java.io.FileNotFoundException;

public class CommandTest {
    @Test
    public void isEndTest() throws FileNotFoundException {
        Command command=new Command();
        command.Init();
        System.out.println(command.isEnd());
    }
}
