# 作业3：面向葫芦娃编程
本次作业要求使用面向对象程序设计方法，实现葫芦娃和蝎子精在NxN的二维空间中两军对峙

## 设计思路
本次作业，我的实现中包含位置（Position），生物体(Creatrue)，战场（BattleField），阵营（Lineup），战术（Tactic）五个基类。
葫芦娃（CalabashBoy）类、爷爷（Grandfather）类、蝎子精（Scorpion）类、蛇精（Snake）类、小喽啰（Minion）类由生物体类派生而来，葫芦娃阵营（CalabashLineup）类、蝎子精阵营（ScorpionLineup）类由阵营类派生而来。
### 位置（Position）类
```Java
public class Position {
        private int posX;
        private int posY;
	Position(int x, int y);//构造函数
	public int getPosX();
	public int getPosY();//获取坐标
	public void setPos(int x, int y);
	public void setPos(Position p);//设置坐标
}
```
维护每个生物体在NxN二维空间的横纵坐标，并可以对其进行修改以实现布阵、变阵功能

### 生物体(Creatrue)类
```Java
public abstract class Creature {
    	enum Type {
        	CALABASHBOY, GRANDFATHER, SCORPION, SNAKE, MINION
    	}
    	protected Type id;//身份
    	protected String name;//姓名
    	protected Position pos;//位置
	public void setPos(int x, int y);
	public void setPos(Position p);//设置坐标，代表移动自己的位置
	public void printMark();//在战场上表示自己
	public boolean join(BattleField b, Position p);//加入战场
	public boolean withdraw(BattleField b);//从战场撤退
}
```
是一个抽象类，描述本程序中所有的生物体具有的共有特征：有身份、姓名、位置；可以移动位置，可以在战场上表示出自己，可以加入战场和从战场撤退。

### 战场（BattleField）类
```Java
public class BattleField {
	public Creature[][] info;//存储战场上所有生物的信息
	private int size;//战场的宽度，代表题设中的N
	BattleField(int x);//构造函数
	public int getSize();
	public boolean isAvailable(int x, int y);//判断某个位置是否可以占领
	public void printBattleField();//打印整个战场的状态
	public void joinBattleField(Creature c);//生物c加入当前战场
	public void withdraw(Position p);//使位置p上的生物撤离
}

```
维护一个NxN的二维生物类数组，对外提供加入/撤离生物，打印整个战场状态，判断某位置坐标是否可占领的方法。

### 阵营（Lineup）类
```Java
public abstract class Lineup {
	protected Creature captain;
	protected Creature cheerleader;
	protected Vector<Creature> lineupVector;//每个阵营有三类成员：队长、啦啦队、普通士兵
	protected Tactic t;//一个阵营应当拥有阵法信息
	Lineup();//构造函数
	public void addCaptain(Creature c);
	public void addCheerleader(Creature c);
	public void addSoider(Creature c);//添加三类不同成员
}
```
是一个抽象类，描述本程序中所有的阵营具有的共有特征：有队长、啦啦队、普通士兵，有阵法知识，可以添加三类不同成员。

### 战术（Tactic）类
```Java
public class Tactic {
	Position refpoint;//队长的位置，称为“参考点”
	int number;//总人数（不包括啦啦队
	Tactic(Position p, int n);//构造函数
	public List<Position> ChangShe();
	public List<Position> HeYi();
	public List<Position> YanXing();
	public List<Position> ChongE();
	public List<Position> FangYuan();
	public List<Position> FengShi();
	public List<Position> YanYue();
	public List<Position> YuLin();
	//八种阵法方法，返回所需士兵站立的位置坐标信息，存储在队列中
}
```
描述了题目中所述的八种阵法，根据参考点和总人数来布阵。
### 类图
![类图](http://www.plantuml.com/plantuml/png/XPBDRi8m383lUGgV4TNkQ0TInWyQ50HSTZHj9hAb2KfS8RJjtLUHLCsYIUM4FpkE_QLJHsYfgHKh5Je7U-CaIQCPdXrPB0aydrc1r5Z1GeWAp-WkkRa7ihIegtUaYx2174jZhsshBjJusHtVIjqdSYHIOY65gW8vOKcoJ4VOI2sQQpA0XycD0JAXcnfEzwk0BmRXD-CRdlFZAj-zZM5vuCNBWfzMyyCOZhFTOR_U5Us_Wh_Enx1T5st8VXZhjWUJPO-6FHcrf9C64q0721KIGYtgix0iCWNFtrdMcuoC9PbkZmG_32RCcqoWtnz64VEZHOCvzbUXRn7CnIAJLFHQZmkIBXFuC58_tIHTAektj3nweg7oeV7zHpBaFtRXJ_nND1MwQchr2m00)

## 用到的面向对象设计概念、机制、设计理念
### 抽象
**我的实现中有两个抽象类：生物体类和阵营类，分别抽象出了所有生物体的共性和两个对峙阵营的共性。**

*在本程序中，每个生物体都有身份、姓名、位置；可以移动位置，可以在战场上表示出自己，可以加入战场和从战场撤退。*
*每个阵营都有队长、啦啦队、普通士兵，有阵法知识，可以添加三类不同成员。*

### 继承
有五种不同的生物体：**葫芦娃、爷爷、蝎子精、蛇精、小喽啰**，继承自抽象类**生物体**；
有两个不同的阵营：**葫芦娃阵营、蝎子精阵营**，继承自抽象类**阵营**。

*不同的派生类，其构造函数不同。*
*不同的生物体派生类之间，id属性不同（代表身份不同），其表示自己的方法也有不同（打印出不同的字符）。*
*不同的阵营派生类之间，加入战场方法不同，获取的阵法知识不同，且蝎子精阵营多出一个变阵的方法。*

### 组合
**阵营类、战场类**包含**生物体类**对象；**生物体类、战术类**包含**位置类**对象。

### 多态
在包含**生物体类**对象的**阵营类和战场类**中，对于5个不同的派生类，都当作其基类**生物体类**处理。
```Java
	public void addCaptain(Creature c);//方法中给出的形参类型是Creature
	
	addCaptain(new CalabashBoy("老大", "红色"));//构造函数中提供的实参类型是CalabashBoy
```
