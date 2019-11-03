package item;

/*
* 只有图像，没有支撑结构的item，在全局面板上绘画
* 绘图时是最高一层，因此不会与其它组件发生显示冲突
*/
public interface DrawableItem extends Item, Drawable {
}
