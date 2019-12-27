package com.swt.model.advance;

import com.swt.model.basic.Character;
import com.swt.model.basic.Picture;
import com.swt.model.basic.Point;
import com.swt.model.controlled.EnemyTeam;
import com.swt.control.FileUtils;
import javafx.scene.image.Image;

import java.io.IOException;

public class Snake extends Character {
    private Picture orderPicture;
    public Snake(int px, int py, Image image, Picture orderPicture){
        super(px, py, image);
        this.orderPicture = orderPicture;
    }

    /**
     * 命令队伍改变阵型
     * @param enemyTeam
     * @param path
     * @param orderPath
     * @throws IOException
     */
    public void Order(EnemyTeam enemyTeam, String path, String orderPath) throws IOException {
        orderPicture = new Picture(new Point(7, 0), FileUtils.getImage(orderPath));
        enemyTeam.ChangeFormation(FileUtils.getPointList(path));
    }

    public Picture getOrderPicture(){
        return orderPicture;
    }

}
