package item;

/*
* 最基础的物件，存在与显示的交互
 */
public interface Item {
    /*
    * 判断是否可以删除了，若是则会从显示器上删除掉
     */
    boolean isRemovable();

    /*
    * 设置可删除标志
     */
    void setRemovable(boolean flag);
}
