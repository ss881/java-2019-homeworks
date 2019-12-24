package cn.edu.nju.huluwa;

import cn.edu.nju.huluwa.bullet.Bullet;
import cn.edu.nju.huluwa.bullet.BulletManager;
import cn.edu.nju.huluwa.creature.Creature;
import cn.edu.nju.huluwa.creature.Leader;
import cn.edu.nju.huluwa.record.RecordPlayer;
import cn.edu.nju.huluwa.team.Team;
import cn.edu.nju.huluwa.huluworld.HuluWorld;
import cn.edu.nju.huluwa.util.ImageLoader;
import cn.edu.nju.huluwa.huluworld.WorldBuilder;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Optional;

import static cn.edu.nju.huluwa.Config.*;

public class Scene {
    private Canvas canvas;
    private TextArea info;
    private HuluWorld huluWorld;
    private Creature selectedCreature;
    private Team<? extends Leader, ? extends Creature> winner;
    private Image background;

    public Scene(Canvas canvas, TextArea info) {
        this.canvas = canvas;
        this.info = info;
        huluWorld = WorldBuilder.buildHuluWorld(this);
        background = ImageLoader.getBackground();
        selectedCreature = null;
        canvas.setFocusTraversable(true);
        initListener();
        paint();
    }

    public void initListener() {
        canvas.setOnMouseClicked(event -> {
            if (selectedCreature != null) selectedCreature.setSelected(false);
            selectedCreature = huluWorld.getPositionManager().getPosition((int) event.getX() / GRID_WIDTH, (int) event.getY() / GRID_WIDTH).getCreature();
            if (selectedCreature != null) selectedCreature.setSelected(true);
            repaint();
        });
        canvas.getParent().getParent().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            event.consume();
            switch (event.getCode()) {
                case SPACE:
                    if (huluWorld.isInBattle()) {
                        if (huluWorld.isPaused()) huluWorld.continueBattle();
                        else huluWorld.pauseBattle();
                    } else startBattle();
                    break;
                case L:
                    replayRecords();
                    break;
                case Q:
                    huluWorld.endBattle();
                    break;
                default:
                    break;
            }
            if (selectedCreature != null && !selectedCreature.isDead()) {
                switch (event.getCode()) {
                    case W:
                        selectedCreature.move(Creature.Direction.TOP);
                        break;
                    case S:
                        selectedCreature.move(Creature.Direction.DOWN);
                        break;
                    case A:
                        selectedCreature.move(Creature.Direction.LEFT);
                        break;
                    case D:
                        selectedCreature.move(Creature.Direction.RIGHT);
                        break;
                    case K:
                        selectedCreature.attack();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void sortHuluwa() {
        if (!ensureBefore()) return;
        huluWorld.sortHuluwa();
        repaint();
    }

    public void shuffleHuluwa() {
        if (!ensureBefore()) return;
        huluWorld.shufflehuluwa();
        repaint();
    }

    public void buZhen() {
        if (!ensureBefore()) return;
        info.appendText(huluWorld.badTeamBuZhen());
        repaint();
    }

    public synchronized void showInfo(String str) {
        info.appendText(str);
        repaint();
    }

    public void startBattle() {
        if (!ensureBefore()) return;
        huluWorld.startBattle();
    }

    public void saveRecords() {
        if (!ensureState()) return;
        if (huluWorld.getRecordManager().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("暂无记录可以保存!");
            alert.setTitle("警告");
            alert.show();
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("保存记录");
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("游戏记录", "*.json")
        );
        File f = fileChooser.showSaveDialog(null);
        if (f == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("保存记录已取消!");
            alert.setTitle("警告");
            return;
        }
        huluWorld.getRecordManager().exportRecords(f);
//        System.out.println(f);
    }

    private boolean ensureBefore() {
        return ensureState() && ensureRecord();
    }

    private boolean ensureState() {
        if (huluWorld.isInBattle()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("警告");
            alert.setContentText("战斗还未结束，是否继续？");
            Optional<ButtonType> buttonType = alert.showAndWait();
            System.out.println(buttonType);
            if (buttonType.get() != ButtonType.OK) return false;
            huluWorld.endBattle();
        }
        return true;
    }

    private boolean ensureRecord() {
        if (!huluWorld.getRecordManager().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("警告");
            alert.setContentText("当前记录还未保存，是否继续?");
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get() != ButtonType.OK) return false;
//            huluWorld.getRecordManager().clear();
        }
        return true;
    }

    public void replayRecords() {
        if (!ensureBefore()) return;
        huluWorld.getBulletManager().clear();
        huluWorld.getRecordManager().clear();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("读取记录");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("游戏记录", "*.json")
        );
        File f = fileChooser.showOpenDialog(null);
        if (f == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setContentText("读取记录已取消!");
            alert.show();
            return;
        }
        huluWorld.getRecordManager().importRecords(f);
        // TODO: fix bugs
        new Thread(new RecordPlayer(huluWorld, huluWorld.getRecordManager().getRecords())).start();
    }

    public boolean isBattleEnd() {
        return null != (winner = huluWorld.getWinner());
    }

    private boolean inCanvas(int x, int y) {
        return x >= 0 && x < canvas.getWidth() && y >= 0 && y < canvas.getHeight();
    }

    public synchronized void repaint() {
        Platform.runLater(this::paint);
//        // TODO: do paint jobs
//        paint();
    }

    public void paint() {
//        System.out.println(Platform.isFxApplicationThread());
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.save();
        // draw background
        g.drawImage(background, 0, 0);
// draw creatures
        for (Creature c : huluWorld.getAllCreatures()) {
            synchronized (c) {
                if (!c.isDead() && c.hasPosition()) {
                    // draw health bar
                    double x = c.getImage().getWidth() * c.getPosition().getX();
                    double y = c.getImage().getHeight() * c.getPosition().getY();
                    g.drawImage(c.getImage(), x, y);
                    g.setStroke(Color.BLACK);
                    g.strokeRect(x + 5, y + 1, 40, 3);
                    g.setFill(Color.RED);
                    g.fillRect(x + 5, y + 1, 40 * c.getHealth(), 3);
                    if (c == selectedCreature) {
                        double gLineWidth = g.getLineWidth();
                        g.setLineWidth(3.0);
                        g.setStroke(Color.GOLD);
                        g.strokeRect(x, y, GRID_WIDTH, GRID_HEIGHT);
                        g.setLineWidth(gLineWidth);
                    }
                }
            }
        }
        BulletManager bulletManager = huluWorld.getBulletManager();
//        try {
//            bulletManager.lock();
        for (Bullet bullet : bulletManager.getBullets()) {
            if (inCanvas(bullet.getX(), bullet.getY())) {
                g.setFill(bullet.getColor().getColor());
                g.fillOval(bullet.getX(), bullet.getY(), BULLET_RADIUS, BULLET_RADIUS);
            }
        }
//        } finally {
//            bulletManager.unlock();
//        }
        if (isBattleEnd()) {
            g.drawImage(winner.getWinImage(), 200, 100);
        }
        g.restore();
    }
}
