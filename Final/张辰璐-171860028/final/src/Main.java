import gui.StartPageController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("葫芦大战妖精");
        StartPageController startPageController = new StartPageController();
        startPageController.init(primaryStage);
    }
}