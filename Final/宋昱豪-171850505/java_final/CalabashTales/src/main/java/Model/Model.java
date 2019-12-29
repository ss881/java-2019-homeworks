package Model;

import Controller.Controller;
import Model.Bad.Bad;
import Model.Good.Good;
import Model.World.BattleGround;
import Model.World.Lives;
import Model.World.Position;
import Model.World.Replay;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.*;
import java.lang.management.ThreadInfo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Model implements Runnable {
    @Override
    public void run() {
        initThreads();
        System.out.print("battleground1\n");
            System.out.print("battleground2\n");
            while (true) {
                if (!good.stillAlive()) {
                    Image image = new Image("pic/fail.png");
                    ImageView s=new ImageView(image);
                    battleGround.setend(true);
                    battleGround.outcome(false);
                    good.killall();
                    bad.killall();
                    Platform.runLater(()->{
                        pane.getChildren().add(s);
                        s.setX(400);
                        s.setY(450);
                        s.setFitHeight(100);
                        s.setFitWidth(200);
                    });

                    break;
                } else if (!bad.stillAlive()) {
                    Image image = new Image("pic/win.png");
                    ImageView s=new ImageView(image);
                    battleGround.setend(true);
                    battleGround.outcome(true);
                    good.killall();
                    bad.killall();
                    Platform.runLater(()->{
                        pane.getChildren().add(s);
                        s.setX(400);
                        s.setY(450);
                        s.setFitHeight(100);
                        s.setFitWidth(200);
                    });
                    break;
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

    public static BattleGround battleGround;

    static {
        try {
            battleGround = new BattleGround(Controller.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ExecutorService exec = Executors.newCachedThreadPool();
    private Good good;
    private Bad bad;
    private AnchorPane pane;
    public Model(AnchorPane p,int num1,int num2) throws FileNotFoundException, UnsupportedEncodingException {
        battleGround.clear(Controller.file);
        Lives.init(battleGround.ground);
        bad = new Bad(battleGround.ground,num1);
        good = new Good(battleGround.ground,num2);
        this.pane=p;
        BackgroundImage myBI = new BackgroundImage(new Image("pic/map.jpg",1000,1000,true,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));
    }
    public void play()
    {
        System.out.print("first");
        for(Lives life: good.members){
            addImage(life);
        }
        for(Lives life: bad.members){
            addImage(life);
        }
    }
    public void replay(File file) throws IOException {

        Replay r=new Replay(file,pane);
        exec.execute(r);
    }
    public void addImage(Lives x) {

        if(pane==null)
        {
            System.out.println("pane null");
        }
        if(x.GetImage()==null)
        {
            System.out.println("x.Position="+x.GetPosition().x);
            System.out.println("image null");
        }
        if(x.GetLabel()==null)
        {

            System.out.println("label null");
        }
        pane.getChildren().add(x.GetImage());
        pane.getChildren().add(x.GetLabel());

    }


    private void initThreads(){
        exec.execute(good);
        exec.execute(bad);
        exec.execute(battleGround);
    }

}

