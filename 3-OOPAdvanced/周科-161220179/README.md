第三次作业
===
类介绍
----
* CalabashBrothers类：葫芦娃，颜色、名称等属性与实验二相同，新增了属性横纵坐标，以及读取和修改横纵坐标的功能<br>
![](https://github.com/jokerz0624/Pictures/blob/master/images/5.png)
* GrandFather类：爷爷，属性有名字以及横纵坐标，功能为读取和修改横纵坐标<br>
![](https://github.com/jokerz0624/Pictures/blob/master/images/8.png)
* Queen类：蛇精，继承自爷爷，两者类似。<br>
![](https://github.com/jokerz0624/Pictures/blob/master/images/11.png)
* King类：蝎子精，继承自爷爷，两者类似。<br>
![](https://github.com/jokerz0624/Pictures/blob/master/images/9.png)
* Soldiers类：小喽啰类，继承自爷爷，功能类似，各自名称为数字序号。<br>
![](https://github.com/jokerz0624/Pictures/blob/master/images/12.png)
* MySort类：对实验二的内容进行了精简，给定葫芦娃的错乱序列，使用冒泡排序给他们排序。<br>
![](https://github.com/jokerz0624/Pictures/blob/master/images/10.png)
* FormCalabash类：新建了爷爷类和接收了排序后的7个葫芦娃类，并且按照长蛇阵给他们设定各自的坐标，老大站在长蛇阵最前方面敌，爷爷站在长蛇的正上方助威。<br>
![](https://github.com/jokerz0624/Pictures/blob/master/images/6.png)
* FormSoldiers类：新建了蝎子精类、蛇精类以及多个小喽啰类。根据FormKind的具体数值确定需要排列的阵法，根据阵法需要给他们设定各自的坐标，蝎子精站在阵列最末尾处，蛇精站在蝎子精的身后。<br>
![](https://github.com/jokerz0624/Pictures/blob/master/images/7.png)
* Board类：设定了10*20的棋盘，读取FormCalabash类和FormSoldiers类中各个生物体的排兵布阵坐标，标注在棋盘内，并打印出来<br>
![](https://github.com/jokerz0624/Pictures/blob/master/images/4.png)

==
效果展示
---
（葫芦娃由1-7分别表示大娃-七娃，爷爷为G，蝎子精为K，蛇精为Q，小喽啰为s）<br>
*鹤翼阵<br>
![](https://github.com/jokerz0624/Pictures/blob/master/images/1.PNG)<br>
*雁行阵<br>
![](https://github.com/jokerz0624/Pictures/blob/master/images/2.PNG)
