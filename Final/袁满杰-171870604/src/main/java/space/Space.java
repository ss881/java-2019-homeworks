package space;


import command.Command;
import command.LeaveCommand;
import command.WaitCommand;
import creature.*;
import exception.CharacterErrorException;
import exception.CharacterNotFound;
import exception.HuluwaOutOfNumException;
import exception.ZhenfaIDOutOfNumException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.util.Duration;
import team.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Space extends Group {
    public HeroTeam heros;
    public VillainTeam villains;
    public Tile[][] floor;
    public int win;
    private Lock lock = new ReentrantLock();
    private ArrayList<Command> logs;
    private int zhen1, zhen2;
    Random r = new Random();
    static long time = System.currentTimeMillis();
    int N;
    ImageView background;

    public Space(int n) throws HuluwaOutOfNumException {
        win = 0;
        N = n;
        floor = new Tile[n][n];
        logs = new ArrayList<Command>();
        Image image = new Image("image/floor.jpg", 100, 100, true, true);
        background=new ImageView(new Image("image/background.jpg"));
        background.setTranslateX(-50);
        background.setTranslateZ(25);
        getChildren().add(background);

        PhongMaterial floorMaterial = new PhongMaterial();
        floorMaterial.setDiffuseMap(image);
        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i >= N / 2; i--) {
                floor[i][j] = new Tile(i, j);
                getChildren().add(floor[i][j].getBox());
            }

            for (int i = 0; i < N / 2; i++) {
                floor[i][j] = new Tile(i, j);
                getChildren().add(floor[i][j].getBox());
            }
        }
        heros = new HeroTeamFactory(this).create();
        villains = new VillainTeamFactory(this).create();
        heros.born();
        villains.born();
    }

    public int getN() {
        return N;
    }

    public int roll() {
        return r.nextInt(6) + 1;
    }

    public void save_zhenfa(int x, int y) {
        zhen1 = x;
        zhen2 = y;
    }

    public void load_zhenfa(File path) throws IOException, ClassNotFoundException, CharacterErrorException, ZhenfaIDOutOfNumException, HuluwaOutOfNumException {
        FileInputStream fs = new FileInputStream(path);
        ObjectInputStream os = new ObjectInputStream(fs);
        zhen1 = (Integer) os.readObject();
        zhen2 = (Integer) os.readObject();
        os.close();
        heros.zhenfa(zhen1);
        villains.zhenfa(zhen2);
    }

    public void init() throws HuluwaOutOfNumException, ZhenfaIDOutOfNumException, CharacterErrorException {
        lock = new ReentrantLock();
        logs.clear();
        win = 0;
        getChildren().clear();
        getChildren().add(background);
        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i >= N / 2; i--) {
                floor[i][j] = new Tile(i, j);
                getChildren().add(floor[i][j].getBox());
            }

            for (int i = 0; i < N / 2; i++) {
                floor[i][j] = new Tile(i, j);
                getChildren().add(floor[i][j].getBox());
            }
        }
        heros = new HeroTeamFactory(this).create();
        villains = new VillainTeamFactory(this).create();
        heros.born();
        villains.born();
    }

    public int rolln(int n) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += roll();
        }
        return res;
    }

    public void show() {
        for (int i = 0; i < this.N; i++)
            System.out.print("--");
        System.out.println();
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                this.floor[j][i].show();
            }
            System.out.print('\n');
        }
        for (int i = 0; i < this.N; i++)
            System.out.print("--");
        System.out.println();
    }

    public boolean move(int src_x, int src_y, int dest_x, int dest_y, String name,boolean force) {
//        lock.lock();
        try {
            if(!force)
                if(!is_empty(dest_x,dest_y))
                    return false;
            Creature target = null;
            target = heros.find(name);
            if (target == null)
                target = villains.find(name);
            if (target == null)
                throw new CharacterNotFound();
//            target.lock();
            KeyValue kv1x = new KeyValue(target.getBox().translateXProperty(), target.getBox().getTranslateX());
            KeyValue kv1z = new KeyValue(target.getBox().translateZProperty(), target.getBox().getTranslateZ());
            KeyFrame kf1 = new KeyFrame(Duration.seconds(0), kv1x, kv1z);
            KeyValue kv2x = new KeyValue(target.getBox().translateXProperty(), floor[dest_x][dest_y].getBox().getTranslateX());
            KeyValue kv2z = new KeyValue(target.getBox().translateZProperty(), floor[dest_x][dest_y].getBox().getTranslateZ()*2+10);
            KeyFrame kf2 = new KeyFrame(Duration.seconds(0.2), kv2x, kv2z);
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(kf1, kf2);
//            timeline.setAutoReverse(true);
            timeline.setCycleCount(1);

            timeline.play();
            Tile.swap(floor[src_x][src_y], floor[dest_x][dest_y]);
//            Thread.sleep(200);
//            target.unlock();
//        } catch (NullPointerException e) {

        } catch (CharacterNotFound characterNotFound) {
            characterNotFound.printStackTrace();
        } finally {
//            lock.unlock();
        }
        return true;
    }

    public void clear_log() {
        logs.clear();
        time = System.currentTimeMillis();
    }

    public void lock() {
        lock.lock();
//        System.out.println("Locked!");
    }

    public void unlock() {
        lock.unlock();
//        System.out.println("Unlocked!");
    }

    public void leave(int x, int y, String name) {
//        lock.lock();
        try {
            Creature target = null;
            target = heros.find(name);
            if (target == null)
                target = villains.find(name);
            if (target == null)
                throw new CharacterNotFound();
            target.lock();
            target.set_visiable(false);
            if (target.floor != null)
                target.floor.set(null);
            target.unlock();
        } catch (CharacterNotFound characterNotFound) {
            characterNotFound.printStackTrace();
        } finally {
//            lock.unlock();
        }
    }

    public float attack(int src_x, int src_y, int dest_x, int dest_y, float damage, String src_name, String dest_name) {
        float hurt = 0;
        try {
            Creature target = null,source=null;
            source = heros.find(src_name);
            if (source == null)
                source = villains.find(src_name);
            target = heros.find(dest_name);
            if (target == null)
                target = villains.find(dest_name);
            if (target == null)
                throw new CharacterNotFound();
//            new Thread(source.bullet.send(src_x,src_y,dest_x,dest_y)).start();
            Box temp=source.bullet.box;
            KeyValue kv1x = new KeyValue(temp.translateXProperty(), floor[src_x][src_y].getBox().getTranslateX());
            KeyValue kv1z = new KeyValue(temp.translateZProperty(), floor[src_x][src_y].getBox().getTranslateZ());
            KeyValue kv1w = new KeyValue(temp.visibleProperty(), true);
            KeyFrame kf1 = new KeyFrame(Duration.seconds(0), kv1x, kv1z,kv1w);
            KeyValue kv2x = new KeyValue(temp.translateXProperty(), floor[dest_x][dest_y].getBox().getTranslateX());
            KeyValue kv2z = new KeyValue(temp.translateZProperty(), floor[dest_x][dest_y].getBox().getTranslateZ()*2+10);
            KeyFrame kf2 = new KeyFrame(Duration.seconds(0.1), kv2x, kv2z);
            KeyValue kv3x = new KeyValue(temp.visibleProperty(), false);
            KeyFrame kf3 = new KeyFrame(Duration.seconds(0.12), kv3x);
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(kf1, kf2,kf3);
            timeline.setCycleCount(1);
            timeline.play();
            Thread.sleep(100);
            if(!target.is_alive())
                return 0;
            hurt = target.getAttack(damage);

            return hurt;
//        } catch (NullPointerException e) {
//            return 0;
//        }

        } catch (CharacterNotFound characterNotFound) {
            characterNotFound.printStackTrace();
            return 0;
        } catch (InterruptedException characterNotFound) {
            characterNotFound.printStackTrace();
            return 0;
        }
    }

    public void attack(int src_x, int src_y, int dest_x, int dest_y, float damage, float defence, String src_name, String dest_name) {
        try {
            Creature target = null,source=null;
            source = heros.find(src_name);
            if (source == null)
                source = villains.find(src_name);
            target = heros.find(dest_name);
            if (target == null)
                target = villains.find(dest_name);
            if (target == null)
                throw new CharacterNotFound();
            Box temp=source.bullet.box;
            KeyValue kv1x = new KeyValue(temp.translateXProperty(), floor[src_x][src_y].getBox().getTranslateX());
            KeyValue kv1z = new KeyValue(temp.translateZProperty(), floor[src_x][src_y].getBox().getTranslateZ());
            KeyValue kv1w = new KeyValue(temp.visibleProperty(), true);
            KeyFrame kf1 = new KeyFrame(Duration.seconds(0), kv1x, kv1z,kv1w);
            KeyValue kv2x = new KeyValue(temp.translateXProperty(), floor[dest_x][dest_y].getBox().getTranslateX());
            KeyValue kv2z = new KeyValue(temp.translateZProperty(), floor[dest_x][dest_y].getBox().getTranslateZ()*2+10);
            KeyFrame kf2 = new KeyFrame(Duration.seconds(0.1), kv2x, kv2z);
            KeyValue kv3x = new KeyValue(temp.visibleProperty(), false);
            KeyFrame kf3 = new KeyFrame(Duration.seconds(0.12), kv3x);
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(kf1, kf2,kf3);
            timeline.setCycleCount(1);
            timeline.play();
            Thread.sleep(100);
            if(!target.is_alive())
                return;
            target.getAttack(damage, defence);
//            floor[dest_x][dest_y].get().getAttack(damage, defence);
        } catch (CharacterNotFound characterNotFound) {
            characterNotFound.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void add_log(Command c) {
//        lock.lock();
        try {
            long t = System.currentTimeMillis();
            logs.add(new WaitCommand(this, t - time));
            logs.add(c);
            time = t;
        } finally {
//            lock.unlock();
        }
    }

    public void save(File path) throws IOException {
        FileOutputStream fs = new FileOutputStream(path);
        ObjectOutputStream os = new ObjectOutputStream(fs);
        os.writeObject(zhen1);
        os.writeObject(zhen2);
        os.writeObject(logs);
        os.close();
    }

    public Runnable load(File path) throws IOException, ClassNotFoundException, InterruptedException, CharacterErrorException, ZhenfaIDOutOfNumException, HuluwaOutOfNumException {

        System.out.println("----LOAD START!-----");

        FileInputStream fs = new FileInputStream(path);
        ObjectInputStream os = new ObjectInputStream(fs);
        zhen1 = (Integer) os.readObject();
        zhen2 = (Integer) os.readObject();
        final ArrayList<Command> history = (ArrayList<Command>) os.readObject();
        os.close();
//        heros.zhenfa(zhen1);
//        villains.zhenfa(zhen2);
        final Space temp = this;
        return new Runnable() {
            @Override
            public void run() {
                for (Command i : history) {
                    try {
                        i.set_receiver(temp);
                        if (!(i instanceof WaitCommand))
                            temp.lock();
                        if (i instanceof LeaveCommand)
                            continue;
                        i.execute();
//                        show();
                    } catch (Exception e) {
                        temp.unlock();
                        try {
                            throw e;
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }


                }
//                System.out.println("Finished!");
            }
        };

    }


    public boolean is_empty(int x, int y) {
        return floor[x][y].get() == null;
    }

    public Object[] find_nearest(int x, int y, int src_team) {
        Team temp = null;
        if (src_team == 1)
            temp = heros;
        else
            temp = villains;
        int min = 1000000;
        Object[] argmin = {x, y, null};
        for (Creature i : temp) {
            if (i.is_alive()) {
                int[] t = i.get_pos();
                int distance = abs(x - t[0]) + abs(y - t[1]);
                if (distance < min) {
                    min = distance;
                    argmin[0] = t[0];
                    argmin[1] = t[1];
                    argmin[2] = i.getName();
                }
            }
        }
        return argmin;
    }

    int abs(int x) {
        return x > 0 ? x : -x;
    }
}
