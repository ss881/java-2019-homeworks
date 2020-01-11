package  sample;
public class Grandpa extends Creature{

   static public final int the_num_of_brotherstandl=7;// the num of brother standl
    StandLaw military_for_standl;// military book for standl
    private int size_of_space_for_battle=-1;// to store the size of battle space
    private Direction direction_of_attack=Direction.UNSURE;// to store the direction of attack
    private int start_coordinate_x=-1;// leader's coordinate
    private int start_coordinate_y=-1;
    public BrotherStandl []brotherstandls=new BrotherStandl[the_num_of_brotherstandl];// to store 7 brotherstandls
    public Grandpa()
    {
        born(Identity.Grandpa);
        buffer=1;
        current_damage=2;
    }
    public static int buffer=1;
    public void plant_standl()// give birth to brother standls
    {
        for(int i=1;i<=the_num_of_brotherstandl;++i)
        {
            brotherstandls[i-1]=new BrotherStandl(i, i);
        }
    }
    public void study_military_for_standl()// initialize military book by known the size the num and the direction
    {
        military_for_standl=new StandLaw(the_num_of_brotherstandl,direction_of_attack,size_of_space_for_battle);
    }

    public void provoke()// provoke in grandpa version
    {
        System.out.println("I'm the grandpa of these brotherstandls, no one can drive me !");
    }

   private void make_orders(BrotherStandl brotherStandl,int x,int y)// order brotherstandl to goto pointed coordinate
    {
        brotherStandl.follow_orders(get_id(), x,y);
    }
    
    void make_orders(BrotherStandl a,BrotherStandl b)// order two brotherstandls to exchange their space
    {
        a.follow_orders(get_id(), b);
    }

    public void follow_orders(Identity id, int x, int y)// refuse to follow orders by someone else
    {
        provoke();
    }

    public void decide_direction_of_attack(Direction direction) // set the direction of attack
    {
        direction_of_attack=direction;
    }
    public void measure_space_for_battle(SpaceForBattle space)// get the size of space
    {
        size_of_space_for_battle=space.get_size();
    }

    public void decide_start_coordinate(int initial_x, int initial_y)// decide the leader's coordinate
    {
        start_coordinate_x=initial_x;
        start_coordinate_y=initial_y;
    }
    public boolean choose_stand_type(StandType type)// choose one stand type
    {
        boolean flag_of_success=false;
        switch(type)
        {
            case SnakeShape:flag_of_success=military_for_standl.snake_shape(start_coordinate_x, start_coordinate_y);break;
            case WingsShape:flag_of_success=military_for_standl.wings_shape(start_coordinate_x, start_coordinate_y);break;
            case FishShape:flag_of_success=military_for_standl.fish_shape(start_coordinate_x, start_coordinate_y);break;
            case YokeShape:flag_of_success=military_for_standl.yoke_shape(start_coordinate_x, start_coordinate_y);break;
            case GooseShape:flag_of_success=military_for_standl.goose_shape(start_coordinate_x, start_coordinate_y);break;
            case SquareShape:flag_of_success=military_for_standl.square_shape(start_coordinate_x, start_coordinate_y);break;
            case ArrowShape:flag_of_success=military_for_standl.arrow_shape(start_coordinate_x, start_coordinate_y);break;
            case MoonShape:flag_of_success=military_for_standl.moon_shape(start_coordinate_x, start_coordinate_y);break;
            default:break;
        }
        return flag_of_success;
    }

    void cheer_for_battle()// cheer in grandpa version
    {
        System.out.println("Grandpa:My kids, don't be afraid, glory belongs to us!");
    }

    public void embattle_for_battle(SpaceForBattle space)// embattle according to the millitary book
    {
        int current_brother_standl_rank=1;
        for(int i=0;i<size_of_space_for_battle;++i)
            for(int j=0;j<size_of_space_for_battle;++j)
            {
                if(military_for_standl.image_space.read_space(i, j)!=0)
                for(int k=0;k<the_num_of_brotherstandl;++k)
                {
                    if(brotherstandls[k].report_rank()==current_brother_standl_rank)
                    {
                        make_orders(brotherstandls[k], i, j);
                        space.rush_to_coordinate(brotherstandls[k], i, j);
                        ++current_brother_standl_rank;
                        break;
                    }
                }
            }
    }

    public void choose_stand_place(SpaceForBattle space)// find a safe space
    {

        for(int k=0;k<the_num_of_brotherstandl;++k)
        {
            if(space.read_space(brotherstandls[k].get_current_x()-1,brotherstandls[k].get_current_y())==0)
            {
                rush_to_somespace(brotherstandls[k].get_current_x()-1,brotherstandls[k].get_current_y());
                space.rush_to_coordinate(get_id(),brotherstandls[k].get_current_x()-1,brotherstandls[k].get_current_y());
                return;
            }
            else if(space.read_space(brotherstandls[k].get_current_x(),brotherstandls[k].get_current_y()+1)==0)
            {
                rush_to_somespace(brotherstandls[k].get_current_x(),brotherstandls[k].get_current_y()+1);
                space.rush_to_coordinate(get_id(),brotherstandls[k].get_current_x(),brotherstandls[k].get_current_y()+1);
                return;
            }
            else if(space.read_space(brotherstandls[k].get_current_x()+1,brotherstandls[k].get_current_y())==0)
            {
                rush_to_somespace(brotherstandls[k].get_current_x()+1,brotherstandls[k].get_current_y());
                space.rush_to_coordinate(get_id(),brotherstandls[k].get_current_x()+1,brotherstandls[k].get_current_y());
                return;
            }
            else if(space.read_space(brotherstandls[k].get_current_x(),brotherstandls[k].get_current_y()-1)==0)
            {
                rush_to_somespace(brotherstandls[k].get_current_x(),brotherstandls[k].get_current_y()-1);
                space.rush_to_coordinate(get_id(),brotherstandls[k].get_current_x(),brotherstandls[k].get_current_y()-1);
                return;
            }
        }

        for(int i=0;i<size_of_space_for_battle;++i)
            for(int j=0;j<size_of_space_for_battle;++j)
                if(space.read_space(i, j)==0)
                {
                    rush_to_somespace(i, j);
                    space.rush_to_coordinate(get_id(),i, j);
                    return;
                }
    }

    @Override
    public void dead() {
        state=false;
        buffer=0;
    }
};