package item;

import javax.swing.*;

/*
 * 装载了组件,当item被添加进console时会自动填入对应组件，同时在移动时会自动移动组件
 * 一般用于绘图、图像无法完成的功能，例如显示gif动图、添加响应器等
 */
public interface Componented extends Displayable{
    JComponent getComponent();
}
