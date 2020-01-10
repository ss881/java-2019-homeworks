package game;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Game {
    public static boolean isplay = false; //是否在播放录像
    public static boolean running = false;  //游戏是否在运行
    public static boolean pause = false;
    public static Group group;

    public Game() throws Exception {
        Main.gamestage = new Stage();
        Parent game = FXMLLoader.load(getClass().getResource("../resources/game.fxml"));

        Main.gameview = new View();
        group = new Group();
        group.getChildren().add(game);
        //Main.gameview.canvas.setLayoutX(0);
        //Main.gameview.canvas.setLayoutY(0);
        group.getChildren().add(Main.gameview.canvas);

        Main.gamestage.setScene(new Scene(group, 1600, 900));
        Main.gamestage.setTitle("CalabashGame");

        Main.gamestage.setOnCloseRequest(new EventHandler<WindowEvent>() {  //重载game窗口关闭处理程序
            @Override
            public void handle(WindowEvent event) {
                if (running) {
                    running = false;
                    System.out.println("游戏在未结束时退出，删除日志文件" + Log.nowlog.toString());
                    Log.nowlog.delete();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("警告");
                    alert.setHeaderText("游戏在未结束时退出，已删除日志文件");
                    alert.show();
                }
            }
        });
    }

    public static void end(String s)   //游戏结束
    {
        //善后工作

        running = false;
        isplay = false;
        for (Thread t : GameRun.threads)
            t.interrupt();

        Platform.runLater(() -> {
            Main.gamestage.close();
            Main.welcomestage.show();
        });

        if (running) {
            System.out.println("录像已保存");
            System.out.println("游戏结束");

            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("游戏结束");
                alert.setContentText(s);
                alert.setHeaderText("录像已保存");
                alert.show();
            });


        } else {
            System.out.println("录像播放完毕");

            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("游戏结束");
                alert.setHeaderText("录像播放完毕");
                alert.show();
            });
        }
    }

    public static int getRandom(int bound) throws IOException {
        //游戏中获取随机数的途径，根据是否isplay选择返回随机数或读取随机数
        if (isplay) {
            int ret = (int) Log.data.get(0);
            Log.data.remove(0);
            return ret;
        } else {
            Random r = new Random();
            int ret = r.nextInt(bound);
            //将随机因子写入log
            Log.writeLog(Integer.toString(ret));
            return ret;
        }
    }

    public void run() {  //新游戏
        running = true;
        try {
            Log.createLog();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.gamestage.show();
        //开始新游戏，在游戏时产生随机因子并写入log
        Thread t = new Thread(new GameRun());
        t.start();
    }

    public void play(File file) throws IOException { //播放录像
        isplay = true;
        Main.gamestage.show();
        Log.readLog(file);
        //调用Game的getRandom方法从日志获取随机因子
        Thread t = new Thread(new GameRun());
        t.start();
    }
}
