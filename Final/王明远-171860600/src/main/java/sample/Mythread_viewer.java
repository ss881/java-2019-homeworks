
package  sample;

import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.concurrent.CyclicBarrier;

public class Mythread_viewer extends Thread
{
    public  CyclicBarrier cyclicBarrier=new CyclicBarrier(1+3+Grandpa.the_num_of_brotherstandl+MonsterScorpion.the_num_of_monstersaltfish);
    public int []flags=new int[2];
    public int [][]tmp_space;
    Stage currentstage;
    public static  Object speed=new Object();
    static int times=1;
    @Override
    public void run()
    {

        try{

           Main.run_flag.set(1);
            times=1;
//            Main.simpleIntegerProperty.set(times);
            MyFiles.load(tmp_space,Main.size_of_current_space,Main.size_of_current_space);
            sleep(SpaceForBattle.time_gap);
            while(!view_space.isEnd())
        {
         //   view_space.overlook_space();
            cyclicBarrier.await();
            times++;
            for(int i=0;i<Main.size_of_current_space;++i)
                for(int j=0;j<Main.size_of_current_space;++j)
                    tmp_space[i][j]=view_space.read_space(i,j);
            MyFiles.load(tmp_space,Main.size_of_current_space,Main.size_of_current_space);
            Platform.runLater(() -> {
                Main.simpleIntegerProperty.set(times);
            });
            sleep(SpaceForBattle.time_gap);
          //  view_space.show(Main.graphicsContext,null);
           // Main.simpleIntegerProperty.set(times);
//            long t1=System.currentTimeMillis();
//            long t2=System.currentTimeMillis();
//            System.out.println(51531);
//            System.out.println(t2-t1);
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                  Main.simpleIntegerProperty.set(times);
//                    //  view_space.show(Main.graphicsContext,null);
//                }
//            });
//            for(int i=0;i<Main.size_of_current_space;++i)
//                for(int j=0;j<Main.size_of_current_space;++j)
//                    tmp_space[i][j]=view_space.read_space(i,j);
           // view_space.show(currentstage);
           // view_space.overlook_space();

            cyclicBarrier.reset();
            if(view_space.isEnd())
                flags[0]=-1;
            for(int i=0;i<current_creature_num;++i) {
                if(creature_list[i].state)
                Creature.find_direction(creature_list, creature_list[i], current_creature_num, view_space);
            }
            flags[1]++;

        }
            Platform.runLater(() -> {
                Main.simpleIntegerProperty.set(times+1);
            });

          //  System.out.println("finish");
        }catch(Exception e)
    {
        e.printStackTrace();
    }
        finally {
            Main.run_flag.set(0);
            flags[0]=-1;
            MyFiles.close();
        }

    }
    static SpaceForBattle view_space;
    String mypath;
    static public int current_creature_num=0;
    static public Creature [] creature_list;
    void get_set(SpaceForBattle space,String path )
    {
        mypath=path;
        view_space=space;
        creature_list=new Creature[space.get_size()*space.get_size()];
    }
    void get_set(Stage stage)
    {
        flags[0]=0;
        flags[1]=0;
        currentstage=stage;
        for(int i=0;i<current_creature_num;++i) {
            if (creature_list[i].state) {
                Creature.find_direction(creature_list, creature_list[i], current_creature_num, view_space);
                //System.out.println(creature_list[i].aim_direction);
            }
        }
        tmp_space=new int[Main.size_of_current_space][Main.size_of_current_space];
        for(int i=0;i<Main.size_of_current_space;++i)
            for(int j=0;j<Main.size_of_current_space;++j)
                tmp_space[i][j]=view_space.read_space(i,j);

    }
    static int return_num(Creature creature)
    {
        creature_list[current_creature_num]=creature;
        current_creature_num++;
        return current_creature_num-1;
    }
    static void empty()
    {
        current_creature_num=0;
    }


}