package Creature;

import Field.Field;

//蝎子精类
public class Scorpion extends Evil {
    public void initPos(int x,int y,Field field){
        this.field=field;
        this.x=x;
        this.y=y;
        this.field.addCreature(this,this.x,this.y);
    }
}
