package GameModule;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import BattleField.BattleField;
import Item.CalabashCrew;
import Item.Creature;
import Item.MonsterCrew;
import Tactic.*;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.*;
import javafx.scene.canvas.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Controller implements Initializable{
    @FXML  Button startGame;
    @FXML  Button endGame;
    @FXML  Button calabashCrewChangeTactic;
    @FXML  Button monsterCrewChangeTactic;
    @FXML  Button loadRep;
    @FXML  TextArea gameLog;
    @FXML  Canvas gameArea;
    @FXML  javafx.scene.layout.Pane Pane;
    private Image background = new Image("background.png");
    private BattleField battlefield = new BattleField();
    private CalabashCrew calabashCrew = new CalabashCrew();
    private MonsterCrew monsterCrew = new MonsterCrew();
    private ArrayList<TacticMaker> tacticMakers = new ArrayList<TacticMaker>();
    private int calabashTactic = 0;
    private int monsterTactic = 0;
    private boolean isGamming = false;
    private boolean isReplaying = false;
    private Round gameRound;
    private LoadRep gameReplay;
    private ExecutorService gameLauncher = Executors.newSingleThreadExecutor();
    private ExecutorService replayLauncher = Executors.newSingleThreadExecutor();

    class KeyBoredHandler implements EventHandler<KeyEvent> {
        public void handle(KeyEvent event) {
            if(!isGamming && !isReplaying) {
                if(event.getCode() == KeyCode.SPACE)
                    startGameHandler();
                if(event.getCode() == KeyCode.A)
                    calabashCrewChangeTacticHandler();
                if(event.getCode() == KeyCode.S)
                    monsterCrewChangeTacticHandler();
                if(event.getCode() == KeyCode.L)
                    loadRepHandler();
            }
            else {
                if((isGamming || isReplaying) && event.getCode() == KeyCode.ESCAPE)
                    endGameHandler();
            }
        }
    }

    class ClickCloseHandler implements EventHandler<WindowEvent> {
        public void handle(WindowEvent event) {
            endGameHandler();
            System.exit(0);
        }
    }

    @FXML private void startGameHandler() {
        if(!isGamming && !isReplaying) {
            Stage stage = (Stage)Pane.getScene().getWindow();
            stage.setOnCloseRequest(new ClickCloseHandler());
            gameLog.clear();
            gameLog.appendText("战斗开始\n");
            isGamming = true;
            gameLauncher = Executors.newSingleThreadExecutor();
            gameRound = new Round(calabashCrew, monsterCrew, battlefield, gameArea.getGraphicsContext2D(), gameLog);
            gameLauncher.execute(gameRound);
            gameLauncher.shutdown();
            Platform.runLater(new Runnable() {
                public void run() {
                    gameArea.requestFocus();
                    endGame.setText("结束战斗");
                }
            });
        }
        else {
            Platform.runLater(new Runnable() {
                public void run() {
                    gameArea.requestFocus();
                }
            });
        }

    }

    @FXML private void endGameHandler() {
        if(isGamming || isReplaying) {
            GraphicsContext gc = gameArea.getGraphicsContext2D();
            gc.drawImage(background,0,0,960,540);
            try{
                if(isGamming) {
                    gameRound.endGame();
                    gameLauncher.shutdown();
                    while(!gameLauncher.isTerminated()){}
                }
                else {
                    gameReplay.endReplay();
                    replayLauncher.shutdown();
                    while(!replayLauncher.isTerminated()){}
                    endGame.setText("结束战斗");
                }
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
            finally {
                isGamming = false;
                isReplaying = false;
                battlefield = new BattleField();
                calabashCrew = new CalabashCrew();
                monsterCrew = new MonsterCrew();
                calabashTactic = 0;
                monsterTactic = 0;
                Creature.setBattlefield(battlefield.getBattleField());
                calabashCrew.changeTactic(tacticMakers.get(calabashTactic), battlefield.getBattleField());
                monsterCrew.changeTactic(tacticMakers.get(monsterTactic), battlefield.getBattleField());
                gc.drawImage(background,0,0,960,540);
                battlefield.printBattleField(gameArea.getGraphicsContext2D());
            }
        }
        Platform.runLater(new Runnable() {
            public void run() {
                gameArea.requestFocus();
            }
        });
    }

    @FXML private void loadRepHandler() {
        if(!isGamming && !isReplaying) {
            FileChooser repFile = new FileChooser();
            repFile.setTitle("选择回放文件");
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(".replay","*.replay");
            repFile.getExtensionFilters().add(extensionFilter);
            Stage stage = (Stage)Pane.getScene().getWindow();
            stage.setOnCloseRequest(new ClickCloseHandler());
            File rep = repFile.showOpenDialog(stage);
            if(rep!=null) {
                isReplaying = true;
                gameLog.clear();
                gameLog.appendText("开始回放\n");
                // System.out.println(log.getName());
                replayLauncher = Executors.newSingleThreadExecutor();
                gameReplay = new LoadRep(rep, gameArea.getGraphicsContext2D(), gameLog);
                replayLauncher.execute(gameReplay);
                replayLauncher.shutdown();
            }
            Platform.runLater(new Runnable() {
                public void run() {
                    gameArea.requestFocus();
                    endGame.setText("结束回放\n");
                }
            });
        }
        else {
            Platform.runLater(new Runnable() {
                public void run() {
                    gameArea.requestFocus();
                }
            });
        }
    }

    @FXML private void calabashCrewChangeTacticHandler() {
        if(!isGamming && !isReplaying) {
            gameLog.appendText("葫芦娃变阵:");
            calabashTactic = (calabashTactic + 1) % tacticMakers.size();
            battlefield.clearBattleField();
            GraphicsContext gc = gameArea.getGraphicsContext2D();
            gc.drawImage(background,0,0,960,540);
            String tName = calabashCrew.changeTactic(tacticMakers.get(calabashTactic), battlefield.getBattleField());
            monsterCrew.changeTactic(tacticMakers.get(monsterTactic), battlefield.getBattleField());
            gameLog.appendText(tName+'\n');
            battlefield.printBattleField(gc);
        }
        Platform.runLater(new Runnable() {
            public void run() {
                gameArea.requestFocus();
            }
        });
    }
    @FXML private void monsterCrewChangeTacticHandler() {
        if(!isGamming && !isReplaying) {
            gameLog.appendText("怪物变阵:");
            monsterTactic = (monsterTactic + 1) % tacticMakers.size();
            battlefield.clearBattleField();
            GraphicsContext gc = gameArea.getGraphicsContext2D();
            gc.drawImage(background,0,0,960,540);
            String tName = monsterCrew.changeTactic(tacticMakers.get(monsterTactic), battlefield.getBattleField());
            calabashCrew.changeTactic(tacticMakers.get(calabashTactic), battlefield.getBattleField());
            gameLog.appendText(tName+'\n');
            battlefield.printBattleField(gc);;
        }
        Platform.runLater(new Runnable() {
            public void run() {
                gameArea.requestFocus();
            }
        });
    }
    public void initialize(URL url, ResourceBundle rb) {
        gameLog.setText("葫芦娃和妖怪互相对峙，战斗一触即发\n");
        gameLog.setEditable(false);
        startGame.setText("开始战斗");
        endGame.setText("结束战斗");
        loadRep.setText("加载回放");
        calabashCrewChangeTactic.setText("葫芦娃变阵");
        monsterCrewChangeTactic.setText("妖怪变阵");
        GraphicsContext gc = gameArea.getGraphicsContext2D();
        gc.drawImage(background,0,0,960,540);
        Platform.runLater(new Runnable() {
            public void run() {
                gameArea.requestFocus();
            }
        });
        gameArea.setOnKeyPressed(new KeyBoredHandler());
        tacticMakers.addAll(Arrays.asList( new ChangShe(), new ChongE(), new FangYuan(), new FengShi(),
               new HeYi(), new YanXing(), new YanYue(), new YuLin()));
        calabashCrew.changeTactic(tacticMakers.get(calabashTactic), battlefield.getBattleField());
        monsterCrew.changeTactic(tacticMakers.get(monsterTactic), battlefield.getBattleField());
        battlefield.printBattleField(gameArea.getGraphicsContext2D());
    }
}

