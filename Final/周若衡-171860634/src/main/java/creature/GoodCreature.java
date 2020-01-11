package creature;

import util.ImageType;

public class GoodCreature extends Creature{
    public GoodCreature(int x, int y, ImageType image, int hp, boolean alive, int battle, String name, int speed, int id){
        super(x,y,image,hp,alive,battle,name,speed,id);
    }
    @Override
    public Object clone() {
        GoodCreature temp = null;
        temp = (GoodCreature) super.clone();	//浅复制
        return temp;
    }
}
