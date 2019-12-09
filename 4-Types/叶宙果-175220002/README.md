第四次作业
========
本次作业中主要更新内容为：</br></br>
在Creature类上使用了泛型设计方法，使Creature类的子类能根据自身不同类型调用Creature类中的方法。</br></br>
在main函数中试用了反射机制，利用getclass()输出了战场上某位置放的生物名字，这样能在战斗中实时获取每个位置上的生物信息，更好的设计战斗机制；</br></br>
另外还用了forname()方法获取类，以及getConstructor()用以生成对象，可以考虑在战斗中不定时加入新的小喽啰，给葫芦娃多造成一些麻烦，保持战斗平衡。

第三次作业
========
本次作业中增加了Creature类，其中定义了x和y坐标，用于储存位置；name和camp用于储存各个生物的名字及其阵营；还实现了setto和moveto两个方法用于指定各种生物在战场上的位置以及之后的移动；同时Huluwa，Scorpion，Snake，Monsters，Grandpa等几个类继承于Creature类。</br></br>
因为葫芦娃的数量一定，所以Huluwa类中使用到了enum类型，其中存储了每个葫芦娃的排行以及颜色，初始化时可以根据index对几个关键变量进行赋值。Huluwa类还与Comparable类有联系，并重写了Comparato方法，用以排序。</br></br>
Space类负责建立二维空间战场，并实现有方法使其初始化战场以及输出战场情况。该二维空间由Creature类的二维数组构成，选择Creature类是因为它是各种生物类的父类。由于父类变量可以指向子类实例（这里使用了多态性），这就为各种生物在战场上交换或指定位置提供了方便，若当前位置无生物则使该位置为null。</br></br>
Formation类中定义了两个方法，分别实现了对坏人和好人进行布阵，各种阵型由switch语句决定。</br></br>
Testrun类主要是为了本次作业的运行输出，main函数所在处。</br></br>

以下是本次作业的PlantUML类图:</br></br>
![](https://github.com/yezhouguo/imgresource/raw/master/java-homework3.png)  
