package item.Finished;

import com.sun.imageio.plugins.common.ImageUtil;
import figure.feature.Lifable;
import imgs.ImageRepository;
import imgs.ImageUtils;
import item.*;
import location.Direction;
import location.LocationUtils;
import myUtils.MyUtils;

import java.awt.*;

/*
* 完整版的 子弹， 具有伤害，速度， 图像的属性
* Caution: 未考虑触碰发射者的情况，若由发射者生成，必须在子类中重载interact方法
 */
public class FinishedBullet extends  FinishedItem implements SinglePointed{
    {
        this.setImage(ImageRepository.getImage("Bullet", new Dimension(MyUtils.getPace(2), MyUtils.getPace(2)), LocationUtils.VIRTUAL_PIXEL_MODE));
    }

    public FinishedBullet() {
        this.damage = DEFAULT_DAMAGE;
        Direction direction = new Direction(MyUtils.random.nextInt(360)); // 随机方向
        int speed = this.DEFAULT_SPEED; // 虚拟像素速度
        // 默认将虚拟像素动量转化成屏幕大小为标准的实际像素动量
        Dimension virtualMomentum = LocationUtils.makeMomentum(direction, speed);
        Dimension realMomentum = LocationUtils.virtualDimToPixelDim(virtualMomentum, Toolkit.getDefaultToolkit().getScreenSize());
        this.setPrimalMomentum(realMomentum);
        this.setImage(ImageUtils.rotateImage(this.getImage(), (int)direction.degree));
    }

    /*
    * @param speed : 虚拟像素速度
     */
    public FinishedBullet(int damage, Direction direction, int speed) {
        this.damage = damage;
        direction = (direction == null) ? new Direction(MyUtils.random.nextInt(360)): direction;
        // 默认将虚拟像素动量转化成屏幕大小为标准的实际像素动量
        Dimension virtualMomentum = LocationUtils.makeMomentum(direction, speed);
        Dimension realMomentum = LocationUtils.virtualDimToPixelDim(virtualMomentum, Toolkit.getDefaultToolkit().getScreenSize());
        this.setPrimalMomentum(realMomentum);
        this.setImage(ImageUtils.rotateImage(this.getImage(), (int)direction.degree));
    }

    public static final int DEFAULT_SPEED = MyUtils.DEFAULT_STD_PACE;

    public static final int DEFAULT_DAMAGE = 10;

    protected int damage = DEFAULT_DAMAGE;

    /*
    * 默认交互： 对任何有生命值的item造成伤害，然后自身消失
     */

    /*
     * 检查item的类型是否可以进行交互，通常被继承类所覆盖以定位不同的交互对象
     */
    protected boolean interactTypeCheck(Item item){
        return item != null && item instanceof Lifable;
    }

    @Override
    public boolean interact(Item item) {
        if(interactTypeCheck(item)){
            ((Lifable) item).diminishLife(damage);
            this.setRemovable(true);
            return true;
        }
        return false;
    }
}
