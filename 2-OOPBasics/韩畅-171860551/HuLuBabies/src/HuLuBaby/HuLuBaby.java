package HuLuBaby;

import Ground.Ground;
import HuLuBaby.HuLuSeq;

public class HuLuBaby
 {
    int id;
    int pos_x;
    int pos_y;
    // 序号和位置，没有名称和颜色————由资源文件配置

    public void standOnTheGround(Ground myGr, int x, int y)
    {
        // 收到由老爷爷发来的指令，之后与ground类进行交互，实现位置的改变
        shoutOutYourSeq();
        System.out.println(" has recieved the order to stand on the ground");
        System.out.println("( " + x + " , " + y + " )");

        // 先检查自己是否处于地面上
        // 再进行相应的移动操作
        if (amIOnTheGround(myGr, pos_x, pos_y))
        {
            System.out.print("\nMoving From ");
            shoutOutYourPosition();
            System.out.print("\nTo...");
            myGr.moveFromTo(pos_x, pos_y, x, y);
        }
        else
        {
            System.out.println("\nMoving to...");
            myGr.moveTo(this, x, y);
        }
        pos_x = x;
        pos_y = y;
        // 自己心里记一下自己的位置————其实这个也可以自己瞅一眼地面，但是总觉得好蠢啊
        System.out.println("( " + x + " , " + y +" )");
        System.out.println("moving successfully\n");
    }

    boolean amIOnTheGround(Ground myGr, int x , int y)
    {
        if (x < 0 || y < 0 || x >= myGr.getGroundLen() || y >= myGr.getGroundWid())
            return false;
        else
            return true;
    }


    public HuLuBaby()
    {
        id = 0;
        pos_x = -1;
        pos_y = -1;
    }
    public int getId()
    {
        return id;
    }
    public int getPosX()
    {
        return pos_x;
    }
    public int getPosY()
    {
        return pos_y;
    }
    boolean amIHuLuBaby(int ini_id)
    {
        if(ini_id < 0 || ini_id > 6)
            return false;
        else
            return true;
    }
    // 参数规格化检查
    boolean amIInQueue(int pos_tem)
    {
        if(pos_tem < 0 || pos_tem > 6)
            return false;
        else
            return true;
    }
    public HuLuBaby(int ini_id)
    {
       if (!amIHuLuBaby(ini_id))
       {
            System.out.println("Error: Initial bad id\n");
       }
       else
       {
            id = ini_id;
       }
       pos_x = -1;
       pos_y = -1;
    }
    // 回应老爷爷的应答，进行报数
    public void shoutOutYourSeq()
    {
        System.out.print(HuLuSeq.seqName[id]);
    }
    public void shoutOutYourColor()
    {
        System.out.print(HuLuSeq.colorName[id]);
    }
    public void shoutOutYourPosition()
    {
        System.out.print("( " + pos_x + " , " + pos_y +" )");
    }

 }
