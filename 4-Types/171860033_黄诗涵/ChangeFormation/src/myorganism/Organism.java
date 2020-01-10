package myorganism;

import java.awt.*;

public class Organism {
    protected boolean queuing;
    protected Position mypos;
    protected String id;
    public Organism(Position p)
    {
        mypos=p;
    }
    public Organism() {
        queuing=false;
    }
    public void setpos(Position p)
    {
        //if(this.mypos!=null)
        String[] classname=this.getClass().getName().split("\\.");
        if(this.mypos!=null) {
            if(p!=null) {
                System.out.printf("%s(ID:%s) : %s->%s\n", classname[1], this.id, mypos.coordinateStr(), p.coordinateStr());
                this.queuing=true;
            }
            else
                System.out.printf("%s(ID:%s) : 离场\n",classname[1],this.id);
        }
        else {
            if(p!=null)
            {
                System.out.printf("%s(ID:%s) : 上场 %s\n", classname[1], this.id, p.coordinateStr());
                queuing=true;
            }
        }
        mypos=p;

    }
    public void setId(String id)
    {
        this.id=id;
    }
    public String getId()
    {
        return this.id;
    }

    public void setQueuing(boolean queuing) {
        this.queuing = queuing;
    }
    /*   public boolean move(int x,int y,Organism[][] map)
    {
        if(map[x][y]!=null)
        {
            return false;
        }
        map[x][y]=this;
        if(pos_x>=0&&pos_y>=0&&queuing==false)
            map[pos_x][pos_y]=null;
        switch (map[x][y].type) {
            case HULUWA:System.out.printf("Huluwa: ");break;
            case GRANDFATHER:System.out.printf("Grandfather: ");break;
            case MONSTER:System.out.printf("Monster: ");break;
            case SNAKE:System.out.printf("Snake: ");break;
            case SCORPION:System.out.printf("Scorpion: ");break;
        }
        if(pos_y<0||pos_x<0)
            System.out.printf("上场-->(%d,%d)\n",x,y);
        else
            System.out.printf("移动 (%d,%d)-->(%d,%d)\n",pos_x,pos_y,x,y);
        pos_x=x;
        pos_y=y;
        return true;
    }
    public void swap(int x,int y,Organism[][] map)
    {
        Organism temp=this;
        map[pos_x][pos_y]=null;
        int tx=pos_x,ty=pos_y;
        map[x][y].move(tx,ty,map);
        System.out.println(temp.type);
        temp.pos_y=temp.pos_x=-1;
        temp.move(x,y,map);

    }*/
}