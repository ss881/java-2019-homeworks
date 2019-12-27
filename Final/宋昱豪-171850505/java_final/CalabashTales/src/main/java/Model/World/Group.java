package Model.World;

import Model.Good.CalabashBrothers;
import Model.Good.Grandpa;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Group implements Runnable{
    ExecutorService exec = Executors.newCachedThreadPool();
    public Set<Lives> members=new HashSet<Lives>();
    protected int formnum=0;
    public Group()
    {

    }

    private void initThreads(){

        for(Lives life: members){
            exec.execute(life);
        }
        exec.shutdown();
    }
    public boolean stillAlive()
    {
        for(Lives life: members){
            if(life.attributes.living== Attributes.livingStatus.live)
            {
                return true;
            }
        }
        return false;
    }
    public int livingnum()
    {
        int num=0;
        for(Lives life: members){
            if(life.attributes.living== Attributes.livingStatus.live)
            {
                num=num+1;
            }
        }
        return num;
    }
    public void killall()
    {
        for(Lives life: members){
            life.attributes.living= Attributes.livingStatus.dead;
        }
        exec.shutdownNow();
    }
    @Override
    public void run()
    {
        initThreads();
    }
}
