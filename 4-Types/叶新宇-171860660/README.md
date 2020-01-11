# 作业4：面向葫芦娃编程
本次作业要求使用面向对象程序设计方法，实现葫芦娃和蝎子精在NxN的二维空间中两军对峙。

本次作业在作业3的基础上，要求添加泛型和反射机制，并进行显式的说明。

## 设计思路
本次作业，我的实现中包含位置（Position），生物体(Creatrue)，战场（BattleField），阵营（Lineup），战术（Tactic）五个基类。
葫芦娃（CalabashBoy）类、爷爷（Grandfather）类、蝎子精（Scorpion）类、蛇精（Snake）类、小喽啰（Minion）类由生物体类派生而来，葫芦娃阵营（CalabashLineup）类、蝎子精阵营（ScorpionLineup）类由阵营类派生而来。
### 位置（Position）类
```Java
public class Position <T extends Creature> {
	private int posX;
	private int posY;
	private T holder;//该位置上的生物体，如果为null表示为空
	Position(int x, int y);//构造函数
	public T getHolder();
	public void setHolder(T h);//将位置上的生物体和位置进行绑定
	public int getPosX();
	public int getPosY();//获取坐标
	public void setPos(int x, int y);
	public void setPos(Position p);//设置坐标
}
```
维护每个生物体在NxN二维空间的横纵坐标，并可以对其进行修改以实现布阵、变阵功能。

作业4追加修改：可以通过位置来获取站在该位置上的生物体。

### 生物体(Creatrue)类
```Java
public abstract class Creature {
	protected String name;//姓名
	protected Position pos;//位置
	public void setPos(int x, int y);
	public void setPos(Position p);//设置坐标，代表移动自己的位置
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
	private void printMark(Creature c);//打印表示不同类型的生物体
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
	protected ArrayList<Creature> lineupList;//每个阵营有三类成员：队长、啦啦队、普通士兵
	protected Tactic t;//一个阵营应当拥有阵法信息
	Lineup();//构造函数
	public void addCaptain(Creature c);
	public void addCheerleader(Creature c);
	public void addSoider(Creature c);//添加三类不同成员
}
```
是一个抽象类，描述本程序中所有的阵营具有的共有特征：有队长、啦啦队、普通士兵，有阵法知识，可以添加三类不同成员。

作业4追加修改：修改了过时的容器Vector，改用ArrayList。

### 战术（Tactic）类
```Java
public class Tactic {
	Position refpoint;//队长的位置，称为“参考点”
	int number;//总人数（不包括啦啦队
	Tactic(Position p, int n);//构造函数
	public ArrayList<Position> ChangShe();
	public ArrayList<Position> HeYi();
	public ArrayList<Position> YanXing();
	public ArrayList<Position> ChongE();
	public ArrayList<Position> FangYuan();
	public ArrayList<Position> FengShi();
	public ArrayList<Position> YanYue();
	public ArrayList<Position> YuLin();
	//八种阵法方法，返回所需士兵站立的位置坐标信息，存储在队列中
}
```
描述了题目中所述的八种阵法，根据参考点和总人数来布阵。

作业4追加修改：把List改为ArrayList。
### 类图
![类图](http://www.plantuml.com/plantuml/png/XP71IiGm48RlUOev2yNxuc5PGIyAWhx0toJIQ9eioPH5yE759h5nMEWntqmo_pSvPaRYPVPAU-HCBp4xTZ4eZ9aJD5FXzyc2bsHLFS9ZH9vEyQkInuHWti6JJPMywfZEBePs3FXirvzTs0edC7lxuAmtbRn1izComfCBTZcB00MAYHKgrLBJpJ3ygT61a0bamA3PpgDDglEXk-zXs7mxBr5fqoJ-zHIi-0fOKgxH6YlHkc1P_HSjJrI_JbUMkfsk0yGh1_g8BjnU74ycuIBRjqNkj-_4Br-vk_zk5-N6jQQZ3MQP_Gy0)

## 用到的面向对象设计概念、机制、设计理念
### 抽象
**我的实现中有两个抽象类：生物体类和阵营类，分别抽象出了所有生物体的共性和两个对峙阵营的共性。**

*在本程序中，每个生物体都有身份、姓名、位置；可以移动位置，可以在战场上表示出自己，可以加入战场和从战场撤退。*
*每个阵营都有队长、啦啦队、普通士兵，有阵法知识，可以添加三类不同成员。*

### 继承
有五种不同的生物体：**葫芦娃、爷爷、蝎子精、蛇精、小喽啰**，继承自抽象类**生物体**；
有两个不同的阵营：**葫芦娃阵营、蝎子精阵营**，继承自抽象类**阵营**。

*不同的派生类，其构造函数不同。*
*不同的生物体派生类之间，属性有所不同（葫芦娃类具有颜色属性）。*
*不同的阵营派生类之间，加入战场方法不同，获取的阵法知识不同，且蝎子精阵营多出一个变阵的方法。*

### 组合
**阵营类、战场类**包含**生物体类**对象；**生物体类、战术类**包含**位置类**对象。

### 多态
在包含**生物体类**对象的**阵营类和战场类**中，对于5个不同的派生类，都当作其基类**生物体类**处理。
```Java
	public void addCaptain(Creature c);//方法中给出的形参类型是Creature
	
	addCaptain(new CalabashBoy("老大", "红色"));//构造函数中提供的实参类型是CalabashBoy
```


## 泛型与反射机制的运用
### 泛型
在**位置类Position**中添加了泛型标识：
```Java
	public class Position<T extends Creature>
```
添加了数据成员**holder**，类型为**T**，表示站在该位置上的生物体。如果值为null，表示该位置为空。
还添加了有关该成员的两个新的方法：
```Java
	public T getHolder();//获取该位置上的生物体
	public void setHolder(T h);//将位置上的生物体和位置进行绑定
```
当生物体加入战场时，将生物体与该位置进行“绑定”：设置生物体的pos属性，设置位置的holder属性。

当生物体撤离战场时，将生物体与该位置进行“解绑”：将生物体的pos设置为(-1, -1)，清除1原位置的holder属性。

此外，一些其他有关方法的实现也做了相应修改，以更好地利用位置和生物体的这种绑定。
### 反射
在上一个版本的作业中，**生物体类Creature**具有以下属性：
```Java
	enum Type {
		CALABASHBOY, GRANDFATHER, SCORPION, SNAKE, MINION
	}
	protected Type id;//身份
```
和方法：
```Java
	public void printMark();//在战场上表示自己，通过id属性来实现
```
现在应用反射机制，删除了这些属性和方法，转而在**战场类BattleField**中来实现生物体的表示。
```Java
private void printMark(Creature c){
	Class type = c.getClass();
	if(type.getName().equals("CalabashBoy"))
		System.out.print('c');
	if(type.getName().equals("Grandfather"))
		System.out.print('g');
	if(type.getName().equals("Scorpion"))
		System.out.print('S');
	if(type.getName().equals("Snake"))
		System.out.print('s');
	if(type.getName().equals("Minion"))
		System.out.print('m');
}
```
