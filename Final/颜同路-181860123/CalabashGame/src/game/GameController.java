package game;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameController {

    public Button control;
    public Button back;

    //TODO:GUI控件

    public void typeSPACE(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            if (Game.pause == false) {
                Game.pause = true;
                System.out.println("游戏暂停");
            } else {
                Game.pause = false;
                System.out.println("游戏继续");
            }
        }
    }

    public void buttonClick(ActionEvent event) {
        if (event.getSource() == back) {
            if (Main.thisgame.running) {
                Main.thisgame.running = false;
                System.out.println("游戏在未结束时退出，删除日志文件" + Log.nowlog.toString());
                Log.nowlog.delete();

                Main.gamestage.close();
                Main.welcomestage.show();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("警告");
                alert.setHeaderText("游戏在未结束时退出，已删除日志文件");
                alert.show();

            } else {
                Main.gamestage.close();
                Main.welcomestage.show();
            }
        } else if (event.getSource() == control) {
            if (Game.pause == false) {
                Game.pause = true;
                System.out.println("游戏暂停");
            } else {
                Game.pause = false;
                System.out.println("游戏继续");
            }
        }
    }
}
