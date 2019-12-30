package huluwa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        int width = 1500,height = 800;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        GameController controller = GameController.newInstance(width,height);
        Scene scene = new Scene(controller,width,height);
        stage.setTitle("葫芦娃大战妖精");
        stage.setScene(scene);
        controller.reset();
        stage.show();
        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(Main.class, args);
    }

}
