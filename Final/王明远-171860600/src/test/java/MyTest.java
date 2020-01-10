import org.testng.Reporter;
import org.testng.annotations.Test;
import sample.*;

import java.util.ArrayList;

public class MyTest{

    ArrayList<Creature>creatures;
  int size_of_current_space= 15;

    public  boolean test() {

        int times=10;
        while (times>0) {
            SpaceForBattle space = new SpaceForBattle(size_of_current_space);
            MonsterScorpion monsterScorpion = new MonsterScorpion();
            MonsterSnake monsterSnake = new MonsterSnake();
            Grandpa grandpa = new Grandpa();
            monsterScorpion.summon_monstersaltfish();// in present circumstances, we suppose that monsterscorpion summon 15 saltfish
            grandpa.plant_standl();
            monsterScorpion.measure_space_for_battle(space);
            monsterScorpion.decide_direction_of_attack(Direction.WEST);
            monsterScorpion.study_military_for_monster();
            grandpa.measure_space_for_battle(space);
            grandpa.decide_direction_of_attack(Direction.EAST);
            grandpa.study_military_for_standl();
            grandpa.decide_start_coordinate(1, 3);
            grandpa.choose_stand_type(StandType.SnakeShape);
            monsterScorpion.decide_self_coordinate(space, 8, 6);
            monsterScorpion.choose_stand_type(StandType.MoonShape);
            grandpa.embattle_for_battle(space);
            monsterScorpion.embattle_for_battle(space);
            grandpa.choose_stand_place(space);
            monsterSnake.choose_stand_place(space);
            creatures=new ArrayList<>();
            creatures.add(grandpa);
            creatures.add(monsterSnake);
            creatures.add(monsterScorpion);
            for(int i=0;i<Grandpa.the_num_of_brotherstandl;++i)
                creatures.add(grandpa.brotherstandls[i]);
            for(int i=0;i<MonsterScorpion.the_num_of_monstersaltfish;++i)
                creatures.add(monsterScorpion.monsterSaltFishs[i]);
            int current_creature_num=creatures.size();
            Creature[]creaturelist=new  Creature[current_creature_num];
            for(int i=0;i<current_creature_num;++i)
                creaturelist[i]=creatures.get(i);
            int []mark=new int[current_creature_num];
            for(int i=0;i<current_creature_num;++i)
                mark[i]=space.read_space(creaturelist[i].get_current_x(),creaturelist[i].get_current_y());
            try {
                while (!space.isEnd()) {
                    for (int i = 0; i < current_creature_num; ++i) {
                        if (creatures.get(i).state)
                            Creature.find_direction(creaturelist, creatures.get(i), current_creature_num, space);
                    }
                    for (int i = 0; i < current_creature_num; ++i) {
                        if (creatures.get(i).state)
                            creatures.get(i).move(space, mark[i]);
                    }
                    for (int i = 0; i < current_creature_num; ++i) {
                        if (creatures.get(i).state)
                            creatures.get(i).do_battles(space, creaturelist, current_creature_num);
                    }
                }
            }
            catch (Exception e)
            {

                return false;
            }
            int tmp=11-times;
            Reporter.log(tmp+"times succ!");
            times--;
        }

        return true;
    }
    @Test
    public void dothreadtest()
    {
        boolean res=test();

        if(res==true)
        {
            System.out.println("ALL is succ");
            Reporter.log("ALL is succ");
            return;
        }
        else {
            System.out.println("wrong");
            Reporter.log("wrong");
            return;
        }

    }




}
