package Ground;

import HuLuBaby.*;

// 一片（多块）地面类型，是一个二维的类似容器的构造
// 虽然它的ground数组对外部类不可见
// 但是它可以通过showTheGroundCondition对外界显示其状态
// 这是为了模拟现实中的地面可见而不可直接使用的属性
public class Ground
{
    OnePieceOfGround[][] ground;
    String name;
    int len;
    int wid;

    Ground()
    {
        ground = new OnePieceOfGround[10][10];
        len = 10;
        wid = 10;
        for (int i = 0; i < len; ++i)
        {
            for(int j = 0; j < wid; ++j)
            {
                ground[i][j] = new OnePieceOfGround();
            }
        }
        name = "untitled";
    }
    // default, create 10 piece of ground
    public Ground(int gr_len, int gr_wid, String gr_name)
    {
        ground = new OnePieceOfGround[gr_len][gr_wid];
        len = gr_len;
        wid = gr_wid;
        for (int i = 0; i < len; ++i)
        {
            for(int j = 0; j < wid; ++j)
            {
                ground[i][j] = new OnePieceOfGround();
            }
        }
        name = gr_name;
    }
    // get pieces of ground by a given size
    public int getGroundSize()
    {
        return len * wid;
    }
    public int getGroundLen()
    {
        return len;
    }
    public int getGroundWid()
    {
        return wid;
    }
    public String getGroundName()
    {
        return name;
    }
    public HuLuBaby getPeopleOnGround(int x, int y)
    {
        return ground[x%len][y%wid].getThePeopleOnTheGround();
    }
    // 上面的几个看名字就知道功能
    public int findAvailableGround()
    {
        for (int i = 0; i < len; ++i)
        {
            for (int j = 0; j < wid; ++j)
            {
                if(ground[i][j].isThisGroundAvailable())
                return i * wid + j;
            }
        }
        return -1;
    }
    //if find it, return the ground index
    //else return -1
    public void showTheGroundCondition(boolean Seq, boolean Color)
    {
        System.out.println(name+" : ");
        for (int i = 0; i < len; ++i)
        {
            for (int j = 0; j < wid; ++j)
            {
                ground[i][j].showTheGroundCondition(Seq, Color);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
    // Seq == true, show "老大"
    // Color == true, show "红娃"
    
    public boolean moveTo(HuLuBaby b, int x, int y)
    {

        if (ground[x][y].isThisGroundAvailable() == false)
            return false;
        else
        {
            ground[x][y].standOnTheGround(b);
            return true;
        }
    }
    // 对于地面上单位状态改变的两个函数
    public boolean moveFromTo(int x1, int y1, int x2, int y2)
    {
        if (ground[x1][y1].peopleValidation == false)
            return false;
        else if (ground[x2][y2].isThisGroundAvailable() == false)
            return false;
        else
        {
            ground[x2][y2].standOnTheGround(ground[x1][y1].standOut());
            return true;
        }
    }

}
// 内部类，一小块地面，只能承载一个单位
class OnePieceOfGround
{
    boolean peopleValidation;
    // 这个属性是在不熟悉null的用法时加上的，为了显示地面上单位的有效性，其实略有点多余，懒得改了（狗头保命）
    HuLuBaby peopleOnTheGround;
    HuLuBaby getThePeopleOnTheGround()
    {
        if (peopleValidation)
            return peopleOnTheGround;
        else
        {
            return null;
            //System.out.println("Error: No");
        }    
    }
    // 控制地面上的单位
    public boolean isThisGroundAvailable()
    {
        return !peopleValidation;
    }
    // 判断地面是否为空
    public boolean standOnTheGround(HuLuBaby peopleToStand)
    {
        if (!isThisGroundAvailable())
            return false;
        else
        {
            peopleOnTheGround = peopleToStand;
            peopleValidation = true;
            return true;
        }
    }
    // 在地面上放置一个单位
    public HuLuBaby standOut()
    {
        peopleValidation = false;
        return peopleOnTheGround;
    }
    // 单位从地面上离开，变成移动中的状态
    public void showTheGroundCondition(boolean Seq, boolean Color)
    {
        if(peopleValidation == false)
            System.out.print(" 空 ");
        else
        {
            if (Seq)
                peopleOnTheGround.shoutOutYourSeq();
            if (Color)
                peopleOnTheGround.shoutOutYourColor();
        }
    }
    // 显示地面状态
    OnePieceOfGround()
    {
        peopleOnTheGround = new HuLuBaby();
        peopleValidation = false;// 单纯因为一开始不知道正常的变量也能置null
    }
}