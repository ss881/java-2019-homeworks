# JAVA第四次作业
**171860618 王昆**  
## 作业介绍
本次作业应用了javafx框架进行GUI绘制
![stage.png](https://i.loli.net/2019/12/29/LWwgyvmu3q1FexY.png)
当进入应用时，双方生物会按基本阵型在地图两侧列阵，此时点击左上右上方的按钮即可为双方阵营变阵。
## 代码框架 
![uml.png](https://i.loli.net/2019/12/30/BGK7VIkcxTuRQl3.png)
本次作业中，我共设计了`Position`、`BattleMap`、`Formation`、`Creature`、`GameController`五个基本类。取消了`Army`类，将其军队的组织加到了`Creature`的派生类：`GoodCreature`和`BadCreature`的静态域中，并从`Creature`类派生出了葫芦娃(`Huluwa`)、老爷爷(`Grandpa`)、蝎子精(`Scorpion`)、小喽啰(`Follower`)等具体生物类。
- `GameController`负责交互和控制游戏。维护游戏的主要状态，负责接收键盘事件、设置双方阵型等等。
- `Creature`是具体生物类，在它的两个派生类`GoodCreature`和`BadCreature`中维护了静态变量的双方阵营的生物列表，并且为`GameController`提供了静态的函数接口，可以为双方阵营添加生物、改变阵型。
- `Formation`类封装了不同阵型的计算方法，根据双方的基础位置、人数、面向方向等参数，提供不同阵型的位置信息。
- `BattleMap`类是双方的战场，维护双方位置信息(具体方式是维护了以`Position`为键码，以`Creature`为值的`Map`对象)，并且在`BattleMap`中负责对GUI进行更新。
- `Position`类维护了地图网格的位置信息，并且可以进行地图网格位置到画面位置的转换。
## 设计思路
### 1 设计模式
在项目设计中体现了以下的设计模式思想：  
#### 单例模式
`GameController`实现为单例模式，在程序入口处调用`GameController.newInstance()`初始化后，全局只可以通过`GameController.getInstance()`访问其实例。

#### 观察者模式 
- `GameCotroller`按照观察者模式进行键盘事件、按钮鼠标事件的监听、处理。
### 2 应用机制 
在项目中应用了Java的异常处理、集合类型、泛型、输入输出等机制。
#### 集合类型
`BattleMap`类中维护了生物及其当前位置的映射Map `Map<Position, Creature> characterList`。`GoodCreture`和`BadCreature`分别维护了双方阵营军队的List列表。使得集合内容更易维护。
#### 异常处理
当`GameController`添加生物或改变阵型出现错误情况下都会抛出异常，并在捕获异常后转入`GameController`的`crashed()`方法中处理，进行游戏状态的重置。
#### 类型信息和反射
在为双方阵营添加生物时使用 `instance of`进行`GoodCreature`或`BadCreature`类型信息的判断，防止添加非本阵营的生物。
#### 继承和多态
具体生物类都继承自抽象的`Creature`类，而对于任何不同的生物，依赖时都将其当做基类Creature来处理，实现向上转型。方便对不同生物的扩展。
