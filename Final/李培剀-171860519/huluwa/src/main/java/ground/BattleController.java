package ground;

import creature.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import log.*;
import trajectory.Trajectory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BattleController {

    private static final int IMAGE_SIZE = 32;
    private static final int TRAJECTORY_SIZE = 20;
    private static final int REFRESH_CYCLE = 10; // millisecond
    private static final Image BACKGROUND = new Image("background.png", 736, 512, false, true);

    private Ground ground;
    private GraphicsContext context;

    private static final MediaPlayer musicPlayer = new MediaPlayer(new Media(new File("src\\main\\resources\\HuluwaBgm.mp3").toURI().toString()));
    private static final LogWriter logWriter = new LogWriter();
    private static final LogReader logReader = new LogReader();
    private static final Map<String, Image> images = new HashMap<>();
    static {
        images.put("Creep", new Image("Creep.png"));
        images.put("Scorpion", new Image("Scorpion.png"));
        images.put("Snake", new Image("Snake.png"));
        images.put("Grandpa", new Image("Grandpa.png"));
        images.put("Huluwared", new Image("Huluwared.png"));
        images.put("Huluwaorange", new Image("Huluwaorange.png"));
        images.put("Huluwayellow", new Image("Huluwayellow.png"));
        images.put("Huluwagreen", new Image("Huluwagreen.png"));
        images.put("Huluwacyan", new Image("Huluwacyan.png"));
        images.put("Huluwablue", new Image("Huluwablue.png"));
        images.put("Huluwapurple", new Image("Huluwapurple.png"));
        images.put("goodtomb", new Image("goodtomb.png"));
        images.put("badtomb", new Image("badtomb.png"));
        images.put("CreepsWin", new Image("CreepsWin.png"));
        images.put("HuluwasWin", new Image("HuluwasWin.png"));
        images.put("HuluwaTrajectory", new Image("HuluwaTrajectory.png"));
        images.put("CreepTrajectory", new Image("CreepTrajectory.png"));
        images.put("ScorpionTrajectory", new Image("ScorpionTrajectory.png"));
        images.put("SnakeTrajectory", new Image("SnakeTrajectory.png"));
    }

    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane displayPane;
    @FXML
    private Canvas displayCanvas;
    @FXML
    private AnchorPane labelPane;
    @FXML
    private AnchorPane battleLogPane;
    @FXML
    private TextArea battleLogArea;

    @FXML
    void initialize() {
        new Thread(() -> {
            musicPlayer.setVolume(0.14);
            while(true) {
                musicPlayer.play();
                try {
                    TimeUnit.SECONDS.sleep(2 * 60 + 14);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        ground = new Ground(battleLogArea);
        mainPane.addEventFilter(KeyEvent.KEY_PRESSED, new KeyEventHandler());
        Platform.runLater(()->mainPane.requestFocus());
        context = displayCanvas.getGraphicsContext2D();
        context.drawImage(BACKGROUND, 0, 0);
    }

    void init() {
        try {
            if (ground.isBattling() || logReader.isReplaying())
                return;
            battleLogArea.clear();
            logWriter.clear();
            ground.groundClear();
            System.out.println("...Initializing...");
            battleLogArea.appendText("...Initializing...\n");
            logWriter.writeLog(REFRESH_CYCLE);
            ground.groundInit(logWriter);
            refreshCanvas();
            System.out.println("...Successfully inited...");
            battleLogArea.appendText("...Successfully inited...\n");
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    void start() {
        if (!ground.isInited())
            return;
        ground.groundStart();
        System.out.println("...Battle starts...");
        battleLogArea.appendText("...Battle starts...\n");

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(REFRESH_CYCLE), actionEvent -> {
                    ground.trajectoriesMove(REFRESH_CYCLE);
                    refreshCanvas();
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        new Thread(() -> {
            int result = 0;
            try {
                while (true) {
                    result = ground.whoWins();
                    if (result == 1) { // Huluwas win
                        refreshCanvas();
                        System.out.println("Huluwas Win!");
                        battleLogArea.appendText("Huluwas Win!\n");
                        context.drawImage(images.get("HuluwasWin"), 108, 210, 521, 92);
                        break;
                    }
                    else if (result == 2) { // Creeps win
                        refreshCanvas();
                        System.out.println("Creeps Win!");
                        battleLogArea.appendText("Creeps Win!\n");
                        context.drawImage(images.get("CreepsWin"), 113, 208, 510, 95);
                        break;
                    }
                    TimeUnit.MILLISECONDS.sleep(REFRESH_CYCLE);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            timeline.stop();
            System.out.println("...Battle ends...");
            battleLogArea.appendText("...Battle ends...\n");
            logWriter.finish(result);
            ground.groundClear();
        }).start();
    }

    void save() {
        if (ground.isBattling() || ground.isInited() || logWriter.isEmpty() || logReader.isReplaying())
            return;
        File doc = new File("./save");
        if (!doc.exists()) {
            if(!doc.mkdir())
                return;
        }
        System.out.println("...Saving...");
        battleLogArea.appendText("...Saving...\n");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as");
        fileChooser.setInitialDirectory(new File("./save"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Huluwa Battle file", "*.hlwb"));
        fileChooser.setInitialFileName("default");
        File file = fileChooser.showSaveDialog(displayCanvas.getScene().getWindow());

        if (file == null) {
            System.out.println("...Failed to save...");
            battleLogArea.appendText("...Failed to save...\n");
            return;
        }
        logWriter.writeOut(file);
        System.out.println("...Saved as " + file.getName() + "...");
        battleLogArea.appendText("...Saved as " + file.getName() + "...\n");
    }

    void load() {
        if (ground.isBattling() || logReader.isReplaying())
            return;
        battleLogArea.clear();
        System.out.println("...Loading...");
        battleLogArea.appendText("...Loading...\n");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load");
        fileChooser.setInitialDirectory(new File("./save"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Huluwa Battle file", "*.hlwb"));
        File file = fileChooser.showOpenDialog(displayCanvas.getScene().getWindow());
        if (file == null) {
            System.out.println("...Failed to load...");
            battleLogArea.appendText("...Failed to load...\n");
            return;
        }

        ground.groundClear();
        logWriter.clear();
        logReader.readeIn(file);
        if (logReader.isEmpty()) {
            System.out.println("...Failed to load...");
            battleLogArea.appendText("...Failed to load...\n");
            return;
        }

        System.out.println("...Loaded...");
        battleLogArea.appendText("...Loaded...\n");
        int refreshCycle = logReader.getRefreshCycle();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(refreshCycle), actionEvent -> refreshReplay(logReader.getOneFrame()))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        new Thread(() -> {
            try {
                while (logReader.isReplaying()) {
                    TimeUnit.MILLISECONDS.sleep(refreshCycle);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            timeline.stop();
            System.out.println("...Replay ends...");
            battleLogArea.appendText("...Replay ends...\n");
            logReader.clear();
        }).start();
    }

    void setMusicVolume(char ch) {
        if (ch == '-') {
            double minVolumeGap = 0.02;
            double curVolume = musicPlayer.getVolume();
            if (curVolume - minVolumeGap <= 0)
                musicPlayer.setVolume(0);
            else
                musicPlayer.setVolume(curVolume - minVolumeGap);
        }
        else if (ch == '+') {
            double minVolumeGap = 0.02;
            double curVolume = musicPlayer.getVolume();
            if (curVolume + minVolumeGap >= 1)
                musicPlayer.setVolume(1);
            else
                musicPlayer.setVolume(curVolume + minVolumeGap);
        }
    }

    void refreshCanvas() {
        context.drawImage(BACKGROUND, 0, 0);
        ground.display(context);
        ground.displayTrajectory(context);
        logWriter.writeStop();
    }

    void refreshReplay(String[] frame) {
        if (frame == null || frame.length == 0)
            return;
        if (frame[0].equals("WINNER")) {
            int result = Integer.parseInt(frame[1]);
            if (result == 1) { // Huluwas win
                context.drawImage(images.get("HuluwasWin"), 108, 210, 521, 92);
            }
            else if (result == 2) { // Creeps win
                context.drawImage(images.get("CreepsWin"), 113, 208, 510, 95);
            }
            else System.out.println("replay winner error!");
            return;
        }
        context.drawImage(BACKGROUND, 0, 0);
        for(String str : frame) {
            String[] attributes = str.split("[()]"); // [name, position, health ratio]
            try {
                String[] name = attributes[0].split(":");
                String[] position = attributes[1].split(",");
                if (name[0].contains(Trajectory.class.getSimpleName())) { // log of Trajectory
                    double x = Double.parseDouble(position[0]);
                    double y = Double.parseDouble(position[1]);
                    context.drawImage(images.get(name[0]), x, y, TRAJECTORY_SIZE, TRAJECTORY_SIZE);
                }
                else { // log of Creature
                    int x = Integer.parseInt(position[1]) * IMAGE_SIZE;
                    int y = Integer.parseInt(position[0]) * IMAGE_SIZE;
                    if (Double.parseDouble(attributes[2]) <= 0) { // dead
                        if (name[0].equals(Huluwa.class.getSimpleName()) || name[0].equals(Grandpa.class.getSimpleName())) { // Good creature
                            context.drawImage(images.get("goodtomb"), x, y, IMAGE_SIZE, IMAGE_SIZE);
                        }
                        else { // Bad creature
                            context.drawImage(images.get("badtomb"), x, y, IMAGE_SIZE, IMAGE_SIZE);
                        }
                    }
                    else { // alive
                        if (name[0].equals(Huluwa.class.getSimpleName())) { // Huluwa
                            context.drawImage(images.get(name[0] + name[1]), x, y, IMAGE_SIZE, IMAGE_SIZE);
                        }
                        else { // others
                            context.drawImage(images.get(name[0]), x, y, IMAGE_SIZE, IMAGE_SIZE);
                        }
                        context.setFill(javafx.scene.paint.Color.RED);
                        context.fillRoundRect(x, y - 5, IMAGE_SIZE, 5, 10, 10);
                        if (name[0].equals(Huluwa.class.getSimpleName()) || name[0].equals(Grandpa.class.getSimpleName()))
                            context.setFill(javafx.scene.paint.Color.LIGHTGREEN);
                        else
                            context.setFill(Color.DEEPSKYBLUE);
                        double ratio = Double.parseDouble(attributes[2]);
                        context.fillRoundRect(x, y - 5, IMAGE_SIZE * ratio, 5, 10, 10);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class KeyEventHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case I:
                    init();
                    break;
                case SPACE:
                    start();
                    break;
                case S:
                    save();
                    break;
                case L:
                    load();
                    break;
                case UP:
                    setMusicVolume('+');
                    break;
                case DOWN:
                    setMusicVolume('-');
                    break;
                default:
            }
        }
    }
}
