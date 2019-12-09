import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class CalabashArrangement {
    public static void main(String[] args) {
        /*
1. 假设存在一个`NxN`的二维空间（`N>10`)，该空间中的任意一个位置坐标上可站立一个生物体（葫芦娃、老爷爷、蛇精、蝎子精、小喽啰均属于生物体）；
2. 请让初始乱序的七个兄弟按下图所示阵型中的长蛇形依序（老大至老七）站队；
3. 请在图中选择一个阵型（长蛇除外）让蝎子精领若干小喽啰站队；
4. 将葫芦兄弟的长蛇阵营和蝎子精小喽啰阵营放置于二位空间中，形成对峙局面；
5. 请选择合适位置将老爷爷和蛇精放置于空间中，为各自一方加油助威；
6. 将上述对峙局面打印输出；
7. 请让蝎子精小喽啰阵营变换一个阵法（长蛇除外），重复4-6步。
*/
        System.out.println("上古洪荒，葫芦山崩，妖精肆虐，苍生罹难……\n\n老汉慈悲，种七色葫芦，以平息妖孽……\n\n时日，老汉和葫芦娃们突然和妖精们遭遇于古战场……\n");//背景介绍
        Grandpa grandpa = new Grandpa();
        Snake snake = new Snake();
        snake.Talk("哈哈哈！终于抓到你这个老家伙了！今天要给你点颜色瞧瞧！");
        snake.follower[2].Talk("好的，大王！\n");
        grandpa.Talk("孩子们，快来保护爷爷！");
        System.out.println("老爷爷指挥葫芦娃排成一字长蛇阵，并按照老大到老七排序\n");

        grandpa.Conduct(grandpa.calabashbrothers.get(0), 7, 5);
        grandpa.Conduct(grandpa.calabashbrothers.get(1), 8, 5);
        grandpa.Conduct(grandpa.calabashbrothers.get(2), 9, 5);
        grandpa.Conduct(grandpa.calabashbrothers.get(3), 10, 5);
        grandpa.Conduct(grandpa.calabashbrothers.get(4), 11, 5);
        grandpa.Conduct(grandpa.calabashbrothers.get(5), 12, 5);
        grandpa.Conduct(grandpa.calabashbrothers.get(6), 13, 5);
        //先乱序排成一排

        for (int i = 0; i < 6; i++) {       //爷爷用冒泡排序的方法指挥葫芦娃按照老大到老七拍成一排
            for (int j = 0; j < 6; j++) {
                if(((Calabash)Map.map[7 + j][5]).rank > ((Calabash)Map.map[8 + j][5]).rank)//爷爷指挥两个葫芦娃互换位置
                {
                    grandpa.Conduct(Map.map[7 + j][5], 7 + j, 6);
                    grandpa.Conduct(Map.map[8 + j][5], 7 + j, 5);
                    grandpa.Conduct(Map.map[7 + j][6], 8 + j, 5);
                }
            }
        }

        snake.Talk("呔！你这糟老头子，坏得很！小的们，快快布阵，我要抓住这七个葫芦娃，炼成仙丹，长生不老！");
        snake.follower[0].Talk("遵命，大王！这次一定要 GIVE THEM SOME COLOR TO SEE SEE!");
        snake.Talk("说了多少次，不要叫我大王，要叫我女王大人！");
        snake.follower[0].Talk("好的，大王！");
        System.out.println("蛇精指挥蝎子精领小喽啰们布成了鹤翼阵\n");

        snake.Conduct(snake.follower[0], 8, 10);
        snake.Conduct(snake.follower[1], 9, 11);
        snake.Conduct(snake.follower[2], 10, 12);
        snake.Conduct(snake.follower[3], 11, 13);
        snake.Conduct(snake.follower[4], 11, 14);
        snake.Conduct(snake.follower[5], 10, 15);
        snake.Conduct(snake.follower[6], 9, 16);
        snake.Conduct(snake.follower[7], 8, 17);

        Map.Print();

        grandpa.Talk("孩子们加油，我来为你们加油助威！");
        grandpa.Goto(11,3);
        grandpa.Cheer();
        System.out.println("【老爷爷】加入了战场，为葫芦娃们加油助威 [葫芦娃方士气每回合 +10]\n");

        snake.Talk("小的们，我来也，不准松懈，一定要给我抓住葫芦娃！");
        snake.Goto(11, 17);
        snake.Cheer();
        System.out.println("【蛇精】加入了战场，为葫芦娃们加油助威 [妖精方士气每回合 +10]\n");

        Map.Print();

        System.out.println("葫芦娃与妖精们斗智斗勇，双方僵持不下……\n");

        snake.follower[0].Talk("啊呀呀呀呀呀呀！你们这些小屁孩，好生难缠！不过我有的是招数收拾你们！");
        System.out.println("蛇精指挥蝎子精领小喽啰们将阵法变成了雁行阵\n");

        snake.Conduct(snake.follower[0], 8, 10);
        snake.Conduct(snake.follower[1], 9, 11);
        snake.Conduct(snake.follower[2], 10, 12);
        snake.Conduct(snake.follower[3], 11, 13);
        snake.Conduct(snake.follower[4], 12, 14);
        snake.Conduct(snake.follower[5], 13, 15);
        snake.Conduct(snake.follower[6], 14, 16);
        snake.Conduct(snake.follower[7], 15, 17);

        grandpa.Goto(4, 5);
        grandpa.Cheer();

        System.out.println();

        snake.Talk("我来带头冲锋！");
        snake.Goto(12, 9);
        snake.Cheer();

        System.out.println();

        Map.Print();

        System.out.println("\n双方又陷入了对峙……\n");

        System.out.println("\n未完待续……");

        return;
    }
}

class Creature{
    public String name = "";
    public int x = 0;
    public int y = 0;
    //x和y表示这个生物在地图上的位置

    Creature(String name) { //构造器
        this.name = name;
    }

    public void Goto(int nx, int ny){   //Goto方法，用于行走
        Map.map[x][y] = null;
        x = nx;
        y = ny;
        Map.map[x][y] = this;
    }

    public void Talk(String s){     //Talk方法，用于说话
        System.out.println(name + "：" + s);
    }
}

class Calabash extends Creature{    //葫芦娃类
    static int morale = 100;//士气值
    public int rank = 0;//次序
    public void skill(){};//虚方法，独特的技能
    Calabash(String name, int i){
        super(name);    //调用父类的构造器
        rank = i;
    }
}

class Conductor extends Creature{       //指挥官类，爷爷和蛇精的父类
    Conductor(String s){
        super(s);
    }

    public void Cheer(){};//Cheer方法，加油助威
    public void Conduct(Creature c, int nx, int ny){    //指挥自己的一方排兵布阵
        c.Goto(nx, ny);
    };
}


final class Grandpa extends Conductor{     //爷爷类
    public static ArrayList<Calabash> calabashbrothers = new ArrayList<Calabash>();//爷爷心中惦记着七个葫芦娃（的引用）
    Grandpa(){
        super("爷爷");
        calabashbrothers.add(new Calabash("大娃", 1));
        calabashbrothers.add(new Calabash("二娃", 2));
        calabashbrothers.add(new Calabash("三娃", 3));
        calabashbrothers.add(new Calabash("四娃", 4));
        calabashbrothers.add(new Calabash("五娃", 5));
        calabashbrothers.add(new Calabash("六娃", 6));
        calabashbrothers.add(new Calabash("七娃", 7));
        Collections.shuffle(calabashbrothers);//打乱初始顺序
    }

    @Override   //重写（覆写）
    public void Cheer(){
        Random r = new Random();
        int i = r.nextInt(5);
        switch (i){
            case 0:Talk("加油！孩儿们！");break;
            case 1:Talk("稳住，我们能赢！");break;
            case 2:Talk("征程就在眼前！");break;
            case 3:Talk("恐惧屈于信仰！");break;
            case 4:Talk("福禄，万世长存！");break;
        }
    }

    /*//! @Override   错误！参数、返回值不同是重载(Overload)，不是重写(Override)！
    public void Conduct(Calabash c, int nx, int ny){     //指挥葫芦娃排兵布阵
        c.Goto(nx, ny);
    }*/
    //注意：此处有误！若按这种方式重载则不能正确调用！
}

class Leprechaun extends Creature{    //妖精类
    static int morale = 100;//士气值
    public void evilskill(){};//虚方法，邪恶的技能
    Leprechaun(String name){
        super(name);    //调用父类的构造器
    }
}

final class Snake extends Conductor{     //蛇精类
    public static Leprechaun[] follower = new Leprechaun[8];//蛇精手下有一堆小喽啰（的引用）
    Snake(){
        super("蛇精");
        follower[0] = new Leprechaun("蝎子精");//委屈蝎子精一下，和小喽啰并排
        follower[1] = new Leprechaun("蝙蝠精");
        follower[2] = new Leprechaun("蛤蟆精");
        follower[3] = new Leprechaun("蜈蚣精");
        follower[4] = new Leprechaun("天牛精");
        follower[5] = new Leprechaun("黄蜂精");
        follower[6] = new Leprechaun("野猪精");
        follower[7] = new Leprechaun("蜘蛛精");
    }

    @Override
    public void Cheer(){    //Cheer方法，加油助威
        Random r = new Random();
        int i = r.nextInt(5);
        switch (i){
            case 0:Talk("加油！小的们！");break;
            case 1:Talk("猥琐发育，别浪！");break;
            case 2:Talk("不要叫我大王！");break;
            case 3:Talk("要叫我女王大人！");break;
            case 4:Talk("如意如意，顺我心意！");break;
        }
    }

    /*public void Conduct(Leprechaun l, int nx, int ny){     //指挥小喽啰排兵布阵
        l.Goto(nx, ny);
    }*/
    //注意：此处有误！若按这种方式重载则不能正确调用！
}

class Map{  //地图类
    public static Creature[][] map= new Creature[20][20];//初始化为一个20 * 20的地图，地图存放对象的引用
                public static void Print()//静态方法，用于打印整个地图
                {
                    System.out.println("打印地图：");
                    for (int i = 0; i < 20; i++) {
                        for (int j = 0; j < 20; j++) {
                if(map[i][j] != null) {
                    String s = String.format("%s\t", Map.map[i][j].name);
                    System.out.print(s);
                }
                else
                {
                    String b = String.format("%s\t\t", "0");//为了体现战场区块的量子化，再没有站生物的地方输出0，用制表符对齐
                    System.out.print(b);
                }
            }
            System.out.println();
        }
    }
}
