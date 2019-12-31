package top.stqstq.maven_huluwa.gui;

import top.stqstq.maven_huluwa.battlefiled.*;
import top.stqstq.maven_huluwa.being.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import top.stqstq.maven_huluwa.involved.Involved;
import top.stqstq.maven_huluwa.lock.MyLock;
import top.stqstq.maven_huluwa.logger.Logger;

import java.io.*;
import java.security.Key;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static javafx.scene.input.KeyCode.*;


public class GUIMain extends Application {
    final int LIMIT = BattleField.SIZE;
    private Label[][] labels = new Label[LIMIT][LIMIT];
    static GUIMain guiMain;
    private Involved involved;
    private Queue<String> mapQueue;
    private ExecutorService executorService = null;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        try {
            guiMain = this;
            involved = new Involved();
            involved.huluwaCome();
            involved.scorpionOrder(0);
            mapQueue = new LinkedList<String>();
            stage = primaryStage;

            GridPane root = new GridPane();
            root.setGridLinesVisible(true);
            root.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            primaryStage.setTitle("GUI System of Huluwas");
            for (int i = 0; i < LIMIT; i++)
                for (int j = 0; j < LIMIT; j++) {
                    labels[i][j] = new Label(" ");
                    labels[i][j].setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
                    GridPane.setHalignment(labels[i][j], HPos.CENTER);
                    GridPane.setValignment(labels[i][j], VPos.CENTER);
                    //labels[i][j].setFont(new Font("Consolas",20));
                    labels[i][j].setStyle("-fx-font-weight: bold;-fx-font-size: 20px;-fx-font-family: \"Consolas\";-fx-border-width: 2;-fx-border-color: white;");
                    GridPane.setHgrow(labels[i][j], Priority.ALWAYS);
                    GridPane.setVgrow(labels[i][j], Priority.ALWAYS);
                    root.add(labels[i][j], i, j);//
                }
            Scene scene = new Scene(root, 800, 800);
            setKeyEvent(scene);
            primaryStage.setScene(scene);
            primaryStage.show();
            //Thread.sleep(2000);
            //labels[10][10].setText("X");
            //innerTest();
            printBfInGui();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fightMultithreading() {
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                ExecutorService exec = GUIMain.guiMain.involved.warMultithreading();
                Logger logger = new Logger();
                logger.createLoggerFile();

                while (!exec.isTerminated()) {
                    MyLock.printLock.lock();
                    try {
                        Thread.sleep(100);
                        if (MyLock.updateState == 1) {
                            logger.writeLoggerFile(turnMap2String(BattleField.field));
                            GUIMain.guiMain.printBfInGui();
                            MyLock.updateState = 0;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        MyLock.printLock.unlock();
                    }
                }

                logger.closeLoggerFile();
                //System.out.println("log file close success");
            }
        });
        executorService.shutdown();
    }

    private void fightInFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Logging File");
        fileChooser.setInitialDirectory(new File("../"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Log File", "*.log"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File logFile = fileChooser.showOpenDialog(stage);
        //logFile.exists();
        if (logFile == null) ;
        else {
            executorService = Executors.newSingleThreadExecutor();

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStreamReader reader = new InputStreamReader(new FileInputStream(logFile));
                        BufferedReader bufferedReader = new BufferedReader(reader);
                        String line = "";
                        line = bufferedReader.readLine();
                        int no = 0;
                        while (line != null) {
                            //System.out.println(line.length() + " " + (++no));
                            printBfInFile(line);
                            Thread.sleep(100);
                            line = bufferedReader.readLine();
                        }
                        //System.out.println("file end");
                        Thread.sleep(3000);
                        printBfInGui();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            executorService.shutdown();
        }
    }

    private void setKeyEvent(Scene s) {
        s.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                //System.out.println(keyEvent.getCode());
                if (executorService == null) ;
                else if (!executorService.isTerminated()) return;
                switch (keyEvent.getCode()) {
                    case SPACE:
                        //System.out.println("space start");
                        fightMultithreading();
                        break;
                    case DIGIT1:
                    case DIGIT2:
                    case DIGIT3:
                    case DIGIT4:
                    case DIGIT5:
                    case DIGIT6:
                    case DIGIT7:
                        //System.out.println("digit1-7 change zhengxin " + (keyEvent.getCode().getCode() - DIGIT1.getCode() + 1));
                        involved.restoreState();
                        involved.huluwaCome();
                        involved.scorpionOrder(keyEvent.getCode().getCode() - DIGIT1.getCode());
                        printBfInGui();
                        break;
                    case L:
                        //System.out.println("L repaint fight event from file");
                        fightInFile();
                        break;
                    default:
                        ;
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    private String turnMap2String(int[][] a) {
        StringBuilder ret = new StringBuilder();
        for (int[] is : a)
            for (int i : is) {
                if (Involved.a.get(i).getAliveState() == false && Involved.a.get(i).getSymbol() != " ")//非空地的死人
                    ret.append("*");
                else
                    ret.append("-");
                ret.append(Involved.a.get(i).getSymbol());
            }
        return ret.toString();
    }

    private void updateGui() {
        if (mapQueue.isEmpty()) return;
        String mapTmp = mapQueue.poll();
        if (mapTmp.length() != LIMIT * LIMIT * 2) return;
        for (int i = 0; i < LIMIT; i++)
            for (int j = 0; j < LIMIT; j++) {
                String s = String.valueOf(mapTmp.charAt((i * LIMIT + j) * 2 + 1));
                labels[j][i].setText(s);
                if (String.valueOf(mapTmp.charAt((i * LIMIT + j) * 2)).equals("*")) {
                    //System.out.println("someone is dead now");
                    labels[j][i].setStyle("-fx-font-weight: bold;-fx-font-size: 20px;-fx-font-family: \"Consolas\";-fx-border-width: 2;-fx-border-color: black;");
                } else
                    labels[j][i].setStyle("-fx-font-weight: bold;-fx-font-size: 20px;-fx-font-family: \"Consolas\";-fx-border-width: 2;-fx-border-color: white;");
            }
    }

    public void printBfInGui() {
        mapQueue.offer(turnMap2String(BattleField.field));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                updateGui();
            }
        });
    }

    public void printBfInFile(String s) {
        if (s.length() != LIMIT * LIMIT * 2) {
            System.err.println("log file wrong!");
            return;
        }
        mapQueue.offer(s);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                updateGui();
            }
        });
    }

    private void innerTest() {
        int[][] ints = new int[LIMIT][LIMIT];
        for (int i = 0; i < LIMIT; i++)
            ints[0][i] = i;
        mapQueue.offer(turnMap2String(ints));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                updateGui();
            }
        });
    }
}
