package item;

import java.awt.*;

/*
* 具有image
* 在frame中会自动对画板绘制image，不用自己画图
 */
public interface Imagable extends Displayable{
    Image getImage();

    void setImage(Image image);
}
