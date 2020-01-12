package hulubro.controller;

import hulubro.formation.CraneWing;
import hulubro.formation.LongSnake;
import hulubro.gui.Layout;
import hulubro.life.*;
import hulubro.map.Map;
import hulubro.map.Move;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.util.ArrayList;

public class Controller{
    private HuluBro [] huluBros;
    private Grandfather grandfather;
    private Mob [] mobs;
    private Snake snake;
    private Scorpion scorpion;
    File file;
    boolean started;
    private Map map;
    private Layout layout;
    private ArrayList<Thread> threads;
    public Controller(Node root){
        GridPane root1 = (GridPane) root;
        started=false;
        layout=new Layout((GridPane) root);
        map=new Map(layout);
        threads=new ArrayList<Thread>();
    }

    public void play(File file){
        if(started){
            return;
        }
        try {
            this.file=file;
        } catch (Exception e) {
            e.printStackTrace();
        }

        started=true;
        init();
        allrun();
    }

    private void init() {
        huluBros= new HuluBro[7];
        grandfather=new Grandfather(map);
        mobs=new Mob[7];
        snake=new Snake(map);
        scorpion= new Scorpion(map);
        for(int i=0;i<7;i++){
            huluBros[i]=new HuluBro(map,i+1);
            mobs[i]=new Mob(map ,i+1);
        }
        LongSnake longSnake=new LongSnake(huluBros);
        CraneWing craneWing=new CraneWing(mobs);

        map.LifeIn(longSnake, 3, 2);
        map.LifeIn(craneWing, 13, 2);
        map.LifeIn(grandfather,1 , 5);
        map.LifeIn(snake, 16, 4);
        map.LifeIn(scorpion, 16, 6);

        threads.add(new Thread(grandfather));
        threads.add(new Thread(snake));
        threads.add(new Thread(scorpion));
        for(int i=0;i<7;i++){
            threads.add(new Thread(mobs[i]));
            threads.add(new Thread(huluBros[i]));
        }
        layout.setMap(map);
        layout.paint();

    }

    private void allrun() {
        for (Thread t : threads) {
            t.start();
        }
        new Thread(new Check(map,this)).start();
    }


    public void replay(File file) throws IOException, ClassNotFoundException {
        if(started){
            return;
        }
        try {
            this.file=file;
        } catch (Exception e) {
            e.printStackTrace();
        }

        started=true;
        init();
        FileInputStream FIS = new FileInputStream(file);
        ObjectInputStream OIS = new ObjectInputStream(FIS);
        ArrayList<Move> history = (ArrayList<Move>) OIS.readObject();
        new Thread(new Replay(history,map,layout)).start();
    }

    int checkwinner()//2 未结束 0 葫芦娃胜 1 妖精胜
    {
        boolean hulualive=false;
        boolean Enemyalive=false;
        for (HuluBro huluBro:   huluBros      ) {
            if(huluBro.alive){
                hulualive=true;
                break;
            }
        }
        if(grandfather.alive){
            hulualive=true;
        }
        for (Mob mob:   mobs      ) {
            if(mob.alive){
                Enemyalive=true;
                break;
            }
        }
        if(scorpion.alive){
            Enemyalive=true;
        }
        if(snake.alive){
            Enemyalive=true;
        }
        if(!hulualive){
            return 1;
        } else if(!Enemyalive){
            return 0;
        }
        else return 2;

    }
}
