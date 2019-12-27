package com.swt.model.move;

import com.swt.model.basic.Picture;
import com.swt.model.basic.Point;
import com.swt.model.controlled.NMap;

public class MoveUp implements MoveInterface {
    @Override
    public void move(Picture picture, Point point) {
        NMap.changeNMapUp(point);
        picture.getPoint().setPy(point.getPy() - 1);
        point.setPy(point.getPy() - 1);
    }
}
