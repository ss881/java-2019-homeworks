package space;

import creature.Creature;

/**
 * author Yuan Manjie
 * date 2019/12/19
 * java course homework4
 */


public class Tile {
    private int x,y;
    private Creature sth;
    Tile(int x,int y){
        this.sth=null;
        this.x=x;
        this.y=y;
    }
    public int getX(){return x;}
    public int getY(){return y;}
    public Creature get(){return this.sth;}
    public void set(Creature x){
        this.sth=x;
    }
    void show(){
        if(this.sth!=null)
        {
            this.sth.show();
        }else
        {
            System.out.print("囗");
        }
    }
    public static void swap(Tile x,Tile y)
    {
        Creature t=x.get();
        x.set(y.get());
        y.set(t);
        if(y.get()!=null)
            y.get().setFloor(y);
        if(x.get()!=null)
        x.get().setFloor(x);
    }
}
