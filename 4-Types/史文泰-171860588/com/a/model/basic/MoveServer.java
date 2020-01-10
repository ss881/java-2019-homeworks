package com.a.model.basic;

import com.all.model.Picture;
import com.all.model.Point;
import com.all.move.MoveInterface;

public class MoveServer {
    public void move(MoveInterface moveInterface, Picture picture, Point point){
        moveInterface.move(picture, point);
    }
}
