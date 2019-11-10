# 作业2-171870660

## 类结构简介

* Homework2.java: 主程序，含main方法。
* CalabashBrothers.java: “葫芦兄弟”类。包括七个“葫芦娃”对象的`List`，并在此实现了排序，报数等操作。
* Calabash.java: “葫芦娃”类。封装了单个葫芦娃的操作，包括其排行，颜色等信息。
* Position.java: 位置类。将位置封装成一个类，但本次作业中它实际的数据只包含一个整型数，预留扩展空间。

## 面向对象方法

### `Position`类
考虑到“位置”是一个实际的概念，用本类来进行模拟。在本次作业中，它实际的数据只包含一个整型数。

在本类中实现`moveForward`和`moveBackward`两个接口来完成向前一步、向后一步的操作。

### `Calabash`类
将“葫芦娃”的排行、颜色封装为类。实现`getName`，`getColorName`接口来将排行、颜色转换为字符串，实现“报数”和“报告颜色”的操作。

实现了`orderBefore`和`colorBefore`两个接口，类似于重载`<`操作符，分别是按照排行和颜色的比较。

> 注：考虑到“排行”和“颜色”是两个不同的属性，将它们分别列为两个属性`order`和`color`，以提供更多的扩展可能性。但实际操作中，由于类中的`color`属性也是按照序号来存的，实际并没有区别，故只提供了一个构造函数`Calabash(int order)`。

维护了一个`Position`成员对象，用于记载改葫芦娃的位置。这个位置始终与在`CalabashBrothers`类中，`this`对象在`List`中的位置一一对应。实现`moveBackward`和`moveForward`两个接口用于处理“前进一位”和“后退一位”两个操作。我们假定，**葫芦娃每次能且只能在前后相邻的两个位置间交换**。

### `CalabashBrothers`类

将“葫芦兄弟”（七兄弟的整体）对外视作一个对象，七兄弟分别作为七个对象为其成员（存在`List`中）。实现`standRandomly()`方法来模拟“随意站队”的操作，实现`sortByOrder` `sortByColor`方法来进行按排行、颜色排序的操作，实现`tellOrders` `tellColors`方法来模拟按序列、颜色报数（报告）的操作。

在排序操作中，葫芦娃位置的移动都通过`moveForward(int cur)`方法来实现。此方法将`cur`指向的葫芦娃在队列中向前移动一位（相应的，原来`cur-1`位置的葫芦娃向后移动一位而到达`cur`位置），然后调用`Calabash`类的`moveForward`和`moveBackward`方法来记录更改。