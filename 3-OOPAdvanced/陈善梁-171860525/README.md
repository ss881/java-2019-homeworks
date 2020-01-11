# Java 第三次作业
## 设计思路
一. 对象
>1. Creature生物类，有表示位置的Position域。
>2. Huluwa葫芦娃类，继承自生物类，有名字、颜色、等级rank和在葫芦娃数组中的下标index。
>为了更好地进行模拟，葫芦娃有lookBack方法，观察自己的后一个葫芦娃是谁
>，并且能够用compareTo方法比较两者的先后，用swapWithHuluwa进行交换位置，
>3. GrandPa老爷爷类，继承自生物类，有initialize方法，初始化7个葫芦娃，
>并且调用shuffle方法打乱他们的顺序。sortHuluwa函数用于对葫芦娃冒泡排序。
>4. Evial妖精类，继承自生物类。
>5. Scorpion蝎子精类，有initialize方法，初始化20个妖精，并且调用一个changToXXX函数
>改变阵型。有七个changeToXXX方法，改变阵型。
>6. Snake蛇精类，继承自妖精类。
>7. GoodTeam正义阵营，有老爷爷对象和葫芦娃数组。有sortHuluwa函数，让老爷爷
>对葫芦娃排序。
>8. BadTeam邪恶阵营，有蝎子精、蛇精、妖怪数组。有changeToXXX函数让蝎子精指挥变阵。
>9. Tile地砖类，有字符ch1和ch2。因为再阵型变幻过程中，可能出现一个地砖有两个生物，
>还要有count记录数量。为了表示地砖上的生物改变，更好地体现在输出（绿色显示刚刚发生位置改变的生物），还增加了一个boolean值changed。
>10. BattleField战场类。有一个地砖二维数组map，表示战场。setGoodTeam和setBadTeam分别
>将两个阵营放置到战场上。placeEvial和RemoveEvial函数用于辅助上面两个setXXXTeam函数。
>draw()函数用于打印战场，在阵型变幻过程中，每个生物的移动都要draw()一次，体现过程。
>main()方法是入口。

二. 机制
> 1. 继承。所有的生物都继承自Creature类，而Creature类有一个统一的Position属性。
> 2. 多态。不同的生物在地图上是用不同字符区分的。因此不同的生物要有不同的getSymbol方法，返回用于表示各自的字符。其中，葫芦娃用H表示，老爷爷用G，妖精为E，蝎子精为X，蛇精为S。

三. 设计理念
> 1. 降低各个模块之间的耦合度，便于维护拓展。战场类和队伍类是分开的，队伍类负责各自
>阵型的排布，反映在队伍成员的position中。战场类通过这些pisition获取所有人的位置信息，
>可以自己选择作图的方式。目前还是用非GUI的方式体现，以后引入GUI的话，也只要修改战场类的代码，
>而队伍类、老爷爷、蝎子精都不用改变。

## 第三次作业UPDATE
### 根据设计原则修改类的构成
> 1. SRP 原则  
> 把Creature类moveTo,setPosition,swapPosition等职责分散到Moveable,PositionChangeable和
> Swapble三个接口中。  
> 把GranPa类的shuffle和sort职责分散到shuffleable和sortable两个接口中。
>2. OCP 原则  
> 将Creature类设计为抽象类。虽然在本次作业中者并没有产生什么不同的效果。
> 但是如若以后的拓展中，要对Creature派生的生物进行统一的移动、交换动作，就可以
> 以 Creature引用为参数。即使派生处新的类，也不用修改原有代码。
>3. Lod原则  
> 之前版本的代码，所有文件都在一个默认包中，除了private以外的域都可以相互访问、修改，
> 访问权限太高。因此修改项目的结构，分成creatures,interfaces,team三个包，这样不同的包之间的类
> 只能相互访问public域。相应地增加了部分域的set和get函数，修改之前直接访问具有默认或者protected域的相关代码。
> 比如 GoodTeam对于creature类的Position域的访问，之前是直接得到position引用，现在需要通过getPosition和setPosition访问。
### 删除不必要的类,修改类之间的关系
> 1. 之前我定义了两个类:GoodTeam和BadTeam，分别代表两个队伍，队伍中有各自阵营的成员，阵法的变换通过Team提供的
> 函数来实现。但是其实老爷爷和葫芦娃之间、蝎子精和妖怪之间，是没有很强的关联的，没有必要放到一起。因此我去掉了这两个
>Team类，修改了初始化、阵法变换等代码。
 ![UML](https://github.com/mqchenliang/java-2019-homeworks/blob/master/3-OOPAdvanced/%E9%99%88%E5%96%84%E6%A2%81-171860525/Uml.png)
