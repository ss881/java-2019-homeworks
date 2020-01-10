package creature;

import util.ImageType;

public class Goblin extends BadCreature{
    public Goblin(int x, int y, ImageType image, int hp, boolean alive, int battle, String name, int speed, int id) {
        super(x,y,image,hp,alive,battle,name,speed,id);
    }
    @Override
    public Object clone() {
        Goblin temp = null;
        temp = (Goblin) super.clone();	//浅复制
        return temp;
    }
}
