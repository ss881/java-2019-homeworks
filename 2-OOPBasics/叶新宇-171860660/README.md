# 面向葫芦娃编程 解决思路
阅读题面，从“葫芦娃排序”这一事件中我们能抽象出3个类：
- 1. 事件类，可以认为是“葫芦娃排序”等若干事件发生的地点，包含主函数的入口
- 2. 葫芦娃类，描述葫芦娃的各种属性:姓名、颜色、排行，提供葫芦娃与外界交互的方法：报姓名、报颜色、报位置变化
- 3. 葫芦娃操作类，存储葫芦兄弟的信息，提供葫芦娃随意站队、葫芦娃冒泡排序、葫芦娃二分排序三种方法
## 事件类
```Java
    public static void main(String[] args){
        HuluwaOperation huluwaOperation = new HuluwaOperation();
        huluwaOperation.shuffle();
        huluwaOperation.bubbleSort();
        huluwaOperation.shuffle();
        huluwaOperation.quickSort();
    }
```
事件类包含main函数，创建一个葫芦娃操作类对象，调用它提供的抽象方法完成题目需要的葫芦娃排队过程

## 葫芦娃类
```Java
class Huluwa {
    private String name;
    private String color;
    private int num;
    //葫芦娃具有以上三种标识信息：姓名，颜色及排行(用于确定他们之间的顺序)
    Huluwa(String n, String c, int m);
    //构造函数
    public boolean talk(Huluwa bro);
    //两个葫芦娃之间进行交流，排名大的靠前，排名低的靠后
    public void sayName();
    public void sayColor();
    public void report(int a, int b);
    //报自己的名称、颜色、报告位置变化
}
```
葫芦娃类描述了一个葫芦娃具有的属性以及他与外界交互的方式，是“葫芦娃”作为个体的抽象

## 葫芦娃操作类
```Java
class HuluwaOperation {
    private Vector<Huluwa> HuluwaBrothers = new Vector<Huluwa>();
    HuluwaOperation();
    //构造函数
    public void shuffle();
    //葫芦娃随意站队
    public void bubbleSort();
    //葫芦娃冒泡排序
    private void quickSortPartition(int begin, int end);
    public void quickSort();
    //葫芦娃快速排序
}
```
葫芦娃操作类提供一个容器维护了葫芦七兄弟的信息，描述了葫芦兄弟之间的交互方式（随意站队和排序），是“葫芦兄弟”作为一个整体的抽象
