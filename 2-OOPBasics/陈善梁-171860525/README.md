### 第二次作业
1. 葫芦娃类  
   1. 三个成员变量:int id(序号1~7)，String name(名字),String color(颜色)
   2. 构造函数接受三个参数初始化三个成员变量
   3. 三个表示葫芦娃动作的函数:descName(报名字),descColor(报颜色),descMove(报移动)
   4. 实现Comparable接口，使得葫芦娃之间按id比较大小
2. GrandFather类
   1. 私有变量Huluwa数组，表示拥有的七个葫芦娃
   2. bubbleSort和binarySort:分别将Huluwa数组用冒泡、二分排序
   3. findPlace(ArrayList<Huluwa>list,int start,int end,Huluwa h)函数: list的[start,end]已经排序,寻找插入h的位置,辅助binarySort
   4. shuffle函数:将Huluwa数组打乱
   5. showAllName与showAllColor:打印葫芦娃数组的名字、颜色

面向对象思想体现在:
   1. 葫芦娃对象有表示自身信息的属性:名字、颜色等。有表示动作的方法:报名字、颜色、移动等，表示状态变化。
   2. 葫芦娃可以看作是属于老爷爷的，因此老爷爷类有葫芦娃数组作为私有变量，并且可以调用方法对葫芦娃进行打乱顺序、排序以及让他们报数。对葫芦娃的排序都通过GrandFather对象提供的方法进行操作。
 
### 第二次作业更新
一.  运用面向对象思想，更好地模拟世界
> 1. Creature类增加了 moveTo(Position p) 和 switchWith(Creature creature)
> 函数，表示生物移动位置和两个生物之间交换位置.
> 2. Huluwa类增加了 compareTo(Huluwa o)和 Huluwa lookBack() 函数，
> 表示葫芦娃之间比较大小以及葫芦娃向后看后一个葫芦娃是谁.

二. 运用"设计模式"的思想
> 1. SRP 原则.  
>  用接口将类的多个职责分散,类实现多个接口.  
>生物Creature类有两个职责:移动和交换，因此实现了MoveAble和SwitchAble接口。
>葫芦娃有两个职责：比较和查看后一个葫芦娃，因此实现了Comparable和
>LookBackAble(名字不太优雅)两个接口。  
>老爷爷GrandPa有四个职责：打乱葫芦娃顺序、初始化葫芦娃、冒泡排序、二分排序。
>因此实现了四个接口ShuffleAble<Huluwa>、InitializeAble<Huluwa>、
>BubbleSortAble<Huluwa>、
>BinarySortAble<Huluwa>。
> 2. OCP原则  
>我认为本次作业并不需要用到抽象类，因为主体是老爷爷对葫芦娃进行排序，
>而不是对生物进行排序，因为生物不一定能够互相比较以及先后查看。因此没有根据OCP原则修改。
>3. LSP原则  
>使用基类对象指针或引用的函数必须能够在不了解衍生类的条件下使用衍生类的对象。  
>这一原则体现在葫芦娃、老爷爷对Creature类的继承上。Creature类的两个行为:moveTo
>和switch是所有生物都有的，也同样适用于葫芦娃和老爷爷，在行为上，后二者是一种Creature。
>因此使用Creature引用的函数可以在不了解衍生类(葫芦娃)的条件下使用衍生类的对象。   
>4. ISP原则  
>体现在生物类、葫芦娃类、老爷爷类的职责通过多个接口分散上。
>5. CRAP原则  
>对于必要的继承关系:葫芦娃继承了生物类，不能采用聚合。对于has-A关系，我采取了聚合。
>比如位置是生物的一种属性，因此Position作为Creature的域存在。
> 6. LoD原则  
>没有出现public的域，而是用set和get方法修改、获取私有变量。