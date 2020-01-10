package com.b.control;

import com.all.model.NMap;
import com.all.model.Picture;
import com.all.model.Point;
import com.all.control.EnemyTeamInterface;
import com.all.control.PaintInterface;
import com.all.draw.PictureInterface;
import com.all.utils.ClassGenerator;
import com.all.utils.FileUtils;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.List;

public class Control{
    private static final int ALARMGAP = 6000;
    private PaintInterface myPaint;                                //画笔
    private int PointIndex = 1;                             //下一个阵型
    private int PointMax = 5;                               //阵型总数

    public Control(double width, double height){
        //利用反射初始化地图和各个人物
        //是否不应该使用反射？？？？？？？
        try {
            NMap nMap = (NMap)ClassGenerator.generateClassObject(
                    ClassGenerator.generateClassConstructor(
                            "com.all.model.NMap", String.class, String.class),
                            "SRCFile/pic2.jpg", "SRCFile/pic3.jpg");

            PictureInterface calabashTeam = (PictureInterface)ClassGenerator.generateClassObject(
                    ClassGenerator.generateClassConstructor(
                            "com.a.model.controlled.CalabashTeam"));

            EnemyTeamInterface enemyTeam = (EnemyTeamInterface)ClassGenerator.generateClassObject(
                    ClassGenerator.generateClassConstructor(
                            "com.a.model.controlled.EnemyTeam", List.class),
                    FileUtils.getPointList("SRCFile/Point0.txt"));

            Picture goodPicture = new Picture(new Point(1, 0), FileUtils.getImage("SRCFile/yeye.jpg"));
            PictureInterface grandFather = (PictureInterface)ClassGenerator.generateClassObject(
                    ClassGenerator.generateClassConstructor(
                            "com.a.model.controlled.GrandFather", int.class, int.class, Image.class, Picture.class),
                    0, 0, FileUtils.getImage("SRCFile/grandFather.jpg"), goodPicture);

            myPaint = (PaintInterface)ClassGenerator.generateClassObject(
                    ClassGenerator.generateClassConstructor(
                            "com.e.view.MyPaint", double.class, double.class, NMap.class, PictureInterface.class, PictureInterface.class, PictureInterface.class),
                            width, height, nMap, calabashTeam, enemyTeam, grandFather);
            myPaint.startDraw();
            setAlarm(enemyTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置定时器，6秒中改变一次阵型
     */
    private void setAlarm(EnemyTeamInterface enemyTeam){
        EventHandler<ActionEvent> eventHandler = e -> {
            new Thread(() -> {
                try {
                    enemyTeam.Change("SRCFile/Point" + PointIndex + ".txt", "SRCFile/Form" + PointIndex + ".jpg");
                    PointIndex = (PointIndex + 1) % PointMax;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        };
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(ALARMGAP), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    public Object getCanvas(){
        return this.myPaint;
    }

}
