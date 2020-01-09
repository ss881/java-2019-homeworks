package cn.whh;

import cn.whh.controller.PrimaryController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
        primaryStage.setTitle("My Application");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();*/
        //FXMLLoader fxmlLoader = new FXMLLoader();
        //fxmlLoader.setLocation(this.getClass().getResource("/primary.fxml"));
        FXMLLoader fxmlLoader=new FXMLLoader(this.getClass().getResource("/primary.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Huluwa VS Monster");
        Scene scene = new Scene(root,1000,600);
        scene.getStylesheets().addAll(this.getClass().getResource("/primary.css").toExternalForm());
        PrimaryController controller=fxmlLoader.getController();
        controller.initController(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                //controller.serializeCreatures("./src/main/resources/save1.history");
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

}