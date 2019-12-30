# 面向对象

  本次作业是第三次作业的更新版本：
  
    引入反射机制
    
    引入泛型
    
    代码细节优化

## 反射

  反射机制在本次作业中的作用是：
  
    在运行时判断对象所属的类

  在战场BattleField类的print函数中，每个生物不再使用的动态绑定的方式getName()输出对应名字，而是使用instanceof关键字判断对象所属的类并进行对应输出。
  
  
  >void print() {
  
  >      for (int i = 0; i < SIZE; i++) {
  
  >          for (int j = 0; j < SIZE; j++) {
  
  >              if (map[i][j] == -1){
  
  >                  System.out.print("    ");
  
  >              }else{
  
  >                  //System.out.print(creatures[map[i][j]].getName());
  
  >                  if(creatures[map[i][j]] instanceof CalabashBrother)
  
  >                      System.out.print("葫芦娃");
  
  >                  else if(creatures[map[i][j]] instanceof Grandpa)
  
  >                      System.out.print("爷爷");
  
  >                  else if(creatures[map[i][j]] instanceof Snake)
  
  >                      System.out.print("蛇精");
  
  >                  else if(creatures[map[i][j]] instanceof Monster)
  
  >                      System.out.print("妖怪");
  
  >                  else
  
  >                      System.out.print("未知");
  
  >              }
  
  >          }
  
  >          System.out.print("\n");
  
  >      }
  
  >  }

## 泛型

  生物类Creature作为父类，包括了所有的生物体类型，因此此处可以引入泛型：
  
  > public class Creature <T> 


## UML类图

!(https://github.com/Dantelian277/java-2019-homeworks/blob/master/4-Types/%E9%99%88%E8%94%93%E9%9D%92-161220014/UML.png)



