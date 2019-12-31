package cn.edu.nju.huluwa;

import cn.edu.nju.huluwa.util.ImageLoader;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class Controller {
    @FXML
    private TextArea info;
    @FXML
    private Canvas canvas;
    @FXML
    private Button buzhenBtn;
    @FXML
    private Button sortHuluwaBtn;
    @FXML
    private Button aboutBtn;
    @FXML
    private Button battleBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button loadBtn;
    private Scene scene;

    public Controller() {
    }

    @FXML
    public void initialize() {
        scene = new Scene(canvas, info);
        info.setEditable(false);
        buzhenBtn.setOnAction(e -> scene.buZhen());
        sortHuluwaBtn.setOnAction(e -> {
            if ("葫芦娃排序".equals(sortHuluwaBtn.getText())) {
                scene.sortHuluwa();
                sortHuluwaBtn.setText("打乱葫芦娃");
            } else {
                scene.shuffleHuluwa();
                sortHuluwaBtn.setText("打乱葫芦娃");
            }
        });
        aboutBtn.setOnAction(e -> {
            Stage dialog = new Stage();
            DialogPane dp = new DialogPane();
            BorderPane bp = new BorderPane();
            Hyperlink href = new Hyperlink("https://github.com/AJeo0526/java-2019-homeworks");
            href.setOnAction(event -> {
                try {
                    Desktop.getDesktop().browse(new URI(href.getText()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                }
            });
            Label label = new Label();
            label.setFont(Font.font("Comic Sans font", FontWeight.NORMAL, 16));
            label.setTextAlignment(TextAlignment.CENTER);
            label.setText("Nanjing University Java Course Homework\n"
                    + "v1.1.0\n"
                    + "developed by Chu Yougang");
            bp.setLeft(new ImageView(ImageLoader.getLogo()));
            bp.setCenter(label);
            dp.setContent(bp);
            dp.setExpandableContent(href);
            dialog.setScene(new javafx.scene.Scene(dp));
            dialog.setTitle("关于葫芦世界");
            dialog.getIcons().add(ImageLoader.getIcon());
            dialog.setResizable(false);
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.show();
        });
        battleBtn.setOnAction(e -> scene.startBattle());
        saveBtn.setOnAction(e -> scene.saveRecords());
        loadBtn.setOnAction(e -> scene.replayRecords());
    }
}
