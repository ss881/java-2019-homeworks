# 第四次作业
## 反射机制
### Master——主类
```
        Class<?> class1 = Master.class;
        Method method = class1.getMethod("Creat");
        method.invoke(class1.newInstance());

        method = class1.getMethod("Set_pos");
        method.invoke(class1.newInstance());

        method = class1.getMethod("Opposition1");
        method.invoke(class1.newInstance());

        method = class1.getMethod("Opposition2");
        method.invoke(class1.newInstance());
```
通过反射机制直接使用这个类的方法。无需实例化对象。
## 泛型机制
### Ground类——场地类 
将Ground设为泛型类，
```
public class Ground<T extends Creature>
```
通过反射机制创建泛型二维数组，
```
array=(T[][])Array.newInstance(type,N,N);
```
Ground类中其余Creature换成T。