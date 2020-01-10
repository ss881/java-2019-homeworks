package cn.mhj.Controller;

import cn.mhj.Enum.FormationType;
import cn.mhj.Enum.GameStatus;
import cn.mhj.Field.Battlefield;
import cn.mhj.Reply.RepReader;
import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.dom4j.DocumentException;

public class BattlefieldController implements Initializable {
  @FXML public AnchorPane anchorPane;
  @FXML public Canvas battleMap;
  @FXML public Button ChangShe;
  @FXML public Button FangYuan;
  @FXML public Button FengShi;
  @FXML public Button HengE;
  @FXML public Button HeYi;
  @FXML public Button YanXing;
  @FXML public Button YanYue;
  @FXML public Button YuLin;

  GameStatus gameStatus;
  Battlefield battlefield;

  @FXML
  public void ChangSheBtnEntered(MouseEvent mouseEvent) {
    ChangShe.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/ChangShe1.png')");
    battlefield.setSelfFormation(FormationType.CS);
  }

  @FXML
  public void ChangSheBtnExited(MouseEvent mouseEvent) {
    ChangShe.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/ChangShe0.png')");
  }

  //  @FXML
  //  public void ChangSheBtnClicked(MouseEvent mouseEvent) {}

  @FXML
  public void FangYuanBtnEntered(MouseEvent mouseEvent) {
    FangYuan.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/FangYuan1.png')");
    battlefield.setSelfFormation(FormationType.FY);
  }

  @FXML
  public void FangYuanBtnExited(MouseEvent mouseEvent) {
    FangYuan.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/FangYuan0.png')");
  }

  //  @FXML
  //  public void FangYuanBtnClicked(MouseEvent mouseEvent) {}

  @FXML
  public void FengShiBtnEntered(MouseEvent mouseEvent) {
    FengShi.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/FengShi1.png')");
    battlefield.setSelfFormation(FormationType.FS);
  }

  @FXML
  public void FengShiBtnExited(MouseEvent mouseEvent) {
    FengShi.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/FengShi0.png')");
  }

  //  @FXML
  //  public void FengShiBtnClicked(MouseEvent mouseEvent) {}

  @FXML
  public void HengEBtnEntered(MouseEvent mouseEvent) {
    HengE.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/HengE1.png')");
    battlefield.setSelfFormation(FormationType.HE);
  }

  @FXML
  public void HengEBtnExited(MouseEvent mouseEvent) {
    HengE.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/HengE0.png')");
  }
  //
  //  @FXML
  //  public void HengEBtnClicked(MouseEvent mouseEvent) {}

  @FXML
  public void HeYiBtnEntered(MouseEvent mouseEvent) {
    HeYi.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/HeYi1.png')");
    battlefield.setSelfFormation(FormationType.HY);
  }

  @FXML
  public void HeYiBtnExited(MouseEvent mouseEvent) {
    HeYi.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/HeYi0.png')");
  }

  //  @FXML
  //  public void HeYiBtnClicked(MouseEvent mouseEvent) {}

  @FXML
  public void YanXingBtnEntered(MouseEvent mouseEvent) {
    YanXing.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/YanXing1.png')");
    battlefield.setSelfFormation(FormationType.YX);
  }

  @FXML
  public void YanXingBtnExited(MouseEvent mouseEvent) {
    YanXing.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/YanXing0.png')");
  }

  //  @FXML
  //  public void YanXingBtnClicked(MouseEvent mouseEvent) {}

  @FXML
  public void YuLinBtnEntered(MouseEvent mouseEvent) {
    YuLin.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/YuLin1.png')");
    battlefield.setSelfFormation(FormationType.YL);
  }

  @FXML
  public void YuLinBtnExited(MouseEvent mouseEvent) {
    YuLin.setStyle("-fx-background-image: url('cn/mhj/pic/Menu/YuLin0.png')");
  }

  //  @FXML
  //  public void YuLinBtnClicked(MouseEvent mouseEvent) {}

  @FXML
  void FormationBtnClicked(MouseEvent mouseEvent) {
    hideBtn();
    Random rd = new Random();
    battlefield.setEnemyFormation(FormationType.values()[rd.nextInt(8)]);
    setGameStatus(GameStatus.READY);
  }

  @FXML
  public void HandleKeyPressed(KeyEvent event) throws DocumentException, InterruptedException {
    System.out.println(event.getCode());
    switch (event.getCode()) {
      case SPACE:
        if (gameStatus == GameStatus.READY) {
          System.out.println("game start");
          startGame();
        }
        break;
      case L:
        if (gameStatus == GameStatus.READY || gameStatus == GameStatus.END) {
          readReplay();
        }
        break;
    }
  }

  private void startGame() {
    setGameStatus(GameStatus.RUNNING);
    new Thread(battlefield).start();
  }

  public void setGameStatus(GameStatus gameStatus) {
    this.gameStatus = gameStatus;
    battlefield.setGameStatus(gameStatus);
  }

  private void readReplay() throws DocumentException, InterruptedException {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose rep file");
    fileChooser.setInitialDirectory(new File("rep/"));
    fileChooser.getExtensionFilters().add(new ExtensionFilter("Reply File", "*.rep"));
    Stage stage = (Stage) anchorPane.getScene().getWindow();
    File file = fileChooser.showOpenDialog(stage);
    if (file != null) {
      setGameStatus(GameStatus.REPLYING);
      RepReader repReader = new RepReader(file, battlefield);
      repReader.initCreature();
      new Thread(repReader).start();
    }
  }

  private void hideBtn() {
    ChangShe.setDisable(true);
    ChangShe.setVisible(false);
    FangYuan.setDisable(true);
    FangYuan.setVisible(false);
    FengShi.setDisable(true);
    FengShi.setVisible(false);
    HengE.setDisable(true);
    HengE.setVisible(false);
    HeYi.setDisable(true);
    HeYi.setVisible(false);
    YanXing.setDisable(true);
    YanXing.setVisible(false);
    YanYue.setDisable(true);
    YanYue.setVisible(false);
    YuLin.setDisable(true);
    YuLin.setVisible(false);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    battlefield = new Battlefield(gameStatus, battleMap.getGraphicsContext2D(), this);
    setGameStatus(GameStatus.SELECT_FORMATION);
  }
}
