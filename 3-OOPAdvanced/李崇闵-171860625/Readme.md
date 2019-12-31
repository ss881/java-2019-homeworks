# 第三次作业 
## 一、类的介绍 
### 1、Creature——生物类 
所有生物体的父类。具有生物体的基本属性如所处位置的行和列以及姓名。也具有生物体的基本动作如上移、下移、左移和右移。并且具备利用以上四个基本动作移动到指定行列的能力。 
####1.1、Xiezi类——蝎子精 
继承生物类，蝎子精具有创造一定数量小喽啰的能力，且可以指挥小喽啰移动到指定位置从而形成雁行和合翼阵型。 
#### 1.2、Xiaoyao类——小喽啰类 
继承生物类，无其他特殊功能。 
#### 1.3、Snake类——蛇精类 
继承生物类，无其他特殊功能。 
#### 1.4、Old_man类——老爷爷类 
继承生物类，老爷爷具有创造七个特定葫芦娃的能力，且可以指挥葫芦娃按照大小排成一字长蛇阵。 
#### 1.5、Hulu类——葫芦娃类 
继承生物类，且具有观察自己所处位置是否符合长蛇阵要求的能力。如不符合可按照老爷爷指令移动到特定位置。 
### 2、Ground类——场地类 
拥有可容纳N*N个生物体的空间，数组中的每一项都是生物体类的对象，具有入驻生物体登记的能力，且可以在当前空间中已有生物体的情况下，将已有生物体驱逐至其他空地，将后来生物体放入的能力。且可以注销生物体，将生物体占地重新变为空地。最后可以按照顺序输出整个空间中的所有生物体姓名。 
### 3、Master——主类 
创造场地，创造老爷爷、蝎子精、蛇精并将他们放入指定位置且登记。分别调用老爷爷类和蝎子精类使得小喽啰和葫芦娃摆出指定阵型，最后调用场地类打印出响应图形。 
## 二、类图展示 
![](http://www.plantuml.com/plantuml/png/bPBDZjCm4CVlUGeVWzHT2Qv5Q147zh3B8dfPL5NHRE9rh3gUo1zjGk7TSIPniD8Mn2LsVlCnyn_FdNLWd6zKLYgmbdqo79mt_9GnngHspE1nLQ9Q3_yRPwGMJ4F3wV-0icAFUE25R_Ct2rJXKPz1nL_T6JHIrEVKuO1-JNqzgQfeG5DRzrvvjjtLuQ2OoPPJkwlXMwvYgqbMafP_DkXrnGGTYVK1jR0Qzdpf4LkWidFzlnHxGLGSTCY8--8LpH5CbLFFDVdB9EUpv3ya9NsMW3rWqDMDDyf5TghLKMp__yA-W-v2n5NxFU_bDSsngxJ3QyfZo6OO8WNGxL_kOsNoZxiNPh3TVJG6-dNSmYzq6tqfeqxrrw05Brgq-PoYJEpVk93MSNFP-e02lRji-sf2hGAKIbVn4OPafsJp68xddoTZtV1TprDWj5gZY7JKoqTCQc-u8qa9Uce3a4wYVdiPlvjmwr-KB9bruC91JWr8dK_pt-x024kIPjdlVzxSH3qBEkpk0f781IEX2pRjJ3Q9ne2YGpREaLYico2ga09Adu8nPnRVuFRsGqGKcu8xhYlVgDy0)
## 三、面向对象编程的好处 
### 1.功能分类清晰。每个类都具有自己独有的功能，完成自己的任务。
### 2.易维护。继承机制的使用使代码简洁且只需改动父类就可完成所有子类的改变降低维护成本。
### 3.易于理解。场地容纳生物体，生物体就是场地中的一部分，使用聚合使得两者关系清楚。
