#作业：面向葫芦娃编程
##面向对象的设计思路：

##1.设计的package以及其中的类如下：

**（1）organism**

package organism中包含以下几个类来定义各个生物体的状态和方法

Organism:所有生物体的父类，其他的各种生物体均**继承**于这一父类（它们都是一种生物），
考虑到生物体应该有通用的成员变量，在Organism中定义了protected String name（生物体名字和protected
Position position(生物体所在位置)，用继承而不使用接口设计这一部分，是因为接口里面定义的属性是常量无法修改
，比较麻烦

Huluwa: 葫芦娃，该子类特有的状态是颜色和排行，并且一些方法直接从第二次作业中移植过来

GrandFather: 爷爷

Snake：蛇精

Soldier:喽啰

Scorpion:蝎子精

Land:草地


**（2）formation**

package formation中包含了几个类定义各个阵法的状态和方法

Formation: 所有阵法类的父类，定义了所有阵法的通用属性，在设计阵法这一部分时定义了一个
二维数组Organism[][],用来描述各个位置是哪些生物构成了一个阵法

CraneWingFormation: 鹤翼阵，每个阵法都在初始化时传入几个Orginiam生物体用于摆出特定形状的阵法

WildGooseFormation: 雁行阵....

其余阵法设计同理


**（3）map**

package map目前只包含一个地图类

Map：在地图类中定义了长度和宽度（N>10),一个二维数组Organism all[][],用来放置生物体在上面
，以及一个Arraylist<Formation\>,在初始化时储存好所有阵法的信息，运行时只需要取出其中一个元素覆盖
地图上的某些位置，实现变阵处理


**（4）position**

package position包含了一个坐标类

Position:声明了两个变量x和y，用于描述生物体所在的位置坐标


##2.主要用到的面向对象的一些概念和设计理念

**（1）继承和组合**：例如在Organism类中使用了一个Position类的对象，直接用这个对象
用来描述当前的坐标。考虑到葫芦娃，爷爷等等都属于广义上的生物体，所以利用继承思想设计好每个
角色的类，它们都继承于Organism

**（2）向上转型**：在Formation和Map类中都定义了一个Organism的二维数组，但是在
addOrganism(添加某个生物到某个位置)时，可以传入各个子类的生物体（Huluwa，Sodier等等)
,在这里使用了向上转型的思想

**（3）访问控制**：例如坐标类Position的成员x,y访问权限为private，外部不能直接访问这个变量进行
坐标修改，Organism中的name,position访问权限为protected，继承的子类需要用到，进行频繁的读取和修改

##3.图片

**UML图：**
![UML](https://github.com/tigerwhhl/java-2019-homeworks/blob/master/3-OOPAdvanced/%E5%90%B4%E9%B8%BF%E7%A5%9C-171860677/img/uml.png)


**运行截图：**

![对峙](https://github.com/tigerwhhl/java-2019-homeworks/blob/master/3-OOPAdvanced/%E5%90%B4%E9%B8%BF%E7%A5%9C-171860677/img/1.png)


**变阵**


![变阵](https://github.com/tigerwhhl/java-2019-homeworks/blob/master/3-OOPAdvanced/%E5%90%B4%E9%B8%BF%E7%A5%9C-171860677/img/3.png)
