package com.d.move;

import com.all.model.Picture;
import com.all.model.Point;
import com.all.model.NMap;
import com.all.move.MoveInterface;

public class MoveRight implements MoveInterface {
    @Override
    public void move(Picture picture, Point point) {
        NMap.changeNMapRight(point);
        picture.getPoint().setPx(point.getPx() + 1);
        point.setPx(point.getPx() + 1);
    }
}