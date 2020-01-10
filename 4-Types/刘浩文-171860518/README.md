#### 第四次作业readme
##### 171860518 刘浩文
面向对象概念的利用：
    考虑到本实验中各个对象之间移动更加复杂，因此我用了一个“上帝之手”的类Handler，这样可以在保证尽量满足面向对象的要求时，使代码内容稍微简单一点。

*    Handler创造各个角色，初始化一张地图（map类），把各个角色安排到地图上，并“告诉”地图上的各个Creature来干什么，该动到哪里，并调用map的一个输出函数输出布局。

*    此外 还有一个Position类来刻画一个生物的位置，将位置这一属性抽象出来是考虑到之后的地图会更加复杂，不一定可以用两个int就可以准确*形容。

*    由于Handler是上帝，因此各个生物包括地图，不可以独立于它存在，所以类与类之间的关系我用了强合成Composition。

*    葫芦娃还有颜色这一属性，所以有一个enum COLOR，并且葫芦娃 extends Creature。这样可以体现葫芦娃是一种（特别的）生物这一点。
*    反射，泛型的使用：我在这个实验中拿掉了Creature类中有点不自然的tag标签成员变量，而是通过反射获得someone的name，之后再根据name来判断地图上用什么字母来标示。传入参数someone的类型是T extends Creature。
```
public <T extends Creature> char giveLabel(T someone)
    {
        try
        {
            Field fClass=someone.getClass().getField("name");
            //System.out.print((String)fClass.get(someone)+'\n');
            String someoneName=(String)fClass.get(someone);
            String huluJudge=someoneName.substring(0,3);
            //System.out.print(huluJudge);
            if(huluJudge.equals("Lao")) return 'H';
            else
            {
                switch(someoneName)
                {
                    case "Scorpion": return 'S';
                    case "Oracle": return 'O';
                    case "Impian": return 'I';
                    case "Snake": return 'K';
                    default: return '_';
                }
            }
        }
        catch (Exception e)
        {
            System.out.print(e.getMessage());
            return '_';
        }
    }
```
UML类图：
![UML](https://github.com/Lautstark9217/java-2019-homeworks/blob/master/4-Types/171860518-刘浩文/uml.png)