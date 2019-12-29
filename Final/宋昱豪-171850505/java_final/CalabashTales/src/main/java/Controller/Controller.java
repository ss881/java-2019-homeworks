package Controller;

import Model.Model;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {
    @FXML
    private BorderPane borderpane;
    @FXML
    private MenuItem openIcon;
    @FXML
    private MenuItem saveIcon;
    @FXML
    private MenuItem startIcon;
    @FXML
    private MenuItem infoIcon;
    @FXML
    private MenuItem formation;
    @FXML
    private AnchorPane anchorpane;
    private ExecutorService exec = Executors.newCachedThreadPool();
    static Model model;
    public static File file;
    int num1 = 0;
    int num2 = 0;

    public void init() {
        borderpane.setFocusTraversable(true);
        borderpane.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

            public void handle(KeyEvent event) {
                System.out.print(event.getCode());

                if (event.getCode() == KeyCode.SPACE) {
                    try {
                        clear();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    model.play();
                    exec.execute(model);
                } else if (event.getCode() == KeyCode.L) {
                    anchorpane.getChildren().clear();
                    try {
                        model = new Model(anchorpane, num1, num2);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    FileChooser chooser = new FileChooser();
                    chooser.setTitle("打开文件");
                    chooser.getExtensionFilters().addAll(
                            new FileChooser.ExtensionFilter("所有文本", "*.txt"));
                    File file = chooser.showOpenDialog(null);
                    try {
                        model.replay(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            ;

        });
    }

    public static void setFile() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("存储文件位置");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("所有文本", "*.txt"));
        file = chooser.showSaveDialog(null);
    }

    public void clear() throws FileNotFoundException, UnsupportedEncodingException {
        anchorpane.getChildren().clear();
        setFile();
        model = new Model(anchorpane, num1, num2);
        //model.battleGround.clear();
    }

    public void openFile(ActionEvent actionEvent) throws IOException {
        anchorpane.getChildren().clear();
        model = new Model(anchorpane, num1, num2);
        FileChooser chooser = new FileChooser();
        chooser.setTitle("打开文件");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("所有文本", "*.txt"));
        File file = chooser.showOpenDialog(null);
        model.replay(file);

    }


    public void saveFile(ActionEvent actionEvent) {

        setFile();
    }

    public void fightStart(ActionEvent actionEvent) throws FileNotFoundException, UnsupportedEncodingException{
        clear();
        model.play();
        exec.execute(model);
    }

    public void showInfo(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("This product is created by yhsong");
        alert.setContentText("email: yhsong.nju@gmail.com");
        alert.showAndWait();
    }

    public void GformationSet(ActionEvent actionEvent) {
        List<String> formations = new ArrayList<>(Arrays.asList("鹤翼", "雁行", "冲轭", "长蛇", "鱼鳞", "方门", "偃月", "锋矢"));
        ChoiceDialog<String> dialog = new ChoiceDialog<>("鹤翼", formations);
        dialog.setTitle("Choose Formation");
        dialog.setContentText("Choose your Calabash Formation:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String t = result.get();
            if (t.equals("鹤翼")) {
                num1 = 0;
            } else if (t.equals("雁行")) {
                num1 = 1;
            } else if (t.equals("冲轭")) {
                num1 = 2;
            } else if (t.equals("长蛇")) {
                num1 = 3;
            } else if (t.equals("鱼鳞")) {
                num1 = 4;
            } else if (t.equals("方门")) {
                num1 = 5;
            } else if (t.equals("偃月")) {
                num1 = 6;
            } else if (t.equals("锋矢")) {
                num1 = 7;
            } else {
                num1 = 0;
            }
        }
    }

    public void BformationSet(ActionEvent actionEvent) {
        List<String> formations = new ArrayList<>(Arrays.asList("鹤翼", "雁行", "冲轭", "长蛇", "鱼鳞", "方门", "偃月", "锋矢"));
        ChoiceDialog<String> dialog = new ChoiceDialog<>("鹤翼", formations);
        dialog.setTitle("Choose Formation");
        dialog.setContentText("Choose your Calabash Formation:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String t = result.get();
            if (t.equals("鹤翼")) {
                num2 = 0;
            } else if (t.equals("雁行")) {
                num2 = 1;
            } else if (t.equals("冲轭")) {
                num2 = 2;
            } else if (t.equals("长蛇")) {
                num2 = 3;
            } else if (t.equals("鱼鳞")) {
                num2 = 4;
            } else if (t.equals("方门")) {
                num2 = 5;
            } else if (t.equals("偃月")) {
                num2 = 6;
            } else if (t.equals("锋矢")) {
                num2 = 7;
            } else {
                num2 = 0;
            }
        }
    }
}