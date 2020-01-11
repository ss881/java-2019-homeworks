package com.a.model.advance;

import com.all.utils.FileUtils;
import com.a.model.basic.Character;
import com.all.model.Picture;
import com.all.model.Point;
import javafx.scene.image.Image;

import java.io.IOException;

public class Snake extends Character {
    private Picture orderPicture;
    public Snake(int px, int py, Image image, Picture orderPicture) throws Exception {
        super(px, py, image);
        this.orderPicture = orderPicture;
    }

    public void setOrderPicture(String orderPath) throws IOException {
        orderPicture = new Picture(new Point(7, 0), FileUtils.getImage(orderPath));
    }

    public Picture getOrderPicture(){
        return orderPicture;
    }

}
