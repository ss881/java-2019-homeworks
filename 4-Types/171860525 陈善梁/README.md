# Java 第四次作业
## 设计思路
一. 对象
>1. Creature生物类，有表示位置的Position域，实现Swapable和Moveable接口，符合SRP原则，功能通过接口分散。
>2. Leader类，表示能够对Creature对象进行操纵的类，比如老爷爷和蝎子精都继承自Leader类。**Leader类采用了泛型机制** ,定义为
>abstract public class Leader <T extends Creature>extends Creature，表示Leader类也是一种生物，它能对派生自生物类的生物T进行操纵。
>有两个方法：初始化产生生物数组initialize和对生物进行阵法变换changeForm。
>3. Evial类，妖精类，并没有和Creature有什么区别，但是为了更好的体现真实的场景，还是创建了这个类。
>2. Huluwa葫芦娃类，继承自生物类，**实现了Comparable<Huluwa>**，能够和其他葫芦娃进行rank的比较。有名字、颜色、等级rank和在葫芦娃数组中的下标index。
>为了更好地进行模拟，葫芦娃有lookBack方法，观察自己的后一个葫芦娃是谁
>，并且能够用compareTo方法比较两者的先后，用swapWithHuluwa进行交换位置，
>3. GrandPa老爷爷类，**继承自Leader<Huluwa>类**，说明老爷爷能对葫芦娃们进行操纵，有initialize方法，初始化7个葫芦娃，
>并且调用shuffle方法打乱他们的顺序。sortHuluwa函数用于对葫芦娃冒泡排序。
>5. Scorpion蝎子精类，**继承自Leader<Evial>类**，有initialize方法，初始化20个妖精，并且调用一个changToXXX函数
>改变阵型。它的ChangeForm方法调用了七个changeToXXX方法，随机改变阵型。
>6. Snake蛇精类，继承自妖精类。
>7. 抽象的阵营类Team。 **Team类使用了泛型机制**，定义为public abstract class Team<M extends Creature,L extends Leader<M>> ，其中M是
>阵营中的普通成员，派生自生物类，L是阵营中的领袖，派生自Leader<M>，表示能够对阵营中的普通成员进行操纵。
>7. GoodTeam正义阵营，**继承了Team类，将类型参数M设置为Huluwa，L设置为GrandPa**，其中initialize和changeForm分别调用老爷爷的initialize和
>changeForm函数。
>8. BadTeam邪恶阵营，**继承了Team类，将类型参数M设置为Evial，L设置为Scorpion**，还添加了一个域：蛇精。其中initialize和changeForm分别调用蝎子精的initialize和changeForm函数。
>9. Tile地砖类，有字符ch1和ch2。因为再阵型变幻过程中，可能出现一个地砖有两个生物，
>还要有count记录数量。为了表示地砖上的生物改变，更好地体现在输出（绿色显示刚刚发生位置改变的生物），还增加了一个boolean值changed。
>10. BattleField战场类。有一个地砖二维数组map，表示战场。setGoodTeam和setBadTeam分别
>将两个阵营放置到战场上。placeCreature和removeCreature函数用于辅助上面两个setXXXTeam函数。
>draw()函数用于打印战场，在阵型变幻过程中，每个生物的移动都要draw()一次，体现过程。
>main()方法是入口。

二. 机制
> 1. 继承。所有的生物都继承自Creature类，而Creature类有一个统一的Position属性。
> 2. 多态。不同的生物在地图上是用不同字符区分的。因此不同的生物要有不同的getSymbol方法，返回用于表示各自的字符。其中，葫芦娃用H表示，老爷爷用G，妖精为E，蝎子精为X，蛇精为S。
> 3. **泛型**。泛型主要体现在对Leader类、Team类的抽象上。Leader类表示某一种生物的领袖，不同的阵营需要操纵不同普通成员的
>领袖，因此Leader的类型参数为 T extends Creature。对于Team类，不同的阵营的普通成员M和领袖L是不一样的，但是M和L之间有关系，也就是，L应该是M类型的领袖，也即
>L extend Leader<Leader<M>>。泛型还体现在对象实现的接口上，比如说:(1)同类对象之间需要交换位置，那么对于Creature类，就实现了Swapable<Creature>接口，
>将类型参数设置为Creature，表示生物对象之间可以相互交换位置;(2)葫芦娃在排序的时候，可能需要相互比较Rank，于是实现了Comparable<Huluwa>接口，将类型参数设置为Huluwa。
>4. **反射机制**。不同的生物在地图上用不同的字母表示，之前，我是利用派生类重写基类Creature的getSymbol函数，
>然后在绘制地图函数调用getSymbol，得到对应的字母。为了体现反射机制的使用，我不再使用派生类重写机制，而是用了RTTI，对于当前要放置到地图上的Creature，用 instanceof 操作符判断生物是属于哪个类。
>按照 GrandPa、Scorpion、Snake、Evial、Huluwa、Creature的顺序判断，先派生类后基类。 

三. 设计理念
> 1. 降低各个模块之间的耦合度，便于维护拓展。战场类和队伍类是分开的，队伍类负责各自
>阵型的排布，反映在队伍成员的position中。战场类通过这些pisition获取所有人的位置信息，
>可以自己选择作图的方式。目前还是用非GUI的方式体现，以后引入GUI的话，也只要修改战场类的代码，
>而队伍类、老爷爷、蝎子精都不用改变。

![UML](https://github.com/mqchenliang/java-2019-homeworks/blob/master/3-OOPAdvanced/%E9%99%88%E5%96%84%E6%A2%81-171860525/Uml.png)
