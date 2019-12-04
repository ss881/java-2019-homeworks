package item;

import java.awt.*;

/*
* 可绘制的item
* 每次frame进行paint时，都会调用该item的draw方法进行绘制
* 若要绘制图像或gif等，或是需要装载组件，建议使用Imagable接口或Componented接口
 */
public interface Drawable extends Displayable{
    void draw(Graphics g);
}
