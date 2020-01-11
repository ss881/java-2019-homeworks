import annotation.Description;
import gamectrl.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
@Description(comment = "")
public class Main extends Application implements MainConfig {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle(MainConfig.name);
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();
        primaryStage.setFullScreenExitHint(MainConfig.hint);
        primaryStage.setFullScreen(true);
        MainControl.stage = primaryStage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}