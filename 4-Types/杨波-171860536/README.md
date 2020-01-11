## 泛型
  类似于c++中的模板，不过有不小的区别。java中的泛型更像是提供了一种隐式的类型转换、类型检查，由类型擦除到"基类"，再从"基类"隐式转换到原先的类，泛型符号(\<T\>中的T)也提供相应的检查功能，一般不同的符号即使擦除到相同的"基类"也不能替换。
  不过目前写的代码中也没太多要用到泛型的地方，最后把阵型改了一下，改成了用泛型实现。一个抽象的基类:
```
	abstract class Formation<T extends Creature>{
		private T leader;
		protected ArrayList<Creature> members;
		...
	}
```
  根据类的定义也可以看出，不同的Formation根据leader的类型来划分，不同的leader可以拥有不同的阵型，不过在目前的代码中没有体现出来，葫芦娃和蝎子精享有共同的阵型。下面是一个派生的具体的类:
```
	class LineFormation<T extends Creature> extends Formation<T>{
		...
	}
```
  其他阵型的派生类也都类似，如果要想不同的队长享有不同的阵型可以在前面的\<T extends Creature\>中进行修改，如改成\<T extends Monster \>由妖怪专享，不过也有不少局限性。简单说明一下这个派生，基类和派生类中的泛型符号都是T，所以派生类中保证了与基类中变量的一致，这个T也可根据需要限定范围，这里是最基础的"extends Creature"。
  一个具体使用的例子如下:

```
	ScorpionMonster scorpionMonster = new ScorpionMonster();
	...
	scopionMonster.prepareFormation(new LineFormation<ScorpionMonster>(0,0,Direction));
	...
```

## 反射
  反射，用于获得运行时类的信息。功能很强大，可以获得各个类的各个方法、变量等并进行操作，对于编译时还未得到的类也能根据类的名字等在运行时操作。不过在目前葫芦娃中真的找不到哪里需要用到反射的，就简单地加了一段显示一个类中各个的方法名称的代码...只显示当前类的信息，不显示基类的。

  main函数在FightFormation类。