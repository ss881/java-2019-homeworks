package item.Finished;

import figure.Figure;
import figure.feature.Lifable;
import imgs.ImageRepository;
import imgs.ImageUtils;
import item.*;
import location.Direction;
import location.LocationUtils;
import myUtils.MyUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/*
* 基础的可装载，可移动, 可交互（默认产生伤害）的item
* 可自动调整image，speed，damage，direction等参数，或重载其方法 来生成新的item
* 通常会重载interaction方法或interactionTypeCheck来实现特定item的交互功能
* 实现的功能：图像显示
* 待填充的功能：交互、移动（需要自己初始化）
 */
public class FinishedItem implements DisplayableItem, Movable, Imagable, Interactable {
    public FinishedItem(){
    }
    /*
    * 图像模块
     */
    private Image image = ImageRepository.getImage("Item", new Dimension(5, 5), LocationUtils.VIRTUAL_PIXEL_MODE);

    private Dimension imageDim = new Dimension(image.getWidth(null), image.getHeight(null));

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
        imageDim.width = image.getWidth(null);
        imageDim.height = image.getHeight(null);
    }

    @Override
    public Dimension getDisplaySize() {
        return new Dimension(imageDim);
    }

    @Override
    public void setDisplaySize(Dimension size) {
        image = ImageUtils.resizeImage(image, size);
    }

    /*
    * 交互模块，未实现
     */

    @Override
    public boolean interact(Item item) {
        throw new UnsupportedOperationException();
    }

    /*
    * 删除标记模块
     */
    private boolean removal = false;
    @Override
    public boolean isRemovable() {
        return removal;
    }

    @Override
    public void setRemovable(boolean flag) {
        this.removal = flag;
    }

    /*
    * 移动模块
     */
    private Dimension primalMomentum = new Dimension(0, 0);

    private Dimension momentum = new Dimension(0, 0);

    @Override
    public Dimension getMomentum() {
        return new Dimension(momentum);
    }

    @Override
    public void setMomentum(Dimension momentum) {
        this.momentum.width = momentum.width;
        this.momentum.height = momentum.height;
    }

    @Override
    public Dimension getPrimalMomentum() {
        return new Dimension(primalMomentum);
    }

    @Override
    public void setPrimalMomentum(Dimension momentum) {
        this.primalMomentum.width = momentum.width;
        this.primalMomentum.height = momentum.height;
    }
}
