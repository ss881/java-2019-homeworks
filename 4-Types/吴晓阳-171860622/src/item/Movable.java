package item;

import java.awt.*;

/*
* 可移动的item
 */
public interface Movable {
    Dimension getMomentum();

    /*
     * 重置动量
     * 若当前 动量 不足以使得item出现格子移动，则需要进行动量积蓄
     */
    void setMomentum(Dimension momentum);

    /*
    * 获取初始动量
     */
    Dimension getPrimalMomentum();

    /*
    * 重设初始动量
     */
    void setPrimalMomentum(Dimension momentum);
}
