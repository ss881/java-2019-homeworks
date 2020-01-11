package GUI;

import Field.TwoDSpace;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    private TwoDSpace tds;
    private LayoutControl layoutControl;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = getClass().getResource("design.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        fxmlLoader.load();
        layoutControl = fxmlLoader.getController();
        tds = new TwoDSpace(layoutControl);
        Parent root = fxmlLoader.getRoot();
        Scene scene = new Scene(root, 450, 450);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!tds.isMode_battle() && !tds.isMode_replay()) {
                    if (event.getCode() == KeyCode.SPACE) {
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("Save");
                        File file = fileChooser.showOpenDialog(stage);
                        if (file != null) {
                            tds.setFile(file);
                            tds.startBattle();
                        }
                    } else if (event.getCode() == KeyCode.L) {
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("Open");
                        File file = fileChooser.showOpenDialog(stage);
                        tds.replay(file);
                    }
                }
            }
        });
        stage.setTitle("Final Battle");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                tds.closeFile();
                System.exit(0);
            }
        });
    }
}
