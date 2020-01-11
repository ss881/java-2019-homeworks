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

**作业4新增类 OrganismGenerator：** 生物生成器类，输入特定的生物类名，该生成器可以产生一个实例化对象，
并且返回这个类的对象

**作业4新增泛型接口 Generator\<T\>{ T next(String classname) }**:参考工厂模式设计的接口


**（2）formation**

package formation中包含了几个类定义各个阵法的状态和方法

Formation: 所有阵法类的父类，定义了所有阵法的通用属性，在设计阵法这一部分时定义了一个
二维数组Organism[][],用来描述各个位置是哪些生物构成了一个阵法

CraneWingFormation: 鹤翼阵，每个阵法都在初始化时传入几个Orginiam生物体用于摆出特定形状的阵法

WildGooseFormation: 雁行阵....

其余阵法设计同理

**作业4新增类 FormationGenerator：** 阵法生成器类，输入特定的阵法名，返回这个类的一个实例化对象


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

**（2）RTTI**：在Formation和Map类中都定义了一个Organism的二维数组，但是在
addOrganism(添加某个生物到某个位置)时，可以传入各个子类的生物体（Huluwa，Sodier等等)
,在这里使用了向上转型的思想，而在需要获取是具体哪个子类对象的时候，利用**instance of**进行类型判断
，实现安全的向下转型

**（3）访问控制**：例如坐标类Position的成员x,y访问权限为private，外部不能直接访问这个变量进行
坐标修改，Organism中的name,position访问权限为protected，继承的子类需要用到，进行频繁的读取和修改


**作业4新增内容**
##3.泛型和反射

（1）修改前的不足：在第3次作业中并未使用到接口，泛型和反射。由于生物类Organism有很多个
子类，在实现阵法Formation类和实现地图Map类时不得不import若干个organism包下的子类，非常麻烦，而且
Organism包的类暴露在其他包中，既不符合面向对象的抽象封装理念，也使得类之间的耦合过高。

（2）改进：

**·** 在package organism下新增了一个**泛型接口**：public interface Generator\<T> {T next(String classname);}

**·** 新定义了一个生成器类 class OrganismGenerator implements Generator\<Organism\> 在这个类中具有**Class类对象数组**
types={Land.class,GrandFather.class,Snake.class,Scorpion.class,Soldier.class}，描述的是各个子类的类名

**·** 实现接口 Organism next(String classname),将一个类名classname传入方法中，然后for循环会逐一对比找到合适的子类名
"if types[i].getName().equals("organism."+classname)"，这里使用了class的getName()方法获取类名的字符串值，如果找到第
i个元素满足条件，则创建一个该类的实例。这里用到了**反射**的思路，在运行时通过字符串值才能确定要创建的新对象类型，然后利用getConstructor().newInstance()获取
反射类对象，并且返回该对象

**·** 利用该泛型接口，同样可以在package formation下类似地定义生产器类class FormationGenerator implements Generator<Formation>，接口的实现
和OrganismGenerator类似，同样可以生成阵法子类的对象

（3）修改后：例如在package formation下可以只引用基类Organism和生成器类OrganismGenerator，在生成一个新的生物时
Organism creature=generator.next("Soldier")即可得到相应的士兵对象。

##4.图片

**UML图：**
![UML](https://github.com/tigerwhhl/java-2019-homeworks/blob/master/3-OOPAdvanced/%E5%90%B4%E9%B8%BF%E7%A5%9C-171860677/img/uml.png)


**运行截图：**

![对峙](https://github.com/tigerwhhl/java-2019-homeworks/blob/master/3-OOPAdvanced/%E5%90%B4%E9%B8%BF%E7%A5%9C-171860677/img/1.png)


**变阵**


![变阵](https://github.com/tigerwhhl/java-2019-homeworks/blob/master/3-OOPAdvanced/%E5%90%B4%E9%B8%BF%E7%A5%9C-171860677/img/3.png)
