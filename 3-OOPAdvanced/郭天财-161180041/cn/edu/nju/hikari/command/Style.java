package command;

import ground.*;

public interface Style{
    int N = Ground.sizeOfTiles;
    int Central = N/2 + 1;
    
    public void wing();
    public void goose();
    public void punch();
    public void snake();
    public void randomSnake();
    public void fishScale();
    public void square();
    public void moon();
    public void bee();
}