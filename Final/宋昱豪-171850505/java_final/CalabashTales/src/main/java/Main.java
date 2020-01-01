import Controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Group root = new Group();

    Controller controller;

    private Parent createContent() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/board.fxml"));
        this.root.getChildren().add((Node) loader.load(getClass().getResourceAsStream("fxml/board.fxml")));
        controller = loader.getController();
        controller.init();
        return this.root;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Calabash Tales");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("pic/MainIcon.png"));
        Scene scene = new Scene(createContent());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

