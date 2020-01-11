package creature;

import util.ImageType;

public class Scorpion extends BadCreature{

    public Scorpion(int x, int y, ImageType image, int hp, boolean alive, int battle, String name, int speed, int id) {
        super(x,y,image,hp,alive,battle,name,speed,id);
    }
    @Override
    public Object clone() {
        Scorpion temp = null;
        temp = (Scorpion) super.clone();	//浅复制
        return temp;
    }

}