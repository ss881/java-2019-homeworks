package administer;

import java.util.ArrayList;

public class ThreadAdminister {

    private ArrayList<Thread> threads=new ArrayList<>();
    public void addOne(Thread t){
        threads.add(t);
    }
    public void clearAll(){
        threads.clear();
    }
    //发射
    public void startAll(){
        for(Thread t:threads)
            t.start();
    }
    public void interruptAll(){
        for(Thread t:threads){
            t.interrupt();
        }
    }
}
