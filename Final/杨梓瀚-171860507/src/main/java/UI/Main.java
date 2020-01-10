package UI;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Main extends Application {
    private static Media media = new Media(Paths.get("src/main/resources/music/background.mp3").toUri().toString());

    private static MediaPlayer mediaPlayer = new MediaPlayer(media);

    private static final int HEIGHT = 360;

    private static final int WIDTH = 720;

    private static BooleanProperty booleanProperty = new SimpleBooleanProperty();

    public static void setBooleanProperty(boolean b){
        booleanProperty.set(b);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));

        //AnchorPane root = new AnchorPane();

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(Paths.get("src/main/resources/css/style.css").toUri().toString());
        primaryStage.setTitle("葫芦娃大战妖精启动器");
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        booleanProperty.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                mediaPlayer.stop();
                System.out.println("start game");
            }
        });
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
