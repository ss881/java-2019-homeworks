package generators;

import creator.BasicCreator;
import creator.Creator;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/*
* 同步的对象生成器，每隔一段时间生成对象
 */
public class SyncGenerator<T> implements Generator<T> {
    T element;

    Creator<T> creator;

    int timeInterval;

    TimeUnit timeUnit;


    public SyncGenerator(Creator<T> creator, int timeInterval, TimeUnit timeUnit) {
        this.creator = creator;
        this.timeInterval = timeInterval;
        this.timeUnit = timeUnit;
        this.element = null;
    }

    @Override
    public Creator<T> getCreator() {
        return creator;
    }

    @Override
    public void setCreator(Creator<T> creator) {
        this.creator = creator;
    }

    private boolean started = false;

    public void startGenerating(){
        started = true;
        startTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
    }

    public void stopGenerating(){
        started = false;
        startTime = 0;
    }

    public boolean isReady(){
        if(started == false) return false;
        long curTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
        long curInterval = curTime - startTime;
        return curInterval >= timeUnit.toMillis(timeInterval);
    }

    public T fetch(){
        startTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
        return creator.create();
    }

    private long startTime = 0;
}
