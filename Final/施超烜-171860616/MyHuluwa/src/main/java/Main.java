import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {
        try{
            Parent root= FXMLLoader.load(getClass().getResource("Huluwa.fxml"));
            Scene scene=new Scene(root);
            primaryStage.setTitle("葫芦兄弟大战妖精 by scx");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent event) {
                    System.exit(0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        launch(args);
    }
}
