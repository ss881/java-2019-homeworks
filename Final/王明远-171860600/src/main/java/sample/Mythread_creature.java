package  sample;
import java.util.concurrent.CyclicBarrier;

public class Mythread_creature extends Thread
{
    Creature creature;
    Grandpa grandpa;
    MonsterScorpion monsterScorpion;
    MonsterSnake snake;
    Creature []list;
    CyclicBarrier cyclicBarrier;
    static Object b=new Object();
    int []flag;
    int id;
    int mark;
    SpaceForBattle space;
    @Override
    public void run()
    {

        try {
        int times=0;
        int tmp=0;
        while(flag[0]!=-1)
        {
            if(creature.current_hp<=0)
                creature.dead();
           // System.out.println(creature.current_space_x+ " "+creature.current_space_y);
            if(creature.state) {
                times++;
                if (times == creature.current_speed) {
                    times = 0;
                    creature.find_enemy(space, mark);
                   // System.out.println(creature.current_space_x+ " "+creature.current_space_y);
                }
                synchronized (b)
                {
                    creature.do_battles(space,list,Mythread_viewer.current_creature_num);
                }
            }
            cyclicBarrier.await();
            sleep(SpaceForBattle.time_gap);
            while(flag[1]==tmp)
            {
                sleep(100);
            }
            tmp++;
        }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void get_set(CyclicBarrier cyc,Creature []l,Creature c,int []f,SpaceForBattle s)
    {
        cyclicBarrier=cyc;
        list=l;
        creature=c;
        mark=s.read_space(c.get_current_x(),c.get_current_y());
        //System.out.print(c.current_space_x);
        //System.out.println(c.current_space_y);
        id=Mythread_viewer.return_num(c);
       // System.out.println(id);
        flag=f;
        space=s;
    }
    public void get_set(CyclicBarrier cyc,Creature []l,int m,int []f,SpaceForBattle s)
    {
        cyclicBarrier=cyc;
        list=l;
        flag=f;
        if(m==1)
        {
            grandpa =new Grandpa();
            space=s;
            mark=m;
            creature=grandpa;
            grandpa.plant_standl();
            grandpa.measure_space_for_battle(s);
            grandpa.decide_direction_of_attack(Direction.EAST);
            grandpa.study_military_for_standl();
            grandpa.decide_start_coordinate(1,3);
            grandpa.choose_stand_type(StandType.SnakeShape);

            grandpa.embattle_for_battle(s);
            grandpa.choose_stand_place(s);
            id=Mythread_viewer.return_num(creature);
        }
        else if(m==4)
        {
            monsterScorpion=new MonsterScorpion(); 
            space=s;
            mark=m;
            creature=monsterScorpion;
            monsterScorpion.summon_monstersaltfish();
            monsterScorpion.measure_space_for_battle(s);
            monsterScorpion.decide_direction_of_attack(Direction.WEST);
            monsterScorpion.study_military_for_monster();
            monsterScorpion.decide_self_coordinate(space, 8, 6);
            monsterScorpion.choose_stand_type(StandType.MoonShape);
            monsterScorpion.embattle_for_battle(s);
            id=Mythread_viewer.return_num(creature);
        }
        else if(m==3)
        {
            snake=new MonsterSnake(); 
            space=s;
            mark=m;
            creature=snake;
            snake.choose_stand_place(space);
            id=Mythread_viewer.return_num(creature);
        }
    }    
    


}