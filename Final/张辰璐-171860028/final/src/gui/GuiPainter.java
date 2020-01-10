package gui;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import record.FrameInfo;
import chessboard.BattleField;

import javafx.scene.canvas.Canvas;

public class GuiPainter implements Runnable {
    //refresh the frame display of field every fixed time
    private BattleField battleField;
    private Canvas battleFieldCanvas;

    boolean isKilled = false;

    ArrayList<FrameInfo> frameInfos;

    public GuiPainter(Canvas canvas, BattleField field) {
        battleFieldCanvas = canvas;
        battleField = field;
    }

    public void drawBattleField() {
        battleField.guiDisplay(battleFieldCanvas, frameInfos);
    }

    @Override
    public void run() {
        System.out.println("guiPainter is running");
        frameInfos = new ArrayList<>();
        while (!isKilled) {
            synchronized (battleField) {
                drawBattleField();
            }
            try {
                //refresh the gui every 100 ms
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("gui线程退出");
    }

    public void kill() {
        isKilled = true;
    }

    public ArrayList<FrameInfo> getFrameInfos() {
        return frameInfos;
    }
}