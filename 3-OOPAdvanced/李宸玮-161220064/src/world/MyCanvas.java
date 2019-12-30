package world;

import creature.*;
import formation.ChangShe;
import formation.FengShi;
import formation.HeYi;
import formation.YanXing;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyCanvas {
    public static void drawCanvas(StackPane root) {
        BattleField battleStage = new BattleField();
        Canvas canvas = new Canvas(1200, 691);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
    /*    for(int i=0;i<7;i++){
            Image image=battleStage.calaBrothers.getCalaBrothers()[i].getImage();
            gc.drawImage(image,battleStage.calaBrothers.getCalaBrothers()[i].positon.y*70,battleStage.calaBrothers.getCalaBrothers()[i].positon.x*52,160,120);
            //battleStage.calaBrothers.getCalaBrothers()[i].start();
        }*/

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 25; j++) {
                if (battleStage.map[i][j].exist) {
                    Image image=battleStage.map[i][j].getCharacter().getImage();
                    int x,y;
                    x=battleStage.map[i][j].getCharacter().getposition().x;
                    y=battleStage.map[i][j].getCharacter().getposition().y;
                    gc.drawImage(image,y,x,160,120);
                }
            }

        }

    }
}
