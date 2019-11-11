package item;

import figure.Figure;
import figure.feature.Lifable;
import item.Finished.FinishedFireball;

public class BasicBullet implements Bullet{
    private int damage;

    private Item emitter;

    public BasicBullet(){
        this.damage = 10;
        this.emitter = null;
    }

    public BasicBullet(int damage, Item emitter){
        this.damage = damage;
        this.emitter = emitter;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    public Item getEmitter() {
        return emitter;
    }

    public void setEmitter(Item emitter) {
        this.emitter = emitter;
    }

    /*
    * 默认的interact操作
    * 减少figure生命值
    * 效果发生后消失
     */
    @Override
    public boolean interact(Item item) {
        if(item instanceof FinishedFireball){
            this.setRemovable(true);
            return true;
        }
        // 遇到非发射者时释放效果，遇到发射者不影响
        if(item instanceof Figure && getEmitter() != item) {
            if (item instanceof Lifable) {
                ((Lifable) item).diminishLife(damage);
            }
            this.setRemovable(true);
            return false;
        }
        return true;
    }

    boolean removable = false;

    @Override
    public boolean isRemovable() {
        return removable;
    }

    @Override
    public void setRemovable(boolean flag) {
        this.removable = flag;
    }


}
