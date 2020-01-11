package newgui;

import item.DisplayableItem;
import item.DrawableItem;
import item.Item;
import itemEventProcessor.ItemEventProcessor;

import java.awt.*;
import java.util.Collection;

/*
* 负责装填、显示、处理item的平台
 */
public interface Console {
    int getGridRowNum();

    int getGridColNum();

    Dimension getGridSize();

    /*
     * 获取某个格子中的item集
     * 不可被外界改变
     */
    Collection<DisplayableItem> getGridItems(int xPos, int yPos);

    /*
    * 获取item在地图中的对应位置
    * @return item的 格位置
    * 通过itemMap查找
    * 找不到时返回null
     */
    Rectangle getItemPos(Item item);

    /*
    * 获取 显示屏幕尺寸
     */
    Dimension getScreenSize();

    /*
     * 添加item
     * caution: 坐标使用的是 格坐标， 不是像素坐标
     * @param rect : (x格坐标， y格坐标， width跨越横格数， height跨越纵格数）
     * 在绘画时，参照点是输入的 格坐标对应像素坐标
     * 将item添加到每个它属于的格子中去
     * item的大小由 其图像的尺寸换算成的格尺寸 决定
     * 调用addItem(item, xPos, yPos, width, height)
     */
    void addItem(DisplayableItem item, int xItemPos, int yItemPos);

    /*
     * 添加item
     * caution: 坐标使用的是 格坐标， 不是像素坐标
     * @param rect : (x格坐标， y格坐标， width跨越横格数， height跨越纵格数）
     * 在绘画时，参照点是输入的 格坐标对应像素坐标
     * 将item添加到每个它属于的格子中去
     */
    void addItem(DisplayableItem item, int xItemPos, int yItemPos, int itemWidth, int itemHeight);

    /*
    * 添加事件处理器
    * @return 处理器的存储index
     */
    int addItemEventProcessor(ItemEventProcessor processor);

    /*
    * 删除事件处理器
     */
    void removeItemEventProcessor(int index);

    /*
    * 开始显示
     */
    void display();

    void clear();
}
