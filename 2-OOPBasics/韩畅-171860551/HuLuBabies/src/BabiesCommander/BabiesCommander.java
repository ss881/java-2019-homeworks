package BabiesCommander;

import Ground.Ground;
import HuLuBaby.HuLuBaby;

public class BabiesCommander
{   
    BabiesCommander()
    {
        playGround = new Ground(7, 7, "The Playground");
        // 新建一片7 * 7的空地(名称为“操场”)
        boolean[] mark = new boolean[7];
        for (int i = 0; i < 7; ++i){
            mark[i] = false;
        }
        // 上面一段为了记录随机数的生成
        myHuLuBabies = new HuLuBaby[7];
        for (int i = 0; i < 7; ++i)
        {
            int randomNum = 0;
            while((randomNum = getRandomNumber()) < 7 && mark[randomNum] == true){}
            mark[randomNum] = true;
            // 记录该位置已经被占用
            myHuLuBabies[i] = new HuLuBaby(i);
            orderThebabiestoStandOnThePlayground(myHuLuBabies[i], 0, randomNum);
            // 向葫芦娃发送随机生成的位置移动命令
        }
    }
    // 我能看到一片操场以及上面的单位
    public Ground playGround;
    // 我至少要知道我的葫芦娃是哪些
    public HuLuBaby[] myHuLuBabies;
    // 私有函数，生成随机数使用，模拟老爷爷的随机大脑（笑）
    private int getRandomNumber()
    {
        return (int)(Math.random()*7);
    }

    // 向葫芦娃发送移动命令
    // 移动后环顾四周，更新操场显示
    public void orderThebabiestoStandOnThePlayground(HuLuBaby b, int x, int y)
    {
        System.out.print("Send message to ");
        b.shoutOutYourSeq();
        System.out.println("order him to stand on the play ground ( " + x + " , " + y + " ) ");
        b.standOnTheGround(playGround, x, y);
        lookAround(true, false);
    }

    // 调换操场上某两个位置上的单位
    public void changeTwoBabyPosition(int y1, int y2)
    {
        orderThebabiestoStandOnThePlayground(playGround.getPeopleOnGround(0, y1), 1, y1);
        orderThebabiestoStandOnThePlayground(playGround.getPeopleOnGround(0, y2), 1, y2);
        orderThebabiestoStandOnThePlayground(playGround.getPeopleOnGround(1, y1), 0, y2);
        orderThebabiestoStandOnThePlayground(playGround.getPeopleOnGround(1, y2), 0, y1);
        
    }
    // 管理员首先看向操场，观察到操场上的单位后，给该单位发送移动指令

    
    // 由老爷爷（葫芦娃管理员）环顾操场上的人员情况
    public void lookAround(boolean Seq, boolean Color)
    {
        playGround.showTheGroundCondition(Seq, Color);
    }
    // 为了模拟打乱过程，由老爷爷随机两两调换10次葫芦娃的位置
    public void changePositionRandomly()
    {
        System.out.println("-------------------Start to change the babies position randomly----------------------------------\n");
        System.out.println("_________________________________________________________________________________________________\n");
        System.out.println("_________________________________________________________________________________________________\n");
        for (int i = 0; i < 10; ++i)
        {
            int pos1 = getRandomNumber();
            int pos2 = getRandomNumber();
            while(pos2 == pos1 && (pos2 = getRandomNumber()) < 7);
            changeTwoBabyPosition(pos1, pos2);
        }
        System.out.println("_________________________________________________________________________________________________\n");
        System.out.println("_________________________________________________________________________________________________\n");
        System.out.println("-------------------Success to change the babies position randomly----------------------------------\n");

    }
    public void bubbleSortShoutSeq()
    {
        System.out.println("-------------------Start to Bubblesort----------------------------------\n");
        System.out.println("_________________________________________________________________________________________________\n");
        System.out.println("_________________________________________________________________________________________________\n");

        for (int i = 0; i < 6; ++i)
        {
            for (int j = 0; j < 6 - i; ++j)
            {
                if (playGround.getPeopleOnGround(0, j).getId() > playGround.getPeopleOnGround(0, j+1).getId())
                    changeTwoBabyPosition(j, j+1);
                // 由老爷爷发送bubble sort 的相关指令
            }
        }
        System.out.println("_________________________________________________________________________________________________\n");
        System.out.println("_________________________________________________________________________________________________\n");
        System.out.println("-------------------end to Bubblesort----------------------------------\n");
    }
    public void BisectionSortShoutColor(int st, int ed)
    {
        // 不太清楚二分排序到底指的是归并还是快排，先用快排实现的
        if (st >= ed)
            return;
        else if (ed - st == 1)
        {
            if (playGround.getPeopleOnGround(0, st).getId() > playGround.getPeopleOnGround(0, ed).getId())
                changeTwoBabyPosition(st, ed);
            return;
        }
        // 上面是base情况
        int pivot = playGround.getPeopleOnGround(0, st).getId();
        int point = st+1;
        for (int i = st + 1; i <= ed; ++i)
        {
            if (playGround.getPeopleOnGround(0, i).getId() < pivot)
            {
                if (point != i)
                    changeTwoBabyPosition(point, i);
                    // 依然是由老爷爷发送的指令
                point++;
            }
        }
        if (st != point - 1)
            changeTwoBabyPosition(st, point - 1);
        BisectionSortShoutColor(st, point - 2);
        BisectionSortShoutColor(point, ed);
        // 递归排序
        lookAround(false, true);
    }
    public static void main(String[] args) throws Exception 
    {
        BabiesCommander oldMan = new BabiesCommander();
        // new 一个 老爷爷对象，可以向每个葫芦娃发送移动命令，由葫芦娃自己引用自身在操场上移动
        // 构造函数中已经命令葫芦娃随机地移动到操场上
        oldMan.lookAround(true, false);
        // 先刷新一下操场的显示
        oldMan.bubbleSortShoutSeq();
        // 开始冒泡排序
        oldMan.changePositionRandomly();
        // 开始快速排序
        System.out.println("-------------------Start to BisectionSort----------------------------------\n");
        System.out.println("_________________________________________________________________________________________________\n");
        System.out.println("_________________________________________________________________________________________________\n");
        oldMan.BisectionSortShoutColor(0, 6);
        System.out.println("_________________________________________________________________________________________________\n");
        System.out.println("_________________________________________________________________________________________________\n");
        System.out.println("-------------------End to BisectionSort----------------------------------\n");

    }

}
