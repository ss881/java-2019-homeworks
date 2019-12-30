package com.swt.model.controlled;

import com.swt.control.FileUtils;
import com.swt.model.basic.Picture;
import com.swt.model.basic.Point;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NMap {
    private static Lock bufferLock = new ReentrantLock();
    private static int[][]NMap = //原始Map
            {{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}};

    public static final int N = 10;    //10个格子
    private Picture picture1;
    private Picture picture2;

    public static void changeNMapLeft(Point point){
        bufferLock.lock();
        NMap[point.getPx() - 1][point.getPy()] = 1;
        NMap[point.getPx()][point.getPy()] = 0;
        bufferLock.unlock();
    }
    public static void changeNMapRight(Point point){
        bufferLock.lock();
        NMap[point.getPx() + 1][point.getPy()] = 1;
        NMap[point.getPx()][point.getPy()] = 0;
        bufferLock.unlock();
    }
    public static void changeNMapUp(Point point){
        bufferLock.lock();
        NMap[point.getPx()][point.getPy() - 1] = 1;
        NMap[point.getPx()][point.getPy()] = 0;
        bufferLock.unlock();
    }
    public static void changeNMapDown(Point point){
        bufferLock.lock();
        NMap[point.getPx()][point.getPy() + 1] = 1;
        NMap[point.getPx()][point.getPy()] = 0;
        bufferLock.unlock();
    }

    //检测要走的路径是否被占用
    public static boolean notOccupiedLeft(Point point) { return (NMap[point.getPx() - 1][point.getPy()] == 0); }
    public static boolean notOccupiedRight(Point point){ return (NMap[point.getPx() + 1][point.getPy()] == 0); }
    public static boolean notOccupiedUp(Point point){
        return (NMap[point.getPx()][point.getPy() - 1] == 0);
    }
    public static boolean notOccupiedDown(Point point){
        return (NMap[point.getPx()][point.getPy() + 1] == 0);
    }

    public NMap(String srcPath1, String srcPath2) throws IOException {
        picture1 = new Picture(new Point(0, 0), FileUtils.getImage(srcPath1));
        picture2 = new Picture(new Point(0, 0), FileUtils.getImage(srcPath2));
    }

    public Picture getPicture1(){
        return picture1;
    }
    public Picture getPicture2(){
        return picture2;
    }
}
