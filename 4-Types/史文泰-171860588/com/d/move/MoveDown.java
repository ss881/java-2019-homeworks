package com.d.move;

import com.all.model.Picture;
import com.all.model.Point;
import com.all.model.NMap;
import com.all.move.MoveInterface;

public class MoveDown implements MoveInterface {
    @Override
    public void move(Picture picture, Point point) {
        NMap.changeNMapDown(point);
        picture.getPoint().setPy(point.getPy() + 1);
        point.setPy(point.getPy() + 1);
    }
}
