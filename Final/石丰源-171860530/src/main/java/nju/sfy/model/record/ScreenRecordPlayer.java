package nju.sfy.model.record;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import nju.sfy.model.Field;
import nju.sfy.model.Field;

import java.io.File;
public class ScreenRecordPlayer extends Application{
    private String storePath;
    private Field field;

    public ScreenRecordPlayer(String path, Field field) throws Exception {
        System.out.println(path);
        storePath = path;
        this.field = field;
        start(new Stage());
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();

        Canvas canvas = new Canvas(1200, 800);
        gridPane.add(canvas, 0, 0);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                field.setReplayMode(false);
            }
        });

        primaryStage.setTitle("回放");
        primaryStage.setScene(new Scene(gridPane,gridPane.getPrefWidth(), gridPane.getPrefHeight()));
        primaryStage.show();

        new Thread(new Player(canvas, storePath, field)).start();
    }
    public void main(String[] args) {
        launch(args);
    }
}
class Player implements Runnable{
    private String storeDir;
    private Canvas canvas;
    private Field field;
    private int i = 0;
    public Player(Canvas canvas, String storeDir, Field field){
        this.canvas = canvas;
        this.storeDir = storeDir;
        this.field = field;
    }
    @Override
    public void run() {
        int i = 0;
        while (true) {
            try {
                File file = new File(storeDir + File.separator+i+".png");
                if(!file.exists()){
                    field.setReplayMode(false);
                    return ;
                }
                Image image = new Image(file.toURI().toURL().toString());
                canvas.getGraphicsContext2D().drawImage(image, 0, 0);

                i = i + 1;
                Thread.sleep(1000);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}