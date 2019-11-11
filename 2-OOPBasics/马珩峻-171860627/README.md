分析问题，七个葫芦兄弟站队，然后打乱循序，排序，报数，有以下几步

组成七兄弟
七兄弟打乱位置
七兄弟冒泡排序
七兄弟报数
七兄弟打乱位置
七兄弟二分法排序
七兄弟报数

对应代码就是

CalabashBrother calabashBrother = new CalabashBrother();

calabashBrother.shuffle();
calabashBrother.bubbleSort();

calabashBrother.shuffle();
calabashBrother.binarySort();

(报数在Sort方法里实现了)

然后考虑怎么将七兄弟抽象成类

因为葫芦兄弟由7个葫芦娃组成，所以使用了两个类

CalabashBrother是主要的类，表示葫芦兄弟，使用了vector容器来存放CalabashBoy葫芦娃对象，故只有一个私有成员Vector<CalabashBoy> calabashBoys
提供的公有方法有shuffle(洗牌)，bubbleSort(执行冒泡排序)，binarySort(执行二分查找)，printColor(用于遍历vector打印葫芦兄弟的顺序和颜色，主要用于测试)
私有方法有swap(用于排序算法交换vector中两个对象的位置)，quickSort(用于二分查找执行快速排序算法)
因为葫芦娃一定是7个，所以构造时会依次按顺序构造7个葫芦娃对象放入vector容器中。

而每个葫芦娃是一个对象，使用CalabashBoy类来抽象，因为不希望在CalabashBrother类外部使用该类，所以定义为了CalabashBrother的内部私有类。
每个葫芦娃的属性有颜色和名字，所以CalabashBoy类有两个私有成员color和name
因为葫芦娃只有七个颜色，所以在CalabashBrother类中还声明了私有枚举类型Color用来描述葫芦娃的颜色并且通过colorValue值给7个颜色排好序。
CalabashBoy的提供的方法有对应自己属性的get函数，以及在交换位置时用来交换属性的set函数，另外因为交换完需要报数和报颜色，所以有numberOff和colorOff方法，作用为报自己的名字和颜色。
