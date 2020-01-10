package com.e.view;

import com.all.draw.DrawInterface;

public class DrawServer {
    public DrawServer(){}

    /**
     * 画图，遵循DIP原则
     * @param drawInterface
     * @throws Exception
     */
    public void draw(DrawInterface drawInterface) throws Exception{
//        ((DrawBase)drawBase).draw();
        drawInterface.draw();
    }
}
