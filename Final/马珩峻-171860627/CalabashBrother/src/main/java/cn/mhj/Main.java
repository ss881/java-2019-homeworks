package cn.mhj;

import cn.mhj.Controller.MenuController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("CalabashAutoChess");
    primaryStage.setWidth(MenuController.WIDTH);
    primaryStage.setHeight(MenuController.HEIGHT + 26);
    primaryStage.setResizable(false);

    MenuController menuController = new MenuController();
    menuController.init(primaryStage);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
