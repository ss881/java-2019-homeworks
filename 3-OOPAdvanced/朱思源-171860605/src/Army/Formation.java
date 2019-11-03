package Army;

import java.util.Vector;
import Creatures.Creature;
import Space.Coordinate;
public class Formation
{
    public Vector<Coordinate> positions;
    public String name;
    private int Num;
    private final String[] formations={"STORK","GOOSE","BALANCE","SNAKE","SCALE","SQUARE","MOON","ARROW"};
    Formation(String name, Coordinate base_position, int num, int direction)
    {
        positions=new Vector<Coordinate>();
        positions.add(base_position);
        for(int index=0;index<8;index++)
        {
            if(name==formations[index])
            {
                this.name=formations[index];
                switch (index)
                {
                    case 0:
                    {
                        int left_num=(num-1)/2;
                        int right_num=num-left_num;
                        for(int i=0;i<left_num;i++)
                        {
                            Coordinate temp=new Coordinate(base_position.getX()-direction*(i+1),base_position.getY()-direction*(i+1));
                            positions.add(temp);
                        }
                        for(int i=0;i<right_num;i++)
                        {
                            Coordinate temp=new Coordinate(base_position.getX()+direction*(i+1),base_position.getY()-direction*(i+1));
                            positions.add(temp);
                        }
                        break;
                    }
                    case 1:
                    {
                        int num_other=num-1;
                        for(int i=0;i<num_other;i++)
                        {
                            Coordinate temp=new Coordinate(base_position.getX()-direction*(i+1),base_position.getY()-direction*(i+1));
                            positions.add(temp);
                        }
                        break;
                    }
                    case 2:
                    {
                        int behind_num=(num-1)/2;
                        int alongside_num=num-behind_num;
                        for(int i=0;i<behind_num;i++)
                        {
                            Coordinate temp=new Coordinate(base_position.getX(),base_position.getY()-direction*(i*2+2));
                            positions.add(temp);
                        }
                        for(int i=0;i<alongside_num;i++)
                        {
                            Coordinate temp=new Coordinate(base_position.getX()-direction,base_position.getY()-direction*(i*2+1));
                            positions.add(temp);
                        }
                        break;
                    }
                    case 3:
                    {
                        int behind_num=num-1;
                        for(int i=0;i<behind_num;i++)
                        {
                            Coordinate temp=new Coordinate(base_position.getX(),base_position.getY()-direction*(i+1));
                            positions.add(temp);
                        }
                        break;
                    }
                    case 4:
                    {
                        Coordinate temp_coordinate=new Coordinate(base_position.getX()-1,base_position.getY()+1);
                        positions.add(temp_coordinate);
                        Coordinate temp_coordinate1=new Coordinate(base_position.getX(),base_position.getY()+2);
                        positions.add(temp_coordinate1);
                        Coordinate temp_coordinate2=new Coordinate(base_position.getX()-2,base_position.getY()+2);
                        positions.add(temp_coordinate2);
                        Coordinate temp_coordinate3=new Coordinate(base_position.getX()+2,base_position.getY()+2);
                        positions.add(temp_coordinate3);
                        Coordinate temp_coordinate4=new Coordinate(base_position.getX()-1,base_position.getY()+3);
                        positions.add(temp_coordinate4);
                        Coordinate temp_coordinate5=new Coordinate(base_position.getX()-3,base_position.getY()+3);
                        positions.add(temp_coordinate5);
                        Coordinate temp_coordinate6=new Coordinate(base_position.getX()+1,base_position.getY()+3);
                        positions.add(temp_coordinate6);
                        Coordinate temp_coordinate7=new Coordinate(base_position.getX()+3,base_position.getY()+3);
                        positions.add(temp_coordinate7);
                        Coordinate temp_coordinate8=new Coordinate(base_position.getX(),base_position.getY()+4);
                        positions.add(temp_coordinate8);
                        break;
                    }
                    case 5:
                    {
                        Coordinate temp_coordinate1=new Coordinate(base_position.getX()-1,base_position.getY()+1);
                        positions.add(temp_coordinate1);
                        Coordinate temp_coordinate2=new Coordinate(base_position.getX()-2,base_position.getY()+2);
                        positions.add(temp_coordinate2);
                        Coordinate temp_coordinate3=new Coordinate(base_position.getX()+1,base_position.getY()+1);
                        positions.add(temp_coordinate3);
                        Coordinate temp_coordinate4=new Coordinate(base_position.getX()+2,base_position.getY()+2);
                        positions.add(temp_coordinate4);
                        Coordinate temp_coordinate5=new Coordinate(base_position.getX()-1,base_position.getY()+3);
                        positions.add(temp_coordinate5);
                        Coordinate temp_coordinate6=new Coordinate(base_position.getX()+1,base_position.getY()+3);
                        positions.add(temp_coordinate6);
                        Coordinate temp_coordinate7=new Coordinate(base_position.getX(),base_position.getY()+4);
                        positions.add(temp_coordinate7);
                        break;
                    }
                    case 6:
                    {
                        for(int i=0;i<2;i++)
                        {
                            Coordinate temp_coordinate=new Coordinate(base_position.getX(),base_position.getY()+i+1);
                            positions.add(temp_coordinate);
                        }
                        for(int i=0;i<3;i++)
                        {
                            Coordinate temp_coordinate1=new Coordinate(base_position.getX()-1,base_position.getY()+i);
                            positions.add(temp_coordinate1);
                            Coordinate temp_coordinate2=new Coordinate(base_position.getX()+1,base_position.getY()+i);
                            positions.add(temp_coordinate2);
                        }
                        for(int i=0;i<2;i++)
                        {
                            Coordinate temp_coordinate1=new Coordinate(base_position.getX()-i-2,base_position.getY()+i+1);
                            positions.add(temp_coordinate1);
                            Coordinate temp_coordinate2=new Coordinate(base_position.getX()+i+2,base_position.getY()+i+1);
                            positions.add(temp_coordinate2);
                        }
                        for(int i=0;i<3;i++)
                        {
                            Coordinate temp_coordinate1=new Coordinate(base_position.getX()-i-2,base_position.getY()+i+2);
                            positions.add(temp_coordinate1);
                            Coordinate temp_coordinate2=new Coordinate(base_position.getX()+i+2,base_position.getY()+i+2);
                            positions.add(temp_coordinate2);
                        }
                        break;
                    }
                    case 7:
                    {
                        int alongside_num=(num-1)/6;
                        int behind_num=num-2*alongside_num;
                        for(int i=0;i<alongside_num;i++)
                        {
                            Coordinate temp_coordinate1=new Coordinate(base_position.getX()-i-1,base_position.getY()+i+1);
                            positions.add(temp_coordinate1);
                            Coordinate temp_coordinate2=new Coordinate(base_position.getX()+i+1,base_position.getY()+i+1);
                            positions.add(temp_coordinate2);
                        }
                        for(int i=0;i<behind_num;i++)
                        {
                            Coordinate temp_coordinate=new Coordinate(base_position.getX(),base_position.getY()+i+1);
                            positions.add(temp_coordinate);
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
}
