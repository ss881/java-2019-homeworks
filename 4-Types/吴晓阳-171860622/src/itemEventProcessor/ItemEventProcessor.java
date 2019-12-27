package itemEventProcessor;

import item.DisplayableItem;
import item.DrawableItem;
import javafx.util.Pair;
import newgui.Console;

import java.awt.*;
import java.util.Collection;

/*
* 专用于item事件的处理器, 负责在每次图像更新时处理事件
* 推荐对于每一个item属于的功能接口设立单独的processor
* 需要将该processor加入对应的控制台后才能起作用
 */
public interface ItemEventProcessor {
    void process(DisplayableItem item, Rectangle itemRect, Collection<Pair<DisplayableItem, Point>> addSet, Console console);
}
