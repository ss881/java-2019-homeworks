package GameModule;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import BattleField.*;
import Item.*;
import Behavior.*;

public class Round implements Runnable{
    private BattleField battlefield;

    private ArrayList<CalabashBoy> calabashbrothers;
    private Grandfather grandfather;
    private ArrayList<Lackey> lackeys;
    private Scorpion scorpion;
    private Snake snake;
    private ArrayList<Bullet> cBullets;
    private ArrayList<Bullet> mBullets;

    private Queue<Hit> hitQueue;
    private Queue<Meet> meetQueue;

    private ExecutorService cBulletExecutor;
    private ExecutorService mBulletExecutor;
    private ExecutorService creatureExecutor;

    private GraphicsContext gc;
    private TextArea textArea = null;
    private Image background = new Image("background.png");
    private Image effect = new Image("effect.png");
    private Image victory = new Image("victory.png");
    private Image defeat = new Image("defeat.png");
    private BufferedWriter logWriter;
    private boolean isGamming = true;

    public Round(CalabashCrew cc, MonsterCrew mc, BattleField battlefield, GraphicsContext gc, TextArea textArea) {
        this.calabashbrothers = cc.getCalabashbrothers();
        this.grandfather = cc.getGrandfather();
        this.lackeys = mc.getLackeys();
        this.scorpion = mc.getScorpion();
        this.snake = mc.getSnake();
        this.battlefield = battlefield;
        this.gc = gc;
        this.textArea = textArea;
        cBullets = new ArrayList<Bullet>();
        mBullets = new ArrayList<Bullet>();
        hitQueue = new LinkedList<Hit>();
        meetQueue = new LinkedList<Meet>();
        cBulletExecutor = Executors.newCachedThreadPool();
        mBulletExecutor = Executors.newCachedThreadPool();
        creatureExecutor = Executors.newCachedThreadPool();
        CalabashBoy.setBullets(cBullets);
        CalabashBoy.setBulletExecutor(cBulletExecutor);
        Lackey.setBullets(mBullets);
        Lackey.setBulletExecutor(mBulletExecutor);
        Bullet.setHit(hitQueue);
        Creature.setMeet(meetQueue);
        Creature.setBattlefield(battlefield.getBattleField());
    }


    private void initThreads() {
        for(CalabashBoy i : calabashbrothers)
            creatureExecutor.execute(i);
        creatureExecutor.execute(grandfather);
        for(Lackey i : lackeys)
            creatureExecutor.execute(i);
        creatureExecutor.execute(scorpion);
        creatureExecutor.execute(snake);
        creatureExecutor.shutdown();
        Date date = new Date();
        try {
            String path = "Rep_" + date.getTime() + ".replay";
            logWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayAll() {
        long startTime = 0;
        long endTime = 0;
        int FPS = 0;
        while(isGamming) {
            if(CalabashCrew.allDead()) {
                isGamming = false;
            }
            else if(MonsterCrew.allDead()) {
                isGamming = false;
            }
            try {
                startTime = System.currentTimeMillis();
                gc.drawImage(background,0,0,960,540);
                battlefield.printBattleField(gc);
                battlefield.outputRep(logWriter);

                synchronized (cBullets) {
                    for(Bullet i : cBullets) {
                        if(!i.isOut()) {
                            i.display(gc);
                            logWriter.write(i.getStatus());
                            logWriter.newLine();
                        }
                    }
                }
                synchronized (mBullets) {
                    for(Bullet i : mBullets) {
                        if(!i.isOut()) {
                            i.display(gc);
                            logWriter.write(i.getStatus());
                            logWriter.newLine();
                        }
                    }
                }
                synchronized (hitQueue) {
                    while(!hitQueue.isEmpty()) {
                        Hit hit = hitQueue.poll();
                        hitQueue.remove(0);
                        gc.drawImage(effect, hit.getPos().getX() + 10,hit.getPos().getY() + 10,40,40);
                        logWriter.write("E "+hit.getPos().getX()+" "+(hit.getPos().getY()+15));
                        logWriter.newLine();
                        if(hit.getResult() != "") {
                            textArea.appendText(hit.getResult());
                            logWriter.write("M "+hit.getResult());
                        }
                    }
                }
                synchronized (meetQueue) {
                    while(!meetQueue.isEmpty()) {
                        Meet meet = meetQueue.poll();
                        meetQueue.remove(0);
                        textArea.appendText(meet.getResult());
                        logWriter.write("M "+meet.getResult());
                    }
                }
                gc.setStroke(Color.WHITE);
                gc.strokeText("FPS: "+FPS,0,20);
                logWriter.write("==");
                logWriter.newLine();
                TimeUnit.MILLISECONDS.sleep(50);
                endTime = System.currentTimeMillis();
                FPS = (int)(1000 / (endTime - startTime));  //计算帧数
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                //清理子弹
                synchronized (cBullets) {
                    cBullets.removeIf(new Predicate<Bullet>() {
                        @Override
                        public boolean test(Bullet bullet) {
                            return bullet.isOut();
                        }
                    });
                }

                synchronized (mBullets) {
                    mBullets.removeIf(new Predicate<Bullet>() {
                        @Override
                        public boolean test(Bullet bullet) {
                            return bullet.isOut();
                        }
                    });
                }
            }
        }
        try {
            if(CalabashCrew.allDead()) {
                gc.drawImage(defeat,330,100,300,300);
                logWriter.write("Defeat");
                textArea.appendText("妖怪获胜\n");
                isGamming = false;
            }
            else if(MonsterCrew.allDead()){
                gc.drawImage(victory,330,100,300,300);
                logWriter.write("Victory");
                textArea.appendText("葫芦娃获胜\n");
                isGamming = false;
            }
            else {
                logWriter.write("NoResult");
                isGamming = false;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endGame() {
        isGamming = false;
    }

    public void clean() {
        try {
            logWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //清理
        for(CalabashBoy i : calabashbrothers)
            i.die();
        for(Lackey i : lackeys)
            i.die();
        for(Bullet i : cBullets)
            i.setOut();
        for(Bullet i : mBullets)
            i.setOut();

        grandfather.die();
        snake.die();
        scorpion.die();
        creatureExecutor.shutdown();
        cBulletExecutor.shutdown();
        mBulletExecutor.shutdown();

        while(!creatureExecutor.isTerminated()) {}
        while(!cBulletExecutor.isTerminated()) {}
        while(!mBulletExecutor.isTerminated()) {}
    }

    public void run(){
        initThreads();
        displayAll();
        endGame();
        clean();
        textArea.appendText("战斗结束\n");
    }
}