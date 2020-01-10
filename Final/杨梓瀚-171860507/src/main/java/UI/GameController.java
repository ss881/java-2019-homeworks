package UI;

import Battle.Fight;
import Record.Record;
import Record.Replay;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class GameController {
    @FXML
    private Menu menu;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem open;

    private Stage stage;

    private Replay replay;

    private static BorderPane pane;

    private Fight fight;

    private File file;

    private static List<ImageView> list;

    @FXML
    private void initialize(){

    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setPane(BorderPane pane){
        GameController.pane = pane;
    }

    public void prepare(){
        fight = new Fight();
        fight.init();
        Record.newFile();
        list = fight.getImageViews();
        for (ImageView imageView : list){
            pane.getChildren().add(imageView);
        }
    }

    public void startGame(){
        fight.run();
    }


    public void open(ActionEvent actionEvent) {
        if (!Fight.isEnd() && MainWindow.start){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("还未结束无法打开存档!");
            alert.showAndWait();
        }else {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files(*.xml", "*.xml");
            fileChooser.getExtensionFilters().add(extensionFilter);
            fileChooser.setTitle("open");
            file = fileChooser.showOpenDialog(stage);
            if (file != null){
                replay = new Replay(file);
                MainWindow.start = true;
                clearList();
                Thread thread = new Thread(replay);
                thread.start();
               /* replay = new Replay(file);
                Main.start = true;
                replay.replay(pane);*/
            }
        }
        actionEvent.consume();

        //System.out.println("123");
        //replay.replay(pane);
    }

    public void loadGame(){


        //replay.replay(pane);
    }

    public static void addImageView(ImageView imageView){
        list.add(imageView);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                pane.getChildren().add(imageView);
            }
        });


    }

    public static void clearList(){
        for (ImageView imageView : list){
            pane.getChildren().remove(imageView);
        }
        list.clear();
    }
}
