package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage welcomestage;   //当前的欢迎窗口
    public static Stage gamestage;  //当前的游戏窗口
    public static Game thisgame;    //当前的游戏
    public static View gameview;    //当前的游戏画面

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Media bgm = new Media("../resources/bgm.mp3");
        //MediaPlayer player = new MediaPlayer(bgm);
        //player.play();
        welcomestage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../resources/welcome.fxml"));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root, 1600, 900));
        primaryStage.show();
    }
}
