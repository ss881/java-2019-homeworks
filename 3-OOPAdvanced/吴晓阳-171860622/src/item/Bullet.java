package item;

/*
* 基本的子弹
* 可以与figure互动，降低其生命值
 */
public interface Bullet extends Item, Interactable {
    int getDamage();
}
