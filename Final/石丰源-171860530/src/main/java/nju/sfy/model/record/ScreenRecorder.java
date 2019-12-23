package nju.sfy.model.record;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nju.sfy.model.Field;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenRecorder implements Runnable{
    private GridPane gridPane;
    private Field field;
    private int i = 0;
    private Robot robot;
    private Stage primaryStage;
    public final String storeDir="record/" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

    public ScreenRecorder(GridPane gridPane, Field field, Stage primaryStage) throws AWTException {
        this.gridPane = gridPane;
        this.field = field;
        robot = new Robot();
        this.primaryStage = primaryStage;
        new File(storeDir).mkdirs();
    }

    @Override
    public void run() {
        System.out.println(storeDir);
        /*
        //主界面直接不更新了
        Platform.runLater(() -> {
            while(!Thread.interrupted()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        */

        while (!Thread.interrupted()){
            try{
                BufferedImage image = robot.createScreenCapture(new Rectangle((int)primaryStage.getX() + 11, (int)primaryStage.getY() + 34, 1205, 765/* (int)primaryStage.getWidth(), (int)primaryStage.getHeight()*/));
                ImageIO.write(image,"png",new File(storeDir + "/" + i + ".png"));
                i = i + 1;
                Thread.sleep(1000);
            } catch (InterruptedException | IOException e) {
                return;
            }
        }

/*
        Platform.runLater(() -> {
            while(!Thread.interrupted()){
                WritableImage image = gridPane.snapshot(new SnapshotParameters(), null);
                i = i + 1;
                String path = "record/" +  i + ".png";
                try {
                    //field.draw();
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", new File(path));
                    Thread.sleep(1000);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

 */
        /*
        while(!Thread.interrupted()){
            try
            {
                //序列化要实现serilizable接口，序列化的每个对象都要实现serilizable接口
                ObjectOutputStream out =
                        new ObjectOutputStream(
                                new FileOutputStream("worm.out"));
                out.writeObject("Worm storage");
                out.writeObject(field);
                out.close(); // Also flushes output
                Thread.sleep(1000);
            }catch(IOException | InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        */
    }
}
