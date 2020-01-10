# 葫芦娃大战妖精

### 运行环境
java version: 1.8  
Intellij IDEA: 2019.3  
maven: 4.0.0  
maven插件和依赖项版本：（以在pom.xml中写明）  
maven-compiler-plugin: 3.8.0  
maven-jar-plugin: 3.0.2  
junit: 4.12  
dom4j: 2.1.1  

### 游戏说明
运行后点击star game进入游戏战场界面，quit退出。  
点击star game后需要选择葫芦娃阵型，鼠标放到阵型上后葫芦娃会显示该阵型效果（偃月阵因为葫芦娃人数不够不能成阵所以不能选择）。  
选择阵型后敌军会随机一个阵型并显示，此时按空格键可以开始战斗，葫芦娃和敌人会自动索敌，移动，攻击，攻击方式为本体和目标单位power值相加取随机数，若结果小于本体power则敌军死亡，否则本体死亡，每次击杀单位后击杀者的power-1。  
游戏在选择阵型后按空格开始前以及战斗结束后按L会弹出文件选择框选择录像文件(*.rep)可以回放录像。每次战斗之后都会保存本次战斗录像，文件名为战斗结束时的时间。  

### 代码说明
src.main.java.cn.mhj中是主要实现代码部分  

##### package Controller
Controller中为实现javaFX 的controller类
- MenuController: 菜单界面的Controller，管理菜单界面的按钮事件。
- BattlefieldController: 战场界面的Controller，管理战场的初始化，阵型选择的按钮，战斗的画布。

##### package Creature
Creature中为各种生物和阵营相关的类  
- Creature: 生物的最基本类，记录了生物所在的战场和贴图。
- FightCreature: 战斗单位的基类，继承自Creature，实现Runnable接口，可多线程运行，可以在地图上移动，索敌，攻击  
- CalabashBoy: 葫芦娃，继承自FightCreature
- Enemy: 敌人，继承自FightCreature
- Grandpa: 爷爷，继承自Creature
- Snake: 蛇精，继承自Creature
- Camp: 阵营接口，声明了isLoss和setFormation两个方法
- SelfCamp: 自己阵营
- EnemyCamp: 敌军阵营

##### package Enum
Enum中为各种枚举类
- CalabashColor: 葫芦娃颜色枚举类
- FormationType: 阵型枚举类
- GameStatus: 游戏状态枚举类

##### package Field
Field中为战场中用到的各种类
- Battlefield: 战场主类，记录战场上的单元，阵营，单位，对战斗初始化，开始战斗，记录战斗，实现Runnable接口，可以作为线程运行。
- Position: 单位位置类
- Range: 战场范围，主要用于排阵时确定阵营初始化时候单位在战场上能占的范围
- Unit: 战场上的单元格，战场被分为多个小块，每个块为一个Unit

##### package Formation
Formation中为各种阵型的接口。
- Formation: 只有一个静态方法，根据formationType调用其他接口函数进行排阵
其他阵型接口，都只有一个静态方法，将传入的队伍排列成本阵型。

##### package Reply
Reply中主要为录像的记录和回放相关类，录像记录文件格式考虑到灵活性最后在json和XML中选择了XML文件
- XMLFormat: 定义了XML文件中的节点名
- Recorder: 继承自XMLFormat，实现记录的初始化，添加记录，保存
- RepReader: 继承自XMLFormat，实现录像中单位的初始化，录像的回放

##### Main
JavaFx程序运行的入口

src.main.resources.cn.mhj中为各项资源文件  
- CSS中为FXML文件中用到的样式表文件
- FXML中为javaFX界面设计的FXML文件
- pic中为各种用到的图片

src.test.java.cn.mhj中为单元测试文件  
因为GUI的复杂性和各个部分之间依赖关系复杂，只对FightCreature，Position和Unit中的几个关键方进行了单元测试

### 设计原则
#### SRP 单一职责原则
- 每个类都只负责自己部分的功能，战斗单位的行为都在FightCreature中实现，GUI的各项控制在Controller中实现，战场上的各个部分在Battlefield中实现，录像的记录和回放在Recorder和RepReader中实现
#### OCP 开放封闭原则
- 开放封闭原则是指实体应该对扩展是开放的，对修改是封闭的。即，可扩展，不可修改。应该通过增加代码来扩展功能，而不是修改已经存在的代码。在生物类的设计体现这条原则：所有战斗单位抽象成一个abstract class Creature，然后具体的单位通过继续抽象类Creature。同时生物的阵营声明Camp接口，实现阵型设置和判负方法。原本想在阵型Formation上通过抽象Formation然后继承Formation来实现阵型的排阵，后来因为不想每次排阵都实例化一个阵型对象，所以将所有阵型做成了接口类并用静态方法实现阵型调用来排阵。
#### LSP LISKOV替换法则
- FightCreature的子类CalabashBoy、Enemy都可以替换FightCreature类，Creature的子类Grandpa、Snake都可以替换程序中的Creature类。实际上在后续操作中，都是用Vector来表示生物集合。
#### CARP 合成/聚合复用原则
- 战场Battlefield由一组Unit类，两边阵营SelfCamp，EnemyCamp聚合而成
- SelfCamp由自己的单位CalabashBoy，Grandpa聚合而成
- EnemyCamp由敌军单位Enemy和Snake聚合

### 其他说明
##### 线程安全
主要对战斗单位的移动，战斗的方法加上了synchronized锁，以及单位移动，战斗时对单位所在的战场battlefield上锁，避免线程冲突使得多个单位攻击同一单位或者多个单位站在同一位置。  
战场线程Battlefield中战斗开始后采用CountDownLatch计数器来阻塞该线程，等待所有战斗单位的子线程结束。而子线程通过线程池管理运行。

### 运行结果展示
菜单界面  
![Menu](https://raw.githubusercontent.com/UnableToCode/java-2019-homeworks/master/Final/%E9%A9%AC%E7%8F%A9%E5%B3%BB-171860627/CalabashBrother/example_pic/Menu.png)  
阵法选择界面  
![Select](https://raw.githubusercontent.com/UnableToCode/java-2019-homeworks/master/Final/%E9%A9%AC%E7%8F%A9%E5%B3%BB-171860627/CalabashBrother/example_pic/selectFormation.png)  
战斗界面  
![battle](https://raw.githubusercontent.com/UnableToCode/java-2019-homeworks/master/Final/%E9%A9%AC%E7%8F%A9%E5%B3%BB-171860627/CalabashBrother/example_pic/battle.png)  
战斗结束
![end](https://raw.githubusercontent.com/UnableToCode/java-2019-homeworks/master/Final/%E9%A9%AC%E7%8F%A9%E5%B3%BB-171860627/CalabashBrother/example_pic/end.png)  
录像选择
![reply](https://raw.githubusercontent.com/UnableToCode/java-2019-homeworks/master/Final/%E9%A9%AC%E7%8F%A9%E5%B3%BB-171860627/CalabashBrother/example_pic/reply.png)  
