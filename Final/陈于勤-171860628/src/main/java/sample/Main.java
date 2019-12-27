package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.canvas.Canvas;
import javafx.stage.*;

import javax.swing.*;
import java.io.File;


public class Main extends Application {
    Canvas _canvas;
    Controller _controller;
    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        _canvas = new Canvas(GroundMap.WIDTH, GroundMap.HEIGH);
        Group root = new Group();
        root.getChildren().add(_canvas);
        primaryStage.setTitle("葫芦娃大战妖精");
        primaryStage.setScene(new Scene(root, GroundMap.WIDTH, GroundMap.HEIGH));
        primaryStage.show();
        _controller = new Controller(primaryStage, _canvas);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
