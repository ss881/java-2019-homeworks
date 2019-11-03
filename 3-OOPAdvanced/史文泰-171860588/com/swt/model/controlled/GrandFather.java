package com.swt.model.controlled;

import com.swt.model.basic.Picture;
import com.swt.model.basic.Character;
import javafx.scene.image.Image;

public class GrandFather extends Character {
    private Picture goodPicture;
    public GrandFather(int px, int py, Image image, Picture goodPicture){
        super(px, py, image);
        this.goodPicture = goodPicture;
    }

    public Picture getGoodPicture(){
        return goodPicture;
    }
}
