import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.util.Random;

public class  Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller controller = new Controller(primaryStage);
        Scene scene= new Scene(controller.myPane, 1000, 700);
        primaryStage.setTitle("Java葫芦娃");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
