package com.c.draw;

import com.all.draw.DrawInterface;
import com.all.model.NMap;
import com.all.model.Picture;
import javafx.scene.canvas.GraphicsContext;

public abstract class DrawBase implements DrawInterface {
    private int width;                                          //每格的宽
    private int height;                                         //每格的高
    GraphicsContext graphicsContext;
    public DrawBase(NMap nMap, GraphicsContext graphicsContext) throws Exception {
        this.graphicsContext = graphicsContext;
        this.width = nMap.getPictures().get(0).getWidth();
        this.height = nMap.getPictures().get(0).getHeight();
    }
    public void drawPic(Picture picture) throws Exception {
        graphicsContext.drawImage(picture.getImage(),
                0, 0, picture.getWidth(), picture.getHeight(),
                picture.getPoint().getPx() * width,
                picture.getPoint().getPy() * height,
                picture.getWidth(), picture.getHeight());
    }
}
