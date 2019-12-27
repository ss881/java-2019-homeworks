package com.swt.view.draw;

import com.swt.model.controlled.GrandFather;
import com.swt.model.controlled.NMap;
import javafx.scene.canvas.GraphicsContext;

public class DrawGrand extends DrawBase {
    private GrandFather grandFather;                        //老爷爷
    public DrawGrand(NMap nMap, GraphicsContext graphicsContext, GrandFather grandFather) {
        super(nMap, graphicsContext);
        this.grandFather = grandFather;
    }

    @Override
    public void draw() {
        drawPic(grandFather.getPicture());
        drawPic(grandFather.getGoodPicture());
    }
}
