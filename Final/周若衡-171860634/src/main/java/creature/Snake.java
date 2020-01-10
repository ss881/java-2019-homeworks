package creature;

import util.ImageType;

public class Snake extends BadCreature {

    public Snake(int x, int y , ImageType image, int hp, boolean alive, int battle, String name, int speed, int id) {
        super(x,y,image,hp,alive,battle,name,speed,id);
    }
    @Override
    public Object clone() {
        Snake temp = null;
        temp = (Snake) super.clone();	
        return temp;
    }
}
