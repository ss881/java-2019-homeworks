package com.swt.model.move;

import com.swt.model.basic.Picture;
import com.swt.model.basic.Point;
import com.swt.model.controlled.NMap;

public class MoveLeft implements MoveInterface {
    @Override
    public void move(Picture picture, Point point) {
        NMap.changeNMapLeft(point);
        picture.getPoint().setPx(point.getPx() - 1);
        point.setPx(point.getPx() - 1);
    }
}
