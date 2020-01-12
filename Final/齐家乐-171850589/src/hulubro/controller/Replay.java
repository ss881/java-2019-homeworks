package hulubro.controller;

import hulubro.gui.Layout;
import hulubro.life.Life;
import hulubro.map.Map;
import hulubro.map.Move;
import javafx.application.Platform;

import java.util.ArrayList;

public class Replay implements Runnable{
    private ArrayList<Move> history;
    private Map map;
    private Layout layout;

    Replay(ArrayList<Move> history, Map map, Layout layout) {
        this.history=history;
        this.map=map;
        this.layout=layout;
    }

    @Override
    public void run() {
        for (Move i:history) {
            map.PrintMap();
            System.out.println(i.x1+" "+i.y1+" "+i.x2+" "+i.y2+" "+i.alive);
            Life life=map.getGrids()[i.x1][i.y1].life;
            Life finalLife = life;
            Platform.runLater(() -> {
                layout.repaint(i.x1,i.y1,i.x2,i.y2,finalLife,false);
            });
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("回放结束");
    }
}
