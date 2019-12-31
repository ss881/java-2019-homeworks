package controllers;

import battle.Bullet;
import battle.Formation;
import battle.Ground;
import battle.Status;
import config.Config;
import creature.Creature;
import creature.Good;
import creature.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import team.BadTeam;
import team.GoodTeam;
import team.Team;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Controller implements Initializable {

    @FXML
    private Canvas canvas;

    @FXML
    private Button startButton;

    @FXML
    private Button replayButton;

    @FXML
    private Button saveButton;

    private GoodTeam good;
    private BadTeam bad;
    private Ground ground;
    private Image backGround;
    private List<Object> list;
    public void initialize(URL location, ResourceBundle resources){
        Image bg = new Image("home.png");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(bg, 0, 0, Config.WIDTH, Config.HEIGHT);
        ground = new Ground();
        good = new GoodTeam(true);
        bad = new BadTeam(true);
    }

    private List<Object> draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(backGround, 0, 0, Config.WIDTH, Config.HEIGHT);
        List<Object> ret = new ArrayList<Object>();
        GoodTeam g1 = new GoodTeam();
        BadTeam b1 = new BadTeam();
        List<Bullet> bulletList = new ArrayList<Bullet>();
        synchronized (ground) {
            g1.copy(good);
            b1.copy(bad);
            drawCreature(good, State.DEAD);
            drawCreature(bad, State.DEAD);
            drawCreature(good, State.LIVE);
            drawCreature(bad, State.LIVE);
            for (Bullet bullet : ground.getBullets()) {
                bullet.draw(canvas.getGraphicsContext2D());
                Bullet b = new Bullet();
                b.copy(bullet);
                bulletList.add(b);
            }
            ret.add(g1);
            ret.add(b1);
            ret.add(bulletList);
        }
        return ret;
    }


    private void drawCreature(Team t, State state){
        List<Creature> list = t.getTeamMembers();
        for(Creature c: list){
            if(c.getState()==state){
                c.draw(canvas.getGraphicsContext2D());
            }
        }
    }

    public void pressStartButton(ActionEvent event){
        good.setCheererDead();
        bad.setCheererDead();
        good.setSoldiersDead();
        bad.setSoldiersDead();
        ground.clearBullet();
        try{
            TimeUnit.MILLISECONDS.sleep(1650);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        Formation.changShe(good.getSoldiers());
        Formation.fangYuan(bad.getSoldiers());
        good.setCheererLive();
        bad.setCheererLive();
        backGround = new Image("background.jpg");
        new Thread(new Runnable() {
            public void run() {
                ExecutorService exec = Executors.newCachedThreadPool();
                exec.execute(good);
                exec.execute(bad);
                final List<Object>ret = new ArrayList<Object>();
                exec.execute(new Thread(new Runnable() {
                    public void run() {
                        Status status = null;
                        while(true) {
                            List<Object> t;
                            t = draw();
                            ret.add(t);
                            try{
                                TimeUnit.MILLISECONDS.sleep(100);
                            }catch (InterruptedException e){
                                throw new RuntimeException(e);
                            }
                            synchronized (ground) {
                                if ((status=ground.whoWin())!= Status.RUNNING) {
                                    ret.add(draw());
                                    System.out.println(ground.whoWin());
                                    list = ret;
                                    list.add(0, ground.whoWin());
                                    try{
                                        TimeUnit.MILLISECONDS.sleep(100);
                                    }catch (InterruptedException e){
                                        throw new RuntimeException(e);
                                    }
                                    GraphicsContext gc = canvas.getGraphicsContext2D();
                                    Image win;
                                    if(status == Status.GOODWIN)win = new Image("win.png");
                                    else win = new Image("fail.png");
                                    gc.drawImage(win, 400, 240, 400, 240);
                                    break;
                                }
                            }
                        }
                    }
                }));
                exec.shutdown();
            }
        }).start();
    }
    public void pressReplayButton(ActionEvent event){
        backGround = new Image("background.jpg");
        System.out.println("replay pressed");
        try {
            FileChooser chooser = new FileChooser();
            Stage stage = new Stage();
            File file = chooser.showOpenDialog(stage);
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
            List l = (List)input.readObject();
            input.close();
            reDraw(l);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void pressSaveButton(ActionEvent event){
        if(list==null){
            System.out.println("没有可保存的游戏记录，请先开始游戏");
        }else{
            FileChooser chooser = new FileChooser();
            Stage stage = new Stage();
            File file = chooser.showSaveDialog(stage);
            if(file!=null){
                try {
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                    out.writeObject(list);
                    out.close();
                }catch (IOException e){
                    System.out.println("无该文件");
                }
            }
        }
    }
    @SuppressWarnings("unchecked")
    private void reDraw(final List list0){
        new Thread(new Runnable() {
            public void run() {
                for(Object obj: list0.subList(1, list0.size())){
                    List lst = (List)obj;
                    GoodTeam g = (GoodTeam) lst.get(0);
                    BadTeam b = (BadTeam)lst.get(1);
                    List<Bullet>list = (List<Bullet>)lst.get(2);
                    GraphicsContext gc = canvas.getGraphicsContext2D();
                    gc.drawImage(backGround, 0, 0, Config.WIDTH, Config.HEIGHT);
                    drawCreature(g, State.DEAD);
                    drawCreature(b, State.DEAD);
                    drawCreature(g, State.LIVE);
                    drawCreature(b, State.LIVE);
                    for (Bullet bullet : list) {
                        bullet.draw(canvas.getGraphicsContext2D());
                    }
                    try{
                        TimeUnit.MILLISECONDS.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                Status status = (Status)list0.get(0);
                Image image = status==Status.GOODWIN?new Image("win.png"):new Image("fail.png");
                canvas.getGraphicsContext2D().drawImage(image, 400, 240, 400, 240);
            }
        }).start();

    }

}
