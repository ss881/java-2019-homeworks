package game;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class WelcomeController {
    public Button load;
    public Button startgame;

    //public void initialize(URL location, ResourceBundle resources) { }

    public void loadVedio() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("载入已有录像");
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String test;

            test = br.readLine();
            if (!test.equals("假装自己是Magic")) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("错误");
                alert.setHeaderText("魔数校验失败");
                alert.setContentText("错误的文件格式！请打开保存在./src/resources/log/目录下的日志文件！");
                alert.show();

                System.out.println("错误的文件格式！请打开保存在./src/resources/log/目录下的日志文件！");
            } else {
                System.out.println("魔数校验成功");
                Main.welcomestage.close();
                System.out.println("播放已有录像");
                Main.thisgame = new Game();
                Main.thisgame.play(file);
            }
            reader.close();
            br.close();
        }
    }

    public void typeL(KeyEvent event) throws Exception {
        if (event.getCode() == KeyCode.L)
            loadVedio();
    }

    public void buttonClick(ActionEvent event) throws Exception {
        if (event.getSource() == load) {
            loadVedio();
        } else if (event.getSource() == startgame) {
            Main.welcomestage.close();
            System.out.println("开始新游戏");
            Main.thisgame = new Game();
            Main.thisgame.run();
        }
    }
}

