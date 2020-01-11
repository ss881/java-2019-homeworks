package com.c.draw;

import com.all.draw.PictureInterface;
import com.all.model.NMap;
import com.all.model.Picture;
import javafx.scene.canvas.GraphicsContext;

public class DrawEnemyTeam extends DrawBase {
    private PictureInterface enemyTeam;

    public DrawEnemyTeam(NMap nMap, GraphicsContext graphicsContext, PictureInterface enemyTeam) throws Exception {
        super(nMap, graphicsContext);
        this.enemyTeam = enemyTeam;
    }

    @Override
    public void draw() throws Exception {
        for(Picture picture: enemyTeam.getPictures()){
            drawPic(picture);
        }
    }
}
