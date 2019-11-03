package com.swt.view.draw;

import com.swt.model.basic.Picture;
import com.swt.model.controlled.NMap;
import javafx.scene.canvas.GraphicsContext;

public class DrawBase {
    NMap nMap;                                      //地图
    public GraphicsContext graphicsContext;
    DrawBase(NMap nMap, GraphicsContext graphicsContext){
        this.nMap = nMap;
        this.graphicsContext = graphicsContext;
    }
    void drawPic(Picture picture){
        graphicsContext.drawImage(picture.getImage(),
                0, 0, picture.getWidth(), picture.getHeight(),
                picture.getPoint().getPx() * this.nMap.getPicture1().getWidth(),
                picture.getPoint().getPy() * this.nMap.getPicture1().getHeight(),
                picture.getWidth(), picture.getHeight());
    }
    public void draw(){}
}
