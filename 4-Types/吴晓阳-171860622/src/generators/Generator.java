package generators;

import creator.Creator;

/*
* 生成器
* 每隔一段时间生成物品
* 需要传递创造器, 且创造器必须是无参数的
 */
public interface Generator<T> {
    Creator<T> getCreator();

    void setCreator(Creator<T> creator);

    void startGenerating();

    void stopGenerating();

    boolean isReady();

    T fetch();
}
