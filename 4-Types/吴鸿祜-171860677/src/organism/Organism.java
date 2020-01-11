package organism;
import position.Position;

import javax.print.attribute.standard.MediaSize;

public class Organism {
    protected String name;
    protected Position position;

    public Organism(){this.position=new Position(-1,-1);}

    public Organism(int x,int y){this.position=new Position(x,y);}

    public Organism(Organism temp)
    {
        name=temp.name;
        setPosition(new Position(temp.position.getX(),temp.position.getY()));
    }

    public String getName(){return this.name;}

    public Position getPosition(){return this.position;}

    public void setPosition(int x,int y)
    {
        this.position=new Position(x,y);
    }

    public void setPosition(Position position)
    {
        this.position=position;
    }
}
