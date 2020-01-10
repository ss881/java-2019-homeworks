package UI;
/*
    controller
    定义登录界面
 */


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class StartController {
    @FXML
    private Button buttonStart;

    @FXML
    private Button buttonExit;

    @FXML
    private void initialize(){

    }

    @FXML
    private void handleActionStart(ActionEvent actionEvent) throws IOException {
        ((Stage)buttonStart.getScene().getWindow()).close();
        Main.setBooleanProperty(true);
        MainWindow mainWindow  = new MainWindow();
        Stage stage = new Stage();
        mainWindow.start(stage);
    }

    @FXML
    private void handleActionExit(ActionEvent actionEvent){
        Platform.exit();
    }

}
