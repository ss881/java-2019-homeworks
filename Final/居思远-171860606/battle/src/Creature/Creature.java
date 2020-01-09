package Creature;

import Map.Map;
import javafx.application.Platform;
import sample.Controller;
import sample.Main;

public class Creature implements Runnable{

    @Override
    public void run() {

        while(alive==true && Main.status == 1) {
            if(id==8)
            {
                int tempid = -1;
                for(int jk=0;jk<7;jk++)
                {
                    if(Main.hulu[jk].alive==true)
                    {
                        tempid=jk;break;
                    }
                }
                if(tempid==-1)
                    rush=true;
            }
            else if(id==16||id==17)
            {
                int tempid = -1;
                for(int jk=0;jk<7;jk++)
                {
                    if(Main.monster[jk].alive==true)
                    {
                        tempid=jk;break;
                    }
                }
                if(tempid==-1)
                    rush=true;
            }
            if(Main.pause==false) {
                synchronized (Main.map) {

                    if (alive == false)
                        break;
                    if (rush == true) {
                        searchEnemy();
                        Main.drawByMap();
                    }
                }
            }
            int total1=0,total2=0;
            for(int jk=0;jk<7;jk++)
            {
                if(Main.hulu[jk].alive==true)
                    total1++;
            }
            if(Main.grandpa.alive==true)
                total1++;
            for(int jk=0;jk<7;jk++)
            {
                if(Main.monster[jk].alive==true)
                    total2++;
            }
            if(Main.scorpion.alive==true)
                total2++;
            if(Main.snake.alive==true)
                total2++;
            if(total1==0)
            {
                Main.endBattle(1);
                Main.status = 0;
            }
            if(total2==0)
            {
                Main.endBattle(0);
                Main.status = 0;
            }
            if(alive==true&&Main.status==1)
            {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            }
        }
    }
    private String name;
    private int id;
    private int state;
    protected boolean alive;
    protected int camp;   //阵营，0为葫芦娃阵营，1为蛇精阵营
    protected boolean rush;     //是否开始就可以移动
    private int map[][]=new int[15][15];

    Creature()
    {}

    Creature(int id,String name)
    {
        this.id=id;
        this.name=name;
    }

    protected void setId(int id)
    {this.id=id;}
    protected void setName(String name)
    {this.name=name;}
    protected void setCamp(int camp)
    {this.camp=camp;}

    public void place(int x, int y)
    {
        if(Main.map.getX(id)!=-1)
        {
            Main.map.setMap(Main.map.getX(id),Main.map.getY(id),-1);
        }
        Main.map.setMap(x,y,id);
    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }

    public String getName()
    {return name;}
    public int getId()
    {return id;}
    public int getState()
    {return state;}
    public boolean getAlive()
    {return alive;}
    public int getCamp()
    {return camp;}

    public void searchEnemy()
    {
        int tempx=Main.map.getX(id);
        int tempy=Main.map.getY(id);
        int min=1000,tx=0,ty=0,tid=-1,rid=-1;
        if(camp==0)
        {
            for(int jk=0;jk<7;jk++)
            {
                if(Main.monster[jk].alive==true)
                {
                    tid = Main.monster[jk].getId();
                    tx = Math.abs(tempx-Main.map.getX(tid));
                    ty = Math.abs(tempy-Main.map.getY(tid));
                    if(tx+ty<min)
                    {
                        min = tx + ty;
                        rid = tid;
                    }
                }
            }
            if(Main.scorpion.alive==true)
            {
                tid = Main.scorpion.getId();
                tx = Math.abs(tempx-Main.map.getX(tid));
                ty = Math.abs(tempy-Main.map.getY(tid));
                if(tx+ty<min)
                {
                    min = tx + ty;
                    rid = tid;
                }
            }
            if(Main.snake.alive==true)
            {
                tid = Main.snake.getId();
                tx = Math.abs(tempx-Main.map.getX(tid));
                ty = Math.abs(tempy-Main.map.getY(tid));
                if(tx+ty<min)
                {
                    min = tx + ty;
                    rid = tid;
                }
            }
            if(rid==-1)
                return;
            if(Math.abs(tempx-Main.map.getX(rid)) >= Math.abs(tempy-Main.map.getY(rid)))
            {
                if(tempx > Main.map.getX(rid))
                    {
                        if(tempy > Main.map.getY(rid))
                            moveOne(3,2,1,4);
                        else
                            moveOne(3,1,2,4);
                    }
                else
                {
                    if(tempy > Main.map.getY(rid))
                        moveOne(4,2,1,3);
                    else
                        moveOne(4,1,2,3);
                }
            }
            else
            {
                if(tempy > Main.map.getY(rid))
                {
                    if(tempx > Main.map.getX(rid))
                        moveOne(2,3,4,1);
                    else
                        moveOne(2,4,3,1);
                }
                else
                {
                    if(tempx > Main.map.getX(rid))
                        moveOne(1,3,4,2);
                    else
                        moveOne(1,4,3,2);
                }
            }
        }
        else if(camp==1)
        {
            for(int jk=0;jk<7;jk++)
            {
                if(Main.hulu[jk].alive==true)
                {
                    tid = Main.hulu[jk].getId();
                    tx = Math.abs(tempx-Main.map.getX(tid));
                    ty = Math.abs(tempy-Main.map.getY(tid));
                    if(tx+ty<min)
                    {
                        min = tx + ty;
                        rid = tid;
                    }
                }
            }
            if(Main.grandpa.alive==true)
            {
                tid = Main.grandpa.getId();
                tx = Math.abs(tempx-Main.map.getX(tid));
                ty = Math.abs(tempy-Main.map.getY(tid));
                if(tx+ty<min)
                {
                    min = tx + ty;
                    rid = tid;
                }
            }
            if(rid==-1)
                return;
            if(Math.abs(tempx-Main.map.getX(rid)) >= Math.abs(tempy-Main.map.getY(rid)))
            {
                if(tempx > Main.map.getX(rid))
                {
                    if(tempy > Main.map.getY(rid))
                        moveOne(3,2,1,4);
                    else
                        moveOne(3,1,2,4);
                }
                else
                {
                    if(tempy > Main.map.getY(rid))
                        moveOne(4,2,1,3);
                    else
                        moveOne(4,1,2,3);
                }
            }
            else
            {
                if(tempy > Main.map.getY(rid))
                {
                    if(tempx > Main.map.getX(rid))
                        moveOne(2,3,4,1);
                    else
                        moveOne(2,4,3,1);
                }
                else
                {
                    if(tempx > Main.map.getX(rid))
                        moveOne(1,3,4,2);
                    else
                        moveOne(1,4,3,2);
                }
            }
        }
    }

    public void getMap()
    {
        for(int jk=0;jk<15;jk++)
            for(int jt=0;jt<15;jt++)
                this.map[jk][jt]=Main.map.getContent(jk,jt);
    }


    public Class getById(int id)
    {
        if(id>=1&&id<=7)
            return Hulu.class;
        else if(id==8)
            return Grandpa.class;
        else if(id>=9&&id<=15)
            return Monster.class;
        else if (id==16)
            return Scorpion.class;
        else if(id==17)
            return Snake.class;
        else
            return null;
    }

    public boolean moveOne(int direction1,int direction2, int direction3, int direction4)
    {
        //direction 1234相当于上下左右
        int tempx=Main.map.getX(id);
        int tempy=Main.map.getY(id);
        if (moveIf(direction1) == true)
        {
            return true;
        }
        if (moveIf(direction2) == true)
        {
            return true;
        }
        if (moveIf(direction3) == true)
        {
            return true;
        }
        if (moveIf(direction4) == true)
        {
            return true;
        }
        return false;
    }

    public boolean moveIf(int direction)
    {
        int tempx=Main.map.getX(id);
        int tempy=Main.map.getY(id);
        if(direction == 1)
        {
            if(tempy == 14)
                return false;
            if (Main.map.getContent(tempx, tempy + 1) == -1) {
                Main.map.setMap(tempx, tempy + 1, id);
                Main.map.setMap(tempx, tempy, -1);
                return true;
            }
            else
            {
                int td = Main.map.getContent(tempx, tempy + 1);
                if(this.camp == getCreature(td).camp)
                    return false;
                else
                {
                    startFight(getCreature(td));
                    return true;
                }
            }
        }
        else if(direction == 2)
        {
            if(tempy == 0)
                return false;
            if (Main.map.getContent(tempx, tempy - 1) == -1) {
                Main.map.setMap(tempx, tempy - 1, id);
                Main.map.setMap(tempx, tempy, -1);
                return true;
            }
            else
            {
                int td = Main.map.getContent(tempx, tempy - 1);
                if(this.camp == getCreature(td).camp)
                    return false;
                else
                {
                    startFight(getCreature(td));
                    return true;
                }
            }
        }
        else if(direction == 3)
        {
            if(tempx == 0)
                return false;
            if (Main.map.getContent(tempx - 1, tempy) == -1) {
                Main.map.setMap(tempx - 1, tempy, id);
                Main.map.setMap(tempx, tempy, -1);
                return true;
            }
            else
            {
                int td = Main.map.getContent(tempx - 1, tempy);
                if(this.camp == getCreature(td).camp)
                    return false;
                else
                {
                    startFight(getCreature(td));
                    return true;
                }
            }
        }
        else
        {
            if(tempx == 14)
                return false;
            if (Main.map.getContent(tempx + 1, tempy) == -1) {
                Main.map.setMap(tempx + 1, tempy, id);
                Main.map.setMap(tempx, tempy, -1);
                return true;
            }
            else
            {
                int td = Main.map.getContent(tempx + 1, tempy);
                if(this.camp == getCreature(td).camp)
                    return false;
                else
                {
                    startFight(getCreature(td));
                    return true;
                }
            }
        }
    }

    public void startFight(Creature oppo)
    {
        if(Math.random()>=0.5)
        {
            alive = false;
            int tempx=Main.map.getX(id);
            int tempy=Main.map.getY(id);
            Main.map.setMap(tempx, tempy, -1);
            Main.dieX[Main.dieNumber]=tempx;
            Main.dieY[Main.dieNumber]=tempy;
            Main.dieNumber++;
        }
        else
            {
                int tempx=Main.map.getX(id);
                int tempy=Main.map.getY(id);
                oppo.alive = false;
                Main.map.setMap(tempx, tempy, -1);
                int tempx2=Main.map.getX(oppo.id);
                int tempy2=Main.map.getY(oppo.id);
                Main.map.setMap(tempx2, tempy2, id);
                Main.dieX[Main.dieNumber]=tempx2;
                Main.dieY[Main.dieNumber]=tempy2;
                Main.dieNumber++;
        }
    }

    public Creature getCreature(int id)
    {
        if(id<=7)
            return Main.hulu[id-1];
        else if(id==8)
            return Main.grandpa;
        else if (id>8&&id<16)
            return Main.monster[id-9];
        else if (id==16)
            return Main.scorpion;
        else
            return Main.snake;
    }

    static public void resetCreature()
    {
        for(int jk=0;jk<7;jk++)
            Main.hulu[jk].alive=true;
        for(int jk=0;jk<7;jk++)
            Main.monster[jk].alive=true;
        Main.grandpa.alive=true;
        Main.grandpa.rush=false;
        Main.scorpion.alive=true;
        Main.scorpion.rush=false;
        Main.snake.alive=true;
        Main.snake.rush=false;

    }


}                           //生物类


