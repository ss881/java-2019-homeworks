public class MonsterSnake extends Creature{

    MonsterSnake()
    {
        born(Identity.MonsterSnake);
    }

    void provoke()//provoke in monstersnake version
    {
        System.out.println("As a lady, I will never join to battle!");
    }
    void cheer_for_battle()//cheer in monstersnake version
    {
        System.out.println("MonsterSnake:Listen MonsterSaltFish! GO to battle or kill by me!");
    }

 
    void follow_orders(Identity id,int x,int y)// refuse to follow orders by anyone else
    {
        provoke();
    }

    void follow_orders(Identity id,Creature creature)// refuse to follow orders by anyone else
    {
        provoke();
    }

    void choose_stand_place(SpaceForBattle space)// find a safe space
    {
        for(int i=space.get_size()-1;i>=0;--i)
            for(int j=space.get_size()-1;j>=0;--j)
                if(space.read_space(i, j)==0&&(space.check_space(Identity.MonsterSaltFish,i+1,j)||
                space.check_space(Identity.MonsterSaltFish,i,j+1)||space.check_space(Identity.MonsterSaltFish,i,j-1)||
                space.check_space(Identity.MonsterSaltFish,i-1,j)))
                {
                    rush_to_somespace(i, j);
                    space.rush_to_coordinate(get_id(),i, j);
                    return;
                }
        for(int i=0;i<space.get_size();++i)
            for(int j=0;j<space.get_size();++j)
                if(space.read_space(i, j)==0)
                {
                    rush_to_somespace(i, j);
                    space.rush_to_coordinate(get_id(),i, j);
                    return;
                }
    }

    void leave_for_some_time(SpaceForBattle space)//leave when monsterscorpion decide to change stand type 
                                                  //in order to make room for choosing different stand type
    {
        int tmp_x=get_current_x();
        int tmp_y=get_current_y();
        rush_to_somespace(-1, -1);
        space.set_space(0,tmp_x,tmp_y);
    }
}