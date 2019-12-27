package Army;

import Creatures.Creature;
import Space.Coordinate;

import java.util.Vector;

public class Army {
    public Creature cheerleader;
    public Coordinate cheer_position;
    public Vector<Creature> members;
    private Coordinate base_position;
    private int direction;
    public Formation formation;
    public Army(Creature cheerleader,Coordinate cheer_position, Vector<Creature> members,Coordinate base_position,int direction)
    {
        this.cheerleader=cheerleader;
        this.cheer_position=cheer_position;
        this.members=members;
        this.base_position=base_position;
        this.direction=direction;
    }
    public void arrangeFormation(String name)
    {
        formation=new Formation(name,base_position,members.size(),direction);
    }

}
