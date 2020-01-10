package com.e.view;

import com.all.control.PaintInterface;
import com.all.draw.DrawInterface;
import com.all.draw.PictureInterface;
import com.all.model.NMap;
import com.all.utils.ClassGenerator;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class MyPaint extends Canvas implements PaintInterface {
    private static final int SLEEPTIME = 50;                //睡眠100ms
    private DrawServer drawServer;                          //画图
    private DrawInterface drawCalabashTeam;
    private DrawInterface drawEnemyTeam;
    private DrawInterface drawMap;
    private DrawInterface drawGrand;
    private boolean isRunning = true;

    public MyPaint(double width, double height, NMap nMap, PictureInterface calabashTeam, PictureInterface enemyTeam, PictureInterface grandFather) throws Exception {
        super(width, height);
        GraphicsContext graphicsContext = getGraphicsContext2D();

        drawServer = new DrawServer();
        drawCalabashTeam = (DrawInterface)ClassGenerator.generateClassObject(
                ClassGenerator.generateClassConstructor(
                        "com.c.draw.DrawCalabashTeam", NMap.class, GraphicsContext.class, PictureInterface.class),
                        nMap, graphicsContext, calabashTeam);
        drawEnemyTeam = (DrawInterface)ClassGenerator.generateClassObject(
                ClassGenerator.generateClassConstructor(
                        "com.c.draw.DrawEnemyTeam", NMap.class, GraphicsContext.class, PictureInterface.class),
                        nMap, graphicsContext, enemyTeam);
        drawMap = (DrawInterface)ClassGenerator.generateClassObject(
                ClassGenerator.generateClassConstructor(
                        "com.c.draw.DrawMap", NMap.class, GraphicsContext.class),
                        nMap, graphicsContext);
        drawGrand = (DrawInterface)ClassGenerator.generateClassObject(
                ClassGenerator.generateClassConstructor(
                        "com.c.draw.DrawGrand", NMap.class, GraphicsContext.class, PictureInterface.class),
                        nMap, graphicsContext, grandFather);

//        this.drawServer = new DrawServer();
//        drawCalabashTeam = new DrawCalabashTeam(nMap, graphicsContext, calabashTeam);
//        drawEnemyTeam = new DrawEnemyTeam(nMap, graphicsContext, enemyTeam);
//        drawMap = new DrawMap(nMap, graphicsContext);
//        drawGrand = new DrawGrand(nMap, graphicsContext, grandFather);
    }

    /**
     * 绘画线程
     */
    private class DrawThread extends Thread{
        @Override
        public void run() {
            while (isRunning){
                Platform.runLater(() -> {
                    try {
                        drawServer.draw(drawMap);
                        drawServer.draw(drawCalabashTeam);
                        drawServer.draw(drawEnemyTeam);
                        drawServer.draw(drawGrand);
//                        ClassGenerator.getAccessibleMethod(drawServer, 0, "draw", Object.class).invoke(drawServer, drawMap);
//                        ClassGenerator.getAccessibleMethod(drawServer, 0, "draw", Object.class).invoke(drawServer, drawCalabashTeam);
//                        ClassGenerator.getAccessibleMethod(drawServer, 0, "draw", Object.class).invoke(drawServer, drawEnemyTeam);
//                        ClassGenerator.getAccessibleMethod(drawServer, 0, "draw", Object.class).invoke(drawServer, drawGrand);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
