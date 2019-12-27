package com.swt.model.basic;

import javafx.scene.image.Image;

public class Picture {
    private Image image;  //图片
    private Point point;  //坐标
    private int width;        //每个格子的长度
    private int height;       //每个格子的宽度
    public Picture(Point point, Image image){
        this.point = new Point(point.getPx(), point.getPy());
        this.image = image;
        width = (int)image.getWidth();
        height = (int)image.getHeight();
    }

    public Image getImage() {
        return image;
    }

    public Point getPoint() {
        return point;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPoint(Point point) {
        this.point.setPx(point.getPx());
        this.point.setPy(point.getPy());
    }

//    /**
//     * 画出图片
//     */
//    public void draw(GraphicsContext graphicsContext){
//        //改变格子
////        NMap.NMap[point.getPx()][point.getPy()] = 1;
////        NMap.occupiedList.add(new Point(point.getPx(), point.getPy()));
//        graphicsContext.drawImage(image,
//                0, 0, width, height,
//                point.getPx() * width, point.getPy() * height, width, height);
//    }
}
