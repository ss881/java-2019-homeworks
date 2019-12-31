# 作业：面向葫芦娃编程 v2

本次作业的任务如下：

1. 先让七个兄弟随意站队，然后让他们按冒泡法依照各自排行（从老大到老七）进行排序，排序结束从第一个到最后一个报数（如老大报“老大”，以此类推）；
2. 然后让七兄弟再次随意站队，然后用让他们按二分法依照各自颜色（赤橙黄绿青蓝紫）进行排序，排序结束从第一个到最后一个报颜色（如老大报“红色”，以此类推）；
3. 排序过程中，每个葫芦娃在每次交换位置的时候报告交换动作（例如老大从位置5换至位置6，报告“老大：5->6“）

修改了v1中的大部分代码，有以下几点：

1. 将v1中的唯一一个文件（Homework2.java）进行拆分，每个类都分别为一个单独的文件（God.java, Map.java, Tile.java, Huluwa.java），使得结构更加清楚。

2. 引入地砖（`Tile`类）概念，地砖组成了地图（`Map`类中的数据成员`tiles`），每个地砖上可以站立一个葫芦娃。`Tile`类如下所示：

   ~~~java
   class Tile {
       Huluwa huluwa;
       Tile(){
           huluwa=null;
       }
   }
   ~~~

3. `Map`类中有一个成员变量名为`tempPosition`，表示一个暂定的位置，用于葫芦娃之间进行位置交换。`Map`中的`swap`函数实现如下：

   ~~~java
   private void swap(int p1,int p2){
       tiles[p2].huluwa.runToTempPosition(tempPosition,p2);
       tiles[p1].huluwa.runTo(tiles,p1,p2);
       tempPosition.huluwa.returnFromTemp(tiles,p1);
   }
   ~~~

   同时，在位置交换信息的输出时，使用'temp'表示暂定位置，例如以下`shuffle`函数的执行输出：

   <img src="./shuffleInfo.png" alt="image-20190923132552695" style="zoom:50%;" />

4. Huluwa`类中实现了名为`decideToChange(Huluwa)`的函数，两个葫芦娃进行交流（比较其颜色以及名字）决定是否可以进行交换（次序靠后的葫芦娃排在前面），如果可以交换，返回`true`，反之返回`false`。



# 作业：面向葫芦娃编程 v1

## 葫芦娃（HuLuWa）

### 数据成员（Field）

1. rank：指定不同葫芦娃的次序，这个次序是排序的依据。大娃为0，二娃为1，以此类推。

2. names：葫芦娃名字集合，为**static**成员变量，每个对象均可访问。
  
   `private static String[] names={"老大","老二","老三","老四","老五","老六","老七"};`

3. colors：葫芦娃颜色集合，为**static**成员变量，每个对象均可访问。
  
   `private static String[] colors={"红色","橙色","黄色","绿色","青色","蓝色","紫色"};`

### 成员函数（Method）

1. count( countKind kind )：报数是葫芦娃的行为，因此将其作为HuLuWa类中的方法。两次报数需要根据不同的类型，因此需要传入参数加以区分，countKind为枚举类型，声明如下：
  
   `enum countKind {NAME, COLOR}`

2. getRank( )：获取葫芦娃次序，用作排序依据

3. runTo( HuLuWa[ ] huLuWaBrothers, int src, int dst )：此处的参数src以及dst代表的含义不是数组的下标，葫芦娃从第**[src+1]**个葫芦娃的位置移动到第**[end+1]**个葫芦娃的位置。同时在屏幕上输出移动信息。

4. runToTempPosition( )：假设**HuluwaQueue**所维持的队列中存在一个**空闲的中间位置**，葫芦娃移动到这个空闲的中间位置以便进行位置交换。

5. returnFromTemp( HuLuWa[ ] huLuWaBrothers, int dst )：葫芦娃从**空闲的中间位置**移动到**dst**位置，最终完成位置交换。 

---

## 葫芦娃队列（HuluwaQueue）

### 数据成员 (Field)

1. huLuWaBrothers：使用数组存放葫芦娃对象
2. numberOfHuLuWa：葫芦娃数量
3. tempPosition：空闲的中间位置，以便葫芦娃之间进行位置交换

### 成员函数 (Method)

1. HuluwaQueue( int number )：构造函数，用来初始化葫芦娃队列以及葫芦娃

2. swap( )：交换两个葫芦娃的位置，并且在屏幕上进行相应输出
  
   ```java
   private void swap(int p1,int p2){
           huLuWaBrothers[p2].runToTempPosition();
           huLuWaBrothers[p1].runTo(huLuWaBrothers,p1,p2);
           HuLuWa.returnFromTemp(huLuWaBrothers,p1);
       }
   ```

3. shuffle( )：葫芦娃随机排序，并且输出位置交换信息

4. bubbleSort( )：冒泡排序

5. binarySort( )：二分法排序

6. count( countKind kind )：两次报数需要根据不同的类型，因此需要传入参数加以区分

------

## 上帝（God）

### 数据成员（Field）

1. HULUWA_NUM：葫芦娃个数，static类型，值为7
2. huLuWaQueue：葫芦娃队列

### 成员函数（Method）

1. God( )：构造函数，初始化葫芦娃队列

2. run( )：执行排序，报数等命令
