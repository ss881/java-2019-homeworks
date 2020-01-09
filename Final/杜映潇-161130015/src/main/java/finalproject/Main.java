package finalproject;

import finalproject.controller.GameController;
import finalproject.creatures.Creature;
import finalproject.fxview.BattleFieldController;
import finalproject.fxview.LogChooseDialogController;
import finalproject.fxview.ResultDialogController;
import finalproject.utils.CreatureFactory;
import finalproject.utils.Factory;
import finalproject.utils.GameLogger;
import finalproject.utils.Logger;
import finalproject.worldmap.CalabashWorld;
import finalproject.worldmap.World;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    private Stage primaryStage;

    private void clearLogger() {
        Logger logger = GameLogger.getInstance(null);
        if (logger != null) {
            logger.clear();
        }
    }

    private void showResultDialog(Task<Creature.Side> task) {
        try {
            Creature.Side winningSide = task.getValue();

            FXMLLoader resultDialogLoader = new FXMLLoader();
            resultDialogLoader.setLocation(Main.class.getResource("/FXML/ResultDialog.fxml"));
            AnchorPane resultDialog = resultDialogLoader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Result");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene dialogScene = new Scene(resultDialog);
            dialogStage.setScene(dialogScene);

            ResultDialogController resultDialogController = resultDialogLoader.getController();
            resultDialogController.showResult(winningSide);

            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File showLogChooseDialog() {
        try {
            Stage logChooseStage = new Stage();
            logChooseStage.setTitle("Choose a log file to rewind");
            logChooseStage.initOwner(primaryStage);
            logChooseStage.initModality(Modality.WINDOW_MODAL);
            FXMLLoader logChooseDialogLoader = new FXMLLoader(Main.class.getResource("/FXML" +
                    "/LogChooseDialog.fxml"));
            AnchorPane logChoosePane = logChooseDialogLoader.load();
            Scene logChooseScene = new Scene(logChoosePane);
            logChooseStage.setScene(logChooseScene);
            LogChooseDialogController logChooseDialogController = logChooseDialogLoader.getController();
            logChooseDialogController.register(logChooseStage);

            logChooseStage.showAndWait();

            File logFile = logChooseDialogController.getFile();
            logChooseStage.close();
            return logFile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Calabash Brothers V.S. Goblins");

        try {
            /* Set up root layout */
            FXMLLoader rootLayoutLoader = new FXMLLoader();
            rootLayoutLoader.setLocation(Main.class.getResource("/FXML/RootLayout.fxml"));
            BorderPane rootLayout = rootLayoutLoader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            /* Set up world map and utils */
            World calabashWorld = new CalabashWorld();
            Factory creatureFactory = new CreatureFactory();
            File logFolder = new File("./Log");
            if (!logFolder.exists()) {
                logFolder.mkdir();
            }

            /* Initialize controller */
            GameController gameController = new GameController(calabashWorld, creatureFactory);
            gameController.refresh(); /* Initial battle field */

            FXMLLoader battleFieldLoader = new FXMLLoader();
            battleFieldLoader.setLocation(Main.class.getResource("/FXML/BattleField.fxml"));
            AnchorPane battleField = battleFieldLoader.load();

            rootLayout.setCenter(battleField);

            final BattleFieldController controller = battleFieldLoader.getController();
            controller.showBattleField(calabashWorld);

            scene.setOnKeyPressed((final KeyEvent keyEvent) -> {
                if (keyEvent.getCode() == KeyCode.SPACE) {
                    Task<Creature.Side> task = new Task<Creature.Side>() {
                        @Override
                        protected Creature.Side call() {
                            /* Initialize logger */
                            String logFileName = GameLogger.getLogFileName();
                            GameLogger.getInstance("./Log/" + logFileName);

                            gameController.refresh();
                            controller.showBattleField(calabashWorld);
                            Creature.Side winningSide = gameController.startGame(controller);
                            System.out.println(winningSide);
                            Logger logger = GameLogger.getInstance(null);
                            logger.do_logging();
                            return winningSide;
                        }
                    };

                    task.setOnSucceeded(event -> {
                        showResultDialog(task);
                        clearLogger();
                    });
                    task.setOnFailed(event -> clearLogger());
                    Thread gameLogic = new Thread(task);
                    gameLogic.setDaemon(true);
                    gameLogic.start();
                } else if (keyEvent.getCode() == KeyCode.L) {
                    final File logFile = showLogChooseDialog();

                    if (logFile != null) {
                        Task<Creature.Side> task = new Task<Creature.Side>() {
                            @Override
                            protected Creature.Side call() {
                                try {
                                    gameController.refresh();
                                    controller.showBattleField(calabashWorld);
                                    Creature.Side winningSide = gameController.rewind(logFile, controller);
                                    System.out.println(winningSide);
                                    return winningSide;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return null;
                                }
                            }
                        };

                        task.setOnSucceeded(event -> {
                            showResultDialog(task);
                            clearLogger();
                        });
                        task.setOnFailed(event -> clearLogger());
                        Thread gameLogic = new Thread(task);
                        gameLogic.setDaemon(true);
                        gameLogic.start();
                    }
                } else {
                    System.out.println("Usage: press space to start a new game or press L to rewind a game.");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
