package com.swt.model.move;

import com.swt.model.basic.Picture;
import com.swt.model.basic.Point;

public class MoveServer {
    public MoveServer(){}

    public void move(MoveInterface moveInterface, Picture picture, Point point){
        moveInterface.move(picture, point);
    }
}
