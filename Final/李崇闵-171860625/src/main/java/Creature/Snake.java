package Creature;

import Field.Field;

//蛇精
public class Snake extends Evil {
    public void initPos(int x,int y,Field field){
        this.field=field;
        this.x=x;
        this.y=y;
        this.field.addCreature(this,this.x,this.y);
    }
}
