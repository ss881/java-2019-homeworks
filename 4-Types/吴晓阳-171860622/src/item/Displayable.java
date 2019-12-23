package item;

import java.awt.*;

/*
* item的可显示接口，表示item是可以显示的，提供给frame来进行分析设计
* Caution：由Drawable、Componented、Imagable这三个可以提供实际显示的接口来实现这一接口，不要自己implement本接口
* frame在处理item移动事件时，当遇到Labeled接口时会自动移动对应的Label
* 外部类不要继承此接口
 */
interface Displayable {
    /*
    * 获取显示区域的需求尺寸
     */
    Dimension getDisplaySize();

    /*
    * 更改显示尺寸，根据不同的接口实现有不同的更改方式：
    * Imagable： 更改image的尺寸
    * Component： 更改component的尺寸
     */
    void setDisplaySize(Dimension size);
}
