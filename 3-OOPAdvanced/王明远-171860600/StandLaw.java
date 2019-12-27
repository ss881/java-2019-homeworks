public class StandLaw{

    final int chess = 10;// use chess to simulate the stand type
    public class Coordinate{// use this class to represent different stand type easily
        int x;
        int y;
        Coordinate()
        {
            x=y=-1;
        }
        Coordinate(int initial_x,int initial_y)
        {
            x=initial_x;
            y=initial_y;
        }
        Coordinate(Coordinate initial_coordinate)
        {
            x=initial_coordinate.x;
            y=initial_coordinate.y;
        }

    }
    int num_of_creature;// to store the num of creature in the stand type
    Direction direction_of_attack;// attack direction
    SpaceForBattle image_space;// space on  page
    StandLaw(int initial_num,Direction initial_direction_of_attack,int initial_size_of_space)
    {
        image_space=new SpaceForBattle(initial_size_of_space);
        num_of_creature=initial_num;
        direction_of_attack=initial_direction_of_attack;
    }
    private Coordinate change_coordinate_by_direction(Coordinate coordinate,Direction direction)
    //change coordinate according to direction
    {
        Coordinate tmp=new Coordinate(coordinate);
        switch(direction)
        {
            case EAST:break;
            case WEST:tmp.x=image_space.get_size()-1-tmp.x;break;
            case SOUTH:{int tmp_x=tmp.x;tmp.x=tmp.y;tmp.y=tmp_x;}break;
            case NORTH:{int tmp_x=tmp.x;tmp.x=tmp.y;tmp.y=tmp_x;tmp.y=image_space.get_size()-1-tmp.y;}break;
            default:break;
        }
        return tmp;
    }
    private Coordinate standard_coordinate_by_direction(Coordinate coordinate,Direction direction)
    // get standard primary coordinate according to direction
    {
        Coordinate tmp=new Coordinate(coordinate);
        switch(direction)
        {
            case EAST:break;
            case WEST:tmp.x=image_space.get_size()-1-tmp.x;break;
            case SOUTH:{int tmp_x=tmp.x;tmp.x=tmp.y;tmp.y=tmp_x;}break;
            case NORTH:{int tmp_x=tmp.x;tmp.x=tmp.y;tmp.y=tmp_x;tmp.x=image_space.get_size()-1-tmp.x;}break;
            default:break;
        }
        return tmp;
    }
    private void change_stand_law_by_direction(SpaceForBattle tmp)
    // change the whole stand type according to direction
    {
        image_space.rebulid_space();
        for(int i=0;i<image_space.get_size();++i)
            for(int j=0;j<image_space.get_size();++j)
            {
                int state=tmp.read_space(i, j);
                if(state!=0)
                {
                    Coordinate now=new Coordinate(i,j);
                    now=change_coordinate_by_direction(now, direction_of_attack);
                    image_space.set_space(state, now.x, now.y);
                }
            }
    }

    // following 8 functions are 8 stand type
    boolean snake_shape(int start_x,int start_y)
    {
        SpaceForBattle tmp =new SpaceForBattle(image_space.get_size());
        Coordinate start=new Coordinate(start_x,start_y);
        start=standard_coordinate_by_direction(start, direction_of_attack);
        if(start.y+num_of_creature-1<=image_space.get_size()-1)
        {
            for(int i=0;i<num_of_creature;++i)
                tmp.set_space(chess, start.x, start.y+i);
            change_stand_law_by_direction(tmp);
            return true;
        }
        else
            return false;
    }
    boolean wings_shape(int start_x,int start_y)
    {
        SpaceForBattle tmp =new SpaceForBattle(image_space.get_size());
        Coordinate start=new Coordinate(start_x,start_y);
        start=standard_coordinate_by_direction(start, direction_of_attack);
        if(start.x>=num_of_creature/2&&start.y>=num_of_creature/2&&start.y+(num_of_creature-1)/2<=image_space.get_size()-1)
        {
            for(int i=0;i<num_of_creature;++i)    
            {
                if(i%2==1)
                tmp.set_space(chess, start.x-(i+1)/2, start.y-(i+1)/2);
                else
                tmp.set_space(chess, start.x-(i+1)/2, start.y+i/2);
            }
            change_stand_law_by_direction(tmp);
            return true;
        }
        else
            return false;
    }
    boolean goose_shape(int start_x,int start_y)
    {
        SpaceForBattle tmp =new SpaceForBattle(image_space.get_size());
        Coordinate start=new Coordinate(start_x,start_y);
        start=standard_coordinate_by_direction(start, direction_of_attack);
        if(start.x>=num_of_creature-1&&start.y+num_of_creature-1<=image_space.get_size()-1)
        {
            for(int i=0;i<num_of_creature;++i)    
            {
                tmp.set_space(chess, start.x-i, start.y+i);
            }
            change_stand_law_by_direction(tmp);
            return true;
        }
        else
            return false;
    }

    boolean yoke_shape(int start_x,int start_y)
    {
        SpaceForBattle tmp =new SpaceForBattle(image_space.get_size());
        Coordinate start=new Coordinate(start_x,start_y);
        start=standard_coordinate_by_direction(start, direction_of_attack);
        if(start.x<=image_space.get_size()-2&&start.y+num_of_creature-1<=image_space.get_size()-1)
        {
            for(int i=0;i<num_of_creature;++i)    
            {
                if(i%2==1)
                tmp.set_space(chess, start.x-1, start.y+i);
                else
                tmp.set_space(chess, start.x, start.y+i);
            }
            change_stand_law_by_direction(tmp);
            return true;
        }
        else
            return false;
    }

    boolean fish_shape(int start_x,int start_y)
    {
        SpaceForBattle tmp =new SpaceForBattle(image_space.get_size());
        Coordinate start=new Coordinate(start_x,start_y);
        start=standard_coordinate_by_direction(start, direction_of_attack);
        int thickness=0;
        for(;thickness*thickness<num_of_creature-1;++thickness);
        if(start.x+thickness<=image_space.get_size()-1&&start.y+thickness-1<=image_space.get_size()-1&&start.y-thickness+1>=0)
        {
            for(int i=0;i<thickness-1;++i)    
                for(int j=-1*i;j<=i;++j)
                {
                    tmp.set_space(chess, start.x+thickness-i, start.y+j);
                }
               
            int gap_of_num=num_of_creature-1-(thickness-1)*(thickness-1);
            for(int flag=1;gap_of_num>0;--gap_of_num)
            {
                tmp.set_space(chess, start.x+1, start.y+flag*(thickness-1));
                flag*=-1;
                if(flag==1)
                    thickness--;
            }
            tmp.set_space(chess, start.x, start.y);
           
            change_stand_law_by_direction(tmp);
            return true;
        }
        else
            return false;
    }
    boolean square_shape(int start_x,int start_y)
    {
        SpaceForBattle tmp =new SpaceForBattle(image_space.get_size());
        Coordinate start=new Coordinate(start_x,start_y);
        start=standard_coordinate_by_direction(start, direction_of_attack);
        int width=(num_of_creature+1)/4;
        int length=num_of_creature/2;
        if(start.x-length>=0&&start.y+width<=image_space.get_size()-1&&start.y-width>=0)
        {
            tmp.set_space(chess, start.x, start.y);
            int flag=1;
            int tmp_num_of_creature=num_of_creature;
            if(num_of_creature%2==0)
            {   
                tmp.set_space(chess, start.x-length, start.y);
                length--;
            }
            else tmp_num_of_creature++;
            if(tmp_num_of_creature%4==0)
                flag=0;
            int tmp_row=1;
            for(int i=1;i<=length;++i)
            {
                tmp.set_space(chess, start.x-i, start.y+tmp_row);
                tmp.set_space(chess, start.x-i, start.y-tmp_row);
                if(flag!=-1)
                {
                    tmp_row++;
                }
                else tmp_row--;
                if(tmp_row==width+1)
                {
                    if(flag==1)
                        tmp_row--;
                    else tmp_row-=2;
                    flag=-1;
                }
            }
            change_stand_law_by_direction(tmp);
            return true;
        }
        else
            return false;
    }
    boolean arrow_shape(int start_x,int start_y)
    {
        SpaceForBattle tmp =new SpaceForBattle(image_space.get_size());
        Coordinate start=new Coordinate(start_x,start_y);
        start=standard_coordinate_by_direction(start, direction_of_attack);
        if(num_of_creature<6)
        return false;
        int length=(num_of_creature-6)/3;
        int width=(num_of_creature-6)/3;
        if(num_of_creature%3==1)
        length++;
        else if(num_of_creature%3==2)
        width++;
        if(start.x-3-length>=0&&start.y+width+1<=image_space.get_size()-1&&start.y-width-1>=0)
        {
            for(int i=0;i<length+4;++i)
            {
                tmp.set_space(chess, start.x-i, start.y);
            }
            for(int i=1;i<=width+1;++i)
            {
                tmp.set_space(chess, start.x-i, start.y+i);
                tmp.set_space(chess, start.x-i, start.y-i);
            }
            change_stand_law_by_direction(tmp);
            return true;
        }
        else
            return false;
    }
    boolean moon_shape(int start_x,int start_y)
    {
        SpaceForBattle tmp =new SpaceForBattle(image_space.get_size());
        Coordinate start=new Coordinate(start_x,start_y);
        start=standard_coordinate_by_direction(start, direction_of_attack);
        if(num_of_creature<5)
        return false;
        int length=1+(num_of_creature-5+6)/7;
        int width_up=(num_of_creature-5+6)/7;
        int width_down=(num_of_creature-5+5)/7;
        if(start.x-length>=0&&start.y+width_down+2<=image_space.get_size()-1&&start.y-width_up-2>=0)
        {
            tmp.set_space(chess, start.x, start_y);
            tmp.set_space(chess, start.x, start_y+1);
            tmp.set_space(chess, start.x, start_y-1);
            tmp.set_space(chess, start.x-1, start_y+2);
            tmp.set_space(chess, start.x-1, start_y-2);
            int current_width=3;
            int current_length=2;
            int flag=0;
            for(int i=0;i<num_of_creature-5;++i)
            {
                switch(flag)
                { 
                    case 0:tmp.set_space(chess, start.x-current_length, start_y+current_width);break;
                    case 1:tmp.set_space(chess, start.x-current_length, start_y-current_width);break;
                    case 2:tmp.set_space(chess, start.x-current_length, start_y+current_width-1);break;
                    case 3:tmp.set_space(chess, start.x-current_length, start_y-current_width+1);break;
                    case 4:tmp.set_space(chess, start.x-current_length+1, start_y+1);break;
                    case 5:tmp.set_space(chess, start.x-current_length+1, start_y-1);break;
                    case 6:tmp.set_space(chess, start.x-current_length+1, start_y);current_width++;current_length++;break;
                }
                flag=(flag+1)%7;
            }
            change_stand_law_by_direction(tmp);
            return true;
        }
        else
            return false;
    }
}