package com.swt.view;

import com.swt.model.controlled.GrandFather;
import com.swt.model.controlled.CalabashTeam;
import com.swt.model.controlled.EnemyTeam;
import com.swt.model.controlled.NMap;
import com.swt.view.draw.*;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class MyPaint extends Canvas {
    private static final int SLEEPTIME = 50;                //睡眠100ms
    private DrawServer drawServer;                          //画图
    private DrawCalabashTeam drawCalabashTeam;
    private DrawEnemyTeam drawEnemyTeam;
    private DrawMap drawMap;
    private DrawGrand drawGrand;
    private boolean isRunning = true;

    public MyPaint(double width, double height, NMap nMap, CalabashTeam calabashTeam, EnemyTeam enemyTeam, GrandFather grandFather) {
        super(width, height);
        GraphicsContext graphicsContext = getGraphicsContext2D();
        this.drawServer = new DrawServer();
        drawCalabashTeam = new DrawCalabashTeam(nMap, graphicsContext, calabashTeam);
        drawEnemyTeam = new DrawEnemyTeam(nMap, graphicsContext, enemyTeam);
        drawMap = new DrawMap(nMap, graphicsContext);
        drawGrand = new DrawGrand(nMap, graphicsContext, grandFather);
    }

    /**
     * 绘画线程
     */
    private class DrawThread extends Thread{
        @Override
        public void run() {
            while (isRunning){
                Platform.runLater(() -> {
                    drawServer.draw(drawMap);
                    drawServer.draw(drawCalabashTeam);
                    drawServer.draw(drawEnemyTeam);
                    drawServer.draw(drawGrand);
                });
                //画完后休息一段时间，避免阻塞
                try {
                    Thread.sleep(SLEEPTIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void drawMapStop(){
        isRunning = false;
    }

    public void startDraw(){
        DrawThread drawThread = new DrawThread();
        drawThread.start();
    }
}
