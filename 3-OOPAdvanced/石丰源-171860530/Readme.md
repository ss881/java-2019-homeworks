# 版本更新

(重写所有代码)

### 一、程序结构

<img src="pic/structure.png" style="zoom:70%;" />	

| 包                           |
| ---------------------------- |
| package hw3                  |
| package hw3.Creature         |
| package hw3.Creature.Monster |
| package hw3.Dictionary       |
| package hw3.Formation        |

### 二、类的设计

| 类名                      | 父类            | 功能                                                         |
| ------------------------- | --------------- | ------------------------------------------------------------ |
| class Main                | 无              | 顶层实体，程序入口                                           |
| class Game                | 无              | 游戏整体逻辑实现                                             |
| class Field               | 无              | 战场，包含NxN个Tile                                          |
| class Tile                | 无              | 砖块，每个砖块可以容纳一个Thing2D类型对象                    |
| class Thing2D             | 无              | 二维事物，为生物和非生物的父类                               |
| class Creature            | class Thing2D   | 生物，为葫芦娃、爷爷、妖怪的父类                             |
| class Monster             | class Creature  | 妖怪，为蛇精、蝎子精、小喽啰的父类                           |
| class Calabash            | class Creature  | 葫芦娃，游戏主要角色                                         |
| class Grandpa             | class Creature  | 爷爷，游戏主要角色，能为葫芦娃呐喊助威                       |
| class Snake               | class Monster   | 蛇精，游戏主要角色，能为其它妖怪呐喊助威                     |
| class Scorpion            | class Monster   | 蝎子精，游戏主要角色，妖怪阵营的领头                         |
| class Heeler              | class Monster   | 小喽啰，游戏主要角色                                         |
| class CheerUp             | 无              | 接口，蛇精和爷爷实现该接口，从而可以呐喊助威                 |
| class ColorDictionary     | 无              | 颜色词典，查阅葫芦娃的可取颜色                               |
| class NameDictionary      | 无              | 名字词典，查阅葫芦娃的可取名字                               |
| class FormationDictionary | 无              | 阵型字典，查阅阵型的可取名字                                 |
| class Formation           | 无              | 阵型抽象类，为各种阵型的父类；存储该阵型的图谱map，用来安放Thing2D的位置 |
| class FormationChangshe   | class Formation | 长蛇阵                                                       |
| class FormationFangyuan   | class Formation | 方圆阵                                                       |
| class FormationFengshi    | class Formation | 锋矢阵                                                       |
| class FormationHenge      | class Formation | 衡轭阵                                                       |
| class FormationHeyi       | class Formation | 鹤翼阵                                                       |
| class FormationYanxing    | class Formation | 雁行阵                                                       |
| class FormationYanyue     | class Formation | 偃月阵                                                       |
| class FormationYulin      | class Formation | 鱼鳞阵                                                       |

### 三、设计思路

1.添加程序入口：程序入口为class Main，通过创建Game对象，调用Game对象提供的方法运行整个游戏。

2.布局：NxN的二维结构(这里N = 12)

​			   Field作为整个战场，包含NxN个砖块，每个砖块Tile可以容纳一个事物Thing2D(实际为Thing2D的子

​			   类，如葫芦娃)

3.运行过程：在Main中创建Game对象，调用init方法进行初始化(生成葫芦娃、爷爷、蛇精、蝎子精和小喽啰)

​					  调用play方法，选择阵型，通过Formation的getMap方法获得阵型图谱，从而设置葫芦娃等的位

​					  置。

### 四、 面向对象的概念、机制、设计理念 

#### 1.封装

数据成员使用private修饰符(对于需要开放给子类的成员使用protected修饰符)，通过get和set方法获得和修改值，防止调用者直接访问数据成员。

#### 2.继承

生物和非生物继承自Thing2D，葫芦娃、爷爷、妖怪等继承生物类，蝎子精、蛇精等则继承妖怪类。通过继承实现代码复用，同时也为多态的使用提供了基础，提高代码灵活度。

#### 3.多态

具体的阵型如长蛇阵等继承抽象类Formation，实现抽象方法 initMap()，从而调用initMap()方法时可以动态绑定到子类的initMap()方法，实现多态。

由于每个Tile可以容纳一个Thing2D及其子类的对象，所以在后续的设计中也可以使用多态。

#### 4.容器

在Game类中，用ArrayList存放葫芦娃们和小喽啰们，可以动态增长，长度不限，更加灵活。

### 五、类图

<img src="pic/class.png" style="zoom:100%;" />

### 六、程序执行

```
cd src
javac -encoding utf-8 hw3/Main.java
java hw3/Main
```

