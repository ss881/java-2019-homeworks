# 郭天财 161180041 作业3

![UML](https://github.com/HIKARI513/java-2019-homeworks/blob/master/3-OOPAdvanced/%E9%83%AD%E5%A4%A9%E8%B4%A2-161180041/cn/edu/nju/hikari/UML/UML%E6%88%AA%E5%9B%BE.png)

## creature
### 生物类Creature

Field/function|作用
-:|:-
name|名字，默认为null
x,y|当前对象在Ground中的位置，与tile对应的索引相同
boolean positionTaken(Tile t)|判断t位置是否被占
void leave()|离开当前位置

****

### 爷爷类Granpa

爷爷对象除继承Creature之外，还有**SevenBros**,代表**七兄弟**,七兄弟的阵型由爷爷Grandpa的**giveCommand()**控制

****

### 蝎子精队伍ScorpionField

此类代表蝎子精阵营，用**giveCommand()**（上图未及时更新）控制其阵型

****

## command
### Command
此类存有所有阵型的指令

****

### 接口Style
包含所有阵型的接口，供两方阵营使用

****

## ground
### 地砖类Tile
Field/function|作用
-:|:-
Creature life|此地砖上的生物
int x, y|此地砖在Ground中的作用
boolean isTaken|判断此地转是否被专用
void welcome(Creature life)|life占用此地转

****

### 地板Ground
Field/function|作用
-:|:-
int sizeOfTiles|地板上地砖的size
Tile tiles[][]|二维地砖铺满地板
void printToScreen()|打印地板上的所有生物名字

****

## 结果展示
![结果](https://github.com/HIKARI513/java-2019-homeworks/blob/master/3-OOPAdvanced/%E9%83%AD%E5%A4%A9%E8%B4%A2-161180041/cn/edu/nju/hikari/UML/result.png)
