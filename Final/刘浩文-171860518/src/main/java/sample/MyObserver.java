package main.java.sample;

import java.io.IOException;

public interface MyObserver {
    void routeUpdate(RouteNotifier rn);
    void battleUpdate(BattleNotifier bn);
    void gameLogPrint(int type,int a,int b,int c,int d) throws IOException;
}
