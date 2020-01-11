package creature;

import util.ImageType;

public class CalabashBrother extends GoodCreature{
    private int rank;
    public CalabashBrother(int x, int y, ImageType image, int rank, int hp, boolean alive, int battle, String name, int speed, int id) {
        super(x,y,image,hp,alive,battle,name,speed,id);
        this.rank=rank;
    }
    public int getRank(){
        return rank;
    }

    @Override
    public Object clone() {
        CalabashBrother temp = null;
        temp = (CalabashBrother) super.clone();	//浅复制
        return temp;
    }
}

