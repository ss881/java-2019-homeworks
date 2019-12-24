import javafx.animation.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.util.*;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Controller {
    public static BorderPane myPane = null;
    public Background backGround = null;
    public Stage stage = null;
    public String savePath = "";
    public static boolean isReplay;
    Controller(Stage stage){
        myPane = new BorderPane();
        this.stage = stage;
        //读取背景图片
        Image img = new Image("backgroundIMG.png",900,900,true,true);
        BackgroundImage backImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        backGround = new Background(backImg);
        //初始化窗口
        initBorderPane();
    }
    public void initBorderPane(){
        if(myPane == null)//申请窗口
            myPane = new BorderPane();
        //设置背景图片
        myPane.setBackground(backGround);
        //添加按钮
        setButtons();
    }
    public ArrayList<Creature> gameStart() {
        this.isReplay = false;
        this.getSavePath();
        God.init();
        World world = new World();
        Creature.world = world;
        Creature.pane = myPane;
        Creature.savePath = this.savePath;
        String[] camp1Name={"爷爷","老四","老二","老三","老七","老大","老六","老五"};
        String[] camp2Name = {"蝎子精","蛇精","妖怪1","妖怪2","妖怪3","妖怪4","妖怪5"};
        //String[] camp1Name={"老四","老二","老三","老七"};
        //String[] camp2Name = {"蝎子精","蛇精","妖怪1"};
        ArrayList<Creature> camp1 = God.getCreatures(camp1Name);
        ArrayList<Creature> camp2 = God.getCreatures(camp2Name);
        God.makeFormation(camp1,"snake",6,5);
        God.makeFormation(camp2,"snake",2,15);
        ArrayList<Creature> allCreatures = new ArrayList<Creature>();
        allCreatures.addAll(camp1);
        allCreatures.addAll(camp2);
        return allCreatures;
    }
    public void gameRun(ArrayList<Creature> creatures) {
            int n = creatures.size();
            Creature[] arr = new Creature[n];
            for(int i = 0; i < n; i++)
                arr[i] = creatures.get(i);
            ExecutorService threadPool = Executors.newCachedThreadPool();
            for (Creature i : arr) {
                threadPool.submit(i);
                //threadPool.execute(i);
            }
            threadPool.shutdown();
            /*try {
                threadPool.awaitTermination(2,TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                System.out.println("超时");
            }*/
    }
    public void replay() {
        this.isReplay = true;
        God.init();
        World world = new World();
        Creature.world = world;
        Creature.pane = myPane;
        Creature.savePath = null;
        getLoadPath();
        if(savePath == null || savePath == "")
            return;
        try {
            FileReader fr = new FileReader(savePath);
            BufferedReader br = new BufferedReader(fr);
            String record;
            ArrayList<Creature> creatureArr = new ArrayList<Creature>();
            long startTime = -1;
            Timeline timeline = new Timeline();
            while((record = br.readLine()) != null) {
               String[] strArr = record.split(" ");
               String name = strArr[0];
               int x1 = Integer.parseInt(strArr[1]);
               int y1 = Integer.parseInt(strArr[2]);
               int x2 = Integer.parseInt(strArr[3]);
               int y2 = Integer.parseInt(strArr[4]);
               String alive = strArr[5];
               long time = Long.parseLong(strArr[6]);
               if(startTime == -1)
                   startTime = time;
               if(x1 == -1 && y1 == -1) {
                    Creature a = God.getCreature(name);
                    creatureArr.add(a);
                    ImageView img = a.getCreatureIMG();
                    ImageTranslation.translation(timeline,img,x1,y1,x2,y2,(int)(time-startTime));
               }
               else {
                   int size = creatureArr.size();
                   int i = 0;
                   for(; i < size; i++) {
                       Creature a = creatureArr.get(i);
                       if(a.tellName().equals(name) == true)
                           break;
                   }
                   if(i >= size)
                       continue;
                   Creature a = creatureArr.get(i);
                   if(alive.equals("false") == true)
                       ImageToDead.dead(timeline,a,x1,y1,(int)(time-startTime));
                   else
                       ImageTranslation.translation(timeline,a.getCreatureIMG(),x1,y1,x2,y2,(int)(time-startTime));
               }
            }
            timeline.play();
        } catch(IOException e) {
            System.out.println("读取存档失败");
        }
    }

    private void setButtons() {
        ButtonBar buttonBar = new ButtonBar();
        Button startButton = new Button();
        Button replayButton = new Button();
        buttonBar.getButtons().add(replayButton);
        buttonBar.getButtons().add(startButton);

        startButton.setText("开始游戏");
        startButton.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ArrayList<Creature> creatures = gameStart();
                        gameRun(creatures);
                    }
                }
        );
        myPane.setFocusTraversable(true);
        myPane.addEventFilter(KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if(event.getCode() == KeyCode.SPACE) {
                            ArrayList<Creature> creatures = gameStart();
                            gameRun(creatures);
                        }
                    }
                }
        );
        /*startButton.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if(event.getCode() == KeyCode.SPACE) {
                            gameStart();
                            gameRun();
                        }
                    }
                }
        );*/

        replayButton.setText("回放记录");
        replayButton.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        replay();
                    }
                }
        );
        myPane.addEventFilter(KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if(event.getCode() == KeyCode.L) {
                            replay();
                        }
                    }
                }
        );
        /*replayButton.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if(event.getCode() == KeyCode.LEFT) {
                            replay();
                        }
                    }
                }
        );*/

        //将按钮栏绑定至窗口上方
        myPane.setBottom(buttonBar);
    }
    private void getSavePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt", "*.txt")
        );
        fileChooser.setTitle("请选择存储路径");
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            savePath = file.getAbsolutePath();
        }
    }
    private void getLoadPath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt", "*.txt")
        );
        fileChooser.setTitle("请选择存档");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            savePath = file.getAbsolutePath();
        }
    }
}
