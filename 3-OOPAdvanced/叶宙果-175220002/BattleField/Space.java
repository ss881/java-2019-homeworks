package BattleField;

import Creatures.Creature;


public class Space
{
    int x;
    int y;
    public Creature[][] space;

    public Space(int x,int y)
    {
        this.x=x;
        this.y=y;
        this.space=new Creature[x][y];
        for(int i=0;i<x;i++)
        {
            for(int j=0;j<y;j++)
            {
                this.space[i][j]=new Creature(i,j);
            }
        }
    }

    public Creature getcreature(int i,int j)
    {

        return this.space[i][j];
    }

    /*public void showthemap(int x,int y)      //输出地图
    {
        for(int i=0;i<x;i++)
        {
            for(int j=0;j<x;j++)
            {
                System.out.print(this.space[i][j].name);
            }
            System.out.print("\n");
        }
    }

    public void initmap(int x,int y)      //初始化地图
    {
        this.x=x;
        this.y=y;
        this.space=new Creature[x][y];
        for(int i=0;i<x;i++)
        {
            for(int j=0;j<y;j++)
            {
                this.space[i][j]=new Creature(i,j);
            }
        }
    }*/

    public void showthemap(int x,int y)      //输出地图
    {
        for(int i=0;i<x;i++)
        {
            for(int j=0;j<x;j++)
            {
                if(this.space[i][j]==null)
                {
                    System.out.print("  ");
                }
                else
                {
                    System.out.print(this.space[i][j].name);
                }
                
            }
            System.out.print("\n");
        }
    }

    public void initmap(int x,int y)      //初始化地图
    {
        this.x=x;
        this.y=y;
        this.space=new Creature[x][y];
        for(int i=0;i<x;i++)
        {
            for(int j=0;j<y;j++)
            {
                this.space[i][j]=null;
            }
        }
    }
}