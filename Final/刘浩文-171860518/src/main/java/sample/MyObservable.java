package main.java.sample;

import java.io.IOException;

public interface MyObservable {
    void registerObserver(MyObserver o);
    void routeNotify();
    void battleNotify();
    void logNotify(int type, int a, int b, int c, int d) throws IOException;
}
