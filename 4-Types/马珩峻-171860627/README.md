封装
================
每种可能会出现的对象都封装成了类和接口。

大致是整个过程是一个Game对象，里面有葫芦兄弟CalabashBrother，爷爷Grandfather等等从抽象基类Unit继承而来的单位。

Unit又有接口Move描述单位移动，每个单位有位置Position。

而这些都是对象，所以都抽象成了对应类。

继承
================
抽象类Unit通过接口Move声明了移动的方法。

而Game中所有的单位（葫芦娃，爷爷，蛇精，敌方臭鱼烂虾）都继承自基本单位Unit，Unit有基本单位的位置和单位类型属性，并实现了基本单位的移动方法。

多态
================
Unit中实现接口Move中的moveToDestination和stepTo方法（但由于本例中只有Unit用了Move接口，此处并不能很好体现）。

抽象类Unit中声明了虚函数printOnMap用来将单位打印在地图上，而每个继承自Unit的类都重写了该方法，这就是printOnMap方法同种形态的不同表现方式。

反射
================
在覆写Position的equals函数时，使用getClass方法判断比较对象是否和自身是同一个类的实例；  
将Unit中的虚函数printOnMap改为在Unit中实现，使用getClass.getSimpleName得到实例对象的类名，将类名首字母作为代号输出。（葫芦娃的除外，葫芦娃类中覆盖此方法通过枚举类型中定义的符号进行输出）。  
因为新增泛型类Camp表示阵营，在遍历两边阵营camp里面的士兵soldier时使用了instanceof判断soldier是否为Unit的子类。（Game.java Line:58 在renewMap方法中）

泛型
================
新增一层抽象Soldiers<T extend Unit>表示两边阵营的士兵队伍，T为士兵的类型。
新增一层抽象Leader<T extend Unit>表示两边阵营的领导人，T为领导的士兵的类型，主要用于在整队调用orderFormation函数时确定整队的士兵队伍中士兵的类型。
新增一层抽象Camp<T extends Leader<E>, E extends Unit>表示阵营，E为阵营中士兵类型，T为阵营领导类型。

UML图
================
![UMLpic](https://raw.githubusercontent.com/UnableToCode/java-2019-homeworks/master/4-Types/%E9%A9%AC%E7%8F%A9%E5%B3%BB-171860627/CalabashBrother.jpg)

