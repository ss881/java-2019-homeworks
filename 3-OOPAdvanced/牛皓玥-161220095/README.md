# 作业：面向葫芦娃编程

## 需求

用 **面向对象编程方法**，以Java语言编写程序，实现以下过程：
1. 假设存在一个NxN的二维空间（N>10)，该空间中的任意一个位置坐标上可站立一个生物体（葫芦娃、老爷爷、蛇精、蝎子精、小喽啰均属于生物体）；
2. 请让初始乱序的七个兄弟按下图所示阵型中的长蛇形依序（老大至老七）站队；
3. 请在图中选择一个阵型（长蛇除外）让蝎子精领若干小喽啰站队；
4. 将葫芦兄弟的长蛇阵营和蝎子精小喽啰阵营放置于二位空间中，形成对峙局面；
5. 请选择合适位置将老爷爷和蛇精放置于空间中，为各自一方加油助威；
6. 将上述对峙局面打印输出；
7. 请让蝎子精小喽啰阵营变换一个阵法（长蛇除外），重复4-6步。

## 类说明
![](https://i.imgur.com/1HlaU3M.png)

## 设计说明
### 设计理念
在世界中创建各种生物和地图，世界消亡后生物和地图也不存在。生物和地图的关系是生物在地图上的某个位置。对峙双方的领导者可以完成诸如选择队伍阵型、将阵型放入地图等多种与队伍中生物相关的功能。地图打印功能由地图类完成。

### 类关系说明
#### 强合成
这里认为一旦领导者消亡，消亡的一方被判断为输，则其带领的全部其他生命均会消亡。因此GrandPa类（**爷爷**）和Gourd类（**葫芦娃**）是强合成的关系，Snake类（**蛇精**）和Scorpion（**蝎子精**）、Bandit（**喽啰**）是强合成关系。

~~~ java
    public class Leaders extends Creature{
    	List<Creature> kids = new ArrayList<Creature>();   //领导的队伍
		……
	}
~~~

#### 聚合
类之间的聚合关系代表着“部分”可以单独存在。在设定中Position和其上的Creature是可以独立存在的。
	
~~~java
	public class Position {
    	Creature creature;          //生物
		……
	}
~~~
还有Leaders可以控制选择阵型，但是二者也可以独立存在。
~~~java
	public class Leaders extends Creature{
    	Formation formation;                        //队伍阵型
	}
~~~


#### 继承
游戏中出现的所有生命体具有一定的共性（名字、位置），都可以完成一些常规的操作（报告姓名、报告命令执行结果），因此在设计时抽象出了Creature类定义他们的共有属性和方法。葫芦娃、蝎子精、小喽啰直接继承了Creature类。
~~~java
	public class Creature {
	    String name;    //生物名称
	    int pos_x;      //位置坐标 x
	    int pos_y;      //位置坐标 y
	
	    //报告姓名
	    public void tellname(){
	        System.out.print(name);
	    }
	
	    //报告完成命令
	    public void report(){
	        System.out.print("\t【" + this.name +"】"+ " 完成！\n");
	    }

}
~~~
另外爷爷和蛇精作为双方的领导者，也具有一些其他的共性，例如选择阵型、将阵型放入地图。因此也将这些属性和方法抽象成Leaders类，Leaders类继承Creature类，爷爷和蛇精继承Leaders类。
~~~java
	public class Leaders extends Creature{
	    List<Creature> kids = new ArrayList<Creature>();   //领导的队伍
	    int for_num;                                	   //阵型序号
	    Formation formation;                        	   //队伍阵型
	    int center_x;                               	   //阵型中心在地图中的位置 x
	    final static int center_y = 10;             	   //阵型中心在地图中的位置 y（固定）

	    Leaders(){
	        this.pos_x = 0;
	        Random r = new Random();
	        this.for_num = r.nextInt(8);
	    }
	
	    //输出一方的生物位置坐标（也就是数组编号）
	    public void print_map_position(){	……	}
	
	    //随机选择阵型序号
		public int random_choose_formation(int total_formation_num){	……    }
	
	    //产生阵型
	    public void genernate_formation(int for_num){	……	}
	
	    //将阵型摆入地图
	    public void put_formation(Map map){	……		}
	
	    //进入地图
	    public void put_self(Map map){	……    }
	
	    //撤回阵型
	    public void retreat_formation(Map map){	……	}
	}
~~~

继承关系图：

![](https://i.imgur.com/SgrifmK.png)

