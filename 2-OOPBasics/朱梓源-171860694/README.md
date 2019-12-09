# 面向葫芦娃排序说明

首先，我们建立葫芦娃类（包含名字和颜色两个信息），每个葫芦娃基本上有如下功能：

* 说出自己的名字，颜色 (shoutName, shoutColor)
* 按照输入说出自己走去了哪里 (shoutPosMove)
* 和别的葫芦娃比较某个方面（名字，颜色）的大小 (isBiggerThan)

然后，维护一个队伍类，这个类有如下功能：

* 加入一个新的葫芦娃 (addHuluwa)
* 让葫芦娃报数 (report)
* 根据指定的方法进行冒泡或者二分排序 (sortBubble, sortBi)

在队伍类里，有如下私有的管理葫芦娃方法：

* 让葫芦娃出列 (kickout)
* 交换两个葫芦娃的位置 (swap)
  * 先让要交换的葫芦娃出列，再进入对方的队伍中
* 让葫芦娃直接走到某个位置 (move)
  * 先让要走的葫芦娃出列，然后中间的葫芦娃全部移动一个位置，最后出列的葫芦娃走到要去的位置
* 打乱葫芦娃的位置 (shuffle)
  * 随机交换两个葫芦娃的位置，交换100次。

关于二分排序，还有着一个专有的寻找插入位置的函数 (binarySeatch)，但其中并不会修改队伍中葫芦娃的信息。

# 演示

以二分排序为例，我们在主函数只需要写：

```java
cheque.shuffle();
cheque.report("color");
cheque.sortBi("color");
cheque.report("color");
System.out.println("Binarysort done!");
```

很简单！然后就会输出：

```
----Reporting!----
老二:橙色
老四:绿色
老五:青色
老七:紫色
老三:黄色
老六:蓝色
老大:红色
------------------
老四:1->stay
老五:2->stay
老七:3->stay
老三:4->outside
老七:3->4
老五:2->3
老四:1->2
老三:4->1
老六:5->outside
老七:4->5
老六:5->4
老大:6->outside
老七:5->6
老六:4->5
老五:3->4
老四:2->3
老三:1->2
老二:0->1
老大:6->0
----Reporting!----
老大:红色
老二:橙色
老三:黄色
老四:绿色
老五:青色
老六:蓝色
老七:紫色
------------------
Binarysort done!
```

我们来看看排序是如何实现的：

```java
//队列的内部数据类型和成员名是ArrayList<Huluwa> line

private int binarySearch(Huluwa guy, String key, int low, int high) {
    if (high <= low)
        return (guy.isBiggerThan(line.get(low), key)) ? (low + 1) : low;

    int mid = (low + high) / 2;

    //considering huluwa cannot duplicate theirselves, the following block is hidden.
    //if (guy == line.get(mid))
    //    return mid + 1;

    if (guy.isBiggerThan(line.get(mid), key)) return binarySearch(guy, key, mid + 1, high);
    else return binarySearch(guy, key, low, mid - 1);
}

private void move(int dest, int src) {
    if (dest == src) {
        line.get(src).shoutPosMove("stay", "" + src);
        return;
    }

    Huluwa outsider = kickout(src, true);
    if (dest > src) { //considering we're using Binart Sort, this block would not be used.
        for (int i = src + 1; i <= dest; i += 1) {
            Huluwa guy = line.get(i);
            guy.shoutPosMove(i - 1, i);
        }
    } else { // dest < src
        for (int i = src - 1; i >= dest; i -= 1) {
            Huluwa guy = line.get(i);
            guy.shoutPosMove(i + 1, i);
        }
    }

    outsider.shoutPosMove(dest, src);
    line.remove(src);
    line.add(dest, outsider);
}
```

