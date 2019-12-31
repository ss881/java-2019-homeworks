葫芦娃大战妖精

## 游戏展示

由于视频占容量过大，视频文件存储在resource/game压缩文件下，请打开自行查看。

## 游戏使用手册
* 开始战斗按钮： 点击后开始游戏；
* 重放按钮： 点击后选择需要重放的文件，重放相关记录；
* 保存文件按钮：点击后选择存放的文件名，将游戏记录保存至该文件中。
## 游戏实现
### 1.类图

![](resource\class.png)

* battle：存放与战争相关的元素
  * bullet：子弹，每个子弹是一个线程，有三种状态：飞行，击中，结束，击中敌人会导致敌人的血量下降；
  * Cell：大地的组成单位，地面由很多的地板组成，生物可以站立在上面；
  * Formation：负责生成阵型，游戏没有实现选择阵型的功能，故只是固定地葫芦娃使用长蛇阵，妖精使用方円阵型；
  * Ground：大地，拥有大地上运行的生物和飞行的子弹的全部信息。
  
* creature：与战斗相关的所有生物
  * Creature：所有生物的父类，实现Runnable接口，运行时有两个线程，负责移动和发射子弹；
  * Good：Creature的子类，表示葫芦娃阵营的人，包括葫芦娃和爷爷；
  * Bad：Creature的子类，表示蛇精阵营的人，包括蛇精、蝎子和小喽啰；
  * Grandpa、Snake、Scorpion、Lackey: 分别表示爷爷，蛇精，蝎子和小喽啰；
  * CalabashBros：葫芦兄弟的抽象类
  * First~Seventh: 葫芦兄弟从老大到老七的类，继承自CalabashBros
* team：各个类型的生物组成队伍
  * Team: 所有队伍的子类，实现Runnable接口，每个Team表示一个线程，Run负责启动队伍中每个队员的线程；
  * GoodTeam: 继承Team，表示葫芦娃队；
  * BadTeam：继承Team，表示妖精队。
* config：配置类
  
  * Config: 负责管理每个生物、子弹图片的大小及整个画布的大小设置。
* controllers: 管理整个界面
  
  * Controller: 负责界面的初始化，各个按钮事件的处理。
* Main
  
  负责Controller对象的加载
### 2.游戏逻辑
#### 2.1多线程
**游戏中的线程**

每个生物有两个线程：移动线程和发射子弹线程，生物还活着时，会在一定时间间隔下移动和发射子弹。

每个子弹有一个线程：飞行线程，飞行结束（击中敌人或离开画布）后，线程结束。

刷新界面的功能是一个线程。

**线程间的数据访问**

鉴于每个线程都可能会改变同一个对象的状态，故对所有线程中访问相同数据的部分加上同一个锁，为了游戏体验，只对存在读写冲突的部分上锁。也就是synchronized使用在代码段中，而不是整个方法上。

#### 2.2 I/O
使用对象的序列化方法，在每一次刷新画布时，记录当前的生物、子弹的位置。

由于文件I/O较耗费时间，故文件I/O操作不在刷新画布时实现，而是将所有的状态存入List中，在所有刷新界面操作结束后在一次性写入文件，或在重放前先将所有的状态读入，再开始刷新界面。
### 课程内容相关
#### 面向对象思想
* 封装：根据游戏中的概念将生物体、子弹等封装为一个类；
* 继承：生物体之间的继承关系；
* 多态：每个生物体的攻击线程、图片设置等方法使用多态。
#### 异常处理
对于读写异常、中断异常等，均使用try-catch语句捕捉异常并处理。
如
```
try {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(list);
        out.close();
    }catch (IOException e){
        System.out.println("无该文件");
    }
```
#### 泛型和集合类型
对于每个队伍中的对员，使用ArrayList\<Good\>或ArrayList\<Bad\>来存储。
#### 注解
对某些强制类型转换，使用`SuppressWarnings("unchecked")`，在Test代码的编写过程中，使用`@Test`注解。
#### 输入输出
针对保存和重放功能，使用对象的序列化方法，在每次刷新界面时，序列化葫芦娃和妖精队伍以及子弹的列表，游戏结束后将信息存入文件中，重放时按序读入并重新刷新界面。
```
    public void pressSaveButton(ActionEvent event){
        if(list==null){
            System.out.println("没有可保存的游戏记录，请先开始游戏");
        }else{
            FileChooser chooser = new FileChooser();
            Stage stage = new Stage();
            File file = chooser.showSaveDialog(stage);
            if(file!=null){
                try {
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                    out.writeObject(list);
                    out.close();
                }catch (IOException e){
                    System.out.println("无该文件");
                }
            }
        }
    }
```
#### 面向对象设计原则
* SRP 单一职责原则
  
    Creature类中寻找敌人是由Ground类实现，与Creature无关，每个生物只有通过Ground类才能知道与其它生物相关的全局信息。
* OCP 开放封闭原则

    Good，Bad类重写Creature中shootThread方法，根据自身特点修改发射线程。后期如果要让游戏更加精彩，可以让Snake, Scorpion,First....重写shootThread方法，让生物攻击的方式更多样化。
* LSP LISKOV替换法则
  
    所有从Creature类继承的子类Good,Bad, 都可以替换Creature，所有从Good或Bad继承的子类也可以替换Good或Bad。
* ISP 接口隔离原则
  
    在葫芦娃游戏的代码设计中，没有出现一个类拥有其功能所不需要的方法。
* CARP 合成/聚合复用原则
  
    GoodTeam类由葫芦娃和爷爷组成;

    BadTeam类由蛇精、蝎子和小喽啰组成；

    Ground类由地板和在上面飞行的子弹组成。
#### 设计模式
* 组合模式：界面由几个Button和一个Canvas组成；
* 观察者模式： Button的点击等使用观察者模式响应事件；
* 模板方法模式：Main类继承Application,使用模板方法模式；
* ......
