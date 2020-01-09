package finalproject.fxview;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class LogChooseDialogController {
    @FXML
    Button button;
    File file = null;


    public void register(Stage stage) {
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Calabash v.s Goblin log file (*.cglog)"
                , "*.cglog");
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(filter);

        button.setOnAction(event -> {
            file = fileChooser.showOpenDialog(stage);
            stage.close();
        });
    }

    public File getFile() {
        return file;
    }
}
