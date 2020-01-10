import org.junit.*;
import GameModule.*;

public class TestReplay {
    @Test(expected = ExceptionInInitializerError.class)
    public void testLoadRep() throws Exception {
        try {
            LoadRep loadRep = new LoadRep(null, null,null);
        }
        catch(RuntimeException e) {
            ;
        }
    }
}
