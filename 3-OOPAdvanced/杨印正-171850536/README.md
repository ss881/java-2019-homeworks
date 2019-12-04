#代码说明
1.类War包括常量MAX，FIRE等，确定了二维数组的大小以及对阵时葫芦娃长蛇阵所在的具体行位置，包含main函数设置二维数组进行实现需求
2.类LivingBody为父类，各生物体属性有locationx，locationy，与二维数组下标对应，name，葫芦娃为红橙黄绿青蓝紫，老爷爷为爷等。CalabashBrother为子类，属性number用来排序。
3.Location类为阵法类，实现阵法变动，包含各阵法的实现以及删除方法，如长蛇阵法 void changshe（）和 void deletechangshe。
![UML](https://github.com/yyz944600382/Nsywtz/blob/master/homework3.jpg)