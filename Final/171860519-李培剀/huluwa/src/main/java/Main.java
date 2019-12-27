
import creature.Color;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("HuLuwa Fight!");
        primaryStage.setOnCloseRequest(event->System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("megumi.jpg"));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
