# hw4：面向葫芦娃编程-反射，泛型

![UML图](https://github.com/shi1ro/java-2019-homeworks/blob/master/4-Types/%E6%AC%A7%E4%B9%89%E6%9D%B0-171830568/img/uml.png)

## 面向对象思路(原hw3部分)

1.由于蛇精、葫芦娃等个体都具有位置，姓名等属性，将这些属性聚合起来作为一个虚基类Creature，葫芦娃，爷爷，蝎子精，蛇精，小喽啰都从该虚基类中继承，都使用相同的对外get,set虚方法，方便之后增加这些生物的共性、特性，同时可以用Creature的上溯造型将这些生物都包括进去。一些某一类所有对象公用的变量，如该类对象在map中的表示形式，该类一共new了几个对象等信息使用static变量修饰</br>
2.由于各阵营人数不固定，为了之后方便添加、删除各阵营中个体数量，使用Vector<Creature>(类CreatureVec)对对象进行管理。同时暂时有5种Creature，使用一个Vector<Creature>数组(CreatureControl中的creavecs数组)对五种Creature进行管理，数组初始大小设为5，同时数组下标0-4使用enum，化为HULUWA,XIEZI等enum对象，方便编程时使用</br>
3.每个Creature对象都有place变量，同时在最下层控制类设有char二维数组map,始终保持map中位置与对象中存储位置一致，才能在频繁的清理，随机，排序过程中保持统一、正确。在打印时按照map进行打印。</br>
  
## hw4重构更新

1.(泛型)使用Vector<Creature>的泛型容器思路不变，泛型边界为Creature，共有5个Vector<Creature>,分别装葫芦娃，爷爷，蛇精，喽啰，蝎子5个类的对象，这些extends Creature的类都能放入Vector中，拿出时均赋值给Creature类对象，通过调用一些抽象方法来达成目标</br>
2.(反射)将原来static变量(如皮肤H,Y,S...,)的get方法从abstract方法转为static方法，更符合使用规范。但是外界一般需要使用这些static变量时，只有一个传入的Creature对象，无法通过一个Creature对象直接get这些static信息(在hw3中，由于get函数是abstract,直接object.get即可)，需要得到如HuLuWa,LouLuo这些子类的类名才能调用，因此此时可以使用反射，在运行中动态获得一个creatureclass的Class<?>对象，通过该creatureclass调用getDeclaredMethod("get..."),得到get函数，再使用invoke调用，在invoke调用时传入参数null,表示不通过特定对象调用该方法，即调用static方法</br>
3.(反射)删除原来的enum CreatureName。该枚举类型原来是为了将"HuLuWa"等类字符串转化为int的数组下标，通过数组下标找到Vector数组中对应的装该类对象的Vector。</br>删除后，使用反射，在下层函数中接受一个String变量(如HuLuWa,LouLuo)，在运行中动态地使用该String变量得到一个Class<?>，从该Class<?>对象中可反射得到带参构造函数(getDeclaredConstructor)，创建该类对象，而且能反射(getDeclaredMethod)得到getOrder方法，得到数组下标。这样通过反射，在一些下层函数的使用中，无需明确用到如“HuLuWa”,"LouLuo"等明显字符串，都使用变量代替，提高了代码抽象，可复用性</br>
4.(重构)将原来杂乱的CreatureControl类中一些功能，变量放入一个新抽象出的类Court(战场)中，存放二维数组的map，以及提供清理，打乱顺序，setget等方法供上层CreatureControl使用</br>
5.(重构)将单文件分成多文件，main仍放在HuLuWaCourt类中</br>

## 一些踩到的坑

1.反射时需要加上try语句块</br>
2.通过string反射Class时需要加上包名(.app)<br>

## 待实现目标

1.阵型的类抽象</br>
2.CreatureControl与Court之间功能的分割、清晰化
