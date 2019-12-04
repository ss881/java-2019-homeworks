package com.swt.control;

import com.swt.model.basic.Point;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.scene.image.Image;

/**
 * 工具类
 */
public class FileUtils {
    /**
     * 读取文件返回坐标
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public static List<Point> getPointList(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        List<Point> list = new ArrayList<>();
        int x, y;
        while (scanner.hasNext()){
            x = scanner.nextInt();
            y = scanner.nextInt();
            list.add(new Point(x, y));
        }
        return  list;
    }

    /**
     * 根据路径读取图片
     * @param pathName
     * @return
     * @throws IOException
     */
    public static Image getImage(String pathName) throws IOException {
        FileInputStream fis = new FileInputStream(new File(pathName));
        Image image = new Image(fis);
        fis.close();
        return image;
    }
}
