# 基于类的模块划分
## Creature类及其子类
&emsp; Creature类及其子类代码保存子package creature中，Creature是creature包中其他类的基类，在Creature中记录了所有生物都包含的
种族race（race的类型是enum Race，在Race.java中记录了所有种族），位置current和在二维空间中用于表示的符号symbol。同时为了
体现面向对象思想的现实性，在Creature类中实现了rushto（）和followOrder（）两种方法，分别实现该生物跑向二维空间的某个位置和
该生物听从某个生物的命令跑向某个位置。<br><br>
&emsp; 通过extends Creature得到CalabashBrother类、GrandFather类、Wannable类等分别表示葫芦娃、爷爷、喽啰、蝎子精、蛇精、空白等
子类，这里与作业3不同的是，为了保证单一职责，原本Creature同时具有提供基本信息和充当空白类的职责，这次作业中将充当空白类的代码
分离出来。每个类通过重写followOrder（）方法使得该类只听从固定类的指挥，如葫芦娃只听从爷爷的指挥，蝎子精和喽啰只听从蛇精的指挥
同时爷爷和蛇精作为指挥官，具备makeOrder（）方法（指挥某个生物前往某个位置）和fight（）方法（自行前往二维空间的某个位置准备作战）<br>
## Formation类及其子类
&emsp; 在作业3中，阵法的内容是由Map类提供的，有悖于单一职责的设计理念，因此在本次作业中把阵法相关的代码分离出来，Formation类是其他
阵法类的父类，记录了阵法的length，width和阵法中所有生物的列表all[][]，并在构造函数中将所有生物置为Space空白类。<br><br>
&emsp; 其他阵法类通过继承Formation类得到基本信息的定义，并在自己的构造函数中通过调用父类的构造函数设置信息并完成阵法的布置。<br><br>
## Loaction类
&emsp; 与作业3不同的是，由于需要保证面向对象思想的现实性，每个生物应该具有自己的坐标并能够跑到某个坐标，而不是由一个“上帝之手”将
一个位置中某个生物放到另一个位置中，因此在本次作业中我讲Location类内置在Creature类中，充当一个坐标，因此Location类的设计非常简单
仅需保护记录二维空间位置的x，y信息即可
## Generator接口与实现该接口的类
&emsp; Generator接口定义了一个可以生成某个类的实例化对象的类应具有的方法generate（），同时通过使用泛型的机制实现对自定义对象Creature和
Formation类的支持。<br><br>
&emsp; CreatureGenerator类和FormationGenerator类都implements了Generator接口，分别具备根据类名生成一个Creature类及其子类和生成一个
Formation类及其子类的功能，这两个类的实现基于java的反射机制，通过java的反射机制调用类中的构造函数产生实例化对象。<br><br>
## Map类
&emsp; Map类控制整个游戏的进程，类中记录了Map的大小、类中所有生物的列表map[][]、一个生物生成器CreatureGenerator、一个阵法生成器
FormationGenerator、一个GrandFather类的对象、一个SnakeEssence类的对象和一个当前阵法的对象。整个过程如下：首先Map上充满了空白，然后
爷爷和蛇精进入Map（通过fight（）方法），分别呼唤葫芦兄弟和蝎子精喽啰进入战场（通过makeOrder（）方法），最后将对阵情况输出。<br><br>
# 使用的面向对象的概念和机制
## 继承和聚合
&emsp; 继承：Creature类及其子类之间的继承关系，Formation类及其子类之间的继承关系。<br><br>
&emsp; 聚合：Creature类和Location类的聚合关系，Formation类和Creature类的聚合关系等等。<br><br>
## 泛型与接口
&emsp; 泛型接口Generator<T>。<br><br>
## 反射机制
&emsp; CreatureGerenator类和FormationGenerator类中通过反射机制生成相应类名对应的实例化对象。<br><br>
# 使用的设计理念
## 单一职责原则
&emsp; 相对于作业3的代码来说进行了一些解耦工作，尽量实现了单一职责原则。<br><br>
## 里氏替换原则
&emsp; 在CreatureGenertor类和FormationGenerator类的设计中应用了里氏替换原则，使用子类替换父类。<br><br>
## uml图片
![uml类图](https://github.com/tmsxk/java-2019-homeworks/blob/master/4-Types/%E6%96%BD%E8%B6%85%E7%83%9C-171860616/img/uml.png)
