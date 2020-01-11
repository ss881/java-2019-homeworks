package com.c.draw;

import com.all.draw.PictureInterface;
import com.all.model.NMap;
import com.all.model.Picture;
import javafx.scene.canvas.GraphicsContext;

public class DrawCalabashTeam extends DrawBase {
    private PictureInterface calabashTeam;                                //葫芦兄弟
    public DrawCalabashTeam(NMap nMap, GraphicsContext graphicsContext, PictureInterface calabashTeam) throws Exception {
        super(nMap, graphicsContext);
        this.calabashTeam = calabashTeam;
    }

    @Override
    public void draw() throws Exception {
//        //不做安全性检查，直接获取变量
//        Field calabashesField = calabashTeam.getClass().getDeclaredField("calabashes");
//        calabashesField.setAccessible(true);
//        for(Object calabash: (List)calabashesField.get(calabashTeam)){
//            Field pictureField = calabash.getClass().getSuperclass().getDeclaredField("picture");
//            pictureField.setAccessible(true);
//            drawPic((Picture)pictureField.get(calabash));
//        }


//        Method getCalabashesM = calabashTeam.getClass().getDeclaredMethod("getCalabashes");
//        getCalabashesM.setAccessible(true);                     //不做安全性检查，加快速度


        for(Picture picture: calabashTeam.getPictures()){
            drawPic(picture);
        }


//        for (Character calabash: (List<Character>)calabashTeam.getClass().getDeclaredMethod("getCalabashes").invoke(calabashTeam)){
//            drawPic(calabash.getPicture());
//        }
    }
}
