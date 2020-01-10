package FileIO;

import Creature.Creature;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.*;
import javafx.scene.image.*;
import java.io.*;
import javafx.stage.*;
import BattleField.*;
import Control.*;
import Position.Position;

public class FileIO {
	private BattleField battlefield;
    private Timer replayTimer = new Timer();
    private ArrayList<String> buffer = new ArrayList<>();
    private GUIControl guiControl;

    private final ImageView cross = new ImageView(new Image("fight.png", 50, 50, false, true));

    public FileIO(BattleField field, GUIControl guiControl) {
        this.battlefield = field;
        this.guiControl = guiControl;
    }

    private File loadFile() { //弹出文件选择窗口
        FileChooser logChooser = new FileChooser();
        logChooser.setTitle("加载游戏存档");
        
        logChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("选择log文件","*.log");
        logChooser.getExtensionFilters().add(extensionFilter);
        Stage stage = (Stage)guiControl.pane.getScene().getWindow();
        return logChooser.showOpenDialog(stage);
    }

    private ArrayList<String> parseFile(File file) {
    	System.out.println(file);
        if(file == null) return buffer;
        BufferedReader reader;
        try {
            //装饰器模式
            reader = new BufferedReader(new FileReader(file));
            String text;
            while((text = reader.readLine()) != null) {
                buffer.add(text);
            }
        } catch (IOException e) {
            throw new RuntimeException("加载存档错误");
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("关闭流失败");
        }
        return buffer;
    }

    //写入日志文件
    public void writeFile(String filename, String Log) {
        try {
            //装饰器模式
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false));
            writer.write(Log);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("存档错误!");
        }
    }

    public void replayGame() {
        if(GameLogic.isReplaying || GameLogic.isGaming) return;

        buffer = parseFile(loadFile());
        if(buffer.isEmpty()) return;
        GameLogic.isReplaying = true;
        
        guiControl.timeline.playFromStart();
        
        replayTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Platform.runLater(() -> {
                    String text = buffer.get(0);
                    buffer.remove(0);
                    if(text.equals("New Log")) return;
                    if(text.equals("Game Start")) return;
                    System.out.println(text);
                    
                    String[] s = text.split(" ");
                    if(s.length == 1) { //阵型
                    	//System.out.println(text);
                        battlefield.initBattleField(text);
                        battlefield.allSetView();
                        guiControl.refresh();
                        return;
                    }
                    if(s.length == 2) {
                        GameLogic.isReplaying = false;
                        guiControl.timeline.pause();
                        System.gc(); cancel();
                    } else if(s.length == 6) {
                        int fx = Integer.valueOf(s[0]);
                        int fy = Integer.valueOf(s[1]);
                        int tx = Integer.valueOf(s[3]);
                        int ty = Integer.valueOf(s[4]);
                        if(fx == tx) {
                            cross.setY(fx * 50 + 100);
                            cross.setX((fy+ty) * 25 + 100);
                        } else {
                            cross.setX(fy * 50 + 100);
                            cross.setY((fx+tx) * 25 + 100);
                        }
                        guiControl.pane.getChildren().remove(cross);
                        guiControl.pane.getChildren().add(cross);
                    } else if(s.length == 5 && s[2].equals("to")) {
                        int fx = Integer.valueOf(s[0]);
                        int fy = Integer.valueOf(s[1]);
                        int tx = Integer.valueOf(s[3]);
                        int ty = Integer.valueOf(s[4]);
                        Position start = new Position(fx, fy);
                        Position next = new Position(tx, ty);
                        Creature en = battlefield.getLiving(start);
                        en.setPosition(next);
                        battlefield.board[start.getX()][start.getY()].setNull();
                        battlefield.board[next.getX()][next.getY()].setCreature(en);
                        guiControl.refresh();
                    } else if(s.length == 3 && s[2].equals("DIE")) {
                        int dx = Integer.valueOf(s[0]);
                        int dy = Integer.valueOf(s[1]);
                        Position p = new Position(dx, dy);
                        battlefield.getLiving(p).death();
                        guiControl.refresh();
                    } else if(s.length == 3 && s[2].equals("DISAPPEAR")) {
                        int dx = Integer.valueOf(s[0]);
                        int dy = Integer.valueOf(s[1]);
                        Creature ee = battlefield.board[dx][dy].getCreature();
                        assert (!ee.isAlive());
                        battlefield.board[dx][dy].setNull();
                        guiControl.refresh();
                    }
                });
            }
        }, 0, 250);
    }
}
