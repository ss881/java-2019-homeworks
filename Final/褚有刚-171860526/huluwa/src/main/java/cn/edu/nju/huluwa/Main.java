package cn.edu.nju.huluwa;

import cn.edu.nju.huluwa.util.ImageLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage) throws Exception {
        BorderPane bp = FXMLLoader.load(getClass().getResource("/fxml/scene.fxml"));
        Scene scene = new Scene(bp);
        scene.getStylesheets().add(Main.class.getResource("/css/scene.css").toExternalForm());
//        scene.setUserAgentStylesheet(Main.class.getResource("/css/scene.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("葫芦世界");
        primaryStage.getIcons().add(ImageLoader.getIcon());
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();
    }
}
