package imgs;

import location.LocationUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageRepository {

    private static Map<String, Image> imgs = new HashMap<>();

    private static boolean inited = false;

    /*
    * 无异常抛出的获取image方法
    * 在imgs中找不到时，会先从本地查找图像，若找到了则添加进imgs，并返回
    * 若找不到则返回null
     */
    private static Image getImageWithoutCheck(String key){
        Image image = null;

        image = imgs.get(key);
        if(image != null) {
            return image;
        }

        image = addImage("src/" + key, ".jpg");

        return image;
    }

    private static boolean checkInit(){
        if(inited == false){
            imgs.put("Dawa", addImage("src/Dawa", ".jpg"));
            imgs.put("Erwa", addImage("src/Erwa", ".jpg"));
            imgs.put("Sanwa", addImage("src/Sanwa",".jpg"));
            imgs.put("Siwa", addImage("src/Siwa",".jpg"));
            imgs.put("Wuwa", addImage("src/Wuwa",".jpg"));
            imgs.put("Liuwa", addImage("src/Liuwa",".jpg"));
            imgs.put("Qiwa", addImage("src/Qiwa",".jpg"));
            imgs.put("Bullet", addImage("src/Bullet", ".jpg"));
            imgs.put("Giftest", addImage("src/Giftest",".gif"));
            imgs.put("Scorpion", addImage("src/Scorpion", ".jpg"));
            imgs.put("Snake", addImage("src/Snake", ".jpg"));
            imgs.put("Toad", addImage("src/Toad", ".jpg"));
            inited = true;
            return false;
        }
        return true;
    }

    private static Image createImage(Image image, int width, int height){
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = newImage.getGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return newImage;
    }

    public static Image addImage(String key, String postfix){
        Image originImage = null;
        try{
            originImage = ImageIO.read(new File(key + postfix));
        } catch (IOException e){}
        if(originImage != null){
            imgs.put(key, originImage);
        }

        return originImage;
    }

    /*
    * 根据实际像素尺寸添加图像
     */
    public static Image addImage(String key, String postfix, Dimension dimension){
        Image originImage = null;
        Image image = null;
        try{
            originImage = ImageIO.read(new File(key + postfix));
        } catch (IOException e){}
        if(originImage != null){
            image = ImageUtils.resizeImage(image, dimension);
            imgs.put(key, image);
        }

        return image;
    }

    public static Image getImage(String key){
        checkInit();

        Image oldImage = getImageWithoutCheck(key);
        if(oldImage == null) return null;

        Image image = createImage(oldImage, oldImage.getWidth(null), oldImage.getHeight(null));
        return image;
    }

    /*
    * 根据实际像素尺寸获取图像
     */
    public static Image getImage(String key, Dimension dimension){
        checkInit();

        Image oldImage = getImageWithoutCheck(key);
        if(oldImage == null) return null;
        
        Image image = createImage(oldImage, dimension.width, dimension.height);
        return image;
    }

    /*
    * 根据 虚拟像素尺寸或实际像素尺寸 获取图像
    * 这里的实际像素尺寸根据屏幕显示尺寸得到，本机为1920*1080
    * @param SCREEN_PIXEL_MODE : 像素模式， 存在于 类LocationUtils中
     */
    public static Image getImage(String key, Dimension dimension, int SCREEN_PIXEL_MODE) {
        Image image;
        switch (SCREEN_PIXEL_MODE) {
            case LocationUtils.REAL_PIXEL_MODE:
                image = getImage(key, dimension);
                break;
            case LocationUtils.VIRTUAL_PIXEL_MODE:
                image = getImage(key, LocationUtils.virtualDimToPixelDim(dimension, Toolkit.getDefaultToolkit().getScreenSize()));
                break;
            default:
                throw new UnsupportedOperationException("Mode error: " + SCREEN_PIXEL_MODE);
        }
        return image;
    }
}
