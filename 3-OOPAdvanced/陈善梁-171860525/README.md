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

![UML](https://github.com/mqchenliang/java-2019-homeworks/blob/master/3-OOPAdvanced/%E9%99%88%E5%96%84%E6%A2%81-171860525/Uml.png)
