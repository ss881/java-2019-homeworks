# 基于JavaFX的葫芦娃大战妖精
# 171860613 郁辰阳
##1. 项目结构
`/test`目录下为测试文件

`/src`目录下为源代码文件

精彩回放`combat.log`放在`/target`目录下

通过命令`mvn clean test package`可以在`/target`目录下得到可执行jar文件`huluwaGame-0.0.1-SNAPSHOT.jar`

## 2. 使用说明
**初始阵型**: 葫芦娃初始阵型为长蛇阵，妖精初始阵型为鹤翼阵；

**游戏开始**: 点击`start`按钮或者按下空格键；

**回放功能**: 战斗结束后会在`/target`目录下生成本次战斗日志文件`combat.log`，该文件会在下一次战斗（非回放）时被覆盖；

如果想要多次回放某一场精彩战斗，可以将该战斗的日志文件存到其它目录，通过`load`按钮选择该日志进行回放；

**切换阵型**: 可以通过点击屏幕左侧的阵型按钮来切换阵型；

**战斗结束**: 当葫芦娃或者妖精一方全部死亡时，游戏结束；

## 3. 程序框架
### 1. `Main.java`
程序入口；
### 2. `Package Creature`
包含对所有生物体的定义，具体如下：

#### **`Creature.java`**：
定义为`Abstract class`，作为其它生物体的基类；包含属性有：

`String name`：生物体名称；

`Position position`：在战场棋盘上的坐标；

`boolean justice`：所处势力，`true`为葫芦娃一方

`ImageView view`：生物体贴图；

`ImageView dead`：墓碑贴图；

`Timer timer`：生物体线程；

`boolean alive`：是否存活；


**`Huluwa.java`，`Louluo.java`，`Grandpa.java`，`Snake.java`，`Xiezijing.java`**分别表示葫芦娃，喽啰，老爷爷，蛇精和蝎子精；

### 3. `Package BattleField`
由`BattleField.java`和`Container.java`构成，其中`container`类表示`BattleField`的一个单元；

#### **`Container.java`**：

`int x,y`表示坐标;

`void setCreature(Creature creature)`放置生物;

`void setNull()`清空；

`Creature getCreature()`获取位置上的生物;

`boolean isEmpty()`是否为空；

#### **`BattleField.java`**：
大小为16*11，包含对战场棋盘`Container board[][]`的一系列操作：

`void initBattleField(String formation)`:按照输入的阵型放置生物，进行初始化；

`void allSetView()`：全部贴图；

`boolean posQualified(Position p)`：p位置是否合法；

`boolean emptyplace(Position p)`：p位置是否为空；

`Creature getLiving(Position p)`：返回p位置上活着的生物（如果有的话）

`Position isBeside(Positino p)`

`boolean isOpposite(Position a, Position b)`
：两个函数结合判断p位置旁边是否有敌人；

`Position getRandomPos(Position p)`

`synchronized Position findNext(Position a, Position b)`
：两个函数结合，用于判断从a到b下一步该怎么走；

### 4. `Package Formation`
阵型存储类，包含长蛇，鹤翼等共8个阵型；

阵型中包含属性`Position leader`表示首领所处位置，整个阵型依照首领的相对位置进行排布；

### 5. `Package Position`
用于表示坐标的基本类,主要包含以下成员函数:

`int getX()`获取X坐标；

`int getY()`获取Y坐标；

`Position up()`,`Position down()`,`Position left()`,`Position down()`来获取当前坐标的上下左右的坐标；

`String toString()`主要用于写入日志文件时将坐标转成字符串；

### 6. `Package FileIO` & `Package Control`
其中包含`FileIO`,`GameLogic`,`GUIControl`三个主要控制类；

#### 1. `FileIO.java`
该类主要负责存取日志文件来进行存储战斗过程和回放，主要成员函数如下：

`File loadFile()`：弹出文件选择窗口，返回用户选择的文件路径；

`ArrayList<String> parseFile(File file)`：按行读取日志文件并存入`ArrayList`;

`void writeFile(String filename, String log)`将log写入到路径为filename的文件中；

`void replayGame()`回放游戏，其中引用`loadFile(),parseFile()`函数；

#### 2. `GUIControl.java`
刻画GUI布局以及button,title等相关组件；

核心函数：`refresh()`：用于根据当前战场棋盘状况重新布局GUI上各生物的位置；

#### 3. `GameLogic.java`
核心控制类: 采用多线程编程的方式控制整个游戏的进展，通过`Timer.scheduleAtFixedRate()`使得线程定时执行，推动游戏不断进行；

主要线程如下：

##### 1. 生物线程：

检查自身是否死亡；如未死亡，接着检查自己身旁是否有敌人；如果身旁没有敌人，选择一个敌人向其移动；

##### 2. 清理墓碑线程
生物体死后会在原地生成阻碍行进的墓碑，为了避免可能出现的墓碑堵塞所有行进路线，每隔一段时间随机选取一个墓碑消失；

##### 3. 屏幕刷新线程
每隔一段时间调用`refresh()`函数刷新屏幕；


## 4. 游戏逻辑
首先创建`GameLogic`类，在该类中创建`GUIControl`类，`FileIO`类和`BattleField`类，分别初始化图形界面，文件IO和战场棋盘，之后监听按钮和键盘；

用户选择开始：创建各个线程，进入游戏进行阶段，直到游戏结束，写入日志文件，在此过程中不接受来自按钮和键盘的signal；

用户选择回放：在`FileIO`类中处理字符串，一步一步重现之前战斗的过程（而不是定时刷新）；

## 5. 程序中用到的思想和机制

### 1. 多线程并发
多个生物体同时进行活动，通过`synchronized`进行同步，避免共享资源的冲突访问；
### 2. 文件读写，输入输出和异常处理
在`FileIO`中实现日志文件的读写以及相关异常处理；
### 3. 单元测试
利用`Junit`测试工具对`BattleField`中的函数进行针对测试；
### 4. 类的聚合复用，继承
`GUIControl,GameLogic,BattleField,FileIO`之间的聚合复用；
### 5. 开放封闭原则
各个生物体类通过实现基类`Creature`中的虚函数`SetView`方法来完成对自己图像的初始化；

## 6. 总结
看《java编程思想》的过程中，仍然有不少地方理解不够深刻或者不够理解，这门课让我感觉到了java这门语言的博大精深，感谢老师们的教导。
