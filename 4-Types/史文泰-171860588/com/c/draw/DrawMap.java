package com.c.draw;

import com.all.model.NMap;
import com.all.model.Picture;
import javafx.scene.canvas.GraphicsContext;

public class DrawMap extends DrawBase {
    private Picture pic1;
    private Picture pic2;
    private int number;
    public DrawMap(NMap nMap, GraphicsContext graphicsContext) throws Exception {
        super(nMap, graphicsContext);
        this.pic1 = nMap.getPictures().get(0);
        this.pic2 = nMap.getPictures().get(1);
        this.number = NMap.N;
    }

    @Override
    public void draw() {
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                if((i + j) % 2 == 0){
                    graphicsContext.drawImage(pic1.getImage(),
                            0, 0, pic1.getWidth(), pic1.getHeight(),
                            j * pic1.getWidth(), i * pic1.getHeight()
                            , pic1.getWidth(), pic1.getHeight());
                }else{
                    graphicsContext.drawImage(pic2.getImage(),
                            0, 0, pic2.getWidth(), pic2.getHeight(),
                            j * pic2.getWidth(), i * pic2.getHeight()
                            , pic2.getWidth(), pic2.getHeight());
                }
            }
        }
    }
}
