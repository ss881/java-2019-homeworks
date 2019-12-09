<!-- TOC -->

- [关于代码的说明](#关于代码的说明)
    - [一、题目分析](#一题目分析)
    - [二、设计思路](#二设计思路)
        - [1、Creature类](#1creature类)
        - [2、SpaceForBattle类](#2spaceforbattle类)
        - [3、StandLaw类](#3standlaw类)
        - [4、BrotherStandl类](#4brotherstandl类)
        - [5、Grandpa类](#5grandpa类)
        - [6、MonsterSaltfish类](#6monstersaltfish类)
        - [7、MonsterScorpion类](#7monsterscorpion类)
        - [8、MonsterSnake类](#8monstersnake类)
    - [三、反射和泛型机制的使用](#三反射和泛型机制的使用)
    - [四、对现实的模拟（main函数说明）](#四对现实的模拟main函数说明)
    - [五、面向对象概念、机制、设计理念](#五面向对象概念机制设计理念)
    - [附录：用plantuml绘制的类图](#附录用plantuml绘制的类图)

<!-- /TOC -->
## 关于代码的说明
### 一、题目分析
将题目要求转换为常见的形式：给定一个N*N的二维空间，将两种不同属性的元素按照一定的规律放置在空间中，再加入两个元素之后将二维空间输出，之后重复上述过程。
### 二、设计思路
由于利用面向对象的设计方式进行编程，因此在设计中主要关注的问题是每个对象以及对象的属性以及对象行为，在本体中，主要对象为葫芦娃、老爷爷、蝎子精、蛇精、杂兵以及提供的战场(N*N的二维空间）。由于交战双方都是生物，因此创建了一个基类，交战双方均继承此基类。除此之外，由于给出了8种阵法，并且理论上交战双方都可以使用这些阵法，因此额外构造了一个兵书类。接下来具体分析每个类的实现细节。
#### 1、Creature类
对于每个智慧生物而言，具有如下属性：  
自身身份、所处的位置  
能够进行如下行为：  
跑向某处、服从特定指令、与某个对象交换位置、进行挑衅嘲讽、汇报自身位置
由于服从特定指令以及挑衅嘲讽对于每个生物而言各不相同，因此在设计是将creature类设计为虚基类，服从特定指令和进行挑衅嘲讽设置为抽象函数  
为了加强代码的可读性，使用枚举类型Identity作为自身身份，在Identity中有 Grandpa, BrotherStandl, MonsterSnake, MonsterScorpion, MonsterSaltFish,Unkown这几种。
除此之外，在creature类中设置了born函数，从而对Id进行初始化
#### 2、SpaceForBattle类
该类表示存储的空间，因此在该类中存在用于表示边长的参数以及N*N的int型数组用于存储  
为了模拟现实，在该类中提供了如下的方法：  
记录某个对象来到了某处、记录某个对象改变了自身的站位、从空中俯瞰整体的排兵布阵，返回自身的边长，返回特定地点是否存在对象，确认某一地点对象的身份，设置某一地点的对象（该空间可以是真实存在的，也可以是兵书中的某一页），清空整个空间（模拟双方撤退的场景）除此之外，为方便实现还提供了ID与int之间的映射关系
#### 3、StandLaw类
该类即兵书类，使用者使用兵书之时只需要知道作战人数、作战地形大小、攻击方向、阵型名称、主将所在的位置便可以查找到相应的阵型。因此在兵书这一类中有如下的成员变量：  
作战空间（书页）、作战人数、作战地形大小、攻击方向、主将所在的位置  
为了加强代码的可读性，使用枚举类型Direction作为攻击方向  
提供了如下方法：  
长蛇阵、冲轭阵、雁行阵、鹤翼阵、鱼鳞阵、箭矢阵、方形阵、偃月阵。
除此之外为了根据攻击方向进行转换提供了3个private类型的函数，同时为了表示方便在兵法类中定义了Coordinate类用于较为方便的刻画阵型。  
当然有些阵型无法在某些情况下实现，比如箭矢阵和偃月阵在人数过少的情况下无法构成、对该种情况的模拟是函数返回false，且在书页中不显示任何阵型  
为了方便使用者使用：设计了枚举类型StandType，其中有SnakeShape,WingsShape,GooseShape,YokeShape,FishShape,SquareShape,ArrowShape,MoonShape等8种阵型
#### 4、BrotherStandl类
继承Creature类。在BrotherStandl类即葫芦娃类中除了ID之外，葫芦娃额外具有排行以及颜色两个属性  
葫芦娃类中提供的方法主要有：  
听从老爷爷或葫芦娃的指令进行位置移动、与某个葫芦娃互换位置、进行挑衅嘲讽、感应某个葫芦娃的位置、汇报自身颜色、排行
#### 5、Grandpa类
继承Creature类。对于Granpa而言，除了自身Id之外，其还拥有一株葫芦、一本葫芦兵书、在作战时：他需要确定人数、主将位置、进攻方向、得到作战空间的大小，因此在类中具有这些成员变量。  
对于Grandpa而言，提供如下方法用于对现实的模拟：  
种葫芦、学兵书、测量作战区域、决定主将位置、决定进攻方向、选择阵型、给葫芦娃下达前往某处的指令、命令两个葫芦娃交换位置、拒绝听从任何其他指令、进行嘲讽挑衅、根据葫芦兵法排兵布阵、挑选安全位置、为战局加油鼓劲。  
特别说明：在下达命令这个方法中有两个部分，即葫芦娃听从了指令以及观察到葫芦娃执行了这个指令（即调用SpaceForBattle类中的函数）
#### 6、MonsterSaltfish类
继承Creature类。在MonsterSaltFish类即杂兵类中只有ID这个属性  
杂兵类中提供的方法主要有：  
听从蝎子精的指令进行位置移动、与蝎子精互换位置、进行挑衅嘲讽
#### 7、MonsterScorpion类
继承Creature类。同时应用了泛型机制，使用了<Monster extends Creature> 用于设定杂兵的种类。对于MonsterScorpion而言，除了自身Id之外，其还拥有召唤杂兵的能力、一本残缺的妖怪兵书（其中没有长蛇阵）、在作战时：他需要确定人数、进攻方向、得到作战空间的大小（由于自身就是主将不需要额外确认主将位置），因此在类中具有这些成员变量。  
对于MonsterScorpion而言，提供如下方法用于对现实的模拟：  
召唤杂兵、学兵书、感知作战区域大小、决定进攻方向、选择阵型、给杂兵下达前往某处的指令、拒绝听从任何其他指令、进行嘲讽挑衅、挑选自身位置、根据妖怪兵法排兵布阵、改变自身阵型。  
特别说明：在下达命令这个方法中有两个部分，即杂兵听从了指令以及观察到杂兵执行了这个指令（即调用SpaceForBattle类中的函数）
#### 8、MonsterSnake类
继承Creature类。在MonsterSnake类即蛇精类中只有ID这个属性  
蛇精类中提供的方法主要有：  
不听从任何指令、进行挑衅嘲讽、挑选安全位置、在变阵时暂时离开、为战局加油鼓劲。

### 三、反射和泛型机制的使用
在本题中，由于葫芦娃具有特殊的属性(颜色，排行)，因此在**Grandpa**类中很难使用泛型机制，因此选择在**MonsterScorpion**类中使用泛型。在MonsterScorpion类中，其召唤的杂兵**MonsterSalfFish**类与Creature类包含相同的成员变量，其中只实现了Creature类中声明的虚方法，以及自身
Identity不同，因此在MonsterScorpion类中可以较好地使用反射和泛型机制，具体使用如下：  
MonsterScorpion类的声明：
```java
public class MonsterScorpion<Monster extends Creature> extends Creature；  
```
(其中Monster类表示召唤生成的杂兵)  
其中有如下与泛型相关的成员变量：  
```java
private Class<Monster> kind;//使用反射机制创建monsterSaltfishs数组  
Monster []monsterSaltFishs;  
```
增加了MonsterScorpion类的新的构造方法：
```java  
MonsterScorpion(Class<Monster> kind)  
{  
    this.kind=kind;  
    born(Identity.MonsterScorpion);  
}   
```
修改了召唤杂兵方法：  
```java
@SuppressWarnings("unchecked") //这行代码用于消除使用强制类型转换导致的warning
void summon_monstersaltfish()//give birth to monstersaltfish
{
    try{       
    this.monsterSaltFishs=(Monster[])Array.newInstance(kind, the_num_of_monstersaltfish);
    //由于存在着擦除，因此使用newInstance这一反射机制，使用Kind类构造Monster类的数组
    }catch (Exception e){
            e.printStackTrace();
        }
        for(int i=0;i<the_num_of_monstersaltfish;++i)
        {
            try{
                this.monsterSaltFishs[i] = kind.newInstance();
                 //由于存在着擦除，因此使用newInstance这一反射机制，使用Kind类构造Monster类
            }catch (Exception e){
                e.printStackTrace();
            }
        }
}
```
除此之外，还在指令函数中进行了类的确认，从而对现实进行更好的模拟(蝎子精需要确认手下，然后再下指令)
```java
private void make_orders(Monster saltFish)// order saltfish to exchange with oneself
{
    if(saltFish instanceof MonsterSaltFish)//使用反射机制进行Class的判断
    saltFish.follow_orders(get_id(), (Creature)this);
}
```
在main函数中的调用也进行了相关修改:
```java
MonsterScorpion<MonsterSaltFish> monsterScorpion=new MonsterScorpion<>(MonsterSaltFish.class);
```
由于设计原因使得在其他处使用泛型机制有些牵强，例如强行在**Grandpa**类中使用会导致父类必须调用子类方法，违背使用继承机制的初衷。同时在本次实现中将战争环境进行了抽象，N*N的空间中使用不同符号表示不同的身份，这其实是对战场俯视视角的一种模拟，同时也正好模拟了兵书中的一页，因此在空间中使用泛型机制：用具体的**Creature类**去表示每一个位置会使得代码冗余，且不能很好地模拟兵书书页这一场景。因此在**SpaceForBattle**类中不使用泛型机制。

### 四、对现实的模拟（main函数说明）
main函数是以第三方视角根据要求模拟了双方排兵布阵的过程，过程中的每一个步骤对应一个方法调用，过程如下：  
1、有一块战场  
2、蝎子精诞生  
3、蛇精诞生  
4、老爷爷出生  
5、蝎子精召唤杂兵(假设召唤出了15个)(此时的杂兵的类型为Saltfish)  
6、老爷爷种葫芦  
7、蝎子精感知作战区域大小  
8、蝎子精决定自东向西发起进攻  
9、蝎子精学习妖怪兵法  
10、老爷爷测量作战区域大小  
11、老爷爷决定自西向东发起进攻  
12、老爷爷学习葫芦兵法  
13、老爷爷决定主将站在（1，3）这个位置　　注:位置是随意挑选的  
14、老爷爷决定采用长蛇阵  
15、蝎子精决定自己站在（8，6）位置　　注:位置是随意挑选的  
16、蝎子精决定采用偃月阵  
注:阵法可以在除长蛇阵之外任选，不过有可能因地形因素无法排列   
17、老爷爷根据葫芦兵法排兵布阵  
18、蝎子精根据妖怪兵法排兵布阵  
19、老爷爷选择安全位置  
20、蛇精选择安全位置  
21、老爷爷为葫芦娃加油  
22、蛇精为妖怪加油  
23、从空中俯瞰战场得到战况
24、为了方便变阵，射精暂时离开了战场  
25、蝎子精重新决定自己站在（11，2）位置　　注:位置是随意挑选的 
26、蝎子精决定采用冲轭阵  
注:阵法可以在除长蛇阵之外任选，不过有可能因地形因素无法排列   
27、蝎子精根据妖怪兵法改变阵型  
28、蛇精选择安全位置  
29、蛇精为妖怪加油  
30、老爷爷为葫芦娃加油  
31、从空中俯瞰战场得到战况  
32、双方对峙不下、都离开了战场，世界重归宁静  
至此main函数结束

### 五、面向对象概念、机制、设计理念
在编程中，将问题分解成了多个对象，通过构造对象之间的联系，实现不同对象的不同方法，从而模拟问题场景并加以解决。  
本次编程过程中主要利用了类的封装，继承机制，抽象类和抽象方法，以及泛型和反射机制。  
主要遵循了LSP设计原则。  
使用面向对象编程的好处在于通过对于问题场景的模拟，将问题拆分成多个对象，这使得编程十分灵活，结构清晰，便于维护。同时编程中很多类可以进行复用，从而提高了编程效率。  


*对于代码的说明以及其对现实的抽象至此结束，具体实现细节见代码及其注释，谢谢！*

### 附录：用plantuml绘制的类图
<img src="relationship between class.png">


