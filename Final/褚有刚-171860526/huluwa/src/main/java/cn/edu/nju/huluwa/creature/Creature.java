package cn.edu.nju.huluwa.creature;

import cn.edu.nju.huluwa.bullet.Bullet;
import cn.edu.nju.huluwa.bullet.BulletFactory;
import cn.edu.nju.huluwa.bullet.BulletManager;
import cn.edu.nju.huluwa.position.Position;
import cn.edu.nju.huluwa.position.PositionManager;
import cn.edu.nju.huluwa.record.RecordFactory;
import cn.edu.nju.huluwa.huluworld.HuluWorld;
import cn.edu.nju.huluwa.util.ImageLoader;
import javafx.scene.image.Image;

import java.util.Random;

import static cn.edu.nju.huluwa.Config.*;

public abstract class Creature implements Runnable {
    public enum Direction {
        TOP, DOWN, LEFT, RIGHT
    }

    public enum State {
        JOIN, WATCH // join the war or just watch the war
    }

    public static int allocId = 0;
    protected Image image;
    protected Position position;    // 多线程
    private String name;
    private double health;  // 多线程
    private double damage;  // 多线程
    private boolean selected;
    private State state;
    private int id;
    private HuluWorld huluWorld;
    private Colour color;

    public Creature(HuluWorld huluWorld, String name, Colour color) {
        this.huluWorld = huluWorld;
        this.name = name;
        this.color = color;
        image = ImageLoader.getImage(name);
        position = null;
        health = INIT_HEALTH;
        damage = INIT_DAMAGE;
        state = State.WATCH;
        selected = false;
        id = ++allocId;
    }

    public Colour getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public Image getImage() {
        return image;
    }

    public boolean hasPosition() {
        return position != null;
    }

    public void releasePosition() {
        if (position != null) {
            position.clearCreature();
            position = null;
        }
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public synchronized void upgrade() {
        damage += INCR_DAMAGE;
        huluWorld.getRecordManager().add(RecordFactory.newUpgradeRecord(this));
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public abstract boolean isEnemy(Creature creature);

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public synchronized void getHurt(double damage) {
        health -= damage;
        huluWorld.getRecordManager().add(RecordFactory.newHurtRecord(this));
        if (isDead()) {
            huluWorld.getRecordManager().add(RecordFactory.newDeadRecord(this));
            position.clearCreature();
            position = null;
        }
    }

    public void attack() {
        BulletManager bulletManager = huluWorld.getBulletManager();
        Bullet bullet = BulletFactory.newBullet(this, findTarget());
        bulletManager.addBullet(bullet);
        huluWorld.getRecordManager().add(RecordFactory.newBulletCreateRecord(bullet));
        huluWorld.getScene().repaint();
    }

    private Position findTarget() {
        Random random = new Random();
        int x = random.nextInt(ROW);
        int y = random.nextInt(COL);
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                Position pos = huluWorld.getPositionManager().getPosition((x + i) % ROW, (y + j) % COL);
                Creature c = pos.getCreature();
                if (c != null && isEnemy(c) && !c.isDead()) return pos;
            }
        }
        return position.getNextY() != null ? position.getNextY() : position.getPrevY();
    }

    public void move(Direction direction) {
        PositionManager positionManager = huluWorld.getPositionManager();
        // 移动前获取positionMgr的锁
        synchronized (positionManager) {
            if (isDead()) return;
            Position nextPos = null;
            switch (direction) {
                case TOP:
                    nextPos = position.getPrevX();
                    break;
                case DOWN:
                    nextPos = position.getNextX();
                    break;
                case LEFT:
                    nextPos = position.getPrevY();
                    break;
                case RIGHT:
                    nextPos = position.getNextY();
                    break;
            }
            if (nextPos != null) {
                // some creature is here, this creature dead
                Creature creature = nextPos.getCreature();
                if (creature != null) {
                    if (isEnemy(nextPos.getCreature())) {
                        getHurt(nextPos.getCreature().getDamage());
                        nextPos.getCreature().upgrade();
                    }
                } else {
                    position.clearCreature();
                    nextPos.setCreature(this);
                    position = nextPos;
                    huluWorld.getRecordManager().add(RecordFactory.newMoveRecord(this));
                }
            }
        }
//        lock.unlock();
        huluWorld.getScene().repaint();
    }

    public void setPosition(Position position) {
        // clear creature previous
        if (this.position != null) {
            this.position.clearCreature();
        }
        if (position == null) {
            this.position = null;
            return;
        }
        position.setCreature(this);
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void swapPosWith(Creature c) {
        Position p = c.getPosition();
        c.setPosition(getPosition());
        setPosition(p);
    }

    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (!isDead() && !Thread.interrupted()) {
            if (!selected) {
                int r = random.nextInt(3);
                if (r == 0) {
                    Direction direction = null;
                    if (random.nextBoolean()) {
                        direction = random.nextBoolean() ? Direction.LEFT : Direction.RIGHT;
                    } else {
                        direction = random.nextBoolean() ? Direction.TOP : Direction.DOWN;
                    }
//            System.out.println(position);
                    move(direction);
                } else if (r == 1) {
                    attack();
                }
            }
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                return;
//                e.printStackTrace();
            }
        }
//        System.out.println(Thread.activeCount());
    }

    // for debug
    @Override
    public String toString() {
        return name + "@" + position;
    }
}
