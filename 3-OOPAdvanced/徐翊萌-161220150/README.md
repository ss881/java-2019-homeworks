# 第三次作业代码说明

## 输出内容截图
![res1](https://github.com/XYm1998/java-2019-homeworks/blob/master/3-OOPAdvanced/徐翊萌-161220150/img/res1.png)
![res2](https://github.com/XYm1998/java-2019-homeworks/blob/master/3-OOPAdvanced/徐翊萌-161220150/img/res2.png)

## Class Diagram
![class_diagram](https://github.com/XYm1998/java-2019-homeworks/blob/master/3-OOPAdvanced/徐翊萌-161220150/img/Calss_Diagram.png)

## 设计思路
* 1、Battle类构建战场，私有成员的类型为Unit[][]。提供setUnit方法，将某生物体放置在某座标上<br>
* 2、Unit不需要实例，设为**抽象类**。GrandFather、CalabashBrother、Snake、Scorpion、Monster继承基类Unit<br>
* 3、为实现GrandFather、Snake为同阵营对象加油的功能，提供leader接口，实现相关功能
```Java
interface leader {
	void cheer();
	void instruction(); //发出“布阵/变阵”的指示
}
```
* 4、Camp类的对象用于存储单个阵营的所有对象
```Java
public calss Camp<T extends Unit> {
	private ArrayList<Unit> soldiers;
	private T leader;
	...
}
```
* 5、阵型变换在现阶段统一交给Controller类处理，相关的成员函数均为public static void。考虑在后续代码中提供caption接口，Scorpion实现该接口，指挥妖怪侧

## 利用的机制与好处
* 封装：将不同的内容封装为多个类，隐藏内部实现，提高代码安全性、可读性。<br>
* 继承：GrandFather、CalabashBrother、Snake、Scorpion、Monster继承基类Unit，提高代码复用程度。使多个类之间产生联系，提高代码可读性和可维护性<br>
* 多态：Unit的子类重写了父类的方法，提高可扩充性和可维护性。