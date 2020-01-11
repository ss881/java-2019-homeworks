package Control;

import Creature.*;
import BattleField.*;
import FileIO.FileIO;
import Formation.Formation;
import Position.Position;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.util.*;

public class GameLogic {

    public static boolean isGaming = false;
    public static boolean isReplaying = false;
    
    private BattleField battlefield = new BattleField();
    
    private Timer clearTimer = new Timer (); //清除墓碑的线程
    private String combatLog; //记录战斗的字符串
    private ArrayList<Position> tomb = new ArrayList<>(); //墓碑列表
    private final String combatFile = System.getProperty("user.dir") + "\\Combat.log"; //存档文件
    
    private ArrayList<Integer> randArray = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));

    
    public void initGame(String input) {
        //for(int i = 0; i < 7; i ++) huluwa.add(new Huluwa(i + 1));
        //Collections.shuffle(huluwa); //打乱
        battlefield.initBattleField(input);
        battlefield.allSetView();
        
        guiControl.refresh();

        guiControl.timeline.stop();
        guiControl.pane.setOnKeyPressed(new KeyParse());
    }
    
    //获取键盘输入
    class KeyParse implements EventHandler<KeyEvent> {
        public void handle(KeyEvent event) {
            if (!GameLogic.isGaming || !GameLogic.isReplaying) {
                if (event.getCode() == KeyCode.L)
                    fileReplay.replayGame();
                else if(event.getCode() == KeyCode.F21)
                	playGame();
            }
        }
    }

    public GUIControl guiControl = new GUIControl(battlefield, createNavigation());
    private FileIO fileReplay = new FileIO(battlefield, guiControl);

    //非NPC的生物体线程
    private synchronized TimerTask getTask(Creature en) {
        return new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    Position start = en.getPosition();
                    
                    if (!isGaming) { //如果比赛已经结束
                        if(en.isAlive()) cancel();;
                        System.gc();
                        cancel();
                    }
                    
                    if (!en.isAlive()) { //如果该生物已经死亡
                        tomb.add(en.getPosition());
                        System.gc(); cancel();
                    }
                    
                    Position neighbor = battlefield.isBeside(start);
                    if (neighbor != null && battlefield.getLiving(neighbor)!=null && en.isAlive())
                        fightRecord(start, neighbor); //记录到日志文件
                    else {
                        Position end = chooseEnemy(en);
                        if (end == null) {
                            //比赛结束界面
                        	cancel();
                            if(isGaming) endGame(en);
                            System.gc(); cancel();
                        } else if (en.isAlive())
                            forwardGame(start, end);
                    }
                });
            }
        };
    }
    //清理墓碑线程 定期随机选取一个墓碑消失
    private void initClearThread() {
        clearTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                synchronized (this) {
                    if(!isGaming) { System.gc();  cancel(); }
                    else if (!tomb.isEmpty()) {
                        Collections.shuffle(tomb);
                        combatLog += tomb.get(0).toString() + " DISAPPEAR\n";
                        if(isGaming) battlefield.board[tomb.get(0).getX()][tomb.get(0).getY()].setNull();
                        tomb.remove(0);
                    }
                }
            }
        },6000, 3000);//6s后每3s清空一个墓碑
    }

    //打乱对方的数组，并且遍历直到找到一个活的生命体
    private synchronized Position chooseEnemy(Creature en) { //选择下一个敌人
        if (en.getJustice()) { //如果是葫芦娃
            Collections.shuffle(battlefield.getLouluo());
            for (Louluo s : battlefield.getLouluo())
                if (s.isAlive())  return s.getPosition();
            if (battlefield.getXiezijing().isAlive())
                return battlefield.getXiezijing().getPosition();
        } else { //如果是妖精
            Collections.shuffle(randArray);
            for (Integer i : randArray)
                if (battlefield.getHuluwa().get(i).isAlive())
                    return battlefield.getHuluwa().get(i).getPosition();
        }
        return null;
    }
    
    //两个位置打架，判定输赢
    private synchronized boolean letsFight(Position a, Position b) {
        //assert(battlefield.getLiving(a) != null && battlefield.getLiving(b) != null);
        Creature er = battlefield.getLiving(a);
        Creature ee = battlefield.getLiving(b);
        //assert (er.isAlive() && ee.isAlive());
        int prob_a, prob_b;
        if(er.getJustice()) {
            prob_a = battlefield.getLouluo().size() + 1;
            prob_b = battlefield.getHuluwa().size();
        } else {
            prob_a = battlefield.getHuluwa().size();
            prob_b = battlefield.getLouluo().size() + 1;
        }
        
        //采取的对抗算法 人少的一边容易输
        Random rand = new Random();
        int live = rand.nextInt(prob_a + prob_b);
        if(live < prob_a) {
            ee.death(); return true;
        } else {
            er.death(); return false;
        }
    }
    
    //打架并且记录
    private synchronized void fightRecord(Position start, Position neighbor) { //打架过程和记录
        guiControl.addCross(start, neighbor);
        guiControl.refresh();
        combatLog += start.toString() + " and " + neighbor.toString() + " FIGHT\n";
        if (letsFight(start, neighbor))
            combatLog += neighbor.toString() + " DIE\n";
        else combatLog += start.toString() + " DIE\n";
    }
    
    //将一个生物体移到下一个位置上
    private synchronized void forwardGame(Position start, Position end) {
        Position next = battlefield.findNext(start, end);
        combatLog += (start.toString() + " to " + next.toString() + '\n');
        Creature en = battlefield.getLiving(start);
        en.setPosition(next);
        battlefield.board[start.getX()][start.getY()].setNull();
        battlefield.board[next.getX()][next.getY()].setCreature(en);
    }
    //游戏结束，将输赢结果输出到文件
    private synchronized void endGame(Creature en) {
        if (en.getJustice())
            combatLog += "Calabash win\n";
        else
            combatLog += "Soldiers win\n";
        fileReplay.writeFile(combatFile, combatLog);
        isGaming = false;
        guiControl.timeline.pause();
    }
    

    private void playGame() {
        if(isGaming || isReplaying) return;
        GameLogic.isGaming = true;
        this.tomb.clear();
        combatLog = "New Log\n" + "Game Start\n" + battlefield.getXiezijing().getFormation().name + '\n';
        
        guiControl.timeline.playFromStart();
        guiControl.refreshTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(!GameLogic.isGaming) { System.gc(); cancel(); }
                Platform.runLater(() -> guiControl.refresh());
            }
        }, 100, 500);
        
        this.initClearThread();
        for (Huluwa c : battlefield.getHuluwa())
            c.timer.scheduleAtFixedRate(getTask(c), 1000, 1000);
        battlefield.getXiezijing().timer.scheduleAtFixedRate(getTask(battlefield.getXiezijing()), 1000, 1000);
        for (Louluo s : battlefield.getLouluo())
            s.timer.scheduleAtFixedRate(getTask(s), 1000, 1000);
    }
    
    private VBox createNavigation() { //设置按钮以及事件监听
        Button buttonStart = new Button("开始");
        buttonStart.setOnAction((ActionEvent t) -> playGame());
        Button buttonLoad = new Button("回放");
        buttonLoad.setOnAction((ActionEvent t) -> fileReplay.replayGame());
        
        Button button1 = new Button("鹤翼");
        button1.setOnAction((ActionEvent t) -> forceRestart("鹤翼"));
        Button button2 = new Button("雁行");
        button2.setOnAction((ActionEvent t) -> forceRestart("雁行"));
        Button button3 = new Button("长蛇");
        button3.setOnAction((ActionEvent t) -> forceRestart("长蛇"));
        Button button4 = new Button("锋矢");
        button4.setOnAction((ActionEvent t) -> forceRestart("锋矢"));
        Button button5 = new Button("偃月");
        button5.setOnAction((ActionEvent t) -> forceRestart("偃月"));
        Button button6 = new Button("方门");
        button6.setOnAction((ActionEvent t) -> forceRestart("方门"));
        Button button7 = new Button("鱼鳞");
        button7.setOnAction((ActionEvent t) -> forceRestart("鱼鳞"));
        Button button8 = new Button("衡轭");
        button8.setOnAction((ActionEvent t) -> forceRestart("衡轭"));

        //垂直布局
        VBox navigate = new VBox(10);
        navigate.getChildren().addAll(buttonStart, buttonLoad,button1, button2, button3, button4,
                button5, button6, button7, button8);
        navigate.setLayoutX(30);
        navigate.setLayoutY(120);
        return navigate;
    }
    
    //改变阵型强行重启
    private synchronized void forceRestart(String formation) {
        if(isReplaying || isGaming) return;
        battlefield.initBattleField(formation);
        battlefield.allSetView();
        guiControl.timeline.stop();
        guiControl.refresh();
    }
}
