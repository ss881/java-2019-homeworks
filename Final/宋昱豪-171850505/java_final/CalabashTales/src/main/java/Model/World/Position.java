package Model.World;

import javafx.geometry.Pos;

public class Position {
    public int x;
    public int y;
    public Position()
    {

    }
    public Position(int a,int b)
    {
        x=a;
        y=b;
    }
    public Position(Position t)
    {
        x=t.x;
        y=t.y;
    }

}

