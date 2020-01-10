package com.a.model.controlled;

import com.all.model.Picture;
import com.a.model.basic.Character;
import com.all.draw.PictureInterface;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class GrandFather extends Character implements PictureInterface {
    private Picture goodPicture;
    public GrandFather(int px, int py, Image image, Picture goodPicture) throws Exception {
        super(px, py, image);
        this.goodPicture = goodPicture;
    }

    @Override
    public List<Picture> getPictures() {
        List<Picture> pictureList = new ArrayList<>();
        pictureList.add(getPicture());
        pictureList.add(goodPicture);
        return pictureList;
    }
}
