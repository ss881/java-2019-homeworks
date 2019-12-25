# 葫芦兄弟大战妖精

## 程序介绍

运行程序后，游戏界面如上图所示：正反两阵营按照默认阵型在战场中对峙（正方：长蛇阵，反方：鹤翼阵）。  

![开始界面](https://github.com/tmsxk/java-2019-homeworks/blob/master/Final/%E6%96%BD%E8%B6%85%E7%83%9C-171860616/MyHuluwa/img/开始页面.png)  

用户可以通过点击下方的四个按钮控制游戏的进行，点击”改变葫芦娃阵型“的按钮可以将葫芦娃的阵型改变为随机阵型，点击”改变妖精阵型“可以将妖精的阵型改变为随机阵型。选择好双方阵型后，可以点击开始新游戏开始新的战斗，战斗过程中会自动保存游戏的存档并以战斗开始的世界命名。战斗结束后可以点击读取存档进行战斗回放。  

最精彩的战斗保存在随之打包的best_battle.txt中

游戏中每个角色的属性和专属技能都不同，具体如下表所示：  

| 角色名称 | 血量 | 攻击力 | 防御力 |                                                              |
| -------- | ---- | ------ | ------ | ------------------------------------------------------------ |
| 大娃     | 100  | 18     | 5      | 大娃力大无穷，可以无限增加攻击力（5）                        |
| 二娃     | 100  | 20     | 0      | 二娃千里眼顺风耳，能够发现敌方弱点使下一次攻击暴击           |
| 三娃     | 100  | 10     | 10     | 三娃铜墙铁壁，防御力高的同时能够恢复自身血量（10）           |
| 四娃     | 100  | 15     | 5      | 四娃能喷火，对周围敌人造成大量伤害（30）                     |
| 五娃     | 100  | 15     | 5      | 五娃能喷水，对周围敌人造成大量伤害（30）                     |
| 六娃     | 100  | 15     | 5      | 六娃能隐身，进入隐身后攻击力增加到（30）但防御力清零（30）   |
| 七娃     | 100  | 15     | 5      | 七娃释放宝贝葫芦的力量，对所有敌人造成伤害（15）             |
| 爷爷     | 100  | 15     | 5      | 爷爷为所有友方单位回复生命值（10）                           |
| 小喽啰   | 100  | 15     | 6      | 小喽啰很垃圾，只能无能狂怒，恢复自己少量血量（5）            |
| 蝎子精   | 100  | 20     | 10     | 蝎子精头脑简单四肢发达，能释放无上妖力使下一次攻击三倍暴击   |
| 蛇精     | 100  | 20     | 0      | 蛇精释放妖风，对所有敌人造成伤害（10）为所有友军恢复血量（10） |

# 基于包的模块划分

![UML类图](https://github.com/tmsxk/java-2019-homeworks/blob/master/Final/%E6%96%BD%E8%B6%85%E7%83%9C-171860616/MyHuluwa/img/Huluwa-final.png)

类的基本信息如上UML类图所示，模块划分如package划分所示，主要包括控制生物及其行为的creature包，控制存储信息的history包，控制多线程的thread包以及控制游戏进程的controller包。

### creature包

creature包中自定义了Fight接口，以支持战斗的行为，实现该接口的类可以对某一生物进行攻击以及对于某个游戏地图释放技能

```
interface Fight {    
public void attack(Creature enemy, Canvas canvas);
public void skill(MyMap map,Canvas canvas); 
}
```

之后定义了`Creature`类实现了`Fight`接口，这是所有生物类的父类，`Creature`类提供了一只生物所需要的通用接口，如：探寻前进方向、向画布上显示图片、回血、掉血、攻击、绘制攻击动作、使用技能等。对于子类而言，只需复写`Creature`的对应方法即可。

`CalabashBrother`类、`Grandfather`类、`Wannabe`类、`ScorpionEssence`类以及`SnakeEssence`类均继承自`Creature`类，但根据自身的需要复写了父类的接口，从而使得`Creature`及其子类之间的转型更加安全。

### history包

history包中定义了`Init`类和`Action`类，分别用于初始战场的存储和战斗行为的存储。

```
public class Init{ //保存的初始化的战场
    private int[][]map = new int[10][20];
    public Init(int [][]init){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 20; j++){
                map[i][j] = init[i][j];
            }
        }
    }
    public int[][] getMap(){
        return this.map;
    }
}
```

`Init`类通过一个int[][] 数组存储初始地图的信息，生物的编号取决于其线程编号，由controller包中的Battle类提供，在此不多提。

```
public class Action{ //保存战斗动作
    private int type;//0表示移动，1表示攻击，2表示技能
    private int[] start;//攻击者
    private int[] end;//被攻击者
    public Action(int type, int[] start, int[] end){
        this.type = type; //是攻击还是移动
        this.start = start;
        this.end = end;
    }
    public int getType()
    {
        return this.type;
    }
    public int getStartX(){
        return start[0];
    }
    public int getStartY(){
        return start[1];
    }
    public int getEndX() {
        return end[0];
    }
    public int getEndY(){
        return end[1];
    }
}
```

Action类分别存储移动，攻击和技能三种动作，对于移动记录起始点和结束点，对于攻击记录攻击者的位置和被攻击者的位置，技能记录释放者的位置。在回放中通过重现战局中的所有动作实现战斗的回放。

### thread包

thread包中主要有两个类，分别是负责控制生物运行的CreatureThread类以及控制游戏地图刷新的UpdateThread类。这两个类都继承自Thread类，并复写了run()方法，在run()方法中实现对线程的控制。

CreatureThread类通过传入生物，游戏地图以及画布，让生物重复移动，攻击技能的循环，直到生物死亡，在延迟一段时间后结束进程。

```
public  synchronized void run() {//每个生物再活着时始终进行移动，攻击，使用技能的循环直到死亡
        while(true){
            if(this.creature.isAlive()){
                try {
                    this.move();
                    Thread.sleep(500);
                    this.attack();
                    Thread.sleep(500);
                    this.skill();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
            else {
                if (this.living) { //结束延迟5s后死亡
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.living = false;
                    return; //结束线程
                }
            }
        }
    }
```

UpdateThread类实现对整个游戏全局的控制，在玩家点击开始游戏或读取历史后，该线程开始运行并开始播放BGM。当选择开始游戏时：该线程首先激活所有CreatureThread线程，同时时刻检查游戏是否结束并实时保存游戏中所有生物的动作，在游戏结束结束时公布游戏结果并停止BGM。当选择回放时，该线程首先读取一个初始地图并显示出来，之后根据文件中保存的动作一步一步复现战斗的过程，并在战斗结束时显示游戏结果并停止BGM。在run()

```
public void run() {
        //播放BGM
        Media media = new Media(this.getClass().getClassLoader().getResource(new String("music/battle.mp3")).toString());
        MediaPlayer mp = new MediaPlayer(media);
        mp.setAutoPlay(true);
        mp.setCycleCount(100); //一百次
        mp.play();
        if (!this.reloaded){ //战斗
            this.battle.startFight();//开始战斗，让葫芦娃动起来了
            while (true){
                this.check(); //进行检查判断是否是应该结束
                if (!this.finished){
                    this.showBattleMap(); //刷新
                    this.save(); //保存
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    this.showBattleMap();
                    this.save(); //保存
                    this.canvas.getGraphicsContext2D().drawImage(this.winImage, 100, 50);
                    System.out.println("游戏结束!");
                    this.battle.closeAll();
                    mp.stop();
                    try{
                        if(this.winner)
                            this.writer.write("calabashBrothers win");
                        else
                            this.writer.write("monsters win");
                        this.writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
        }
        else{//回放
            this.map.setStart();
            BufferedReader bf = new BufferedReader(this.reader); //
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.clear();
            while (true){
                String temp = "";
                try{
                    if((temp = bf.readLine()) == null)
                        break;
                }catch(IOException e) {
                    e.printStackTrace();
                }
                arrayList.add(temp);
            }
            //开始处理
            lineNum = 0;
            readNum = arrayList.size();//
            int[][] pos_start = new int[10][20];
            while (lineNum < 10){
                String[] t = arrayList.get(lineNum).split(" ");
                for (int j = 0; j < 20; j++){
                    pos_start[lineNum][j] = Integer.parseInt(t[j]); //
                    System.out.print(pos_start[lineNum][j]);
                }
                System.out.println();
                lineNum++;
            }
            this.battle.initMap(pos_start);
            this.showBattleMap();
            while (true){
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
                this.review(arrayList);
                if (lineNum >= readNum){
                    System.out.println("回放结束");
                    this.canvas.getGraphicsContext2D().drawImage(this.winImage, 100, 50);
                    mp.stop();
                    break;
                }
            }
        }
    }
```



### controller包

controller包中存储了有关游戏地图以及游戏进程控制的类。

Position类抽象了游戏地图中一个地图格的概念，同时提供添加生物，从某个地图格中移动生物到该地图格，与某个地图格交换生物等接口。同时为了避免类之间交叉引用，在Creature类中通过int数组的形式表达坐标，避免了Creature类和Position类之间的互相引用。

MyMap类聚合了规定规模Position类的对象，抽象出游戏地图的概念，MyMap类对外提供了初始化地图，添加生物到指定位置，移动生物到指定位置，正反阵营是否存活等必要接口，并通过调用Position类的接口实现功能。

Formation类中提供了正反阵营不同阵型在游戏地图中的初始化位置信息，对外提供改变阵型时的初始化阵型接口。同时添加了FormationName枚举类型，以提供通过阵型名称获取阵型信息的功能。

Battle类抽象化了游戏一场战斗的概念，在Battle类中聚合了必要的Creature类或其子类的对象，一个游戏地图MyMap类的对象，每个生物对应的CreatureThread类对象以及提供阵法信息的Formation类对象。在创建对象使（即构造函数中），Battle类为各对象申请内存并初始化各CreatureThread线程。Battle类对外提供初始化生物位置信息，初始化游戏地图，控制游戏开始，改变某阵营阵型等接口以供Controller类使用。

Controller类实现了后端代码与外端GUI界面的连接，通过注释@FXML将后端的对象与前端的对应控件连接起来，同时通过消息机制控制前端按钮按下后游戏的进程。

## 面向对象思想

### 封装

封装是指利用抽象数据类型将数据和基于数据的操作封装在一起，使其构成一个不可分割的独立个体，数据被保护在抽象数据类型的内部，尽可能地隐藏内部的细节，只保留一些对外接口使之与外部发生联系。

在我的代码中很好地实现了封装的思想，每个类通过private关键字将自己的数据封装在类的内部，只能通过提供的public方法进行访问或修改。

### 继承

继承是使用已存在的类的定义建立新类的技术，新类的定义可以增加新的数据或新的功能，也可以使用父类提供的非private的功能。

在我的代码中，继承的思想主要体现在Creature类及其子类的建立，CalabashBrother类、Grandfather类、Wannabe类、ScorpionEssence类和SnakeEssence类均通过extends关键字继承了Creature类，并能够使用Creature类提供的非private属性和方法。

### 多态

多态是指程序中定义的引用变量所指向的具体类型和通过该引用变量发出的方法调用在编程时并不确定，而是在程序运行期间才确定，即一个引用变量到底会指向哪个类的实例对象，该引用变量发出的方法调用到底是哪个类中实现的方法，必须在有程序运行期间才能决定。

在我的代码中，多态思想主体现在Creature类及其子类使用技能skill()方法的动态绑定上，由于每种生物的技能不同，因此每个子类会复写skill()方法，当通过某个引用变量调用skill()方法时，会根据具体的实例化对象调用对应的技能方法。

## 设计原则

### SRP单一职责原则

一个类应该只有一个引起该类变化的原因。在我的代码中，尽量遵循该原则，每个类只负责单一的任务，如每个Creature只负责自己的相关操作。

### OCP开放封闭原则

一个软件实体模块应对扩展开发，对修改关闭。即软件实体应尽量在不修改原有代码的情况下扩展。这一原则同样体现在Creature类及其子类的实现中，技能的代码可以通过继承Creature类后复写，而不允许修改。

### LSP Liskov替换原则

子类可以替换父类并出现在父类能够出现的任何地方。在我的代码中，实现了这一原则，父类Creature出现的地方，其任意子类均可以出现在相同的地方。

### CARP 合成/聚合复用原则

使用对象组合来达到复用的目的。该原则就是在一个新的对象里面使用一些已有的对象，使之成为新对象的一部分，新的对象通过向这些对象的委派达到复用已有功能的目的。在我的代码中，MyMap类聚合了Position类、Battle聚合了Formation类和MyMap类等，实现了这一原则。

## Java机制

### 异常处理

在我的代码的很多地方使用的Java的异常处理机制，通过异常处理机制检测可能出现的错误。

### 集合类型

在记录的存储与读取中，使用了Java的集合类型ArrayList。

### 注解

使用@Override实现方法的复写。使用@FXML实现对象与控件的关联

### 输入输出

使用了Java提供的FileReader和FileWriter类实现文件的读写。

### 多线程

通过继承Thread并复写run()方法实现线程。同时在必要方法或代码块中使用了synchronized关键字对对象上锁，以保证多线程的安全。

### 单元测试

通过Junit对生物的攻击行为和技能行为进行单元测试，以达到期望的目的。

## 结语

通过本学期的Java课程让我了解了很多Java语言的特性，虽然感觉自己的大作业和课堂上展示的同学的大作业还有很大差距，但已经是我所能做的最好了。感谢曹春老师和余萍老师的教导，让我受益匪浅。







