package Space;

import Army.Army;
import Army.Formation;
import Creatures.Creature;

import java.util.Vector;

public class SpaceFlat {
    private SpaceUnit[] space_vector;
    private int MAX_SIZE;
    public SpaceFlat(int MAX_SIZE)
    {
        this.MAX_SIZE=MAX_SIZE;
        space_vector=new SpaceUnit[MAX_SIZE*MAX_SIZE];
        for(int i=0;i<MAX_SIZE;i++)
            for(int j=0;j<MAX_SIZE;j++)
            {
                Coordinate temp_coordinate=new Coordinate(i,j);
                SpaceUnit temp_unit=new SpaceUnit(temp_coordinate);
                space_vector[i*MAX_SIZE+j]=temp_unit;
            }
    }
    public boolean isFull(Coordinate coordinate)
    {
        return space_vector[coordinate.getX()*MAX_SIZE+coordinate.getY()].isFull();
    }
    public void enterArmy(Army army)
    {
        int num;
        if(army.formation.name=="SCALE")
        {
            num=10;
        }
        else if(army.formation.name=="SQUARE")
        {
            num=8;
        }
        else num=army.members.size();
        space_vector[army.cheer_position.getX()*MAX_SIZE+army.cheer_position.getY()].containCreature(army.cheerleader);
        space_vector[army.formation.positions.get(0).getX()*MAX_SIZE+army.formation.positions.get(0).getY()].containCreature(army.members.get(0));
        for(int i=1;i<num;i++)
        {
            space_vector[army.formation.positions.get(i).getX()*MAX_SIZE+army.formation.positions.get(i).getY()].containCreature(army.members.get(i));
        }
    }
    public void clear()
    {
        for(int i=0;i<MAX_SIZE;i++)
            for(int j=0;j<MAX_SIZE;j++)
            {
                space_vector[i*MAX_SIZE+j].clear();
            }
    }
    public void printMap()
    {
        for(int i=0;i<MAX_SIZE;i++)
        {
            for (int j = 0; j < MAX_SIZE; j++) {
                space_vector[i * MAX_SIZE + j].printUnit();
            }
            System.out.println();
        }
    }
}
