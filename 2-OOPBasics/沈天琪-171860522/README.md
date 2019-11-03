要解决这个问题，我们首先要分析题目要求我们完成哪些任务 注意到一共有如下任务
1. 生成七兄弟
2. 随机化排列
3. 两个排序任务
4. 报告交换的位置

分析一下即可了解到这些操作都是针对一个由葫芦娃构成的数组的，那么显然我们的对象内部就要包含这样的一个葫芦娃数组

那么葫芦娃需要哪些内容呢？名字及其对应颜色自不必说 为了方便排序，一个辅助代号显然也是必要的，实际上，对于葫芦娃类，我的设计中它对应的功能更多的是c++中的struct所对应的功能

这样现在我们的设计就很明晰了，在主函数或者说main方法中 我们只需实例化一个兄弟类，对其初始化之后就可以不断地对他发消息以解决作业需求了。即如下所示:

```java
public static void main(String[] args) {
        //System.out.println("123");
        String[] namesText = new String[]{"老大", "老二", "老三", "老四", "老五", "老六", "老七"};
        String[] colorsText = new String[]{"赤", "橙", "黄", "绿", "青", "蓝", "紫"};
        XiongDi a = new XiongDi();
        a.init(namesText, colorsText);
        System.out.println("进行随机化");
        a.randomIt();
        a.report(1);
        System.out.println("进行冒泡排序");
        a.bubbleSortIt();
        a.report(1);
        System.out.println("\n\n进行随机化");
        a.randomIt();
        a.report(2);
        System.out.println("进行二分排序");
        a.binaryInsertSortIt();
        a.report(2);
        /*
        a.report(0);
        a.randomIt();
        a.report(0);
        */
        /*
        a.report(0);
        a.swapTest();
        a.report(0);
        */
        /*
        a.report(0);
        System.out.println("\n以下测试state==1");
        a.report(1);
        System.out.println("\n以下测试state==2");
        a.report(2);
        */
        /*
        HuLuWa[] temp = new HuLuWa[namesText.length];
        for(int i=0;i<temp.length;i++)
            temp[i]=new HuLuWa(namesText[i],colorsText[i],i);
        */
        /*
        temp[0] = new HuLuWa("老大", "赤", 1);
        temp[1] = new HuLuWa("老大", "赤", 1);
        temp[2] = new HuLuWa("老大", "赤", 1);
        temp[3] = new HuLuWa("老大", "赤", 1);
        temp[4] = new HuLuWa("老大", "赤", 1);
        temp[5] = new HuLuWa("老大", "赤", 1);
        temp[6] = new HuLuWa("老大", "赤", 1);
        */

    }
```
参考结果展示

```
进行随机化
我是老七。
我是老五。
我是老大。
我是老四。
我是老二。
我是老六。
我是老三。
进行冒泡排序
老七：0->1
老五：1->0
老七：1->2
老大：2->1
老七：2->3
老四：3->2
老七：3->4
老二：4->3
老七：4->5
老六：5->4
老七：5->6
老三：6->5
老五：0->1
老大：1->0
老五：1->2
老四：2->1
老五：2->3
老二：3->2
老六：4->5
老三：5->4
老四：1->2
老二：2->1
老五：3->4
老三：4->3
老四：2->3
老三：3->2
我是老大。
我是老二。
我是老三。
我是老四。
我是老五。
我是老六。
我是老七。


进行随机化
我的颜色为蓝。
我的颜色为绿。
我的颜色为赤。
我的颜色为橙。
我的颜色为紫。
我的颜色为青。
我的颜色为黄。
进行二分排序
老六：0->1
老四：1->0
老六：1->2
老大：2->1
老四：0->1
老大：1->0
老六：2->3
老二：3->2
老四：1->2
老二：2->1
老七：4->5
老五：5->4
老六：3->4
老五：4->3
老七：5->6
老三：6->5
老六：4->5
老三：5->4
老五：3->4
老三：4->3
老四：2->3
老三：3->2
我的颜色为赤。
我的颜色为橙。
我的颜色为黄。
我的颜色为绿。
我的颜色为青。
我的颜色为蓝。
我的颜色为紫。
```