package figure.feature;

import item.Item;

/*
* 可以发射item
 */
public interface Emittable {
    boolean isReadyToEmit();

    /*
    * 添加item生成器
     */

    Item emit();
}
