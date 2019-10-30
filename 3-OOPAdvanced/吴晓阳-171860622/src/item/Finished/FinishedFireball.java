package item.Finished;

import figure.feature.Lifable;
import imgs.ImageRepository;
import item.*;
import itemEventProcessor.ItemEventProcessor;
import javafx.util.Pair;
import location.Direction;
import location.LocationUtils;
import myUtils.MyUtils;
import newgui.Console;

import java.awt.*;
import java.util.Collection;

/*
* 火球是范围伤害，具有总伤害值，对接触到的item造成伤害
* Caution: 未考虑触碰发射者的情况，若由发射者生成，必须在子类中重载interact方法
 */
public class FinishedFireball extends FinishedItem implements SinglePointed{
    {
        this.setImage(ImageRepository.getImage("Fireball", new Dimension(MyUtils.getPace(10), MyUtils.getPace(10)), LocationUtils.VIRTUAL_PIXEL_MODE));
    }

    public FinishedFireball(){
        this.damage = this.DEFAULT_DAMAGE;
        Direction direction = new Direction(MyUtils.random.nextInt(360));
        int speed = DEFAULT_SPEED;
        // 默认将虚拟像素动量转化成屏幕大小为标准的实际像素动量
        Dimension virtualMomentum = LocationUtils.makeMomentum(direction, speed);
        Dimension realMomentum = LocationUtils.virtualDimToPixelDim(virtualMomentum, Toolkit.getDefaultToolkit().getScreenSize());
        this.setPrimalMomentum(realMomentum);
    }

    public FinishedFireball(int damage, Direction direction, int speed){
        this.damage = damage;
        direction = (direction == null) ? new Direction(MyUtils.random.nextInt(360)): direction;
        // 默认将虚拟像素动量转化成屏幕大小为标准的实际像素动量
        Dimension virtualMomentum = LocationUtils.makeMomentum(direction, speed);
        Dimension realMomentum = LocationUtils.virtualDimToPixelDim(virtualMomentum, Toolkit.getDefaultToolkit().getScreenSize());
        this.setPrimalMomentum(realMomentum);
    }

    /*
    * 默认的移动速度
     */
    public static final int DEFAULT_SPEED = MyUtils.DEFAULT_STD_PACE / 2;

    /*
    * 默认的总伤害数值
     */
    public static final int DEFAULT_DAMAGE = 50;

    /*
    * 总的伤害数值
     */
    protected int damage;

    /*
    * 已经造成的伤害数值
    * 当总值超过伤害上限时，火球消失
     */
    protected int damageCaused = 0;

    /*
    * 总的可生效次数
     */
    private static final int effectiveNum = 5;

    /*
     * 检查item的类型是否可以进行交互，通常被继承类所覆盖以定位不同的交互对象
     */
    protected boolean interactTypeCheck(Item item){
        return item != null && item instanceof Lifable;
    }

    @Override
    public boolean interact(Item item) {
        if(item instanceof FinishedBullet){
            item.setRemovable(true);
            return true;
        }
        if(interactTypeCheck(item)){
            int thisDamage = damage / effectiveNum;
            ((Lifable) item).diminishLife(thisDamage);
            damageCaused += thisDamage;
            if(damageCaused >= damage){
                this.setRemovable(true);
            }
            return true;
        }
        return false;
    }

    /*
     * 火球的遭遇检测处理器
     * 与原本只会检测单独的中间点不同
     * 会检测其整个图像中的一些节点的情况，与这些选择点中的item进行交互
     */
    public static final ItemEventProcessor FIREBALL_MEET_CHECK_PROCESSOR = new ItemEventProcessor() {
        Dimension dim;
        Dimension pace = new Dimension();
        Point pos = new Point();
        Point itemPos = new Point();

        @Override
        public void process(DisplayableItem item, Rectangle itemRect, Collection<Pair<DisplayableItem, Point>> addSet, Console console) {
            if (item instanceof FinishedFireball) {
                itemPos.x = itemRect.x;
                itemPos.y = itemRect.y;
                dim = LocationUtils.pixelDimToGridDimLowerBound(item.getDisplaySize(), console.getGridSize());
                pace.width = dim.width / 10;
                pace.height = dim.height / 10;

                for (int i = -4; i <= 4; i++) {
                    pos.x = itemPos.x + i * pace.width;
                    pos.y = itemPos.y + i * pace.height;
                    Collection c = console.getGridItems(pos.x, pos.y);
                    if (c != null) {
                        c.forEach((interacter) -> ((FinishedFireball) item).interact((Item) interacter));
                    }
                    pos.y = itemPos.y - i * pace.height;
                    c = console.getGridItems(pos.x, pos.y);
                    if (c != null) {
                        c.forEach((interacter) -> ((FinishedFireball) item).interact((Item) interacter));
                    }

                    pos.y = itemPos.y;
                    c = console.getGridItems(pos.x, pos.y);
                    if (c != null) {
                        c.forEach((interacter) -> ((FinishedFireball) item).interact((Item) interacter));
                    }
                }
            }
        }
    };

}
