Homework2
=========
* Huluwa类中定义了葫芦娃的颜色，排行，都是String类型，其中还有两个初始化对象的接口，一个带参数一个不带参数，方便在创建Huluwa对象时直接初始化。<br><br>
* HuluwaSort类中定义了Huluwa类型的数组，用以存储7个Huluwa类型的对象；<br>
initialize()：用以创建多个Huluwa类型对象同时利用Huluwa类型的接口对其初始化并随意存进数组中，模拟随意站队；<br>
switchplace()：将两个葫芦娃互换位置，在bubbleSort中调用；<br>
jump()：让葫芦娃跳入参数相应位置；<br>
bubblereport()：让葫芦娃在冒泡排序中报告换位动作；<br>
binaryreport()：让葫芦娃在二分排序中报告换位动作；<br>
bubbleSort()：对葫芦娃进行冒泡排序；<br>
binarySort()：对葫芦娃进行二分排序；<br>
showposition()：每次换位后显示葫芦娃的相应位置；<br>
main()：首先new一个本类对象，并用该对象的初始化方法initialize()对其初始化，再用该对象的bubbleSort()方法进行冒泡排序，然后按排行报数；
再调用一次该对象的initialize()方法打乱葫芦娃的位置，而后调用该对象的bunarySort()方法进行二分排序，最后按颜色报数。
