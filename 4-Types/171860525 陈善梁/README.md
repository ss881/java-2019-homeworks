# Java 第四次作业
## 设计思路
一. 对象
>1. Creature生物类，有表示位置的Position域，实现Swapable和Moveable接口，符合SRP原则，功能通过接口分散。
>3. Evial类，妖精类，并没有和Creature有什么区别，但是为了更好的体现真实的场景，还是创建了这个类。
>2. Huluwa葫芦娃类，继承自生物类，**实现了Comparable<Huluwa>**，能够和其他葫芦娃进行rank的比较。有名字、颜色、等级rank和在葫芦娃数组中的下标index。
>为了更好地进行模拟，葫芦娃有lookBack方法，观察自己的后一个葫芦娃是谁。
>，并且能够用compareTo方法比较两者的先后，用swapWithHuluwa进行交换位置，
>3. GrandPa老爷爷类，继承自Creature类，说明老爷爷能对葫芦娃们进行操纵，有initialize方法，初始化7个葫芦娃，
>并且调用shuffle方法打乱他们的顺序。sortHuluwa函数用于对葫芦娃冒泡排序。
>5. Scorpion蝎子精类，继承自Creature类，有initialize方法，初始化20个妖精，并且使用一个Formation对象来
>设置初始阵型。
>6. Snake蛇精类，继承自妖精类。
>9. Tile地砖类，有字符ch1和ch2。因为再阵型变幻过程中，可能出现一个地砖有两个生物（暂时挤一下），
>还要有count记录数量。为了表示地砖上的生物改变，更好地体现在输出（绿色显示刚刚发生位置改变的生物），还增加了一个boolean值changed。
>10. Formation及其子类。之前我并没有抽象出一个Formation类，阵法的变化都是通过蝎子精的方法实现的。但是这样并不是
>很好，因为阵法应该是独立于生物团体的。阵法需要确定一个领袖，小喽啰的位置要根据领袖的位置确定。因此抽象基类Formation有一个
>changeForm(T leader, ArrayList<T> followers)方法，用于设置领袖和小喽啰的位置。
>11. Factory工厂类。之前的葫芦娃等对象都是我直接new出来的，但是如果对于创建出来的葫芦娃如果有新的需求，则要修改
>所有的new Huluwa()语句，不太合适。而通过葫芦娃工厂，其他地方引用工厂进行创建葫芦娃，可以做到只修改一个地方，其他
>代码都不动，实现了解耦。
>10. BattleField战场类。有一个地砖二维数组map，表示战场。setCreature(Creature)方法根据生物目前和之前的位置修改战场地图信息。
>removeCreature和placeCreatire用于辅助setCreature方法，分别从地图上相应位置删除生物和放置生物。draw()方法用于向
>控制台打印当前战场信息。changeFormRandomly(int n,Scorpion scorpion,ArrayList<Creature>evials)是用于改变邪恶势力的阵型。
>main()方法是程序入口。

二. 机制
> 1. 继承。所有的生物都继承自Creature类，而Creature类有一个统一的Position属性表示当前位置，同时是生物还可以移动和相互交换位置，‘
>因此他们都有moveTo()和swapPosition()方法。
> 2. 多态。主要体现：(1). 工厂，抽象基类Factory T generate()确定了子类的接口，基类Factory可以引用子类对象，比如
>HuluwaFactory和EvialFactory，创建不同的Creature对象。 (2) Formation阵法。抽象基类Formation的changeForm(T leader, ArrayList<T> followers)
>方法确定了子类可以通过changeForm方法修改阵型。可以使用Formation引用子类对象，比如Crane,实现鹤翼阵型。
> 3. **泛型**。泛型主要体现在工厂类和阵法类。工厂的抽象基类Factory有一个类型参数T,表示工厂生成的对象类型。对于具体的工厂，比如葫芦娃工厂HuluwaFactory
>可以继承Factory<Huluwa>，将类型参数设置为Huluwa类型。而对于阵法类，很多东西都可以有阵法，本程序中将Formation的类型参数T设置为Creature，表示是生物的阵法。
>4. **反射机制**。不同的生物在地图上用不同的字母表示，之前，我是利用派生类重写基类Creature的getSymbol函数，
>然后在绘制地图函数调用getSymbol，得到对应的字母。为了体现反射机制的使用，我不再使用派生类重写机制，而是用了RTTI，对于当前要放置到地图上的Creature
>调用creature.getClass获得对于的class对象，再通过getSimpleName获取对象的类名，通过比较类名字符串确定是具体哪种生物，用相应的字符表示。
> 5. 容器。之前的葫芦娃、妖精对象都是用数组存放的。而数组的一个缺点是空间受限，比如如果需要增加妖精数量，就比较麻烦。我改用ArrayList容器来实现对象的存放。
>将原先对于数组的操作改为对于容器的操作，代码逻辑没有改变。
三. 设计理念
> 1. 降低各个模块之间的耦合度，便于维护拓展。战场类和任务对象类是分开的，通过独立的Formation类负责
>阵型的排布，反映在队伍成员的position中。战场类通过这些pisition获取所有人的位置信息，因此战场类
>可以自己选择作图的方式。目前还是用非GUI的方式体现，以后引入GUI的话，也只要修改战场类的代码，
>而老爷爷、蝎子精都不用改变。

![UML](https://github.com/mqchenliang/java-2019-homeworks/blob/master/3-OOPAdvanced/%E9%99%88%E5%96%84%E6%A2%81-171860525/Uml.png)
