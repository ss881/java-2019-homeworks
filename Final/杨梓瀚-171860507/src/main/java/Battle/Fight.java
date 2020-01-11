package Battle;

import Creature.*;
import Formation.Formation;

import Record.Record;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.image.*;
import java.util.ArrayList;
import java.util.List;

public class Fight {
    private Grandpa grandpa;

    private Snake snake;

    private Scorpion scorpion;

    private CalabashBros calabashBros;

    private static int aliveJusticeNum;

    private static int aliveEvilNum;

    private static int nowTurn;

    private static int allCreatureNum;

    private static Object lock;

    public static final int BLOCKPIXELX = 50;

    public static final int BLOCKPIXELY = 50;

    public Fight(){
        lock = new Object();
        nowTurn = 0;
        allCreatureNum = 0;
        grandpa = new Grandpa(lock);
        snake = new Snake(lock);
        scorpion = new Scorpion(lock);
        calabashBros = new CalabashBros(lock);
    }


    public static int getAllCreatureNum(){
        return allCreatureNum;
    }

    public static void addAllCreatureNum(){
        allCreatureNum++;
    }

    public static int getNowTurn(){
        return nowTurn;
    }

    public static void addNowTurn(){
        nowTurn = (nowTurn + 1) % allCreatureNum;
    }

    public static void minusJustice(){
        aliveJusticeNum--;
    }

    public static void minusEvil(){
        aliveEvilNum--;
    }

    public static synchronized boolean isEnd(){
        return aliveEvilNum == 0 || aliveJusticeNum == 0;
    }

    private void initTurn(){
        grandpa.initTurn();
        calabashBros.initTurn();
        snake.initTurn();
        scorpion.initAllTurn();
    }

    public void init(){
        Formation.init();
        aliveJusticeNum = 8;
        aliveEvilNum = scorpion.init();
        calabashBros.queue();
        aliveEvilNum += 1;
        System.out.println(allCreatureNum);
        System.out.println(aliveEvilNum);
        System.out.println(aliveJusticeNum);
        System.out.println(nowTurn);
        initTurn();
    }

    public void run(){
        //grandpa.longsnake(battle);

        //print();
        Record.initFile(calabashBros, grandpa, scorpion, snake);
        Thread threadS = new Thread(scorpion);
        Thread threadG = new Thread(grandpa);
        Thread threadSnake = new Thread(snake);
        //while (aliveJusticeNum >0 && aliveEvilNum > 0){
        threadG.start();
        calabashBros.run();
        threadSnake.start();
        threadS.start();

        System.out.println(aliveJusticeNum + " " + aliveEvilNum);
        //}
    }

    public static void print(){
        for (int i = 0; i < Battle.getM(); i++){
            for (int j = 0; j < Battle.getN(); j++)
                if (Battle.getPosition(i, j) != null)
                    Battle.getPosition(i, j).getHolder().print();
                else
                    System.out.print('*');
            System.out.print('\n');
        }
        System.out.print('\n');
    }

    public List<ImageView> getImageViews(){
        List<ImageView> list = new ArrayList<>();
        list.add(grandpa.getImageView());
        list.add(snake.getImageView());
        list.addAll(calabashBros.getAllImageView());
        list.addAll(scorpion.getAllImageView());
        return list;
    }

    public static void setNum(int allCreatureNum) {
        Fight.allCreatureNum = allCreatureNum;
        Fight.aliveJusticeNum = 8;
        Fight.aliveEvilNum = allCreatureNum - Fight.aliveJusticeNum;
    }

    public Scorpion getScorpion(){
        return scorpion;
    }

    public CalabashBros getCalabashBros(){
        return calabashBros;
    }

    public Grandpa getGrandpa(){
        return grandpa;
    }

    public Snake getSnake(){
        return snake;
    }

    public static void cheer(){
        if (!isEnd())
            return;

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("胜利!");
                if (aliveEvilNum == 0){
                    alert.setHeaderText("葫芦娃获得胜利!");
                    alert.setContentText("邪不压正");
                }else {
                    alert.setHeaderText("妖怪获得胜利!");
                    alert.setContentText("你们这群小屁孩还是太弱了");
                }
                alert.showAndWait();
            }
        });
    }

    public static Object getLock(){
        return lock;
    }
}
