package creature;

import command.AttackCommand;
import command.LeaveCommand;
import command.MoveCommand;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import space.Bullet;
import space.Space;
import space.Tile;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Creature implements Runnable {
    public float hp, atk, defence, speed, atkrange;
    private float[] info_bk;
    Space space;
    public Tile floor;
    String name;
    String alive_path;
    String dead_path = "image/tomb.png";
    int team_id;
    private Lock lock = new ReentrantLock();
    private Lock Boxlock = new ReentrantLock();
    private static final float length = (float) 1.3, width = (float) 1.3, height = 0;
    Image image;
    PhongMaterial floorMaterial = new PhongMaterial();
    Box box;
    boolean alive;
    public Bullet bullet;

    public Creature(Space s, float[] in, int team_id) {
        space = s;
        this.team_id = team_id;
        this.hp = in[0];
        this.atk = in[1];
        this.defence = in[2];
        this.speed = in[3];
        this.atkrange = in[4];
        info_bk = new float[5];
        for (int i = 0; i < 5; i++) {
            info_bk[i] = in[i];
        }
        box = new Box(length, width, height);
        box.setTranslateY(-0.5);
        box.setVisible(false);
        space.getChildren().add(box);
        bullet = new Bullet();
        space.getChildren().add(bullet.box);
    }

    public void init() {
        this.floor = null;
        this.hp = info_bk[0];
        this.atk = info_bk[1];
        this.defence = info_bk[2];
        this.speed = info_bk[3];
        this.atkrange = info_bk[4];
        box.setVisible(false);
        alive = true;
    }

    public boolean equal(String name) {
        return name.equals(this.name);
    }

    public Box getBox() {
        return box;
    }

    public String getName() {
        return name;
    }

    public void setImage(String path) {
        alive_path=path;
        image = new Image(path, 100, 100, true, true);
        floorMaterial.setDiffuseMap(image);
        box.setMaterial(floorMaterial);
    }

    public void setFloor(Tile floor) {
        this.floor = floor;
    }

    public void show() {
        System.out.print(this.name);
    }

    ;

    public void born() {
        alive = true;
        int map_size = space.getN();
        Random r = new Random();
        while (true) {
            int x = r.nextInt(map_size);
            int y = r.nextInt(map_size);
            if (space.floor[x][y].get() == null) {
                space.floor[x][y].set(this);
                this.floor = space.floor[x][y];
                box.setVisible(true);
                break;
            }
        }
    }

    public void lock() {
        lock.lock();
        System.out.println("Locked!");
    }

    public void unlock() {
        lock.unlock();
        System.out.println("UnLocked!");
    }

    public void boxlock() {
        Boxlock.lock();
    }

    public void boxunlock() {
        Boxlock.unlock();
    }

    public void move_to(int x, int y) {
        MoveCommand m = new MoveCommand(space, floor.getX(), floor.getY(), x, y, name);
        m.execute();
    }

    public void swap_to(int x, int y) {
        MoveCommand m = new MoveCommand(space, floor.getX(), floor.getY(), x, y, name, true);
        m.execute();
    }

    public void leave() {
        LeaveCommand m = new LeaveCommand(space, floor.getX(), floor.getY(), name);
        m.execute();
        alive = false;
    }

    public boolean is_alive() {
        return alive;
    }

    public int[] get_pos() {
        int[] res = new int[2];
        res[0] = floor.getX();
        res[1] = floor.getY();
        return res;
    }

    public float getAttack(float damage) {
        float def = space.rolln((int) defence);
        if (def >= damage) {
            return 0;
        }
//        if(hp<0)
//            return 0;
        lock.lock();
        try {
            hp -= damage - def;
            if (hp < 0) {
//                leave();
//                set_visiable(false);
                setImage(dead_path);
                if (floor != null)
                    floor.set(null);
                alive = false;
            }
        } finally {
            lock.unlock();
        }
        return def;
    }

    public void getAttack(float damage, float def) {
        if (def >= damage) {
            return;
        }
//        if(hp<0)
//            return;
        lock.lock();
        try {
            hp -= damage - def;
            if (hp < 0) {
//                leave();
//                set_visiable(false);
                setImage(dead_path);

                if (floor != null)
                    floor.set(null);
                alive = false;
            }
        } finally {
            lock.unlock();
        }
        return;
    }

    public void run() {
        while (is_alive()) {
            Object[] target_info = space.find_nearest(floor.getX(), floor.getY(), team_id);
            int[] target = {(Integer) target_info[0], (Integer) target_info[1]};
            String tname = (String) target_info[2];
            if (tname == null)
                break;
            int distance = abs(floor.getX() - target[0]) + abs(floor.getY() - target[1]);
            try {
                if (distance > atkrange)//walk
                {
                    int x, y;
                    if (abs(floor.getX() - target[0]) > abs(floor.getY() - target[1])) {
                        x = floor.getX() + sign(target[0] - floor.getX());
                        y = floor.getY();
                    } else {
                        y = floor.getY() + sign(target[1] - floor.getY());
                        x = floor.getX();
                    }

                    Thread.sleep((long) (500 / speed));
                    if (space.is_empty(x, y)) {
                        MoveCommand m = new MoveCommand(space, floor.getX(), floor.getY(), x, y, name);
                        m.execute();
                    }
                } else//atk
                {
                    AttackCommand m = new AttackCommand(space, floor.getX(), floor.getY(), target[0], target[1], space.rolln((int) atk), name, tname);
                    m.execute();
                    Thread.sleep(500);
                }
                Thread.sleep(500);
                Thread.yield();
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    int abs(int x) {
        return x > 0 ? x : -x;
    }

    int sign(int x) {
        return x > 0 ? 1 : -1;
    }

    public void set_visiable(boolean value) {
        box.setVisible(value);
    }
}

