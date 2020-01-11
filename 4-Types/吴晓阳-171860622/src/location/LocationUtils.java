package location;

import item.DisplayableItem;

import java.awt.*;

public class LocationUtils {
    /*
    * Caution: 必须取上下界，否则会在结果为+-0.xx时变成0，造成动量为0
     */
    public static Dimension makeMomentum(Direction direction, int length){
        double width = (double)length * Math.cos(direction.radian);
        double height = (double)length * Math.sin(direction.radian);
        return new Dimension((int)width, (int)height);
    }

    /*
    * 根据初末位置生成动量
     */
    public static Dimension makeMomentum(Point src, Point dst){
        int dx = dst.x - src.x;
        int dy = dst.y - src.y;
        return new Dimension(dx, dy);
    }


    /*
     * 从 像素坐标 到 格坐标 的转化
     */
    public static Point pixelPosToGridPos(Point pixelPos, Dimension gridSize){
        return new Point(pixelPos.x / gridSize.width, pixelPos.y / gridSize.height);
    }

    /*
     * 从 格坐标 到 像素坐标 的转化
     * @return 对应格子的中间像素点
     */
    public static Point gridPosToPixelPosMid(Point gridPos, Dimension gridSize){
        return new Point(gridPos.x * gridSize.width + gridSize.width / 2,
                gridPos.y * gridSize.height + gridSize.height / 2);
    }

    /*
     * 从 格坐标 到 像素坐标 的转化
     * @return 对应格子的左上角像素点
     */
    public static Point gridPosToPixelPosTopLeft(Point gridPos, Dimension gridSize){
        return new Point(gridPos.x * gridSize.width,
                gridPos.y * gridSize.height);
    }

    /*
     * 从 格范围 到 像素范围 的转化
     */
    public static Dimension gridDimToPixelDim(Dimension gridDim, Dimension gridSize){
        return new Dimension(gridDim.width * gridSize.width,
                gridDim.height * gridSize.height);
    }

    /*
    * 从 像素范围 到 格范围 的转化
    * Caution： 格数取上界
     */
    public static Dimension pixelDimToGridDimUpperBound(Dimension pixelDim, Dimension gridSize){
        int row = (int)Math.ceil((double)pixelDim.width / (double)gridSize.width);
        int col = (int)Math.ceil((double)pixelDim.height / (double)gridSize.height);
        return new Dimension(row, col);
    }

    /*
     * 从 像素范围 到 格范围 的转化
     * Caution： 格数取下界
     */
    public static Dimension pixelDimToGridDimLowerBound(Dimension pixelDim, Dimension gridSize){
        int row = (int)Math.floor((double)pixelDim.width / (double)gridSize.width);
        int col = (int)Math.floor((double)pixelDim.height / (double)gridSize.height);
        return new Dimension(row, col);
    }

    /*
    * 获取图像位置的中心点
     */
    public static Point getMidPoint(Shape shape){
        Rectangle b = shape.getBounds();
        return new Point(b.x + b.width / 2, b.y + b.height / 2);
    }

    /*
    * 显示实际的屏幕像素尺寸
     */

    /*
    * 虚拟屏幕尺寸
    * 用于与实际屏幕位置相映射，便于位置的确定
    * 默认是（1600 * 900），即16：9的屏幕比例
     */
    public static final int VIRTUAL_SCREEN_WIDTH = 1600;

    public static final int VIRTUAL_SCREEN_HEIGHT = 900;

    /*
    * 将虚拟屏幕位置 转换为 实际屏幕像素位置
    * @param vPos : 虚拟像素点位置
    * @param screenSize : 实际显示屏幕的像素尺寸
     */
    public static Point virtualPosToPixelPos(Point vPos, Dimension screenSize){
        int pixelX = vPos.x * screenSize.width / VIRTUAL_SCREEN_WIDTH;
        int pixelY = vPos.y * screenSize.height / VIRTUAL_SCREEN_HEIGHT;
        return new Point(pixelX, pixelY);
    }

    /*
    * 将 实际屏幕像素位置 转换为 虚拟屏幕位置
    * @param pPos : 实际像素点位置
    * @param screenSize ： 实际显示屏幕的像素尺寸
     */
    public static Point pixelPosToVirtualPos(Point pPos, Dimension screenSize){
        int virtualX = pPos.x * VIRTUAL_SCREEN_WIDTH / screenSize.width;
        int virtualY = pPos.y * VIRTUAL_SCREEN_HEIGHT / screenSize.height;
        return new Point(virtualX, virtualY);
    }

    /*
    * 将 虚拟屏幕尺寸 转换为 实际屏幕像素尺寸
    * @ virtualDim : 虚拟尺寸
    * @param screenSize ： 实际显示屏幕的像素尺寸
     */
    public static Dimension virtualDimToPixelDim(Dimension virtualDim, Dimension screenSize){
        int pixelWidth = virtualDim.width * screenSize.width / VIRTUAL_SCREEN_WIDTH;
        int pixelHeight = virtualDim.height * screenSize.height / VIRTUAL_SCREEN_HEIGHT;
        return new Dimension(pixelWidth, pixelHeight);
    }

    /*
    * 将 实际屏幕像素尺寸 转换为 虚拟屏幕尺寸
    * @ pixelDim : 实际像素尺寸
    * @param screenSize ： 实际显示屏幕的像素尺寸
     */
    public static Dimension pixelDimToVirtualDim(Dimension pixelDim, Dimension screenSize){
        int pixelWidth = pixelDim.width * VIRTUAL_SCREEN_WIDTH / screenSize.width;
        int pixelHeight = pixelDim.height * VIRTUAL_SCREEN_HEIGHT / screenSize.height;
        return new Dimension(pixelWidth, pixelHeight);
    }

    /*
    * 在不同尺寸坐标下的 坐标转换
     */
    public static Point posTransfer(Point pos, Dimension fromWindow, Dimension toWindow){
        return new Point(pos.x * toWindow.width / fromWindow.width,
                pos.y * toWindow.width / fromWindow.width);
    }

    /*
     * 在不同尺寸坐标下的 坐标转换
     */
    public static Dimension dimTransfer(Dimension dim, Dimension fromWindow, Dimension toWindow){
        return new Dimension(dim.width * toWindow.width / fromWindow.width,
                dim.height * toWindow.height / fromWindow.height);
    }
    /*
    * 像素尺寸的模式
     */
    public static final int VIRTUAL_PIXEL_MODE = 1;

    public static final int REAL_PIXEL_MODE = 2;

    /*
    * 实际屏幕像素尺寸的值，作为缓存，提高提取效率
     */
    private static final Dimension SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();

    /*
    * 获取 屏幕像素尺寸的相对尺寸
     */
    public static Dimension getRelativeDim(Point widthFraction, Point heightFraction){
        return new Dimension(SCREEN_DIMENSION.width * widthFraction.x / widthFraction.y,
                SCREEN_DIMENSION.height * heightFraction.x / heightFraction.y);
    }

    public static Dimension getRelativeDim(Point widthFraction, Point heightFraction, Dimension screenDim){
        return new Dimension(screenDim.width * widthFraction.x / widthFraction.y,
                screenDim.height * heightFraction.x / heightFraction.y);
    }

    public static Point getRelativePos(Point widthFraction, Point heightFraction){
        return new Point(SCREEN_DIMENSION.width * widthFraction.x / widthFraction.y,
                SCREEN_DIMENSION.height * heightFraction.x / heightFraction.y);
    }

    public static Point getRelativePos(Point widthFraction, Point heightFraction, Dimension screenDim){
        return new Point(screenDim.width * widthFraction.x / widthFraction.y,
                screenDim.height * heightFraction.x / heightFraction.y);
    }

    /*
    * 获取 格移动所需要的像素动量
     */
    public static Dimension getGridMovePixelMomentum(Point srcGridPos, Point dstGridPos, Dimension gridSize){
        Point srcPixelPos = gridPosToPixelPosTopLeft(srcGridPos, gridSize);
        Point dstPixelPos = gridPosToPixelPosTopLeft(dstGridPos, gridSize);
        return new Dimension(dstPixelPos.x - srcPixelPos.x, dstPixelPos.y - srcPixelPos.y);
    }
}
