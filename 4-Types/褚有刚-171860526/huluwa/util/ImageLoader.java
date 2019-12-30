package huluwa.util;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Properties;

@PropPath(getPropPath = "./../creature.prop")
public class ImageLoader {
    private ImageLoader() {

    }

    public static Image getImage(String creatureName) {
        Image image = null;
        // use reflection to get annotation object
        PropPath propPath = ImageLoader.class.getAnnotation(PropPath.class);
        String path = propPath.getPropPath();
//        System.out.println("path=" + path);

        Properties properties = new Properties();
        try(Reader reader = new InputStreamReader(new FileInputStream(path), "utf-8")) {
            properties.load(reader);
            String imagePath = properties.getProperty(creatureName);
//            System.out.println(imagePath);
            image = new ImageIcon(imagePath).getImage();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
