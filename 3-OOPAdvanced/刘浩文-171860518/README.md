# Java 第三次作业 171860518 刘浩文 README文件
## 本作业中面向对象思想的介绍
### 我在这次作业里运用了类继承的概念，把所有出现在地图上的东西抽象成了一个creature类，并且有xy坐标还有move移动函数。
```

public class creature;
public class huluboys extends creature;//葫芦娃
public class imps extends creature;//小喽啰
public class oracles extends creature;//老爷爷
public class villians extends creature;//蛇精 蝎子精

```
---
葫芦娃 老爷爷 蛇精蝎子精和小喽啰都继承了这个类。

之后，葫芦娃还有一个rank类表示葫芦娃的排名 构造函数传入的数字是葫芦娃的排名。

其他的老爷爷等直接继承creature类。

---
这样做可以方便代码复用，不然所有的类都是，或者包含很多一样的代码没有必要。
地图我没有用类，因为感觉只是一个方便输出的标记，真实的坐标记录在类的实体化内容的成员变量xy坐标里，
这样做没有什么必要。
阵法我用了长蛇，方圆还有冲轭
地图输出中：
* H-葫芦娃
* O-老爷爷
* K-蛇精
* S-蝎子精
* I-小喽啰

代码类图：

![UML](https://github.com/Lautstark9217/java-2019-homeworks/blob/master/3-OOPAdvanced/%E5%88%98%E6%B5%A9%E6%96%87-171860518/uml.png)