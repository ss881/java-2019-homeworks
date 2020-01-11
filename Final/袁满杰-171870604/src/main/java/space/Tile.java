package space;

import creature.Creature;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

/**
 * author Yuan Manjie
 * date 2019/12/19
 * java course homework4
 */


public class Tile {
    private int x,y;
    private Creature sth;
    private static final float length=1,width= (float) 0.7,height=1;
    static Image image;
    static PhongMaterial floorMaterial = new PhongMaterial();
    Box box;
    static {
        image=new Image("image/floor.jpg",100,100,true,true);
        floorMaterial.setDiffuseMap(image);
    }
    Tile(int x, int y){
        this.sth=null;
        this.x=x;
        this.y=y;
        box=new Box(length,width,height);
        box.setTranslateX(x);
        box.setTranslateZ(-y);
        box.setMaterial(floorMaterial);
    }
    public Box getBox(){return box;}
    public int getX(){return x;}
    public int getY(){return y;}
    public Creature get(){return this.sth;}
    public void set(Creature x){
        this.sth=x;
        if(sth!=null)
        {
            x.getBox().setTranslateX(this.x);
            x.getBox().setTranslateZ(-this.y-0.5);
        }
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
