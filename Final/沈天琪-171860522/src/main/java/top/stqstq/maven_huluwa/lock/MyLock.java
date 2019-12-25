package top.stqstq.maven_huluwa.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock {
    public static Lock lock;
    public static Lock printLock;
    public static int state;
    public static int updateState;

    static {
        lock = new ReentrantLock(true);
        printLock = new ReentrantLock(true);
        //lock.unlock();
        state = 1;
        updateState = 1;
    }

    public MyLock() {
    }
}
