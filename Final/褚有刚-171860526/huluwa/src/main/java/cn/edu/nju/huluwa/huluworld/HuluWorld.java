package cn.edu.nju.huluwa.huluworld;


import cn.edu.nju.huluwa.Scene;
import cn.edu.nju.huluwa.bullet.BulletManager;
import cn.edu.nju.huluwa.creature.Cheerable;
import cn.edu.nju.huluwa.creature.Creature;
import cn.edu.nju.huluwa.creature.Leader;
import cn.edu.nju.huluwa.position.Position;
import cn.edu.nju.huluwa.position.PositionManager;
import cn.edu.nju.huluwa.record.RecordFactory;
import cn.edu.nju.huluwa.record.RecordManager;
import cn.edu.nju.huluwa.team.BadTeam;
import cn.edu.nju.huluwa.team.GoodTeam;
import cn.edu.nju.huluwa.team.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static cn.edu.nju.huluwa.Config.INIT_DAMAGE;
import static cn.edu.nju.huluwa.Config.INIT_HEALTH;

public class HuluWorld {
    private GoodTeam goodTeam;
    private BadTeam badTeam;
    private RecordManager recordManager;
    private PositionManager positionManager;
    private BulletManager bulletManager;
//    private Executors threads;
    private List<Thread> threads;
    private boolean inBattle;
    private boolean paused;
    private Lock lock = new ReentrantLock();
    private Scene scene;

    HuluWorld(Scene scene) {
        super();
        this.scene = scene;
        threads = new ArrayList<>();
        positionManager = new PositionManager();
        recordManager = new RecordManager(this);
        bulletManager = new BulletManager(this);
        inBattle = false;
        paused = false;
//        new Thread(this::test).start();
    }

    private void test() {
        while(true) {
            System.out.println("=============================================");
            for (Thread thread : threads) {
                System.out.println(thread.getName() + ": " + thread.getState());
            }
            System.out.println("=============================================");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resetWorld() {
        goodTeam.setInitialPos(positionManager.getPositions());
        badTeam.setInitialPos(positionManager.getPositions());
        recordManager.clear();
        bulletManager.clear();
        threads.clear();
        for (Creature c : getAllCreatures()) {
            c.setState(Creature.State.WATCH);
            c.setHealth(INIT_HEALTH);
            c.setDamage(INIT_DAMAGE);
        }
    }

    public void sortHuluwa() {
        resetWorld();
        goodTeam.sortHuluwa();
    }

    public void shufflehuluwa() {
        resetWorld();
        goodTeam.shuffleHuluwa();
    }

    public void startBattle() {
        resetWorld();
        inBattle = true;
        scene.showInfo("===== New Battle Start! =====\n");
        for (Creature creature : getAllCreatures()) {
            if (creature.hasPosition()) {
                creature.setState(Creature.State.JOIN);
                threads.add(new Thread(creature, creature.getName()));
            }
            recordManager.add(RecordFactory.newCreateRecord(creature));
        }
        threads.add(new Thread(bulletManager, "bulletManager"));
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void endBattle() {
        if(inBattle) {
            scene.showInfo("======= Battle end! =======\n");
            // shut down all threads
            for (Thread thread : threads) {
                thread.interrupt();
            }
            inBattle = false;
        }
    }

    public RecordManager getRecordManager() {
        return recordManager;
    }

    public void pauseBattle() {
        if(inBattle) {
            paused = true;
            lock.lock();
        }
    }

    public void continueBattle() {
        paused = false;
        lock.unlock();
    }

    public boolean isPaused() {
        return paused;
    }

    public PositionManager getPositionManager() {
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
        return positionManager;
    }

    public BulletManager getBulletManager() {
        return bulletManager;
    }

    public String badTeamBuZhen() {
        resetWorld();
        badTeam.buZhen(positionManager.getPositions());
        StringBuilder cheerInfo = new StringBuilder();
        for (Creature creature : getAllCreatures()) {
            if (creature instanceof Cheerable) {
                cheerInfo.append(((Cheerable) creature).cheer());
            }
        }
        return cheerInfo.toString();
    }

    public ArrayList<Creature> getAllCreatures() {
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.addAll(goodTeam.getAllCreatures());
        creatures.addAll(badTeam.getAllCreatures());
        return creatures;
    }

//    public Position[][] getPositions() {
//        return positions;
//    }

    void setGoodTeam(GoodTeam goodTeam) {
        this.goodTeam = goodTeam;
    }

    void setBadTeam(BadTeam badTeam) {
        this.badTeam = badTeam;
    }

    public GoodTeam getGoodTeam() {
        return goodTeam;
    }

    public BadTeam getBadTeam() {
        return badTeam;
    }

    public Scene getScene() {
        return scene;
    }

    public boolean isInBattle() {
        return inBattle;
    }

    public Creature getCreature(int id) {
        for (Creature creature : getAllCreatures()) {
            if (creature.getId() == id) return creature;
        }
        return null;
    }

    public Team<? extends Leader, ? extends Creature> getWinner() {
        int goodTeamAliveCnt = goodTeam.getAliveCount();
        int badTeamAliveCnt = badTeam.getAliveCount();
//        System.out.println(goodTeamAliveCnt + ", " + badTeamAliveCnt);
        if (goodTeamAliveCnt == 0 && badTeamAliveCnt == 0) return null;
        else if (goodTeamAliveCnt == 0) {
            endBattle();
            return badTeam;
        } else if (badTeamAliveCnt == 0) {
            endBattle();
            return goodTeam;
        } else return null;
    }
}
