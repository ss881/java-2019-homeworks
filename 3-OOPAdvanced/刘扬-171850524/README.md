# java作业3：面向葫芦娃编程

1. 代码说明：
    类homework3包括main函数，是程序执行的入口；
    类Creature用于描述生物体，类Boy用于描述葫芦娃，类Enemy用于描述敌人，后面二者是前者的派生类；
    类World用于描述二维空间；
	类Formation用于描述阵型；
	类God用于调度生物体；
2. God类作用
	创建生物体；移动生物体；对葫芦娃进行排序；调用Formation类对生物体变换阵型
3. Formation类
	将葫芦娃或者妖怪排列成相应的阵型；
4. World类
    数据成员包括map和N，N指定map的大小，map代表了二维空间；拥有函数可以打印对峙局面；
5. Creature类、Boy类和Enemy类
    Creature类数据成员包括姓名name和坐标x、y；方法有walk和tellName；	Boy类继承Creature类，用于描述葫芦娃，数据成员增加了葫芦娃的颜色color和排行rank，方法增加了tellRank和tellColor；
	Enemy类继承Creature类，用于描述妖怪；
6. 用到的面向对象的机制
	继承：葫芦娃和妖怪的类都继承生物体类；
	聚合：World类包含Creature类的二维数组作为数据成员；
	final和static：static final int N用于指定二维空间的大小；
	多态：map是Creature类的引用数组，可以指向Creature类的派生类；

![类图](https://github.com/NJULY/myCode/blob/master/umlGraph1.png)

