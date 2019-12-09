package Creatures;

import BattleField.Space;
import java.lang.reflect.Constructor;

public class Creature<T>
{
    public int x,y;    //位置坐标
    public String name;    //生物名字
    public boolean camp;  //阵营，1为好，0为坏

    public Creature(int i,int j)
    {
        x=i;
        y=j;
        this.name="  ";
        this.camp=true;
    }
    
    public Creature(Creature<T> another)   //复制对象坐标构造函数，创建temp用（Java真烦）
    {
        this.x = another.x;  
        this.y = another.y;
    }

    public Creature(int i,int j,String name)
    {
        x=i;
        y=j;
        this.name=name;
    }

    public Creature(int i,int j,boolean camp)
    {
        x=i;
        y=j;
        this.camp=camp;
    }

    public Creature(int i,int j,String name,boolean camp)
    {
        x=i;
        y=j;
        this.name=name;
        this.camp=camp;
    }

    public void setto(int x,int y,Space filed)
    {
        filed.space[x][y]=this;
        filed.space[x][y].x=x;
        filed.space[x][y].y=y;
    }

    public void moveto(int x,int y,Space filed)
    {
        /*if(this.x==-1 && this.y==-1)    //初始化摆阵的情况
        {
            Creature temp = filed.space[x][y];
            filed.space[x][y]=this;

            temp.x=this.x;
            temp.y=this.y;

            filed.space[x][y].x=x;
            filed.space[x][y].y=y;
        }
        else
        {
            Creature temp = filed.space[x][y];
            filed.space[x][y]=this;

            temp.x=this.x;
            temp.y=this.y;

            filed.space[x][y].x=x;
            filed.space[x][y].y=y;

            filed.space[temp.x][temp.y]=temp;
        }*/

        if(filed.space[x][y]==null)
        {
            Creature temp = new Creature(this);
            
            filed.space[x][y]=this;
            filed.space[x][y].x=x;
            filed.space[x][y].y=y;
            if(temp.x==-1 && temp.y==-1)
            {
                ;
            }
            else
            {
                filed.space[temp.x][temp.y]=null;
            }
            
        }
        else
        {
            Creature temp = filed.space[x][y];
            filed.space[x][y]=this;
            this.x=x;
            this.y=y;
            filed.space[temp.x][temp.y]=temp;
            filed.space[temp.x][temp.y].x=temp.x;
            filed.space[temp.x][temp.y].y=temp.y;
        }
        
    }

    
    


}