①Creatures类用来存储可以占地图一个空格的生物（葫芦娃、蛇精、蝎子精、老爷爷、小喽啰等），成员row和column表示该生物的坐标，成员like用来表示该生物的样子。
②Huluwas类是Creatures的派生类，用来表示葫芦兄弟，他比Creatures类多出String color成员用来表示颜色，int ranking用来表示葫芦娃的排行，Exchange成员函数用来改变位置（排序时用）。
③Formation抽象类用来存储阵型，成员row column表示阵型在地图上的位置（其中一个点），arrange函数用来在地图上排当前阵型。
④CraneWing类是Formation类具体的子类，表示鹤翼阵。
⑤WildGooseFly类也是Formation类具体的子类，表示雁行阵。
⑥World类是总的调度世界类，静态变量N是地图长度，静态变量NobodysNum是指小喽啰数量，静态变量Space是指空地图每格的样子，地图用数组char map[N][N]来表示，在打印时可以直接打印数组内容，内容为Space或者占在当前位置的生物，members是葫芦娃类实例化的7个对象，Nobodys是生物类具体化的若干个小喽啰，Snake是实例化的蛇精，Scorpion是实例化的蝎子精，Grandpa是实例化的爷爷；
成员函数中：Init()用来初始化世界，包括实例化对象，初始化地图等；Printmap()用来打印地图；StandRandomly()是让葫芦娃随机乱序站队；Sort()是冒泡排序让葫芦娃有序；StandBadguys()指将蝎子精和小喽啰排成对应阵型；Clear()清屏，用来刷新；LaySnake()将蛇精放入地图空处;LayGrandpa()将爷爷放入地图空处。
⑦homeworks用来放main函数，进行最终的执行。

用到面向对象的概念：抽象类abstract，静态数据static，派生类和衍生类extends，将各种成员、操作封装；Creatures类聚集到World类等。
好处：思路清晰，将阵型设成抽象类可以更容易做后续修改，易于实现代码复用；使用静态数据可以方便更改地图大小、小喽啰数量上限；派生类和衍生类可以更大程度复用重复代码；封装可以使操作划分更明确，数据不易冲突；Creatures类对象是World类的一部分，所以需要用到聚集的思想。![](http://m.qpic.cn/psb?/V14cpEkc2hqr3t/ECL5fMjvbuaU8YCJJzwsfHfEHIokOfzPu1Uy7G0WLOs!/b/dLYAAAAAAAAA&bo=SAOHAgAAAAADB.w!&rf=viewer_4)