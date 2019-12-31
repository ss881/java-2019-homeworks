# Java作业3

##### 171860014 万玲 2578082378@qq.com



1. 数据结构
   - `Being`：本次作业中涉及到的所有生物的基类
     - `int posX, posY`：所在位置的二维坐标
     - `Being()`：默认构造函数
     - `Being(int x,int y)`：构造函数
     - `void display()`：打印函数，普通生物打印出空白格，特定生物打印出特定字符，派生类可以重写display函数
     - `void changePos(int x,int y)`：修改坐标函数
   - `class Huluwa extends Being`：葫芦娃类
     - `int colornum`：用数字表示葫芦娃的颜色，1对应大娃，以此类推。
     - `int num`：表示葫芦娃的位置
     - `Huluwa(int c, int n)`：构造函数
     - `void setPos(int x, int y)`：设置位置函数
     - `Boolean ChangeOrNot(Huluwa h)`：和葫芦娃h进行排行比较，如果排行小（即num大）则返回true
     - `void display()`：重写display函数，按照葫芦娃的大小排行打印相应字符，这里做了简单处理，大娃打印a，二娃打印b，以此类推
   - `class SheJing extends Being`：蛇精类
     - 构造函数
     - 重写display函数，打印`*`
   - `class LouLuo extends Being`：小喽啰类
     - 构造函数
     - 重写display函数，打印`o`
   - `class LaoYeYe extends Being`：老爷爷类
     - 构造函数
     - 重写display函数，打印'#'
   - `class XieZiJing extends Being`：蝎子精类
     - 构造函数
     - 重写display函数，打印'x'
   - `Mmap`类：布局类，维护一个10x10的二维数组，数组成员均初始化为Being类，然后根据各个生物的位置信息将特定坐标的数组成员实例化为特定类
     - `Being m[][]=new Being[10][10]`：维护的位置信息
     - `Mmap(Huluwa h[])`：构造函数，传进来的h是乱序的葫芦娃，在构造函数中对葫芦娃进行排序，并以长蛇阵型将葫芦娃放置在二维数组的合适位置，以衡轭阵型放置蝎子精和小喽啰，将老爷爷和蛇精分别放在两个阵型下方
     - `void changeQue(Huluwa[] h)`：改变蝎子精和小喽啰的阵型为方门阵型
     - `void Display()`：遍历二维数组，调用每一个成员的display函数，打印出阵型。
2. 本次作业面向对象设计的好处：
   - 设计基类`Being`，将本次作业涉及到的生物的共同性抽象出来，简化程序编程，代码可读性增强；
   - 重写display函数，在功能调用时依靠Java的动态绑定机制，简化程序设计；
   - 根据子类可以实例化父类变量的机制，简化了对地图数组m的值的修改，打印阵型时直接遍历m数组调用每一个成员的display函数即可。
3. 下面是PlantUML画出的类图

![PlantUML diagram](http://www.plantuml.com/plantuml/png/XPA_JiCm4CRtUmfBBAreNe01LOK1eV87iRH46QpKQYmvlYgsWIhq0TZPGEhC1Yj8l4sLru3OHogLe4o-VFVbvo-N6nhB2-jo1QdYnh2Ha7fUmXwJsh85cicsd89lzlfqrlBzm7UNVRX5EMCpQHQABtyi-ymBQSRrN5oYsN6lGACLh93pp1vKJ-lgyNMp_gZUFu5od3ZbxdW9aNyZHOM5TZazrbKmU6mwy29bCC9kxwFhHeXASCsEV9oBuXnjBm1OzYVw2W2e6KKFXs4alxckCt7g9-IBZYbu51bRM6EEKp4L9L11hFzHv6j3eHixB26S7I1ojM0cKjp8y7rDsGLhZ2syivmlIeY2oF8uYHEejMRuSKB8i2XNJb0hJfe_SqotxMpDvkLjy_pbLsOezCpbwXi0)