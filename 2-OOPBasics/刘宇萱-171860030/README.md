葫芦娃站队
===
类的设计
---
(1)CalabashBrother类：

存储葫芦娃的属性，完成葫芦娃可以做的动作。

a)属性：包括葫芦娃的颜色、排行、位置、名字等。

    private int position;
    private int rank;
    private String color;
    private String name;
    
b)方法：获得葫芦娃的某项属性；设置葫芦娃的位置；葫芦娃移动到另一位置。

    int getPosition();
    int getRank();
    String getColor();
    String getName();
    void setPosition(int p);
    void moveTo(int dst);

(2)Sort类：

指挥葫芦娃站队。

方法：生成随机序列；冒泡排序；二分排序。

    void randomSeq(CalabashBrother calas[]);
    void bubbleSort(CalabashBrother calas[]);
    void binSort(CalabashBrother calas[]);

(3)Output类：

提供输出功能，如输出当前葫芦娃的序列（报名字/颜色）、葫芦娃交换位置的情况等。

    void outputContent(String content);
    void outputMove(CalabashBrother cala, int src, int dst);
    void outputAllColors(CalabashBrother calas[]);
    void outputAllNames(CalabashBrother calas[]);

(4)SortCalabashBrothers类：

包含main函数，完成程序的框架。
