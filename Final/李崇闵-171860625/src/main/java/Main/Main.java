package Main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {
    @Override

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Final");

        Group root=new Group();
        MainCanvas mainCanvas=new MainCanvas();
        root.getChildren().add(mainCanvas);

        Scene scene=new Scene(root,800,400);
        primaryStage.setScene(scene);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().getName().equals("L")){
                    FileChooser chooser = new FileChooser();
                    chooser.setTitle("打开文件");
                    chooser.setInitialDirectory(new File("record"));
                    chooser.getExtensionFilters().addAll(
                            new FileChooser.ExtensionFilter("所有文件", "*.*"));
                    File file = chooser.showOpenDialog(primaryStage);
                    try {
                        mainCanvas.replay(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else if (event.getCode().getName().equals("Space")) {
                    try {
                        mainCanvas.Start();
                    } catch (FileNotFoundException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else if(event.getCode().getName().equals("Enter"))
                {
                    try {
                        mainCanvas.newGame();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        primaryStage.show();

    }
    public static void main(String[]args){
        Application.launch(args);
    }
}
