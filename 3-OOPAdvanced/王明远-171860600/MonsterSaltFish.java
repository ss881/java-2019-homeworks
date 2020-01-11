public class MonsterSaltFish extends Creature{

    MonsterSaltFish()
    {
        born(Identity.MonsterSaltFish);
    }

    void provoke() //provoke in monstersaltfish version
    {
        System.out.println("My delicious food! Please fool me in my stomach!");
    }

 
    void follow_orders(Identity id,int x,int y)// follow orther's order to goto someplace
    {
        if(id==Identity.MonsterScorpion)// follow monsterscorpion's order
        {
            rush_to_somespace(x, y);
        }
        else if(id==Identity.MonsterSaltFish||id==Identity.MonsterSaltFish);// no response to saltfish or snake's order
        else// provoke to enemy's order
        provoke();
    }

    void follow_orders(Identity id,MonsterScorpion boss)// exchange space with boss
    {
        if(id==Identity.MonsterScorpion)
        {
            int tmp_x=boss.get_current_x();
            int tmp_y=boss.get_current_y();
            boss.rush_to_somespace(get_current_x(), get_current_y());
            rush_to_somespace(tmp_x, tmp_y);
        }
        else if(id==Identity.MonsterSaltFish||id==Identity.MonsterSaltFish);
        else
        provoke();
    }

}