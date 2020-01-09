# Java期末项目 葫芦兄弟战群妖
171860660 叶新宇

## 游戏背景设计
### 单位设计
<font size="1">
<table>
        <tr>
            <th>角色</th>
            <th>特殊技能</th>
            <th>最大生命</th>
            <th>攻击</th>
            <th>防御</th>
            <th>远程攻击</th>
        </tr>
        <tr>
            <th>大娃</th>
            <th>大娃力大无穷，攻击力高，血量稍高，但是防御比较低。</th>
            <th>500</th>
            <th>80</th>
            <th>10</th>
            <th>70</th>
        </tr>
        <tr>
            <th>二娃</th>
            <th>二娃攻防均衡，属性全面。</th>
            <th>400</th>
            <th>50</th>
            <th>40</th>
            <th>70</th>
        </tr>
        <tr>
            <th>三娃</th>
            <th>三娃钢筋铁骨，防御极高，血量稍高，但是攻击比较低，一般的怪物只能对三娃造成很低的伤害。</th>
            <th>500</th>
            <th>30</th>
            <th>60</th>
            <th>70</th>
        </tr>
        <tr>
            <th>四娃</th>
            <th>四娃有火系远攻，远程攻击力高，发射远程攻击的速度是别的单位的两倍。</th>
            <th>400</th>
            <th>40</th>
            <th>40</th>
            <th>120</th>
        </tr>
        <tr>
            <th>五娃</th>
            <th>五娃有水系远攻，远程攻击力高，一次可以释放三道水弹。</th>
            <th>400</th>
            <th>40</th>
            <th>40</th>
            <th>120</th>
        </tr>
        <tr>
            <th>六娃</th>
            <th>六娃会隐身术，无视敌方的远程攻击。</th>
            <th>400</th>
            <th>40</th>
            <th>40</th>
            <th>70</th>
        </tr>
        <tr>
            <th>七娃</th>
            <th>七娃有一法宝葫芦，攻击力极高，但是防御值为0。</th>
            <th>400</th>
            <th>100</th>
            <th>0</th>
            <th>70</th>
        </tr>
                <tr>
            <th>爷爷</th>
            <th>爷爷可以给周围的葫芦娃提供治疗增益和攻防增益，但是爷爷的战斗属性很低，和喽啰单挑都无法取胜。</th>
            <th>500</th>
            <th>20</th>
            <th>20</th>
            <th>0（不会释放远程攻击）</th>
        </tr>
        <tr>
            <th>喽啰（蜈蚣怪）</th>
            <th>战斗力平庸的妖怪。</th>
            <th>300</th>
            <th>30</th>
            <th>30</th>
            <th>70</th>
        </tr>
        <tr>
            <th>蛇精</th>
            <th>攻防血全面的妖怪，可以给周围的妖怪提供治疗增益。</th>
            <th>500</th>
            <th>50</th>
            <th>50</th>
            <th>0（不会释放远程攻击）</th>
        </tr>
        <tr>
            <th>蝎子精</th>
            <th>攻防血俱高的强大妖怪，释放攻击力高的远程尖刺。</th>
            <th>700</th>
            <th>70</th>
            <th>40</th>
            <th>120</th>
        </tr>
    </table>
</font>

### 战场设计
战场为一个矩形二维空间，有16×9个小方块。每个方块上只能站立一个单位/墓碑。葫芦娃军初始占据战场的左边，向右边进军；妖怪军初始占据战场的右边，向左边进军。子弹也只能在这个二维空间内运动，超出边界的子弹将会消失。

### 作战设计
战斗准备时，可以给两边的阵营切换阵型。

每个单位每1秒钟会移动一次。除四娃每1秒钟射击一次外，其他可以射击的单位每2秒钟射击一次。

当两个敌对阵营的单位试图进入同一个方块时，会立刻发生生死决斗，两个单位互相近战攻击至有一个单位阵亡为止。

每个死亡的单位会在原地留下墓碑，墓碑会阻碍生物的行动。经过一段时间墓碑会自动消失。

伤害计算方式：
近战伤害 = 敌方攻击 - 己方防御（小于等于0的视作1点计算）
远程伤害 = 子弹攻击 - 己方防御（六娃免疫远程攻击；未能超过防御的远程攻击无效）

当有一方阵营的生物体全部阵亡时，游戏结束。

## 系统功能说明
界面由3大块组成：作战区、按钮区和战报区。其中只有按钮区可以点击。每个按钮都有对应的快捷键。
![image](https://github.com/Sphinxxx1984/Java_Final_Project/blob/master/GUI.jpg)
### 作战区
是一块画布，占据游戏UI的左上角，负责展示葫芦娃阵营和妖怪阵营的战斗。
### 按钮区
#### 开始战斗（SPACE）
如果战斗处在准备阶段，按下开始战斗按钮或键盘空格键，战斗将会开始。
#### 结束战斗/回放（ESC）
如果战斗（回放）正在进行或是战斗（回放）已经结束，按下结束战斗/结束回放按钮或键盘ESC键，战斗将会结束，返回到准备阶段。
#### 葫芦娃变阵（A）
如果战斗处在准备阶段，按下葫芦娃变阵按钮或键盘A键，葫芦娃阵营将切换阵型。
#### 妖怪变阵（S）
如果战斗处在准备阶段，按下妖怪变阵按钮或键盘S键，妖怪阵营将切换阵型。
#### 加载回放（L）
如果战斗处在准备阶段，按下加载回放按钮或键盘L键，将会弹出一个文件选择框，选择后缀为.replay的回放文件即可回看一场战斗。
### 战报区
是一个文本区，占据游戏UI的底部，以文本的形式展示葫芦娃和妖精的交战情况。

## 设计思路
### 物体Item
Item是游戏的基本单元，能在战场上展示的事物都属于Item。生物类**Creature**和子弹类**Bullet**都是Item的派生类。生物类**Creature**又派生出了不同的生物：葫芦娃**CalabashBoy**，爷爷**Grandfather**，妖怪**Lackey**，死亡生物**DeadCreature**。妖怪类**Lackey**又派生出了BOSS单位蛇精**Snake**和蝎子精**Scorpion**。

![image](https://github.com/Sphinxxx1984/Java_Final_Project/blob/master/item.jpg)
葫芦娃**CalabashBoy**和爷爷**Grandfather**聚合形成了葫芦娃阵营**CalabashCrew**，妖怪**Lackey**、蛇精**Snake**和蝎子精**Scorpion**聚合形成了妖怪阵营**MonsterCrew**。这两个阵营内部都有统计阵营生存数的方法、判断阵营是否全军覆没的方法和变阵的方法。

![image](https://github.com/Sphinxxx1984/Java_Final_Project/blob/master/crew.jpg)
### 行为Behavior
任意两个Item之间的交互，我称之为行为**Behavior**。
一共包括四种行为：增益**Buff**、射击**Shoot**、子弹击中生物**Hit**、两个敌对阵营生物相遇**Meet**。

![image](https://github.com/Sphinxxx1984/Java_Final_Project/blob/master/behavior.jpg)
#### 增益Buff
作为一个Interface。因为爷爷**Grandfather**的**Buff**和蛇精**Snake**的**Buff**有所不同，爷爷可以给葫芦娃提供生命增益和攻防增益，而蛇精只能提供生命增益。故具体的实现放在他们的类内部。体现了简单工厂模式。
#### 射击Shoot
作为一个Interface。因为不同的生物的射击方式有所不同，所以会放在具体的生物类内部实现shoot。这里运用了简单工厂模式：实现不同的shoot方法，就能产生不同的子弹。把子弹塞入到队列中，让战斗控制线程来处理子弹击中生物的事件。
#### 击中Hit
需要传入子弹、被击生物，然后处理产生子弹击中生物的结果。如果生物被击中前已经身亡，那么就会直接取消这次Hit以防生物被杀死两次。如果没有，就要计算子弹击中生物后生物的生命值，把子弹移除，判断生物是否死亡。
这样的实现可以把所有的子弹击中事件塞到一个队列中，让战斗控制线程集中处理子弹击中事件，同时也可以保证先发生的事件先产生效果。
#### 相遇Meet
和子弹击中生物类似，需要传入作战两方的生物，然后处理战斗的结果。如果相遇前已经有生物身亡，那么就会取消这次Meet以防生物被杀死两次。如果没有，就要计算胜者的生命值，留下死者的墓碑，计算相应阵营的生存数。
这样的实现可以把所有的生物相遇事件塞到一个队列中，让战斗控制线程集中处理生物相遇事件，同时也可以保证先发生的事件先产生效果。
### 战场BattleField
战场**BattleField**由区块**Block**聚合而成。
每个区块**Block**同一时刻只能被一个生物**Creature**占据（包括死亡生物**DeadCreature**）。
如果一个生物试图进入一个被敌方阵营占据的区块，两者就会发生战斗相遇**Meet**。

![image](https://github.com/Sphinxxx1984/Java_Final_Project/blob/master/battlefield.jpg)
### 战略Tactic
作为一个Interface。运用了简单工厂模式：实现不同的Tactic，就能提供不同的阵型。

![image](https://github.com/Sphinxxx1984/Java_Final_Project/blob/master/tactic.jpg)
### 属性Property
其中子弹类型**BulletType**、葫芦娃属性**CalabashProperty**、子弹方向**Direction**和身份**Identity**都实现为枚举类型，为其他类提供参数。位置**Position**实现为一个类。
#### 位置Position
为了支持单位按照区块为单位移动，添加了根据位置返回区块编号的方法和按照区块移动生物的方法。
### 游戏模块GameModule
控制器类**Controller**负责UI逻辑的处理，回合类**Round**负责一局战斗的执行，加载回放类**LoadRep**、回放画面类**Replay**和回放单元类**Unit**负责展示战斗回放。
## 实现细节
### 生物Creature
提供了每个生物体都会用到的移动方法、移动至战场中心方法（为了加快游戏速度）和作战方法。
```java
public abstract class Creature extends Item {
    public void moveTo(Position pos);
    public Position moveToCenter();

    protected void fight(Position nextPos) {
        int i = nextPos.getXH();
        int j = nextPos.getYH();
        synchronized (battlefield) {
            if(battlefield[i][j].isEmpty())
                moveTo(nextPos);
            else {
                Creature creature = battlefield[i][j].getHolder();
                if(creature.getId() != id && creature.getId() != Identity.DEAD) {
                    synchronized (meet) {
                        meet.offer(new Meet(this, creature));
                    }
                    DeadCreature dead = new DeadCreature();
                    if(isDead()) {
                        battlefield[getPosition().getXH()][getPosition().getYH()].withdraw();
                        dead.setPosition(getPosition().getX(),getPosition().getY());
                        battlefield[getPosition().getXH()][getPosition().getYH()].join(dead);
                    }
                    else {
                        battlefield[i][j].withdraw();
                        dead.setPosition(nextPos.getX(), nextPos.getY());
                        battlefield[i][j].join(dead);
                    }
                }
            }
        }
    }
```
不同的生物体根据特性的不同实现了不同的Interface：
```java
public class CalabashBoy extends Creature implements Shoot, Runnable{...}
public class Grandfather extends Creature implements Buff, Runnable {...}
public class Lackey extends Creature implements Shoot, Runnable {...}
public class Scorpion extends Lackey implements Shoot, Runnable {...}
public class Snake extends Lackey implements Buff, Runnable {...}
```

### 多线程之间的同步问题
主要采用临界区控制的方法。每当要访问共享资源时（不论读写），总是需要先获取资源上的锁。
```java
synchronized (battlefield) {
    //do some operations
}
```
需要进行临界区控制的地方主要有生物移动、生物作战、子弹运动、子弹击中生物和遍历展示战场状态。

### 战斗过程的控制
需要进行如下的工作：判断战斗是否结束、展示战场画面、控制所有的子弹、控制子弹击中事件、控制生物相遇事件、计算帧数、清理子弹、序列化输出战斗状态、展示游戏结果。
```java
public class Round implements Runnable {
    ...
    private void displayAll() {
        long startTime = 0;
        long endTime = 0;
        int FPS = 0;
        while(isGamming) {
            if(CalabashCrew.allDead()) {
                isGamming = false;
            }
            else if(MonsterCrew.allDead()) {
                isGamming = false;
            }
            try {
                startTime = System.currentTimeMillis();
                gc.drawImage(background,0,0,960,540);
                battlefield.printBattleField(gc);
                battlefield.outputRep(logWriter);

                synchronized (cBullets) {
                    for(Bullet i : cBullets) {
                        if(!i.isOut()) {
                            i.display(gc);
                            logWriter.write(i.getStatus());
                            logWriter.newLine();
                        }
                    }
                }
                synchronized (mBullets) {
                    for(Bullet i : mBullets) {
                        if(!i.isOut()) {
                            i.display(gc);
                            logWriter.write(i.getStatus());
                            logWriter.newLine();
                        }
                    }
                }
                synchronized (hitQueue) {
                    while(!hitQueue.isEmpty()) {
                        Hit hit = hitQueue.poll();
                        hitQueue.remove(0);
                        gc.drawImage(effect, hit.getPos().getX() + 10,hit.getPos().getY() + 10,40,40);
                        logWriter.write("E "+hit.getPos().getX()+" "+(hit.getPos().getY()+15));
                        logWriter.newLine();
                        if(hit.getResult() != "") {
                            textArea.appendText(hit.getResult());
                            logWriter.write("M "+hit.getResult());
                        }
                    }
                }
                synchronized (meetQueue) {
                    while(!meetQueue.isEmpty()) {
                        Meet meet = meetQueue.poll();
                        meetQueue.remove(0);
                        textArea.appendText(meet.getResult());
                        logWriter.write("M "+meet.getResult());
                    }
                }
                gc.setStroke(Color.WHITE);
                gc.strokeText("FPS: "+FPS,0,20);
                logWriter.write("==");
                logWriter.newLine();
                TimeUnit.MILLISECONDS.sleep(50);
                endTime = System.currentTimeMillis();
                FPS = (int)(1000 / (endTime - startTime));  //计算帧数
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                //清理子弹
                synchronized (cBullets) {
                    cBullets.removeIf(new Predicate<Bullet>() {
                        @Override
                        public boolean test(Bullet bullet) {
                            return bullet.isOut();
                        }
                    });
                }

                synchronized (mBullets) {
                    mBullets.removeIf(new Predicate<Bullet>() {
                        @Override
                        public boolean test(Bullet bullet) {
                            return bullet.isOut();
                        }
                    });
                }
            }
        }
        try {
            if(CalabashCrew.allDead()) {
                gc.drawImage(defeat,330,100,300,300);
                logWriter.write("Defeat");
                textArea.appendText("妖怪获胜\n");
                isGamming = false;
            }
            else if(MonsterCrew.allDead()){
                gc.drawImage(victory,330,100,300,300);
                logWriter.write("Victory");
                textArea.appendText("葫芦娃获胜\n");
                isGamming = false;
            }
            else {
                logWriter.write("NoResult");    //未分胜负，战斗被打断
                isGamming = false;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    ...
}
```

### 战斗回放的展示
根据先前战斗的结果，把序列化输出的结果反过来序列化输入，展示到画布上。
```java
public class LoadRep implements Runnable {
    ...
     public void run() {
        for(Replay replay : replayList) {
            replay.display(gc, textArea);
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!isReplaying) {
                break;
            }
        }
        textArea.appendText("回放结束\n");
    }
    ...
}
```

### 图形界面逻辑
```java
public class Controller implements Initializable {
    @FXML  Button startGame;
    @FXML  Button endGame;
    @FXML  Button calabashCrewChangeTactic;
    @FXML  Button monsterCrewChangeTactic;
    @FXML  Button loadRep;
    @FXML  TextArea gameLog;
    @FXML  Canvas gameArea;
    @FXML  javafx.scene.layout.Pane Pane;
    //各个控件
    class KeyBoredHandler implements EventHandler<KeyEvent>;
    class ClickCloseHandler implements EventHandler<WindowEvent>;
    //键盘事件控制、关闭事件控制
    @FXML private void startGameHandler();
    @FXML private void endGameHandler();
    @FXML private void loadRepHandler();
    @FXML private void calabashCrewChangeTacticHandler();
    @FXML private void monsterCrewChangeTacticHandler();
    //各个按钮的事件控制
    ...
}
```

## 单元测试
一共有5个负责进行单元测试的类：**TestBlock、TestCreature、TestHit、TestMeet、TestReplay**。

TestBlock测试了生物体进入/撤出block；

TestCreature测试了生物体移动/向中心移动/死亡判定；

TestHit测试了生物体被子弹击中时扣取生命的数值是否正常；

TestMeet测试了两个敌对阵营的生物体战斗的结果是否正确；

TestReplay测试了无效的回放应当不能被加载。

## 面向对象思想的体现
### 抽象
**我的实现中有两个抽象类：物品Item、生物体Creature。**
*物品Item是作为空间内的基本单元存在的，凡是能在地图上显示的都属于Item。*
*每个生物都可以移动、向战场中心移动、交战，这些方法都在抽象类Creature中实现*。

### 继承
葫芦娃**CalabashBoy**、爷爷**Grandfather**、妖怪**Lackey**和死亡生物**DeadCreature**继承自抽象类生物**Creature**。
蛇精**Snake**和蝎子精**Scorpion**继承自妖怪**Lackey**。

*不同的派生类，其构造函数不同。*
*不同的生物体派生类之间，属性有所不同（例如葫芦娃类具有颜色属性）。*
*不同的生物体派生类之间，实现的接口有所不同（爷爷和蛇精实现了Buff接口，可以给同阵营单位施加增益；葫芦娃、普通妖怪和蝎子精实现了Shoot接口，可以进行远程攻击）。*

### 组合
生物**Creature**包含位置**Position**对象。
区块**Block**包含生物**Creature**对象。
战场**BattleField**包含一个区块**Block**对象的二维数组。
两个阵营**CalabashCrew**、**MonsterCrew**包含对应的生物体对象。

### 多态
在包含生物**Creature**对象的区块**Block**类中，把不同的生物体派生类都当作其基类**Creature**处理。
实际的调用中，会被动态绑定到**Creature**的不同子类上。
```java
public class Block {
    private Creature holder = null;
    private boolean isEmpty = true;

    public void join(Creature creature) {
        isEmpty = false;
        this.holder = creature;
        assert(this.holder != null);
    }

    public void withdraw() {
        isEmpty = true;
        holder = null;
    }

    public Creature getHolder() {
        return holder;
    }

    public Image getImage() {
        if(holder == null)
            return null;
        else
            return holder.getImage();
    }
    ...
}
```

### 简单工厂
增益**Buff**、射击**Shoot**和战略**TacticMaker**实现为接口Interface。不需要知道每个生物体/阵法的不同数值和实现，调用不同类的相应方法就可以产生不同的产品（不同的实现）。

## 心得和收获
1.学会了构建工具maven的使用

2.学会了单元测试的撰写方法

3.学会了应用SOLID原则的面向对象程序设计方式

4.设计一个能按照需要运行的程序很容易，但是设计一个复用性好、可拓展性好、线程安全、健壮的程序很难
