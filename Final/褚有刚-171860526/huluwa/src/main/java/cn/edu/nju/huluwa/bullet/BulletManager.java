package cn.edu.nju.huluwa.bullet;

import cn.edu.nju.huluwa.creature.Creature;
import cn.edu.nju.huluwa.position.Position;
import cn.edu.nju.huluwa.position.PositionManager;
import cn.edu.nju.huluwa.record.RecordFactory;
import cn.edu.nju.huluwa.huluworld.HuluWorld;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static cn.edu.nju.huluwa.Config.*;

public class BulletManager implements Runnable {
    //    private CopyOnWriteArrayList<Bullet> bullets;
    private List<Bullet> bullets;
    private HuluWorld huluWorld;

    public BulletManager(HuluWorld huluWorld) {
//        bullets = new CopyOnWriteArrayList<>();
        bullets = new LinkedList<>();
        this.huluWorld = huluWorld;
    }

    // creature调用
    public synchronized void addBullet(Bullet bullet) {
        assert(bullet != null);
        bullets.add(bullet);
    }

    public void removeById(int id) {
        Iterator<Bullet> iter = bullets.iterator();
        while(iter.hasNext()) {
            Bullet bullet = iter.next();
            if(bullet.getId() == id) iter.remove();
        }
    }

    public synchronized void remove(int i) {
        bullets.remove(i);
    }

    // ui线程调用
    public synchronized List<Bullet> getBullets() {
        List<Bullet> bullets = new LinkedList<>();
        bullets.addAll(this.bullets);
        return bullets;
    }

//    public synchronized void remove(int i) {
//        bullets.remove(i);
//    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            // draw record
            PositionManager positionManager = huluWorld.getPositionManager();
            for (int i = 0; i < bullets.size(); ) {
//                    System.out.println(bullets.size());
//                System.out.println("test");
                Bullet bullet = bullets.get(i);
//                    Bullet bullet = bullets.get(i);
                huluWorld.getRecordManager().add(RecordFactory.newBulletMoveRecord(bullet));
                bullet.setX(bullet.getX() + (int) Math.round(bullet.getSpeed() * REFRESH_INTER * Math.cos(bullet.getRadians())));
                bullet.setY(bullet.getY() + (int) Math.round(bullet.getSpeed() * REFRESH_INTER * Math.sin(bullet.getRadians())));
                int x = bullet.getX() / GRID_WIDTH;
                int y = bullet.getY() / GRID_HEIGHT;
                if (!positionManager.inSpace(x, y)) {
                    huluWorld.getRecordManager().add(RecordFactory.newBulletRemoveRecord(bullets.get(i)));
                    remove(i);
                    continue;
                } else /*if(checkBounds(bullet.getX(), bullet.getY()))*/{
                    // 先对position manager加锁，再对 creature加锁
                    synchronized (positionManager) {
                        Position pos = positionManager.getPosition(x, y);
                        Creature attacker = bullet.getAttacker();
                        Creature creature = pos.getCreature();
                        if (creature != null && !creature.isDead()) {
                            if (attacker.isEnemy(creature)) {
                                creature.getHurt(attacker.getDamage());
                                attacker.upgrade();
                                huluWorld.getRecordManager().add(RecordFactory.newBulletRemoveRecord(bullets.get(i)));
                                remove(i);
                                continue;
                            }
                        }
                    }
                }
                i++;
                huluWorld.getScene().repaint();
//                System.out.println(i);
            }
            try {
                Thread.sleep(REFRESH_INTER);
            } catch (InterruptedException e) {
                return;
            }
//            System.out.println(Thread.interrupted());
        }
    }

    public void clear() {
        bullets.clear();
    }

//    private boolean checkBounds(int x, int y) {
//        return x % GRID_WIDTH >= 2 && x % GRID_WIDTH <= 48
//                &&  y % GRID_HEIGHT >= 2 && y % GRID_HEIGHT <= 48;
//    }

    public void move(int id, int x, int y) {
        for (Bullet bullet : bullets) {
           if(bullet.getId() == id)  {
               bullet.setX(x);
               bullet.setY(y);
           }
        }
    }
}
