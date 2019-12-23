package nju.sfy;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import nju.sfy.model.record.ScreenRecordPlayer;
import nju.sfy.model.record.ScreenRecorder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class MyApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5));
        gridPane.setHgap(50);
        gridPane.setVgap(50);

        File file = new File("src/main/resources/background2.png");
        Image image = new Image(file.toURI().toURL().toString());

        Canvas canvas = new Canvas(image.getWidth(), image.getHeight());
        gridPane.add(canvas, 0, 0);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, 0, 0);

        BorderPane borderPane = new BorderPane();
        Button startButton = new Button("开始战斗");
        Button openButton = new Button("回放记录");

        HBox hBox = new HBox(50);
        hBox.getChildren().add(startButton);
        hBox.getChildren().add(openButton);
        borderPane.setTop(hBox);

        ScrollPane scrollPane = new ScrollPane();
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        scrollPane.setMaxWidth(400);
        scrollPane.setContent(textArea);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        borderPane.setCenter(scrollPane);

        gridPane.add(borderPane, 1, 0);

        //是内部类的解决方案么
        final Controller[] controller = {new Controller(canvas, textArea, gridPane, primaryStage)};

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!controller[0].isRunning() && !controller[0].isReplayMode()) {
                    controller[0].setRunning(true);
                    if(controller[0].isCompleted()){
                        try {
                            controller[0] = new Controller(canvas, textArea, gridPane, primaryStage);
                            controller[0].setCompleted(false);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("开始战斗");
                    try {
                        controller[0].play();
                    } catch (MalformedURLException | AWTException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        openButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!controller[0].isRunning() && !controller[0].isReplayMode()){
                    controller[0].setReplayMode(true);
                    System.out.println("回放记录");
                    try {
                        DirectoryChooser directoryChooser = new DirectoryChooser();
                        File directory = directoryChooser.showDialog(new Stage());
                        ScreenRecordPlayer srp = null;
                        if(directory != null)
                            srp = new ScreenRecordPlayer(directory.getPath(), controller[0].getField());
                        else{
                            controller[0].setReplayMode(false);
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });

        primaryStage.setTitle("葫芦娃");
        primaryStage.setScene(new Scene(gridPane,gridPane.getPrefWidth(), gridPane.getPrefHeight()));
        primaryStage.show();

    }


    public void main(String[] args) {
        launch(args);
    }
}
