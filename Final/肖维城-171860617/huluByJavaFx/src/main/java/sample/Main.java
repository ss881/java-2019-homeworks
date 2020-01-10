package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import location.Position;
import record.Player;

import war.BattleField;

import java.io.File;

//enum STATE{START,PLAYING,END};

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        /*Image image = new Image("background (1).jpg");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);*/

        BattleField bf = BattleField.getInstance();

        primaryStage.setTitle("HuluBros");
        Scene scene = new Scene(bf, 300, 275);

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case SPACE:{
                    bf.setPlay();
                    bf.play();
                    break;
                }
                case R:{
                    bf.reStart();
                    break;
                }
                case L:{
                    bf.reStart();
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Open Record File");
                    fileChooser.getExtensionFilters().addAll(
                            new FileChooser.ExtensionFilter("Record Files", "*.rcd"),
                            new FileChooser.ExtensionFilter("All Files", "*.*"));
                    File selectedFile = fileChooser.showOpenDialog(primaryStage);
                    if (selectedFile != null) {
                        Player.getInstance().setFilePath(selectedFile.getPath());
                        System.out.println(selectedFile.getPath());
                        Thread t = new Thread(Player.getInstance());
                        t.start();
                    }
                    break;
                }
                default:{

                    break;
                }
            }
        });
        primaryStage.setScene(scene);
        primaryStage.setWidth(Position.P_WIDTH * BattleField.FIELD_WIDTH+50);
        primaryStage.setHeight(Position.P_HEIGHT * BattleField.FIELD_HEIGHT+50);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
