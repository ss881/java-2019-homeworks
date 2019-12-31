package cn.edu.nju.huluwa.util;

import javafx.scene.Node;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

@PropPath(getPropPath = "/props/creature.prop")
public class ImageLoader {

    private ImageLoader() {

    }

    public static Image getImage(String creatureName) {
        Image image = null;
        // use reflection to get annotation object
        PropPath propPath = ImageLoader.class.getAnnotation(PropPath.class);
        String path = propPath.getPropPath();

        Properties properties = new Properties();
        try(Reader reader = new InputStreamReader(ImageLoader.class.getResourceAsStream(path), "utf-8")) {
            properties.load(reader);
            String imagePath = properties.getProperty(creatureName);
            image = new Image(ImageLoader.class.getResourceAsStream(imagePath));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static Image getBackground() {
        return new Image(ImageLoader.class.getResourceAsStream("/images/background.png"));
    }
    public static Image getIcon() {
        return new Image(ImageLoader.class.getResourceAsStream("/images/icon.png"));
    }

    public static Image getLogo() {
        return new Image(ImageLoader.class.getResourceAsStream("/images/logo.jpg"));
    }
    public static Image getBadTeamWinImage() {
        return new Image(ImageLoader.class.getResourceAsStream("/images/snake_win.png"));
    }
    public static Image getGoodTeamWinImage() {
        return new Image(ImageLoader.class.getResourceAsStream("/images/huluwa_win.png"));
    }
}
