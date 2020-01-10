import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;

public class App extends Application {
    static ImageView iv;
    static Group root;
    static boolean runned;
    @Override
    public void start(Stage stage) {
        root = new Group();
        Scene scene = new Scene(root, 700, 700, Color.WHEAT);

        catchKey(scene);

        stage.setTitle("calabash_brothers");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    void  catchKey(Scene scene){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if(ke.getCode()== KeyCode.SPACE&&!runned) {
                    try {
                        runned=true;
                        BattleControl.go();
                    }catch(Exception e){
                        System.err.println(e);
                    }
                }
                if(ke.getCode()==KeyCode.L&&!runned){
                    try{
                        runned=true;
                        BattleControl.rego();
                    }catch(Exception e){
                        System.err.println(e);
                    }
                }
            }
        });
    }

    static void runLater(ImageView iv,int ox,int oy,int tx,int ty){
        Timeline t=new Timeline();
        t.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,new KeyValue(iv.xProperty(),ox*100)),
                new KeyFrame(new Duration(100),new KeyValue(iv.xProperty(),tx*100)),
                new KeyFrame(Duration.ZERO,new KeyValue(iv.yProperty(),oy*100)),
                new KeyFrame(new Duration(100),new KeyValue(iv.yProperty(),ty*100))
        );
        t.play();
    }
}