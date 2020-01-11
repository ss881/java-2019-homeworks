# 面向葫芦娃编程

## 类的设计

### （一）程序入口

**Game类**：包含main函数。

### （二）控制模块

**Control类**：完成游戏主体的流程控制。

```java
public void processControl();
```

### （三）Enum类型

**Color类**：存放葫芦娃的七种颜色。

```java
public enum Color { RED, ORANGE, YELLOW, GREEN, SYAN, BLUE, PURPLE }
```

### （四）游戏状态相关

**（1）Coordinate类**

字段：两个整形数，分别代表横坐标和纵坐标。

方法：获取及设置横纵坐标值。

```java
private int x;
private int y;

public Coordinate(int newx, int newy);
public int getX();
public int getY();
public void setX(int newx);
public void setY(int newy);
```

**（2）Formation类**

字段：存放全部阵型。

方法：更改当前阵型类型，获取某个葫芦娃或者小喽啰在当前所选阵法中的坐标，判断某个坐标是否在阵法中等等。

```java
private Coordinate calabashstart[];
private Coordinate line[];
private Coordinate underlingstart[];
private Coordinate cranewing[];
private Coordinate wildgoose[];
private Coordinate yoke[];
private Coordinate fishscale[];
private Coordinate square[];
private Coordinate crescentmoon[];
private Coordinate arrow[];
private Coordinate calabashform[];
private Coordinate underlingform[];

public void setCalabashForm();
public void setUnderlingForm();
public Coordinate getCalaCoordinate(int i);
public Coordinate getUnderlingCoordinate(int i);
public boolean isInUnderlingCoordinate(int x, int y);
```

**（3）Role类及其派生类**

**1)基类：Role类**（所有角色的公共特性）

字段：坐标。

方法：设置坐标、获取坐标。

```java
protected Coordinate mycoordinate;

void setCoordinate(Coordinate c);
public Coordinate getCoordinate();
```

**2)派生类**

**CalabashBrother类:**

```java
private Color myColor;
private int rank;

public CalabashBrother();
public CalabashBrother(Color c, int r);
public int getRank();
```

**Grandpa类：**

```java
Grandpa(int x, int y);
```

**Scorpion类：**

```java
Scorpion(int x, int y);
```

**Underling类：**

```java
Underling();
```

**(4)State类：**

字段：所有角色、阵法信息等。

方法：初始化游戏状态、指挥葫芦娃或小喽啰更换阵型等。

```c++
private static final int SIZE = 11;
private static final int UNDERLING_NUM = 8;
private static final int CALA_NUM = 7;
private ArrayList<Role> roles = new ArrayList<Role>();
private Formation form = new Formation();

State();
public void start(Interface i);
public void changeCalabashForm(Interface i);
public void bfsCalabash(Coordinate src, Coordinate dst, Coordinate prev[][]);
public void changeUnderlingForm(char ch, Interface i);
public boolean bfsUnderling(int x, int y, Coordinate[][] prev, Coordinate tobemoved);
public char getCurFormation(int x, int y);
public int getSize();
```

*类图*
![](ClassDiagram.png)

## 泛型与反射

在State类中的getCurFormation函数中，通过使用泛型和反射机制，获取Role数组中每个对象的坐标。

这里使用了异常处理机制，防止某个对象中不存在getCoordinate"函数。

```java
Coordinate c = new Coordinate(0, 0);
for(Role r : roles) {
	Class<?> role = r.getClass();
	try {
		Method get = role.getMethod("getCoordinate", Coordinate.class);
		get.invoke(r, c);
		...
	}
	catch(Exception e) {
		System.out.println("No such method.");
	}
}
```



## 算法

在更换阵型时，通过广度优先搜索找到移动到目标位置的最短路径。