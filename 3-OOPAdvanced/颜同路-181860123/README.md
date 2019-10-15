# 181860123_颜同路_java程序设计第三次作业

## **10.15 Update**

1. 排查出了对Conduct方法重载出现严重错误的原因：这种写法因为参数不同，并没有覆盖父类中的方法。实际上是类似重载，定义了一个全新的方法，而调用时，由于从map中取到的是Creature元素，所以匹配到了父类的方法。这个方法自然被悬空了
2. **根据助教的反馈，对在命令行环境下运行时，打印地图出现的问题进行解释：**最初程序编写时，在打印地图时，由于要同时输出汉字和英文字符、数字，在输出的对齐上，遇到了严重的困难：String类的format()方法无法实现中英文夹杂的对齐，于是，在查阅资料和不断尝试后，采取了利用"\t"制表符对齐的方法，实现了中英文同时输出的对齐；但是由于开发时采用的是IDEA，并没有用命令行进行编译和测试，导致在IDEA的输出窗口输出正常的打印地图，到了命令行下不再能对齐，出现了严重的错误
**所以采用进行如下修改和解释：**
a. 由于原先的测试环境是IDEA，并没有在命令行中测试，导致在命令行中执行时，的确会出现严重的排版错误，具体原因是IDEA输出创库中的字符宽度和命令行中有区别，导致不再能对齐；其次命令行的宽度有限导致触发强制换行，导致了严重的排版错误
b. 修改了打印地图函数中的"\t"字符的数量，使其能在命令行控制台中对齐，并使其总宽度变窄，不易触发命令行的强制换行导致错位
**注意：**
**本次版本的程序经过测试，在PowerShell命令行@Windows和VS Code@Windows下均可显示正常**
**但是经过调整此版本的程序在IDEA下的输出窗口不再能对齐**
**任需要注意的是，在运行程序时，请保证命令行或者VS Code的输出窗口的宽度足够大，否则仍会出现强制换行**

![修改后的输出](./CorrectedPrintOut.png)

## UML类图

![UML类图](http://www.plantuml.com/plantuml/png/XP3FIlDG44VtynGNicafXQ-V84LTLBhefWL1NOTfEZQNJEQ6oOt_VGCNFe0hNy2bJoJeMrWrQMmQw2Ahd7FvdHdb7jKNAOEtdibylZo_Fpr-lBsQmN0mtFyt-BzdniWunpm-LaLPKahYpVbi2X5ZdhVzlOCmp8evsoWC0O8KhNIwy02bCSDia-Ybc45mebRyNswiXBvGQi22AK7FNA--6mWcphkL59mX9nkoaqKHUQUDCUEOIB-54kXq3okfRcdESufCq5EK188yiSoThgcrAMLAKOo5j8i1NLhUrYQAiiYmuKJbXhaw7vFcwycrU2gOK4ExSCpkYdG3hovuTD_lh-FCWLcIa2BRM_JMIGEh3hU3gwDRmEgD7xBit87zLcr38rf1ANy1)

## 类定义简介

1. 主类为CalabaArrangement，包含main方法
2. 定义了Creature类，表示生物，包含public变量name、坐标x和y；同时定义了Goto方法和Talk方法，分别用于在地图上走动和说话
3. 定义了Map类，维护一个20 * 20 的Creature二维数组，存放生物对象的引用，用来表示地图，包含一个打印地图的Print方法
4. 从Creature类派生了Calabash类和Leprechaun类，分别表示葫芦娃和妖精，他们都包含一个morale成员变量表示阵营士气，其中葫芦娃类又包含rank变量表示葫芦娃们从老大到老七的次序，和一个虚方法skill，表示葫芦娃们各自独特的本领；妖精类包含一个虚方法evilskill，表示邪恶的手段
5. 从Creature类派生了Conductor类，表示指挥官类，用于派生老爷爷和蛇精类；包含一个虚方法Cheer用于加油助威、鼓舞士气，以及Conductor方法用于指挥葫芦娃\小喽啰。
6. 从Condutor类派生了Grandpa类表示老爷爷，包含一个实例化为葫芦娃类的ArrayList calabashbrothers，表示老爷爷心中惦记着七个葫芦娃，也便于对七个葫芦娃的访问，初始化时创建七个葫芦娃对象并保存他们的引用；重写了Cheer方法让老爷爷可以为葫芦娃们加油助威
7. 从Conductor类派生了Snake类表示蛇精，包含一个实例化为妖精类的ArrayList follower，表示蛇精手下妖精的集合，用于访问妖精们，初始化时创建蝎子精、蝙蝠精、蛤蟆精、蜈蚣精、天牛精、黄蜂精、野猪精、蜘蛛精八个妖精并储存它们的引用；重写了Cheer方法让蛇精可以为妖精们加油助威

## 实现细节简述

1. Map中的成员变量和函数都定义为了静态，所以可以直接通过类访问，不需要创建任何对象且唯一存在，比较符合事实。
2. 因为老爷爷和蛇精需要访问类外的非静态成员，所以其成员方法无法声明为全静态
3. Map类Print方法打印地图时，发现即使使用String.format方法也无法将元素对齐，经过反复的试验发现是因为输出的角色名是中文，所以最终在输出时利用\t制表符同样实现了对齐输出（没有生物的空地为了更清晰地表示打印'0'）
4. 老爷爷和蛇精的Cheer方法中使用java提供的类库中的Random类实现了随机输出加油口号的效果

## 使用的OOP机制、理念与好处

1. 使用最频繁的是继承机制，例如所有的生物对象都继承自Creature类，因为他们共有的基础属性是名字和坐标，共有的基础方法是移动和说话。使用继承不仅使对象之间的关系清晰，还能实现代码的复用以及不同类型对象的统一管理，即一种**多态**的体现。比如Map类就通过一个Creature类型的二维数组就实现了对所有派生生物的统一管理；Conductor类的Conduct方法也用Creature类的参数同时实现了对葫芦娃和妖精的指挥
2. 其次是对静态方法、变量的使用，如实现细节简述中提到的Map类，就可以很好地表示唯一存在的地图
3. 还有继承时对父类方法的重载或覆写，比如老爷爷类和蛇精类对Conductor类中Cheer虚方法的覆写，以实现各自的需要；但是对Conduct方法进行重载时却发生了严重的问题，目前放弃了对其的重载，正在排查原因**（10.15update：已发现原因）**
