import com.e.view.MyPaint;
import com.b.control.Control;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OOPReflection extends Application{

    private static final int MAPHEIGHT = 640;
    private static final int MAPWIDTH = 640;
    private Control control;                    //程序逻辑控制

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("葫芦兄弟");
        primaryStage.getIcons().add(new Image("SRCFile/hlw.jpg"));
        primaryStage.setWidth(MAPWIDTH + 18);
        primaryStage.setHeight(MAPHEIGHT + 40);

        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, MAPWIDTH, MAPHEIGHT);
        control = new Control(MAPWIDTH, MAPHEIGHT);
        anchorPane.getChildren().add((MyPaint)control.getCanvas());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop(){
        ((MyPaint)control.getCanvas()).drawMapStop();
    }
}