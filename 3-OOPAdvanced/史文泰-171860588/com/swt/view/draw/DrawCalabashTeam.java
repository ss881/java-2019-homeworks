package com.swt.view.draw;

import com.swt.model.advance.Calabash;
import com.swt.model.controlled.CalabashTeam;
import com.swt.model.controlled.NMap;
import javafx.scene.canvas.GraphicsContext;

public class DrawCalabashTeam extends DrawBase {
    private CalabashTeam calabashTeam;                      //葫芦兄弟
    public DrawCalabashTeam(NMap nMap, GraphicsContext graphicsContext, CalabashTeam calabashTeam) {
        super(nMap, graphicsContext);
        this.calabashTeam = calabashTeam;
    }

    @Override
    public void draw() {
        for(Calabash calabash: calabashTeam.getCalabashes()){
            drawPic(calabash.getPicture());
        }
    }
}
