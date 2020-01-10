package  sample;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Creature implements Actions
{
    private Identity id;//this val tells the  difference between different creature
    protected int current_space_x=-1;// let the start coordinate be (-1,-1)
    protected int current_space_y=-1;
    public Object s=new Object();
    static final int time_gap=500;
    public int current_hp=100;
    public int current_speed=1;
    public int current_damage=1;
    public boolean state=true;
    public AtomicInteger s_flag=new AtomicInteger(0);
    Direction aim_direction=Direction.UNSURE;
    Creature()
    {
        id=Identity.Unkown;
    }
    Creature(Identity initial_id)
    {
        id=initial_id;
    }

    void born(Identity initail_id)// in order to simulate the born of creature
    {
        id=initail_id;
    }

    Identity get_id()// return id
    {
        return id;
    }
    void rush_to_somespace(int x,int y)// go to pointed place
    {
        current_space_x=x;
        current_space_y=y;
    }

    public int get_current_x()// report current coordinate_x
    {
        return current_space_x;
    }
    public int get_current_y()// report current coordinate_y
    {
        return current_space_y;
    }

    public void move (SpaceForBattle space,int mark)
    {
        int t=1;
        while(true)
        {
        switch(aim_direction)
        {
            case NORTH:
                if(!space.judge(current_space_x, current_space_y-t))
                {
                    move_to_empty(space, mark);
                    return;
                }
                synchronized(space.sign[current_space_x][current_space_y-t])
                {

                    if(space.read_space(current_space_x, current_space_y-t)==0)
                    {
                        space.rush_to_coordinate(this, mark, current_space_x,  current_space_y-t);
                        rush_to_somespace(current_space_x, current_space_y-t);
                        return;
                    }
                    ++t;
                }
                break;
            case SOUTH:
                if(!space.judge(current_space_x, current_space_y+t))
                {
                    move_to_empty(space, mark);
                    return;
                }
                synchronized(space.sign[current_space_x][current_space_y+t])
                {

                    if(space.read_space(current_space_x, current_space_y+t)==0)
                    {
                        space.rush_to_coordinate(this, mark, current_space_x,  current_space_y+t);
                        rush_to_somespace(current_space_x, current_space_y+t);
                        return;
                    }
                    ++t;
                }
                break;

            case WEST:
                if(!space.judge(current_space_x-t, current_space_y))
                {
                    move_to_empty(space, mark);
                    return;
                }
                synchronized(space.sign[current_space_x-t][current_space_y])
                {


                    if(space.read_space(current_space_x-t, current_space_y)==0)
                    {
                        space.rush_to_coordinate(this, mark, current_space_x-t,  current_space_y);
                        rush_to_somespace(current_space_x-t, current_space_y);
                        return;
                    }
                    ++t;
                }
                break;
            case EAST:
                if(!space.judge(current_space_x+t, current_space_y))
                {
                    move_to_empty(space, mark);
                    return;
                }
                synchronized(space.sign[current_space_x+t][current_space_y])
                {


                    if(space.read_space(current_space_x+t, current_space_y)==0)
                    {
                        space.rush_to_coordinate(this, mark, current_space_x+t,  current_space_y);
                        rush_to_somespace(current_space_x+t, current_space_y);
                        return;
                    }
                    ++t;
                }
                break;
            default: move_to_empty(space, mark); return;
        }
        }
    }

    private void move_to_empty(SpaceForBattle space,int mark)
    {

        int []x=new int [2];
        int []y=new int [2];
        x[0]=current_space_x;
        y[0]=current_space_y;
        while(true)
        {
            space.find_empty(x, y);
            synchronized(space.sign[x[1]][y[1]])
            {
                if(space.read_space(x[1], y[1])==0)
                {
                    space.rush_to_coordinate(this, mark, x[1], y[1]);
                    rush_to_somespace(x[1], y[1]);
                    return;
                }
            }
        }
    }




    static public void find_direction(Creature []list,Creature current,int num,SpaceForBattle space) {
        Creature tmp = null;
        int distance = -1;


        for (int i = 0; i < num; ++i) {
            if (list[i].state && list[i].monster() != current.monster()) {
                int distance1 = SpaceForBattle.compute_distance(list[i].get_current_x(), list[i].current_space_y,
                        current.current_space_x, current.current_space_y);
                if (distance == -1 || distance > distance1) {
                    tmp = list[i];
                    distance = distance1;
                }
            }
        }
        current.aim_direction = Direction.UNSURE;
        if (distance == -1)
            return;
        int x = current.current_space_x;
        int y = current.current_space_y;
        int x1 = tmp.get_current_x();
        int y1 = tmp.current_space_y;


        Random random = new Random();
        if(random.nextInt(10)<1)
           {
               current.aim_direction = Direction.WEST;
               return;
           }




        if (random.nextInt(2) < 1) {
            if (x > x1) {
                if (space.read_space(x - 1, y) <= 0) {
                    current.aim_direction = Direction.WEST;
                    return;
                }
            } else if (x < x1) {
                if (space.read_space(x + 1, y) <= 0) {
                    current.aim_direction = Direction.EAST;
                    return;
                }
            }

            if (y > y1) {
                if (space.read_space(x, y - 1) <= 0) {
                    current.aim_direction = Direction.NORTH;
                    return;
                }
            } else if (y < y1) {
                if (space.read_space(x, y + 1) <= 0) {
                    current.aim_direction = Direction.SOUTH;
                    return;
                }
            }
        }
        else {
            if (y > y1) {
                if (space.read_space(x, y - 1) <= 0) {
                    current.aim_direction = Direction.NORTH;
                    return;
                }
            } else if (y < y1) {
                if (space.read_space(x, y + 1) <= 0) {
                    current.aim_direction = Direction.SOUTH;
                    return;
                }
            }
            if (x > x1) {
                if (space.read_space(x - 1, y) <= 0) {
                    current.aim_direction = Direction.WEST;
                    return;
                }
            } else if (x < x1) {
                if (space.read_space(x + 1, y) <= 0) {
                    current.aim_direction = Direction.EAST;
                    return;
                }
            }


        }
    }







    public void find_enemy(SpaceForBattle space,int mark)
    {
        synchronized(s)
        {
            if(!state)
                return;
            move(space, mark);
        }
    }
    public void do_battles(SpaceForBattle space,Creature []list,int num)
    {
        while(true)
        synchronized(s)
        {
            if(!state)
                return;
            for(int i=0;i<num;++i)
            {
                if(state)
                {
                    if(list[i].monster()!=this.monster()
                            &&list[i].state&&SpaceForBattle.compute_distance(current_space_x,current_space_y,list[i].get_current_x(),list[i].get_current_y())==1)
                    {
                        synchronized(list[i].s)
                        {
                            battle(list[i], this,space);
                        }
                        if(!this.state)
                        {
                            s_flag.set(0);
                            return;
                        }
                    }
                }
            }
            return;


        }
    }




    boolean monster()
    {
        if(id==Identity.MonsterSaltFish||id==Identity.MonsterScorpion||id==Identity.MonsterSnake)
            return true;
        return false;
    }

    static boolean monster(int mark)
    {
        if(mark==3||mark==4||mark==5)
            return true;
        return false;
    }

    public void battle(Creature a,Creature b,SpaceForBattle space)
    {
        if(SpaceForBattle.compute_distance(a.current_space_x,a.current_space_y,b.current_space_x,b.current_space_y)>1)
            return;
        while(a.current_hp>0&&b.current_hp>0)
        {
            Random random=new Random();
            int turns=0;
            if(random.nextInt(a.current_damage+b.current_damage)<a.current_damage)
                turns=1;
            if(turns==1)
            {
                b.current_hp-=a.current_damage;
                if(b.current_hp<=0)
                {
                    b.dead();
                    space.set_space(-1*space.read_space(b.get_current_x(), b.get_current_y()), b.get_current_x(), b.get_current_y());

                }
                else b.get_current_damage();
            }
            else
            {
                a.current_hp-=b.current_damage;
                if(a.current_hp<=0)
                {
                    a.dead();
                    space.set_space(-1*space.read_space(a.get_current_x(), a.get_current_y()), a.get_current_x(), a.get_current_y());
                }
                else a.get_current_damage();
            }


        }
    }

    @Override
    public void dead() {
        state=false;
    }

    @Override
    public void get_current_damage() {

    }
}