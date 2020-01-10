package com.c.draw;

import com.all.draw.PictureInterface;
import com.all.model.NMap;
import com.all.model.Picture;
import javafx.scene.canvas.GraphicsContext;

public class DrawGrand extends DrawBase {
    private PictureInterface grandFather;                        //老爷爷
    public DrawGrand(NMap nMap, GraphicsContext graphicsContext, PictureInterface grandFather) throws Exception {
        super(nMap, graphicsContext);
        this.grandFather = grandFather;
    }

    @Override
    public void draw() throws Exception{
        for(Picture picture: grandFather.getPictures()){
            drawPic(picture);
        }
    }
}
