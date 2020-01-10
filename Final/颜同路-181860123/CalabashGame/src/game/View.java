package game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class View extends Pane {

    public Canvas canvas;
    public ImageView iv;

    public View() {
        drawPane();
    }

    public void drawPane() {
        canvas = new Canvas(1600, 810);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(400, 50, canvas.getWidth(), canvas.getHeight());

        //绘制棋盘
        gc.setStroke(Color.BLACK);

        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++) {
                gc.strokeRect(400 + i * 40, 50 + 40 * j, 40, 40);//清理一个矩形取区域的内容为白色
            }
    }

    public void update() {
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++) {
                if (Map.map[i][j] != null) {
                    GraphicsContext gc = canvas.getGraphicsContext2D();
                    //gc.strokeRect(400+i*40,50+40*j,40,40);//清理一个矩形取区域的内容为白色
                    gc.drawImage(new Image(Map.map[i][j].picture), 400 + i * 40, 50 + 40 * j, 40, 40);
                } else {
                    GraphicsContext gc = canvas.getGraphicsContext2D();
                    gc.strokeRect(400 + i * 40, 50 + 40 * j, 40, 40);//清理一个矩形取区域的内容为白色
                }
            }
    }

}
