import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.Controller;

import java.io.File;
import java.io.IOException;


public class Main extends Application {
    static private Scene scene;
    static private Controller controller;

    public static void main(String[] args) {
        controller=new Controller();
        scene=controller.getScene();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        KeyCombination kc1 = new KeyCodeCombination(KeyCode.getKeyCode("Space"));
        scene.getAccelerators().put(kc1, new Runnable() {
            @Override
            public void run() {
                controller.startBattle();
            }
        });

        KeyCombination kc2 = new KeyCodeCombination(KeyCode.L);
        scene.getAccelerators().put(kc2, new Runnable() {
            @Override
            public void run() {
                if(!controller.getState()){
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
                            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
                    fileChooser.getExtensionFilters().add(extFilter);
                    File file = fileChooser.showOpenDialog(primaryStage);
                    try {
                        controller.replay(file);
                    } catch (IOException | InterruptedException|NullPointerException e) {
                        System.out.println("didn't choose any file, please try again!");
                    }
                }
            }
        });


        primaryStage.setScene(scene);
        primaryStage.setTitle("葫芦娃大战蛇精");
        primaryStage.setMaxWidth(818.0);
        primaryStage.setMinWidth(818.0);
        primaryStage.setMaxHeight(647.0);
        primaryStage.setMinHeight(647.0);
        primaryStage.show();
    }
}