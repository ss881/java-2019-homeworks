public class MonsterScorpion extends Creature{
    
    final int the_num_of_monstersaltfish=15;// the num of monstersaltfish 
                                            //here we set this as 15
    StandLaw military_for_monster;//military book for monster
    private int size_of_space_for_battle=-1;// to store the size of battle space
    private Direction direction_of_attack=Direction.UNSURE;// to store the direction of attack
    MonsterSaltFish []monsterSaltFishs=new MonsterSaltFish[the_num_of_monstersaltfish];// to store 15 saltfish

    MonsterScorpion()
    {
        born(Identity.MonsterScorpion);
    } 
    void summon_monstersaltfish()//give birth to monstersaltfish
    {
        for(int i=0;i<the_num_of_monstersaltfish;++i)
        {
            monsterSaltFishs[i]=new MonsterSaltFish();
        }
    }

    private void make_orders(MonsterSaltFish saltfish,int x,int y)//order saltfish to goto some place
    {
        saltfish.follow_orders(get_id(), x,y);
    }

    private void make_orders(MonsterSaltFish saltFish)// order saltfish to exchange with oneself
    {
        saltFish.follow_orders(get_id(), this);
    }

    void decide_direction_of_attack(Direction direction)// set the direction of attack
    {
        direction_of_attack=direction;
    }
    void measure_space_for_battle(SpaceForBattle space)// get the size of space
    {
        size_of_space_for_battle=space.get_size();
    }

    boolean decide_self_coordinate(SpaceForBattle space,int initial_x,int initial_y)// decide one's coordinate
    {
        int tmp_x=get_current_x();
        int tmp_y=get_current_y();
        if(space.read_space(initial_x, initial_y)==0)
        {
            rush_to_somespace(initial_x, initial_y);
            space.change_coordinate(get_id(),tmp_x,initial_x,tmp_y, initial_y);
            return true;
        }
        else if(space.check_space(Identity.MonsterSaltFish, initial_x, initial_y))
        {
           
            int point_to_monstersaltfish=0;
            for(;point_to_monstersaltfish<the_num_of_monstersaltfish;++point_to_monstersaltfish)
            {
                if(monsterSaltFishs[point_to_monstersaltfish].get_current_x()==initial_x
                &&monsterSaltFishs[point_to_monstersaltfish].current_space_y==initial_y)
                break;
            }
            make_orders(monsterSaltFishs[point_to_monstersaltfish]);
            space.change_coordinate(get_id(), tmp_x, initial_x, tmp_y, initial_y);
            return true;
        }
        return false;
         
    }
    void study_military_for_monster()// initialize military book by known the size the num and the direction
    {
        military_for_monster=new StandLaw(the_num_of_monstersaltfish+1,direction_of_attack,size_of_space_for_battle);
    }
    void provoke()// provoke in monsterscorpion version
    {
        System.out.println("I'm the lord of the world. no matter human or monster can order me!");
    }

    boolean choose_stand_type(StandType type)// choose one stand type
    {
        boolean flag_of_success=false;
        int start_coordinate_x=get_current_x();
        int start_coordinate_y=get_current_y();
        switch(type)
        {
            case WingsShape:flag_of_success=military_for_monster.wings_shape(start_coordinate_x, start_coordinate_y);break;
            case FishShape:flag_of_success=military_for_monster.fish_shape(start_coordinate_x, start_coordinate_y);break;
            case YokeShape:flag_of_success=military_for_monster.yoke_shape(start_coordinate_x, start_coordinate_y);break;
            case GooseShape:flag_of_success=military_for_monster.goose_shape(start_coordinate_x, start_coordinate_y);break;
            case SquareShape:flag_of_success=military_for_monster.square_shape(start_coordinate_x, start_coordinate_y);break;
            case ArrowShape:flag_of_success=military_for_monster.arrow_shape(start_coordinate_x, start_coordinate_y);break;
            case MoonShape:flag_of_success=military_for_monster.moon_shape(start_coordinate_x, start_coordinate_y);break;
            default:break;
        }
        
        return flag_of_success;
    }
    void embattle_for_battle(SpaceForBattle space)// embattle according to the millitary book
    {
        int point_to_monstersaltfish=0;
        for(int i=0;i<size_of_space_for_battle;++i)
            for(int j=0;j<size_of_space_for_battle;++j)
            {
                if(military_for_monster.image_space.read_space(i, j)!=0&&!(i==get_current_x()&&j==get_current_y()))
                {
                    make_orders(monsterSaltFishs[point_to_monstersaltfish], i, j);
                    space.rush_to_coordinate(monsterSaltFishs[point_to_monstersaltfish].get_id(), i, j);
                    ++point_to_monstersaltfish;
                }   
            }
    }

    void change_type_for_battle(SpaceForBattle space)// change stand type according to the millitary book
    {
        for(int point_to_monstersaltfish=0;point_to_monstersaltfish<the_num_of_monstersaltfish ; ++point_to_monstersaltfish)
        { 
            int tmp_x=monsterSaltFishs[point_to_monstersaltfish].get_current_x();
            int tmp_y=monsterSaltFishs[point_to_monstersaltfish].get_current_y();
            if(military_for_monster.image_space.read_space(tmp_x,tmp_y)!=0);
            else
            for(int i=0;i<size_of_space_for_battle;++i)
                for(int j=0;j<size_of_space_for_battle;++j)
                {
                    if(military_for_monster.image_space.read_space(i, j)!=0&&space.read_space(i, j)==0)
                    //if current saltfish has stayed in the new stand type 
                    //in the other word, current saltfish's last coordinate appeals in the new stand type, he needn't change his
                    //coordinate in this case. if not , he will choose the new blank coordinate in new stand type 
                    {
                        make_orders(monsterSaltFishs[point_to_monstersaltfish], i, j);
                        space.change_coordinate(monsterSaltFishs[point_to_monstersaltfish].get_id(), tmp_x,i,tmp_y, j);
                        i=size_of_space_for_battle;
                        break;
                    }   
                }
        }
    }


    void follow_orders(Identity id,int x,int y)// refuse to follow orders by anyone else
    {
        provoke();
    }



}