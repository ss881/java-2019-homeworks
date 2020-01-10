<!-- TOC -->

- [关于代码以及操作方式的说明](#关于代码以及操作方式的说明)
    - [一、题目分析](#一题目分析)
    - [二、代码分析](#二代码分析)
        - [(一)、基础架构设计思路](#一基础架构设计思路)
            - [1、Action接口](#1action接口)
            - [2、Creature类](#2creature类)
            - [3、SpaceForBattle类](#3spaceforbattle类)
            - [4、StandLaw类](#4standlaw类)
            - [5、BrotherStandl类](#5brotherstandl类)
            - [6、Grandpa类](#6grandpa类)
            - [7、MonsterSaltfish类](#7monstersaltfish类)
            - [8、MonsterScorpion类](#8monsterscorpion类)
            - [9、MonsterSnake类](#9monstersnake类)
        - [(二)、多线程设计思路](#二多线程设计思路)
            - [1、Mythread_view类](#1mythread_view类)
            - [2、Mythread_creature类](#2mythread_creature类)
        - [(三)、图形界面部分](#三图形界面部分)
            - [1、Main中的start方法](#1main中的start方法)
            - [2、MyFiles类](#2myfiles类)
            - [3、Replay类](#3replay类)
    - [三、展示结果以及其他的说明](#三展示结果以及其他的说明)
    - [四、相关设计原则和应用机制](#四相关设计原则和应用机制)
        - [1、设计原则](#1设计原则)
        - [2、应用机制](#2应用机制)
    - [附录：用plantuml绘制的类图](#附录用plantuml绘制的类图)

<!-- /TOC -->

# 关于代码以及操作方式的说明
使用的版本为java1.8
## 一、题目分析
在我看来，本次作业主要可以分成如下的三个模块，即基础架构，多线程战斗以及图形界面展示这三个模块，下面针对代码以及操作方式进行解析。

## 二、代码分析 

### (一)、基础架构设计思路
由于利用面向对象的设计方式进行编程，因此在设计中主要关注的问题是每个对象以及对象的属性以及对象行为，在本体中，主要对象为葫芦娃、老爷爷、蝎子精、蛇精、杂兵以及提供的战场(N*N的二维空间）。由于交战双方都是生物，因此创建了一个基类，交战双方均继承此基类。同时给出了接口Action,其中包括了死亡，战斗，嘲讽，听从指令等一系列所有生物都会进行的操作，从而实现多态。除此之外，由于给出了8种阵法，并且理论上交战双方都可以使用这些阵法，因此额外构造了一个兵书类。接下来具体分析每个类的实现细节。
#### 1、Action接口
Action接口中申明了死亡，战斗，嘲讽，听从指令这四个生物都会进行的行为，并且由Creature类实现相关方法

#### 2、Creature类
对于每个智慧生物而言，具有如下属性：  
自身身份、所处的位置、攻击力、血量、移动速度  
实现了Action接口中的方法  
除此之外能够进行如下行为：  
跑向某处、汇报自身位置、以及寻找敌人位置并发起战斗
由于出生方式对于每个生物而言各不相同，因此在设计是将creature类设计为虚基类，出生设置为抽象函数  
为了加强代码的可读性，使用枚举类型Identity作为自身身份，在Identity中有 Grandpa, BrotherStandl, MonsterSnake, MonsterScorpion, MonsterSaltFish,Unkown这几种，与此对应的还有几种死亡的状态。
#### 3、SpaceForBattle类
该类表示存储的空间，因此在该类中存在用于表示边长的参数以及N*N的int型数组用于存储  
为了模拟现实，在该类中提供了如下的方法：  
记录某个对象来到了某处、记录某个对象改变了自身的站位、从空中俯瞰整体的排兵布阵，返回自身的边长，返回特定地点是否存在对象，确认某一地点对象的身份，设置某一地点的对象（该空间可以是真实存在的，也可以是兵书中的某一页），清空整个空间（模拟双方撤退的场景）除此之外，为方便实现还提供了ID与int之间的映射关系
#### 4、StandLaw类
该类即兵书类，使用者使用兵书之时只需要知道作战人数、作战地形大小、攻击方向、阵型名称、主将所在的位置便可以查找到相应的阵型。因此在兵书这一类中有如下的成员变量：  
作战空间（书页）、作战人数、作战地形大小、攻击方向、主将所在的位置  
为了加强代码的可读性，使用枚举类型Direction作为攻击方向  
提供了如下方法：  
长蛇阵、冲轭阵、雁行阵、鹤翼阵、鱼鳞阵、箭矢阵、方形阵、偃月阵。
除此之外为了根据攻击方向进行转换提供了3个private类型的函数，同时为了表示方便在兵法类中定义了Coordinate类用于较为方便的刻画阵型。  
当然有些阵型无法在某些情况下实现，比如箭矢阵和偃月阵在人数过少的情况下无法构成、对该种情况的模拟是函数返回false，且在书页中不显示任何阵型  
为了方便使用者使用：设计了枚举类型StandType，其中有SnakeShape,WingsShape,GooseShape,YokeShape,FishShape,SquareShape,ArrowShape,MoonShape等8种阵型
#### 5、BrotherStandl类
继承Creature类。在BrotherStandl类即葫芦娃类中除了ID之外，葫芦娃额外具有排行以及颜色两个属性  
葫芦娃类中提供的方法主要有：  
听从老爷爷或葫芦娃的指令进行位置移动、与某个葫芦娃互换位置、进行挑衅嘲讽、感应某个葫芦娃的位置、汇报自身颜色、排行  
同时，为增加战斗趣味，当Grandpa战死后BrotherStandl的攻击力会大幅增加。
#### 6、Grandpa类
继承Creature类。对于Granpa而言，除了自身Id之外，其还拥有一株葫芦、一本葫芦兵书、在作战时：他需要确定人数、主将位置、进攻方向、得到作战空间的大小，因此在类中具有这些成员变量。  
对于Grandpa而言，提供如下方法用于对现实的模拟：  
种葫芦、学兵书、测量作战区域、决定主将位置、决定进攻方向、选择阵型、给葫芦娃下达前往某处的指令、命令两个葫芦娃交换位置、拒绝听从任何其他指令、进行嘲讽挑衅、根据葫芦兵法排兵布阵、挑选安全位置、为战局加油鼓劲。  
特别说明：在下达命令这个方法中有两个部分，即葫芦娃听从了指令以及观察到葫芦娃执行了这个指令（即调用SpaceForBattle类中的函数）
同时，为增加战斗趣味，当Grandpa战死后BrotherStandl的攻击力会大幅增加。
#### 7、MonsterSaltfish类
继承Creature类。在MonsterSaltFish类即杂兵类中只有ID这个属性  
杂兵类中提供的方法主要有：  
听从蝎子精的指令进行位置移动、与蝎子精互换位置、进行挑衅嘲讽
同时，为增加战斗趣味，当MonsterScorpion战死后MonsterSaltFish的攻击力会大幅减少。
#### 8、MonsterScorpion类
继承Creature类。对于MonsterScorpion而言，除了自身Id之外，其还拥有召唤杂兵的能力、一本残缺的妖怪兵书（其中没有长蛇阵）、在作战时：他需要确定人数、进攻方向、得到作战空间的大小（由于自身就是主将不需要额外确认主将位置），因此在类中具有这些成员变量。  
对于MonsterScorpion而言，提供如下方法用于对现实的模拟：  
召唤杂兵、学兵书、感知作战区域大小、决定进攻方向、选择阵型、给杂兵下达前往某处的指令、拒绝听从任何其他指令、进行嘲讽挑衅、挑选自身位置、根据妖怪兵法排兵布阵、改变自身阵型。  
特别说明：在下达命令这个方法中有两个部分，即杂兵听从了指令以及观察到杂兵执行了这个指令（即调用SpaceForBattle类中的函数）
同时，为增加战斗趣味，当MonsterScorpion战死后MonsterSaltFish的攻击力会大幅减少。

#### 9、MonsterSnake类
继承Creature类。在MonsterSnake类即蛇精类中只有ID这个属性  
蛇精类中提供的方法主要有：  
不听从任何指令、进行挑衅嘲讽、挑选安全位置、在变阵时暂时离开、为战局加油鼓劲。

### (二)、多线程设计思路
#### 1、Mythread_view类
该类用于各生物线程的同步，同时作为与图形界面交互的接口与主线程进行通信，存储着space以及生物列表  
为了保证多线程的一致性，使用了cyclicbarrier类，为了与图形界面进行交互，使用了simpleIntegerProperty类用于对主线程发出信号
```java
 public void run()
    {

        try{

           Main.run_flag.set(1);
            times=1;
            MyFiles.load(tmp_space,Main.size_of_current_space,Main.size_of_current_space);
            sleep(SpaceForBattle.time_gap);
            while(!view_space.isEnd())
        {
            cyclicBarrier.await();
            times++;
            for(int i=0;i<Main.size_of_current_space;++i)
                for(int j=0;j<Main.size_of_current_space;++j)
                    tmp_space[i][j]=view_space.read_space(i,j);
            MyFiles.load(tmp_space,Main.size_of_current_space,Main.size_of_current_space);
            Platform.runLater(() -> {
                Main.simpleIntegerProperty.set(times);
            });
            sleep(SpaceForBattle.time_gap);
            cyclicBarrier.reset();
            if(view_space.isEnd())
                flags[0]=-1;
            for(int i=0;i<current_creature_num;++i) {
                if(creature_list[i].state)
                Creature.find_direction(creature_list, creature_list[i], current_creature_num, view_space);
            }
            flags[1]++;

        }
            Platform.runLater(() -> {
                Main.simpleIntegerProperty.set(times+1);
            });

          //  System.out.println("finish");
        }catch(Exception e)
    {
        e.printStackTrace();
    }
        finally {
            Main.run_flag.set(0);
            flags[0]=-1;
            MyFiles.close();
        }

    }
```
#### 2、Mythread_creature类
该类作为生物线程类，其包括了space空间以及Creature对象，其行为顺序是  
判断自身是否活着，如果活着向目标方向发起移动，移动之后如果遇到敌人进行战斗，等待Mythread_view类的唤醒  
为了保证一致性，利用了cyclicbarrier类以及自旋锁，并且只有获得某个位置的锁(详见Creature的find_enemy方法)才能移动到该位置，同时进行战斗前需要获得一个共有的锁，从而保证战斗的唯一性
```java
 @Override
    public void run()
    {
        try {
        int times=0;
        int tmp=0;
        while(flag[0]!=-1)
        {
            if(creature.current_hp<=0)
                creature.dead();
            if(creature.state) {
                times++;
                if (times == creature.current_speed) {
                    times = 0;
                    creature.find_enemy(space, mark);
                }
                synchronized (b)
                {
                    creature.do_battles(space,list,Mythread_viewer.current_creature_num);
                }
            }
            cyclicBarrier.await();
            sleep(SpaceForBattle.time_gap);
            while(flag[1]==tmp)
            {
                sleep(100);
            }
            tmp++;
        }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
```
### (三)、图形界面部分
#### 1、Main中的start方法
除了初始化之外需要处理三种额外事件
1、键盘输入事件：  
当战斗未发生或已结束时，点击图片之后按下space即开始战斗； 
当战斗未发生或已结束时，点击图片之后按下大写L即可读取文件;(默认的回放文件在replay文件夹中)  
2、鼠标点击事件：  
进行操作前需点击图片  
3、simpleIntegerProperty类变化事件  
用于接收相关的刷新请求，重新绘制图片  

#### 2、MyFiles类
提供了输入输出的方法，用以记录读取文档
#### 3、Replay类
从输入文件中利用MyFiles方法读取文档，然后修改simpleIntegerProperty，从而在屏幕上进行展示

## 三、展示结果以及其他的说明
主界面：  
<img src="background.jpg">  
点击图片并按下空格  
<img src="oneresult.jpg">   
点击图片并按下大写L,在replay中选择一个txt文件 
<img src="open.jpg">    
<img src="replayresult.jpg">   
说明：
由于保留尸体，且尸体占位，这也就意味着传统的寻路方式即最短路径有限的方式失效，因此各单位速度相同，采用了如下的寻路逻辑：  
1、1/10的概率直接试图向西走(该方式避免无限镜像位移)  
2、寻找最近的敌方目标  
3、1/2的概率先向南北方向移动，1/2的概率先向东西方向移动  
移动逻辑：
1、试图向目标方向移动，如果出现单位，则越过该目标继续向目标方向移动；  
2、如果移动至边界，则向离自身最近的空格移动  

战斗方式： 
A和B进行战斗，A的战斗力为a,B的战斗力为b
使用random r(0< r < a+b)   
如果r < a A对B造成数值为a的血量扣除  
如果r > a B对A造成数值为b的血量扣除  
重复上述操作直至有一方死亡  
(A,B攻击力不是一成不变的，受其他相关单位的生存情况影响)  

## 四、相关设计原则和应用机制
### 1、设计原则
LSP设计原则    
使用面向对象编程的好处在于通过对于问题场景的模拟，将问题拆分成多个对象，这使得编程十分灵活，结构清晰，便于维护。同时编程中很多类可以进行复用，从而提高了编程效率。  
DIP设计原则    
没有从具体类中派生的类  
ISP设计原则
在接口中只有有必要的方法，同时对于内部的修改不会影响外部调用  
LKP设计原则  
各个类之间实现了封装，例如无法对Space直接访问修改，需要调用相关方法。
### 2、应用机制
封装:  
根据对象进行了封装  
继承：  
生物均继承了Creature基类
多态：  
实现了Action接口  
其他机制：
部分使用了异常处理、集合类型、泛型、注解、输入输出等机制。 
由于寻路具有随机性，为此编写了单元测试用例，(在test中),测试了十次的寻路战斗情况。



*对于代码的说明以及其对现实的抽象至此结束，具体实现细节见相关代码，谢谢！*

## 附录：用plantuml绘制的类图
<img src="relationship between class.png">


