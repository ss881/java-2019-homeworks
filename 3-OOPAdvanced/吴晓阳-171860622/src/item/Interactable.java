package item;

import figure.Figure;

/*
* 可以与figure进行互动的item
 */
public interface Interactable {
    /*
    * 与figure进行互动
    * /return 如果成功互动则返回true， 否则返回false
     */
    boolean interact(Item item);
}
