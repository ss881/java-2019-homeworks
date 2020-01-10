package sample;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.*;

import java.io.File;

public class Controller {
    Stage _stage;
    GroundMap _map;
    Canvas _canvas;

    public Controller(Stage stage, Canvas canvas) {
        _stage = stage;
        _canvas = canvas;
        _map = new GroundMap(_canvas, false, false, "");
        _stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

            public void handle(KeyEvent event) {
                synchronized (_map) {
                    if (event.getCode() == KeyCode.SPACE) {
                        if (_map.isGameStart() == false && _map.isGameReview() == false) {
                            _map = new GroundMap(_canvas, true, false, null);
                        } else {
                            _map.switchSuspend();
                        }
                    } else if (event.getCode() == KeyCode.L) {
                        if (_map.isGameStart() == false && _map.isGameReview() == false) {
                            FileChooser file = new FileChooser();
                            file.setTitle("打开文件");
                            file.getExtensionFilters().add(
                                    new FileChooser.ExtensionFilter("葫芦文件", "*.gourd"));
                            file.setInitialDirectory(new File("src\\main\\resources\\record"));
                            File f = file.showOpenDialog(null);
                            if (f != null && f.exists())
                                _map = new GroundMap(_canvas, false, true, f.getAbsolutePath());
                        }
                    }
                }
            }
        });

    }
}
