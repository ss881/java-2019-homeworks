package itemEventProcessor;

import figure.Figure;
import item.DisplayableItem;
import item.DrawableItem;
import item.Finished.FinishedFireball;
import item.Item;
import item.Movable;
import javafx.util.Pair;
import location.LocationUtils;
import newgui.Console;

import java.awt.*;
import java.util.Collection;

/*
* 储存了大量有用的事物处理器
* 此处只保存具有通用性的处理器，对于一些应用于特定对象的处理器，则由该对象的类自行管理
 */
public class ItemEventProcessors {
    /*
     * 人物位置检查处理器
     * 检查是否即将超出范围，若是，则设置动量为0
     */
    public static class FigureBoundPreventProcessor implements ItemEventProcessor{
        private final Figure me;

        public FigureBoundPreventProcessor(Figure me){
            if(me instanceof Movable == false){
                throw new IllegalArgumentException();
            }
            this.me = me;
        }

        @Override
        public void process(DisplayableItem item, Rectangle itemRect, Collection<Pair<DisplayableItem, Point>> addSet, Console console) {
            if(item == me){
                // 获取并添加初始动量
                Dimension momentum = ((Movable) item).getMomentum();
                Dimension primalMomentum = ((Movable) item).getPrimalMomentum();
                momentum.width += primalMomentum.width;
                momentum.height += primalMomentum.height;
                if(momentum.width == 0 && momentum.height == 0){ // 没有移动
                    return;
                }
                // 移动，并处理剩余动量
                // 实际移动的格子范围,取上界，为了增加预测范围
                Dimension movedGridDim = LocationUtils.pixelDimToGridDimUpperBound(momentum, console.getGridSize());
                if(movedGridDim.width == 0 && movedGridDim.height == 0){ // 没有移动
                    return;
                }

                // 现在的动量是全屏范围，需要将其调整到目前的frame尺寸下
                // Caution: 必须扩大范围转换，并且保证至少有1个像素移动，否则会造成小数点省略问题，检测不到移动
                movedGridDim.width *= 100; movedGridDim.height *= 100;
                movedGridDim = LocationUtils.dimTransfer(movedGridDim, Toolkit.getDefaultToolkit().getScreenSize(), console.getScreenSize());
                if(movedGridDim.width > 0) movedGridDim.width += 99;
                else if(movedGridDim.width < 0) movedGridDim.width -= 99;
                if(movedGridDim.height > 0) movedGridDim.height += 99;
                else if(movedGridDim.height < 0) movedGridDim.height -= 99;
                movedGridDim.width /= 100;
                movedGridDim.height /= 100;

                // 获取移动结果
                int dstX = itemRect.x + movedGridDim.width;
                int dstY = itemRect.y + movedGridDim.height;

                if(dstX <= 0 || dstX + itemRect.width >= console.getGridRowNum() || dstY <= 0 || dstY + itemRect.height >= console.getGridColNum()){
                    Dimension d = new Dimension(0, 0);
                    ((Movable) item).setMomentum(d);
                    ((Movable) item).setPrimalMomentum(d);
                }
            }
        }
    }

}
