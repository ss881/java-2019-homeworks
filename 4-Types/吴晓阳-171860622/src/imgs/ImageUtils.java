package imgs;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {
    public static Image resizeImage(Image image, Dimension dimension){
        BufferedImage resImage = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = resImage.getGraphics();
        graphics.drawImage(image, 0, 0, dimension.width, dimension.height, null);
        graphics.dispose();
        return resImage;
    }

    public static Image rotateImage(final Image image, final int degree){
        int w = image.getWidth(null);
        int h = image.getHeight(null);
        int type = BufferedImage.TYPE_INT_RGB;
        BufferedImage img;
        Graphics2D graphics2d;
        img = new BufferedImage(w, h, type);
        graphics2d = img.createGraphics();
        graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);// 旋转，degree是整型，度数，比如垂直90度。
        graphics2d.drawImage(image, 0, 0, null);// 从bufferedimagecopy图片至img，0,0是img的坐标。
        graphics2d.dispose();
        return img;
    }

    /*
    * 画出生命条形式的矩形图形
     */
    public static void drawLifeRect(Graphics g, Rectangle range, int life, int maxLife, Color backColor, Color lifeColor){
        Color tmp = g.getColor();
        int lifeLength = range.width * life / maxLife;
        g.setColor(lifeColor);
        g.fillRect(range.x, range.y, lifeLength, range.height);
        g.setColor(backColor);
        g.fillRect(range.x + lifeLength, range.y, range.width - lifeLength, range.height);
        g.setColor(tmp);
    }
}
