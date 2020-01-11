package UI;

import Battle.Fight;
import Record.Record;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class MainWindow extends Application {
    private static final int HEIGHT = 720;

    private static final int WIDTH = 680;

    public static void main(String[] args) {
        Application.launch(args);
    }

    public static boolean start = false;

    public static BorderPane pane;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainInterface.fxml"));
        pane = loader.load();
        pane.setId("pane");
        GameController controller = loader.getController();

        Scene scene = new Scene(pane);
        scene.getStylesheets().add(Paths.get("src/main/resources/css/main.css").toUri().toString());
        controller.setPane(pane);
        controller.setStage(stage);

        controller.prepare();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE && !start){
                    start = true;
                    controller.startGame();
                }
            }
        });


        stage.setTitle("葫芦娃大战妖精精简版");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
}