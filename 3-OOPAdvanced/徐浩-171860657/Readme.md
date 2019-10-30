## 作业3：面向葫芦娃编程

### 设计思想
1. 使用`class Creature`作为所有角色的父类。葫芦兄弟`class CalabashBrothers`，小喽啰`class BadGuys`，爷爷`class Grandpa`，蛇精`class SerpentDemon`都是其子类。
2. 使用`class Map`来描述角色所在的地图。当地图某处需要出现某角色时，对应的地图点直接生成对应类型的对象。
3. 使用`class Formations`来描述角色的站队队形。将需要排兵布阵的角色数组传入该类方法中，根据所选择的队形在地图上相应位置生成对应角色。并且可以根据角色个数来设置队形。

---

### 主要的类
+ **角色类**

***`class CalabashBrother`*** 描述单个葫芦兄弟
```
class CalabashBrother extends Creature
{
    private double hp;      //血量
    private String color;   //葫芦娃对应颜色
    public CalabashBrother(String name,String color)
    {
        super(name,true,true);
        hp=100;             //初始满血
        this.color=color;
    }
    public void setHp(double hp)
    public double getHp()
    @Override
    public String getName()
    public String getColor()
}
```
***`class CalabashBrothers`*** 用于管理所有葫芦兄弟
```
public class CalabashBrothers
{
    private CalabashBrother[] cbs;
    //得到一个大小为n,值介于(min,max]之间的int型数组，用于初始化葫芦娃的相对次序
    public static int[] randomArray(int min, int max, int n)
    //随机初始化葫芦兄弟在数组中的位次
    public CalabashBrothers()

    //用于参数传递
    public Creature[] getCalabashBrothers()
}
```
使用**数字**来表示葫芦兄弟。

***`class BadGuy`*** 用于描述单个小喽啰
```
class BadGuy extends Creature
{
    private double hp;      //血量

    public BadGuy()
    {
        super("x",true,false);
        this.hp = 100;
    }
    public void setHp(double hp)
    public double getHp()
    @Override
    public String getName()
}
```
***`class BadGuys`*** 用于管理所有小喽啰
```
public class BadGuys 
{
    private BadGuy[] bgs;
    private int num;                    //小喽啰个数

    public BadGuys(int n)
    public int getNum()
    public BadGuy[] getBadGuys()        //用于参数传递
}
```
使用“**x**”来表示小喽啰们。

***`class Grandpa`*** 
```
public class Grandpa extends Creature
{
    public Grandpa(){...}
}
```
使用“**G**”来表示爷爷。

***`class SerpentDemon`***
```
public class SerpentDemon extends Creature
{
    public SerpentDemon(){...}
}

```
使用“**S**”来表示蛇精。

+ **地图类**
    + 地图为20*20的静态矩阵，共400格。
    + 每一格上若存在角色，则直接生成一个对应类型的角色
```
public class Map {
    private static final int N = 20;
    private static Creature[][] map = new Creature[N][N];

    //构造函数
    public Map(){...}  
    
    //map为用来初始化this.map的地图，(x,y)为初始化的坐标点。
    //用于设置单个角色
    public static void setMap(Creature obj, int x, int y) {...}

    //初始化地图
    public static void initMap(){...}

    //打印地图
    public static void printMap(){...}
}
```

+ **阵型类**
可以根据角色的数量对部分阵型进行调整。

```
public class Formations
{
    //设置角色的队形，通过mode选择阵型
    //obj为角色数组，mode为阵型选择因子，n为角色的数量，(centerX,centerY)为阵型的中心位置，camp为阵营（true葫芦娃阵营/false蛇精阵营）
    public static void setCharacter(Creature[] obj,int mode,int n,int centerX,int centerY,boolean camp)

    //mode=1，选择阵型鹤翼
    public static void inHeYi(Creature[] obj,int n, int centerX,int centerY,boolean camp)
    //mode=2，选择阵型雁行
    public static void inYanXing(Creature[] obj,int n, int centerX,int centerY,boolean camp)
    //mode=3，选择阵型冲轭
    public static void inChongE(Creature[] obj,int n, int centerX,int centerY,boolean camp)
    //mode=4，选择阵型长蛇
    public static void inChangShe(Creature[] obj,int n, int centerX,int centerY,boolean camp)
    //mode=5，选择阵型鱼鳞
    public static void inYuLin(Creature[] obj,int n, int centerX,int centerY,boolean camp)
    //mode=6，选择阵型方圆
    public static void inFangYuan(Creature[] obj,int n, int centerX,int centerY,boolean camp)
    //mode=7，选择阵型偃月
    public static void inYanYue(Creature[] obj,int n, int centerX,int centerY,boolean camp)
    //mode=8，选择阵型锋失
    public static void inFengShi(Creature[] obj,int n, int centerX,int centerY,boolean camp)
}
```

---

### 运行结果
*小喽啰设置为11个。其中，G代表爷爷，S代表蛇精，x代表小喽啰，数字代表葫芦兄弟*

![鹤翼](https://raw.githubusercontent.com/irronici/java-2019-homeworks/master/3-OOPAdvanced/%E5%BE%90%E6%B5%A9-171860657/1.jpg)
![雁行](https://raw.githubusercontent.com/irronici/java-2019-homeworks/master/3-OOPAdvanced/%E5%BE%90%E6%B5%A9-171860657/2.jpg)
![冲轭](https://raw.githubusercontent.com/irronici/java-2019-homeworks/master/3-OOPAdvanced/%E5%BE%90%E6%B5%A9-171860657/3.jpg)
![长蛇](https://raw.githubusercontent.com/irronici/java-2019-homeworks/master/3-OOPAdvanced/%E5%BE%90%E6%B5%A9-171860657/4.jpg)
![鱼鳞](https://raw.githubusercontent.com/irronici/java-2019-homeworks/master/3-OOPAdvanced/%E5%BE%90%E6%B5%A9-171860657/5.jpg)
![方圆](https://raw.githubusercontent.com/irronici/java-2019-homeworks/master/3-OOPAdvanced/%E5%BE%90%E6%B5%A9-171860657/6.jpg)
![偃月](https://raw.githubusercontent.com/irronici/java-2019-homeworks/master/3-OOPAdvanced/%E5%BE%90%E6%B5%A9-171860657/7.jpg)
![锋失](https://raw.githubusercontent.com/irronici/java-2019-homeworks/master/3-OOPAdvanced/%E5%BE%90%E6%B5%A9-171860657/8.jpg)

---

### 关系图
![avatar](https://raw.githubusercontent.com/irronici/java-2019-homeworks/master/3-OOPAdvanced/%E5%BE%90%E6%B5%A9-171860657/ver2uml.png)
