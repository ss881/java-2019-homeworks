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

UML图
================
![Image text](https://raw.githubusercontent.com/UnableToCode/java-2019-homeworks/master/3-OOPAdvanced/%E9%A9%AC%E7%8F%A9%E5%B3%BB-171860627/CalabashBrother.png)
