package cn.whh.map;

import cn.whh.creature.*;
import cn.whh.formation.Formation;

public class Field {
    final public int width=13;
    final public int height=10;
    private Creature[][] creatures;

    private volatile int goodGuysCount;
    private volatile int badGuysCount;

    boolean isStop;

    public  Field()
    {
        this.isStop=true;
        this.goodGuysCount=0;
        this.badGuysCount=0;
        this.creatures=new Creature[height][width];
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                creatures[i][j]=new Land();
            }
        }
    }

    public void Add(Formation formation)
    {
        Creature[][] c=formation.getCreatures();
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                if(!(c[i][j] instanceof Land))
                {
                    creatures[i][j]=c[i][j];
                    if(c[i][j] instanceof Huluwa ||c[i][j] instanceof Grandpa) goodGuysCount++;
                    else if(c[i][j] instanceof Snake || c[i][j] instanceof Scorpion || c[i][j] instanceof Sodier) badGuysCount++;
                }
            }
        }
    }

    public void Remove(Formation formation)
    {
        Creature[][] c=formation.getCreatures();
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                if(!(c[i][j] instanceof Land))
                {
                    creatures[i][j]=new Land();
                    if(c[i][j] instanceof Huluwa ||c[i][j] instanceof Grandpa) goodGuysCount--;
                    else if(c[i][j] instanceof Snake || c[i][j] instanceof Scorpion || c[i][j] instanceof Sodier) badGuysCount--;
                }
            }
        }
    }

    public <T extends Creature> void Add(int x, int y, T creature)
    {
        creatures[x][y]=creature;
        creatures[x][y].setPosition(x,y);
        if(creature instanceof Huluwa ||creature instanceof Grandpa) goodGuysCount++;
        else if(creature instanceof Snake ||creature instanceof Scorpion || creature instanceof Sodier) badGuysCount++;
    }

    public boolean hasCreature(Creature[][] map)
    {
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                if(!(map[i][j] instanceof Land)) return true;
            }
        }

        return false;
    }

    public Creature Get(int x, int y)
    {
        return creatures[x][y];
    }

    public boolean isInterrupt(){return this.isStop;}

    public void setState(boolean state) {isStop=state;}

    public int getBadGuysCount(){return badGuysCount;}

    public void setBadGuysCount(int guys){badGuysCount=guys;}

    public int getGoodGuysCount(){return goodGuysCount;}

    public void setGoodGuysCount(int guys){goodGuysCount=guys;}

    public Creature[][] getCreatures()
    {
        //return creatures; //不能直接返回这个二维数组，因为每次返回同一个内存的数据，后面不断修改之后，前面储存的也会被修改了
        Creature[][] tmp=new Creature[height][width];
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                if(creatures[i][j] instanceof Land) tmp[i][j]=new Land((Land)creatures[i][j]);
                else if(creatures[i][j] instanceof Huluwa) tmp[i][j]=new Huluwa((Huluwa)creatures[i][j]);
                else if(creatures[i][j] instanceof Sodier) tmp[i][j]=new Sodier((Sodier)creatures[i][j]);
                else if(creatures[i][j] instanceof Scorpion) tmp[i][j]=new Scorpion((Scorpion) creatures[i][j]);
                else if(creatures[i][j] instanceof Grandpa) tmp[i][j]=new Grandpa((Grandpa)creatures[i][j]);
                else if(creatures[i][j] instanceof Snake) tmp[i][j]=new Snake((Snake)creatures[i][j]);
               //tmp[i][j]=creatures[i][j];
            }
        }

        return tmp;
    }

    //0:上  1：右  2:下   3:左
    //creature会向target靠近，每次往四个方向移动一格
    public void move(Creature creature, Position pos, int direction)
    {
        int x=pos.getX(),y=pos.getY();

        if(direction==0)
        {
            Creature up=null;
            if(x-1>=0) up=creatures[x-1][y];
            else return;
            if(up instanceof Land /*|| !up.isAlive()*/)
            {
                creatures[x][y]=new Land();
                creatures[x-1][y]=creature;
                creature.setPosition(x-1,y);
            }

            else /*if(!up.isAlive())*/ //遇到尸体时先看一下其他方向有没有路，实在没有了再覆盖尸体
            {
                if(!up.isAlive())
                {
                    creatures[x][y]=new Land();
                    creatures[x-1][y]=creature;
                    creature.setPosition(x-1,y);
                }
                else if(y+1<width&&creatures[x][y+1] instanceof Land) move(creature,pos,1);
                else if(x+1<height&&creatures[x+1][y] instanceof Land) move(creature,pos,2);
                else if(y-1>=0&&creatures[x][y-1] instanceof Land) move(creature,pos,3);

            }
        }

        else if(direction==1)
        {
            Creature right=null;
            if(y+1<width) right=creatures[x][y+1];
            else return;
            if(right instanceof Land /*|| !right.isAlive()*/)
            {
                creatures[x][y]=new Land();
                creatures[x][y+1]=creature;
                creature.setPosition(x,y+1);
            }

            else /*if(!right.isAlive())*/
            {
                if(!right.isAlive())
                {
                    creatures[x][y]=new Land();
                    creatures[x][y+1]=creature;
                    creature.setPosition(x,y+1);
                }
                else if(x-1>=0&&creatures[x-1][y] instanceof Land) move(creature,pos,0);
                else if(x+1<height&&creatures[x+1][y] instanceof Land) move(creature,pos,2);
                else if(y-1>=0&&creatures[x][y-1] instanceof Land) move(creature,pos,3);

            }
        }

        else if(direction==2)
        {
            Creature down=null;
            if(x+1<height) down=creatures[x+1][y];
            else return;
            if(down instanceof Land /*|| !down.isAlive()*/)
            {
                creatures[x][y]=new Land();
                creatures[x+1][y]=creature;
                creature.setPosition(x+1,y);
            }

            else /*if(!down.isAlive())*/
            {
                if(!down.isAlive())
                {
                    creatures[x][y]=new Land();
                    creatures[x+1][y]=creature;
                    creature.setPosition(x+1,y);
                }
                else if(x-1>=0&&creatures[x-1][y] instanceof Land) move(creature,pos,0);
                else if(y+1<width&&creatures[x][y+1] instanceof Land) move(creature,pos,1);
                else if(y-1>=0&&creatures[x][y-1] instanceof Land) move(creature,pos,3);

            }
        }

        else if(direction==3)
        {
            Creature left=null;
            if(y-1>=0) left=creatures[x][y-1];
            else return;
            if(left instanceof Land /*|| !left.isAlive()*/)
            {
                creatures[x][y]=new Land();
                creatures[x][y-1]=creature;
                creature.setPosition(x,y-1);
            }

            else /*if(!left.isAlive())*/
            {
                if(!left.isAlive())
                {
                    creatures[x][y]=new Land();
                    creatures[x][y-1]=creature;
                    creature.setPosition(x,y-1);
                }
                else if(x-1>=0&&creatures[x-1][y] instanceof Land) move(creature,pos,0);
                else if(y+1<width&&creatures[x][y+1] instanceof Land) move(creature,pos,1);
                else if(x+1<height&&creatures[x+1][y] instanceof Land) move(creature,pos,2);

            }
        }
    }

    public void cleanField()
    {
        this.isStop=true;
        this.goodGuysCount=0;
        this.badGuysCount=0;
        this.creatures=new Creature[height][width];
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                creatures[i][j]=new Land();
            }
        }
    }
}
