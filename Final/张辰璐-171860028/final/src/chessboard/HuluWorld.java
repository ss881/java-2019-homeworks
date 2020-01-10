package chessboard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


import chessman.creature.*;
import record.CreatureInfo;
import record.FrameInfo;
import chessman.ChessInfo;
import gui.GuiPainter;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class HuluWorld implements Runnable,Constants{

    private BattleField battleField = new BattleField(Constants.ROW, Constants.COLUMN);

    ArrayList<Justice> justices = new ArrayList<>();
    ArrayList<Evil> pawns = new ArrayList<>();

    int battleResult = 0; // 1: suc, 0: loss

    private Canvas battleFieldCanvas;

    private Button saveLogBtn;
    private Button discardBtn;

    private GuiPainter guiPainter;

    private ExecutorService creatureThreadPool = Executors.newCachedThreadPool(); // creature use this to take actions
//    private CountDownLatch creatureThreadPool =new CountDownLatch(16);

    private ExecutorService guiThread = Executors.newSingleThreadExecutor(); // paintGui
    private ExecutorService battleEventThreadPool = Executors.newCachedThreadPool(); // battlefield use this to process events


    private String formname;
    private Formation formation;
    private JusticeList justiceList;
    private MonsterList monsterlist;

    public HuluWorld(Canvas battleFieldCanvas, Button saveLogBtn, Button discardBtn) {
        //resources
        this.formname = "Changeshe";
        this.battleFieldCanvas = battleFieldCanvas;
        this.saveLogBtn = saveLogBtn;
        this.discardBtn = discardBtn;
        guiPainter = new GuiPainter(battleFieldCanvas, battleField);

        //ini the chessmen(evil and justice)
        justiceList = new JusticeList();
        justiceList.init();

        for(Justice temp: justiceList.HuluCollection) {
            justices.add(temp);
        }
        monsterlist = new MonsterList();
        monsterlist.init();
        for(Pawn temp:monsterlist.pawnCollection)
        {
            pawns.add(temp);
        }
        //put chessmen onto chessboard
        formation = new Formation(formname);
        formation.setBattlePlace(battleField, justices, pawns);

        //show gui
        guiPainter.drawBattleField();

        //tell the chessmen where to request moving
        ChessInfo.setField(battleField);

        //allocate Thread live to chessboard, from now on it can response to moving request
        battleField.setEventThreadPool(battleEventThreadPool);
    }

    public void setFormname(String name){formname = name;}

    public void ChangeFormation() {
        battleField.clearAll();
        formation = new Formation(formname);
        formation.setBattlePlace(battleField, justices, pawns);
        guiPainter.drawBattleField();
    }

    public void gameRoundStart() {
        //give chessmen live
        for (Justice justice : justices) {
            creatureThreadPool.execute(justice);
        }

        for (Evil pawn : pawns) {
            creatureThreadPool.execute(pawn);
        }
        creatureThreadPool.shutdown();
        guiThread.execute(guiPainter);
        guiThread.shutdown();

        //make the thread never goto behind before a game end
        while (!(allBadsDead()||allGoodsDead())) {}
//        try {
//            creatureThreadPool.latch.await();
//        } catch (InterruptedException E) {
//            // handle
//        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        guiPainter.kill();

        //show the result page
        GraphicsContext gc = battleFieldCanvas.getGraphicsContext2D();
        if (allBadsDead() && !allGoodsDead()) {
            battleResult = 1;
            gc.drawImage(new Image("victory.jpg"), 20, 0);
            saveLogBtn.setVisible(true);
            discardBtn.setVisible(true);
        } else if (allGoodsDead() && !allBadsDead()) {
            gc.drawImage(new Image("failed.jpg"), 20, 0);
            battleResult = 0;
            saveLogBtn.setVisible(true);
            discardBtn.setVisible(true);
        }
        killAllTheThread();
    }

    @Override
    public void run() {
//        System.out.println("葫芦世界线程开始");
        gameRoundStart();
//        System.out.println("葫芦世界线程退出");
    }


    public void killAllTheThread() {
        for (Justice justice : justices) {
            justice.kill();
        }
        for (Evil pawn : pawns) {
            pawn.kill();
        }
        //battleField.kill();
        guiPainter.kill();
        battleEventThreadPool.shutdown();
    }

    private boolean allGoodsDead() {
        for (Justice justice : justices) {
            if (justice.getState() != CreatureState.DEAD && !justice.isKilled()) {
                return false;
            }
        }
        return true;
    }

    private boolean allBadsDead() {
        for (Evil pawn : pawns) {
            if (pawn.getState() != CreatureState.DEAD && !pawn.isKilled()) {
                return false;
            }
        }
        return true;
    }

    public void saveGameLog(File file) throws Exception {
        BufferedWriter fout = null;
        try {
            fout = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<FrameInfo> frameInfos = guiPainter.getFrameInfos();
        fout.write(frameInfos.size()+"\n");
        for (int i = 0; i < frameInfos.size(); i++) {
            int creatureInfoNum = frameInfos.get(i).creatureInfos.size();
            fout.write(creatureInfoNum+"\n");
            for (CreatureInfo creatureInfo : frameInfos.get(i).creatureInfos) {
                fout.write(creatureInfo.toString());
            }
        }
        fout.write(battleResult+"\n");
        fout.flush();
        fout.close();
    }
}