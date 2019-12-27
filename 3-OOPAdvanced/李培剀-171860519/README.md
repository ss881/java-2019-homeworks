# 面向葫芦娃编程
## 涉及概念

类的继承

类的向上转型

this和super关键字

Vector类

## 设计理念
### 各单位类
题中角色所处的正方形二维空间用int型二维数组表示，考虑到阵型的规模，此处设置该空间边长为21，命名为ground。ground中所有元素初始化为0，数字1-5分别对应葫芦娃、蝎子精、小喽啰、爷爷、蛇精。每种角色对应一个类，分别是Hulu Scorpion Creep Grandpa Snake，由于这些类有共同的成员变量（在ground中的坐标）和共同的方法（设置坐标、获取坐标、移动），所以定义基类Creature，上述5类均从Creature中继承而来，特别地，Hulu类有成员变量color，标明其颜色。
### Generate类
由于初始需要随机生成7个葫芦娃以及蝎子精和它的小喽啰们，故设计Generate类。该类包含genHulu和genCreep两个方法（蝎子精因为只有一个它的随机代码就直接在体现在main函数中）。genHulu有参数color和ground，返回类型为Hulu，color指定生成葫芦娃的颜色，ground用于标注其位置。考虑到对阵的合理性，该方法在ground左半侧一个随机的尚未被占据的位置生成一个指定颜色的葫芦娃并返回。genCreep只有参数ground，返回类型为Creep，在ground右半侧一个随机的尚未被占据的位置生成一个小喽啰并返回。
### Formation类
设计Formation类用于阵型变换。Formation主要包括8个方法，分别对应8种阵型，这些方法命名为transformTo+阵型名，参数为Creature型的Vector数组和ground。每个变换阵型的方法中有一个设定好的局部变量，表示建议的列阵所需的单位数。蝎子精带小喽啰列阵时会检查其单位数，若超过建议数量则将多出的小喽啰踢出ground，若缺少则通过genCreep生成小喽啰进行补员。随后就通过指定的布阵套路命令单位移至指定位置，单位的移位调用了他的moveto方法。
### Display类
包含displayGround方法，参数为ground，用于打印对阵情况。
### 程序主体设计
设置了2个Vector<Creature>型数组，hulus存放葫芦娃，creeps存放蝎子精和小喽啰。在双方阵营的正后方固定位置分别生成爷爷和蛇精，因为啦啦队并没有必要变位置。随机生成7个葫芦娃存入hulus，随机生成蝎子精存入creeps，随机生成若干个小喽啰存入creeps，这里设定为6个。实例化Formation和Display。按照Step2，将葫芦娃变换成长蛇阵。之后将蝎子精和它的小喽啰依次变换成除长蛇阵之外的其他7种阵型。由于变阵的方法中调用了moveto方法，moveto会打印位置变动的信息，所以每次变阵均会打印全体的移位信息，之后用displayGround打印对阵图。

## 类图展示

![image](https://github.com/Mi-racle/java-2019-homeworks/blob/master/3-OOPAdvanced/%E6%9D%8E%E5%9F%B9%E5%89%80-171860519/Huluwa.png)
