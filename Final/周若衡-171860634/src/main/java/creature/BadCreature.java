package creature;

import util.ImageType;

public class BadCreature extends Creature{
    public BadCreature(int x, int y, ImageType image, int hp, boolean alive, int battle, String name, int speed, int id){
        super(x,y,image,hp,alive,battle,name,speed,id);
    }
    @Override
    public Object clone() {
        BadCreature temp = null;
        temp = (BadCreature) super.clone();	//浅复制
        return temp;
    }

}

