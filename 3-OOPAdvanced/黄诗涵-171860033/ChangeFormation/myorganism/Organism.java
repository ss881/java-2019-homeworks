package myorganism;
public class Organism {
    protected Species type;
    protected int pos_y;
    protected int pos_x;
    protected boolean queuing;
    public Organism() {
    }
    public Organism(int x,int y) {
        pos_y=y;
        pos_x=x;
        queuing=false;
    }
    public boolean move(int x,int y,Organism[][] map)
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

    }
}