# Java-2019 作业二 

## 171860633 徐国栋

## 任务简述

* 1、让七个葫芦娃随意站队，而后按照排行进行冒泡排序，报告位置交换，报告最终排行；

* 2、让七个葫芦娃随意站队，而后按照颜色进行二分排序，报告位置交换，报告最终颜色；

## 设计思路

### 葫芦娃外观的模拟

葫芦娃拥有颜色属性，使用外观类记录颜色，记录后期渲染需要的图片
```java
public class BioAppearance {
    public Color color;
    ...
}
```

### 生物的模拟

采用派生的方式描述不同生物，编写生物类为基类，记录共有的信息
```java
public class Creature { 
    private int id;
    public String name; 
    public BioAppearance apperrance; 
    ...
}
```

对于葫芦娃，拥有特有的家族排行，同时编写两个映射函数获取家族排行和颜色的中文名用于展示
```java
final class CalabashBrother extends Creature {
    public int rank; 
     public String rank2String(int _rank) {...}
     public String color2String() {...}
    ...
}
```

### 葫芦娃排序的模拟

设置排序表演类，包含随机、冒泡和归并排序算法，完成作业二的调度任务，作业要求葫芦娃报告交换位置，冒泡排序是原位排序，一次交换，一定是葫芦娃两两报告；二分排序会另外开辟数组空间，将元素填入空位，所以一次位置变化只需要一个葫芦娃单独报告即可
```java
public class SortShow {
    CalabashBrother[] players;
    public SortShow() {... }
    public void randomShuffle() {...}
    private class RankComparator implements Comparator<CalabashBrother> {...}
    private class ColorComparator implements Comparator<CalabashBrother> {...}
    public void playRankBubbleSort() {...}
    private void reportExchange(int a, int b) {...}
    private void reportMoveTo(int a, int b) {...}
    private void merge(int left, int mid, int right) {...}
    private void mergeSort(int start, int end) {...}
    public void playColorMergeSort() {...}
}
```

## 结果展示
```
javac *.java
java Homework2
第一项任务已开始,随机打乱后，葫芦娃报数：
老二:"老二";    老七:"老七";    老六:"老六";    老三:"老三";老四:"老四";    老大:"老大";    老五:"老五";

开始按排行的冒泡排序，注意到冒泡排序是原位排序，元素原地交换：
老七:"1->2";    老六:"2->1";
老七:"2->3";    老三:"3->2";
老七:"3->4";    老四:"4->3";
老七:"4->5";    老大:"5->4";
老七:"5->6";    老五:"6->5";
老六:"1->2";    老三:"2->1";
老六:"2->3";    老四:"3->2";
老六:"3->4";    老大:"4->3";
老六:"4->5";    老五:"5->4";
老四:"2->3";    老大:"3->2";
老三:"1->2";    老大:"2->1";
老二:"0->1";    老大:"1->0";

按照排行冒泡排序，完成后，所有葫芦娃报数：
老大:"老大";    老二:"老二";    老三:"老三";    老四:"老四";老五:"老五";    老六:"老六";    老七:"老七";

第一项任务结束。

第二项任务已开始，随机打乱后，葫芦娃报数：
老三:"黄色";    老七:"紫色";    老六:"蓝色";    老二:"橙色";老五:"青色";    老大:"红色";    老四:"绿色";

开始按颜色的二分排序，注意到归并排序会另外开辟空间，直接移动即可：
老三:"0->0";
老七:"1->1";
老二:"3->2";
老六:"2->3";
老二:"2->0";
老三:"0->1";
老六:"3->2";
老七:"1->3";
老大:"5->4";
老五:"4->5";
老大:"4->4";
老四:"6->5";
老五:"5->6";
老大:"4->0";
老二:"0->1";
老三:"1->2";
老四:"5->3";
老五:"6->4";
老六:"2->5";
老七:"3->6";

按照颜色二分排序，完成后，所有葫芦娃报数：
老大:"红色";    老二:"橙色";    老三:"黄色";    老四:"绿色";老五:"青色";    老六:"蓝色";    老七:"紫色";
```