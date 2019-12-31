package map;

//表示位置坐标
public class Position{
    public int x;
    public int y;
    public Position(int x,int y){
        this.x=x;
        this.y=y;
    }
    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;
        if(o==null)
            return false;
        if(this.getClass()!=o.getClass())
            return false;
        Position p=(Position)o;
        return x==p.x&&y==p.y;
    }
    public void set(int x,int y){
        this.x=x;
        this.y=y;
    }
}