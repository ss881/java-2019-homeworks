package app;
import java.util.*;
public abstract class Creature //base individual
{
    Creature(String initname,int initx,int inity)
    {
        name = initname;
        placex = initx;
        placey = inity;
    }
    abstract public void setPlace(int x,int y);
//    abstract public char getsk();
//   abstract public int getnum();
//    abstract public boolean getgrbool();
    abstract public int getid();
    abstract public int getx();
    abstract public int gety();
//    abstract public int getorder();
    public static int getcreanum(){return creaturenum;}
    final static int creaturenum = 5;
    String name;
    int id;
    int placex;
    int placey;
}
class HuLuWa extends Creature
{
    static char skin;
    static int num;
    static boolean groupbool;
    static int order;
    static
    {
        num = 0;
        skin = 'H';
        groupbool = true;
        order = 0;
    }
    HuLuWa(String initname,int initx,int inity)
    {
        super(initname,initx,inity);
        num++;
        id = num;
    }
    public void setPlace(int x,int y)
    {
        placex = x;
        placey = y;
    }
    public static int getorder(){return order;}
    public static char getsk() {return skin;}
    public static int getnum()  {return num;}
    public static boolean getgrbool()  {return groupbool;}
     public int getid(){return id;}
    public int getx(){return placex;}
    public int gety(){return placey;}
}
class YeYe extends Creature
{
    static char skin;
    static int num;
    static boolean groupbool;
    static int order;
    static
    {
        num = 0;
        skin = 'Y';
        groupbool = true;
        order = 1;
    }
    YeYe(String initname,int initx,int inity)
    {
        super(initname,initx,inity);
        num++;
        id = num;
    }
    public void setPlace(int x,int y)
    {
        placex = x;
        placey = y;
    }
    public static int getorder(){return order;}
    public static char getsk() {return skin;}
    public static int getnum()  {return num;}
    public static boolean getgrbool()  {return groupbool;}
    public int getid(){return id;}
    public int getx(){return placex;}
    public int gety(){return placey;}
}
class XieZi extends Creature
{
    static char skin;
    static int num;
    static boolean groupbool;
    static int order;
    static
    {
        num = 0;
        skin = 'X';
        groupbool = false;
        order = 2;
    }
    XieZi(String initname,int initx,int inity)
    {
        super(initname,initx,inity);
        num++;
        id = num;
    }
    public void setPlace(int x,int y)
    {
        placex = x;
        placey = y;
    }
    public static int getorder(){return order;}
    public static char getsk() {return skin;}
    public static int getnum()  {return num;}
    public static boolean getgrbool()  {return groupbool;}
    public int getid(){return id;}
    public int getx(){return placex;}
    public int gety(){return placey;}
}
class SheJing extends Creature
{
    static char skin;
    static int num;
    static boolean groupbool;
    static int order;
    static
    {
        num = 0;
        skin = 'S';
        groupbool = false;
        order = 3;
    }
    SheJing(String initname,int initx,int inity)
    {
        super(initname,initx,inity);
        num++;
        id = num;
    }
    public void setPlace(int x,int y)
    {
        placex = x;
        placey = y;
    }
    public static int getorder(){return order;}
    public static char getsk() {return skin;}
    public static int getnum()  {return num;}
    public static boolean getgrbool()  {return groupbool;}
    public int getid(){return id;}
    public int getx(){return placex;}
    public int gety(){return placey;}
}
class LouLuo extends Creature
{
    static char skin;
    static int num;
    static boolean groupbool;
    static int order;
    static
    {
        num = 0;
        skin = 'L';
        groupbool = false;
        order = 4;
    }
    LouLuo(String initname,int initx,int inity)
    {
        super(initname,initx,inity);
        num++;
        id = num;
    }
    public void setPlace(int x,int y)
    {
        placex = x;
        placey = y;
    }
    public static int getorder(){return order;}
    public static char getsk() {return skin;}
    public static int getnum()  {return num;}
    public static boolean getgrbool()  {return groupbool;}
    public int getid(){return id;}
    public int getx(){return placex;}
    public int gety(){return placey;}
}
class CreatureVec
{
    Vector<Creature> creavec;
    CreatureVec()
    {
        creavec = new Vector<Creature>();
    }
    public void addEle(Creature x)
    {
        creavec.addElement(x);
    }
    public int leng()
    {
        return creavec.size();
    }

}