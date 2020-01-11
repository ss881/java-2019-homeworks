package utils;

import battlefield.BattleField;
import creature.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import record.Record;
import record.RecordManager;
import team.EvilTeam;
import team.GoodTeam;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static utils.Config.RECORDS;

public class Controller {
    private GoodTeam goodTeam;
    private EvilTeam evilTeam;
    private BattleField battleField;
    private ArrayList<Thread> threads;
    private Lock lock = new ReentrantLock();
    private boolean isInBattle;
    private Image endImage;
    private UI ui;
    private RecordManager recordManager;

    public Controller() {
        threads = new ArrayList<Thread>();
        isInBattle = false;
        battleField = new BattleField();
        ui = new UI(battleField);
        setBattle();
        recordManager = new RecordManager();
        ui.initPaint();
    }

    class Supervisor implements Runnable {

        boolean checkAllWait() {
            for (Creature c : getAllCreatures()) {
                if (!c.isWait && !c.isDead)
                    return false;
            }
            return true;
        }

        int checkEnd() {
            if (goodTeam.checkLost()) {
                endImage = evilTeam.showImage();
                RECORDS.add(new Record("Evil", "", "", "", 0, 0, 0, 0, 0,0));
                return -1;
            } else if (evilTeam.checkLost()) {
                endImage = goodTeam.showImage();
                RECORDS.add(new Record("Good", "", "", "", 0, 0, 0, 0, 0,0));
                return 1;
            } else
                return 0;
        }

//        void nextRound() {
//            lock.lock();
//            try {
//                System.out.println("Supervisor");
//                TimeUnit.SECONDS.sleep((long) 0.5);
//                ui.repaint();
//                checkEnd();
//                boolean cw = checkAllWait();
//                if (cw)
//                    for (Creature c : getAllCreatures()) {
//                        c.isWait = false;
//                    }
//                notifyAll();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                lock.unlock();
//            }
//        }

        @Override
        public void run() {
            while (!Thread.interrupted()&&isInBattle) {
                try {
                    synchronized (lock) {
                        //System.out.println("Supervisor");
                        TimeUnit.MILLISECONDS.sleep(200);
                        ui.repaint();
                        for (Creature c : getAllCreatures()) {
                            c.isBattle = false;
                        }
                        if (checkEnd() != 0) {
                            lock.notifyAll();
                            endBattle();
                        }else {
                            boolean cw = checkAllWait();
                            if (cw) {
                                for (Creature c : getAllCreatures()) {
                                    c.isWait = false;
                                }
                                lock.notifyAll();
                            }
                        }

                    }
                    System.out.println("paint");
                } catch (Exception e) {
                    System.out.println("sup");
                    e.printStackTrace();
                }
            }

            System.out.println("Completed!");
        }
    }

    public ArrayList<Creature> getAllCreatures() {
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.addAll(goodTeam.getAllCreature());
        creatures.addAll(evilTeam.getAllCreature());
        return creatures;
    }

    public Scene getScene() {
        return ui.getScene();
    }

    public boolean getState() {
        return isInBattle;
    }

    private void initBoth() {
        CreatureFactory creatureFactory = new CreatureFactory(lock);

        Leader elder = creatureFactory.createElder(battleField);
        ArrayList<Huluwa> huluwas = creatureFactory.createHuluwas(battleField);
        goodTeam = new GoodTeam<Leader, Huluwa>(elder, huluwas, battleField);
        goodTeam.setTeamImage();

        Leader snake = creatureFactory.createSnake(battleField);
        ArrayList<Louluo> louluos = creatureFactory.createLouluos(battleField);
        evilTeam = new EvilTeam<Leader, Louluo>(snake, louluos, battleField);
        evilTeam.setTeamImage();
    }

    private void setBattle() {
        threads.clear();
        initBoth();
        battleField.clear();
        goodTeam.arrange();
        evilTeam.arrange();
    }

    public void startBattle() {
        if (!isInBattle) {
            setBattle();
            isInBattle = true;
            ui.repaint();
            for (Creature creature : getAllCreatures()) {
                threads.add(new Thread(creature, creature.getName()));
            }
            Supervisor supervisor = new Supervisor();
            threads.add(new Thread(supervisor, "supervisor"));

            for (Thread thread : threads)
                thread.start();
        }
    }

    public void endBattle() {
        for (int i=0;i<threads.size()-1;i++) {
            threads.get(i).interrupt();
        }
        isInBattle = false;
        ui.endPaint(endImage);
        recordManager.saveFile();
    }


    public void replay(File file) throws IOException, InterruptedException {
        recordManager.readFile(file);
        setBattle();
        isInBattle = true;
        ui.repaint();
        Thread parser=new Thread(new Parse(battleField,ui), "Parser");
        parser.start();
        isInBattle=false;
    }
}
