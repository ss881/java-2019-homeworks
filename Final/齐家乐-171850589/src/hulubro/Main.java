package hulubro;

import hulubro.controller.Controller;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        GridPane root= layoutinit();
        Node result = null;
        ObservableList<Node> childrens = root.getChildren();

        for (Node node : childrens) {
            if(GridPane.getRowIndex(node) == 1 && GridPane.getColumnIndex(node) == 1) {
                result = node;
                break;
            }
        }

        Controller controller = new Controller((GridPane) result);

        Scene scene = new Scene(root,1600 , 950);

        Node finalResult1 = result;
        scene.setOnKeyPressed(event -> {
            if(event.getCode()==KeyCode.SPACE){
                System.out.println("space");
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save");
                File file = fileChooser.showSaveDialog(primaryStage);
                if (file != null) {
                    controller.play(file);
                }
            }else if(event.getCode()==KeyCode.L) {
                System.out.println("L");
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open");
                File file = fileChooser.showOpenDialog(primaryStage);
                try {
                    controller.replay(file);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("pic/Hulu.png")));
        primaryStage.setTitle("葫芦娃大战小妖精 @Jiale Qi");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private GridPane layoutinit(){
        GridPane ro = new GridPane();

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        col1.setPercentWidth(5);
        col2.setPercentWidth(90);
        col3.setPercentWidth(5);

        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        row1.setPercentHeight(6);
        row2.setPercentHeight(88);
        row3.setPercentHeight(6);

        ro.setStyle("-fx-background-color: f0cf85");
        ro.getColumnConstraints().addAll(col1,col2,col3);
        ro.getRowConstraints().addAll(row1,row2,row3);

        GridPane root = new GridPane();
        ro.add(root,1,1);

        final int M = 11 ;
        final int N = 18 ;
        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col ++) {
                StackPane square = new StackPane();
                String color ;
                if ((row + col) % 2 == 0) color = "#e7f0c3";
                else color = "#a4d4ae";
                square.setStyle("-fx-background-color: "+color+";");
                root.add(square, col, row);
            }
        }
        for (int i = 0; i < N; i++) {
            root.getColumnConstraints().add(new ColumnConstraints(80));

        }
        for (int i = 0; i < M; i++) {
            root.getRowConstraints().add(new RowConstraints(80));

        }
        System.out.println("layoutinit");
        return ro;
    }
}
