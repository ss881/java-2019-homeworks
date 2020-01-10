package cn.mhj.Controller;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuController {
  public static final int WIDTH = 1280, HEIGHT = 720;

  @FXML private Button startBtn;

  @FXML public Button replayBtn;

  @FXML public Button quitBtn;

  private BattlefieldController battlefieldController;

  public void init(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("../FXML/Menu.fxml"));
    Scene scene = new Scene(root, WIDTH, HEIGHT);
    stage.setScene(scene);
    stage.show();
  }

  private void startGame() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/Battlefield.fxml"));
    Parent root = fxmlLoader.load();
    battlefieldController = fxmlLoader.getController();
    Scene scene = new Scene(root, WIDTH, HEIGHT);
    Stage stage = (Stage) startBtn.getScene().getWindow();
    stage.setScene(scene);
    stage.setOnCloseRequest(
        event -> {
          System.out.println("detected windows close");
          System.exit(0);
        });
    stage.show();
  }

  @FXML
  public void startBtnEntered(MouseEvent mouseEvent) {
    startBtn.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/game_start_1.png')");
  }

  @FXML
  public void startBtnExited(MouseEvent mouseEvent) {
    startBtn.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/game_start_0.png')");
  }

  @FXML
  public void startBtnClicked(MouseEvent mouseEvent) throws IOException {
    startGame();
  }

  //  @FXML
  //  public void replayBtnEntered(MouseEvent mouseEvent) {
  //    replayBtn.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/replay_1.png')");
  //  }
  //
  //  @FXML
  //  public void replayBtnExited(MouseEvent mouseEvent) {
  //    replayBtn.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/replay_0.png')");
  //  }
  //
  //  @FXML
  //  public void replyBtnClicked(MouseEvent mouseEvent) {}

  @FXML
  public void quitBtnEntered(MouseEvent mouseEvent) {
    quitBtn.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/quit_1.png')");
  }

  @FXML
  public void quitBtnExited(MouseEvent mouseEvent) {
    quitBtn.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/quit_0.png')");
  }

  @FXML
  public void quitBtnClicked(MouseEvent mouseEvent) {
    Platform.exit();
  }
}
