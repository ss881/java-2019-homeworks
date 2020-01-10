package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.File;
import java.util.Random;


import gui.ChessBoard;
import gui.MyTextArea;
import util.FormationType;

import static util.GameStatus.*;



public class Main extends Application {

    public static int BOARD_WIDTH =1000;
    public static int BOARD_HEIGHT=600;
    private int TEXT_WIDTH =400;
    private int TEXT_HEIGHT =600;

    private Stage stage;

    public static Random random=new Random();

    public static MyTextArea textArea;
    static{
        if(textArea==null)
            textArea=new MyTextArea();
        textArea.setEditable(false);
    }

    public static ChessBoard chessBoard=new ChessBoard(BOARD_WIDTH, BOARD_HEIGHT);

    public void printRightText(){
        textArea.setText("");
        textArea.appendText("++++++++++++++++++++++++++++++++++\n");
        textArea.appendText("       按Enter键运行游戏\n");
        textArea.appendText("       按F键选择记录回放\n");
        textArea.appendText("       按R键进行新一轮\n");
        textArea.appendText("       游戏结束按Enter保存回放\n");
        textArea.appendText("++++++++++++++++++++++++++++++++++\n");
    }

    //在当前状态为READY和GAMEOVER时可以切换阵型
    private void switchFormation(){
        if(chessBoard.getGameStatus()== GAME_OVER){
            chessBoard.setGameStatus(READY);
            chessBoard.getReady();
            printRightText();
        }
        else if(chessBoard.getGameStatus()==READY){
            chessBoard.getReady();
            printRightText();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane borderPane = new BorderPane();
        stage=primaryStage;
        borderPane.setLeft(chessBoard);
        borderPane.setRight(textArea);

        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        borderPane.setTop(menuBar);
        Menu goodMenu = new Menu("葫芦娃阵型");

        Menu badMenu = new Menu("妖怪阵型");

        addItems(goodMenu,badMenu);
        menuBar.getMenus().addAll(goodMenu,badMenu);

        chessBoard.setFocusTraversable(true);

        chessBoard.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                chessBoard.requestFocus();
            }
        });
        printRightText();

        chessBoard.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if(e.getCode()==KeyCode.ENTER){
                    if(chessBoard.getGameStatus()==READY){

                        chessBoard.setGameStatus(RANDOM);
                        chessBoard.allMoveUp();
                    }
                    else if(chessBoard.getGameStatus()== GAME_OVER){
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.getExtensionFilters().addAll(
                                new FileChooser.ExtensionFilter("Binary", "*.dat")
                        );
                        fileChooser.setTitle("保存战斗记录");
                        File file = fileChooser.showSaveDialog(stage);
                        if (file != null) {
                            chessBoard.setIsSavePlay(true);
                            chessBoard.setPlayBackFile(file);
                            chessBoard.saveFile(file);
                        }
                        else{
                            chessBoard.setIsSavePlay(false);
                            chessBoard.setPlayBackFile(null);
                        }
                    }
                }
                else if(e.getCode()==KeyCode.R){
                    if(chessBoard.getGameStatus()== GAME_OVER){
                        chessBoard.setGameStatus(READY);
                        chessBoard.getReady();
                        printRightText();
                    }
                }
                else if(e.getCode()==KeyCode.F){
                    if(chessBoard.getGameStatus()==READY){
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.getExtensionFilters().addAll(
                                new FileChooser.ExtensionFilter("Binary", "*.dat")

                        );
                        fileChooser.setTitle("选择战斗记录回放");
                        File file = fileChooser.showOpenDialog(stage);
                        if (file != null) {
                            chessBoard.setGameStatus(PLAY_RECORD);
                            chessBoard.playRecord(file);
                        }
                    }
                }
            }
        });
        Image icon=new Image("icon.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(borderPane, BOARD_WIDTH + TEXT_WIDTH, BOARD_HEIGHT +25));
        primaryStage.setTitle("CalabashBrother Auto-Chess");

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

    private void addItems(Menu goodMenu,Menu badMenu){

        MenuItem []formation=new MenuItem[FormationType.values().length];
        for(int i=0;i<FormationType.values().length;i++){
            formation[i]=new MenuItem(FormationType.values()[i].toString());

            final int index=i;
            formation[i].setOnAction(event -> {
                if(chessBoard.getGameStatus()==READY || chessBoard.getGameStatus()== GAME_OVER){
                    chessBoard.getFormationAdminister().setGoodFormationIndex(index);
                    switchFormation();
                }
            });
        }
        for(int i=0;i<formation.length;i++)
            goodMenu.getItems().addAll(formation[i]);

        MenuItem []formation2=new MenuItem[FormationType.values().length];
        for(int i=0;i<FormationType.values().length;i++){
            formation2[i]=new MenuItem(FormationType.values()[i].toString());

            final int index=i;
            formation2[i].setOnAction(event -> {
                if(chessBoard.getGameStatus()==READY || chessBoard.getGameStatus()== GAME_OVER){
                    chessBoard.getFormationAdminister().setBadFormationIndex(index);
                    switchFormation();
                }
            });
        }
        for(int i=0;i<formation2.length;i++)
            badMenu.getItems().addAll(formation2[i]);
    }
}
